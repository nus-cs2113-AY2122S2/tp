package seedu.meetingjio.timetables;

import seedu.meetingjio.commands.AddMeetingCommand;
import seedu.meetingjio.commands.DeleteCommand;
import seedu.meetingjio.events.Event;
import seedu.meetingjio.events.Meeting;
import seedu.meetingjio.exceptions.DuplicateEventException;
import seedu.meetingjio.exceptions.OverlappingEventException;
import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.commands.ListCommand;

import java.util.ArrayList;

import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_OVERLAPPING_EVENT;

public class MasterTimetable {

    private final ArrayList<Timetable> timetables;
    private final ArrayList<Meeting> meetingList;

    public static final int NUM_DAYS = 7;
    public static final int NUM_MINS = 960;
    public static final int OFFSET = 480;
    public static final int MINS_IN_1_HOUR = 60;
    public static final int HOUR_IN_24_HOUR = 100;
    public static final int BUSY = 1;
    public static final int FREE = 0;

    public MasterTimetable() {
        this.timetables = new ArrayList<>();
        this.meetingList = new ArrayList<>();
    }

    public Timetable getByName(String name) throws TimetableNotFoundException {
        for (Timetable timetable : timetables) {
            if (name.equalsIgnoreCase(timetable.getName())) {
                return timetable;
            }
        }
        throw new TimetableNotFoundException();
    }

    public void removeByName(String name) throws TimetableNotFoundException {
        for (Timetable timetable : timetables) {
            if (name.equalsIgnoreCase(timetable.getName())) {
                timetables.remove(timetable);
            }
        }
        throw new TimetableNotFoundException();
    }

    public Timetable getByIndex(int index) {
        return timetables.get(index);
    }

    public void removeByIndex(int index) {
        timetables.remove(index);
    }

    public void add(Timetable timetable) {
        timetables.add(timetable);
    }

    /**
     * This method iterates through every timetable in the Master Timetable.
     * For each timetable, the name of the person that the timetable belongs to, and the contents of the timetable
     * itself, will be appended to the string.
     * The contents of each timetable is obtained by calling the listUser method from ListCommand
     * Once all timetables have been iterated through, the string is returned
     *
     * @param masterTimetable The Master Timetable containing everyone's timetables
     * @return str A string containing the labelled timetables of everyone
     */
    public String collateAll(MasterTimetable masterTimetable) {
        String str = "";
        for (Timetable timetable : timetables) {
            String user = timetable.getName();
            str += user;
            str += '\n';
            str += ListCommand.listUser(user, masterTimetable);
            str += '\n';
        }
        return str;
    }

    public int getSize() {
        return timetables.size();
    }

    public ArrayList<Meeting> getMeetingList() {
        return meetingList;
    }

    public boolean checkIfClash(Meeting meeting) {
        for (Timetable timetable : timetables) {
            if (checkMeetingOverlap(timetable, meeting)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkMeetingOverlap(Timetable timetable, Meeting meeting) {
        for (Event event : timetable.getList()) {
            if (meeting.overlaps(event)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfMeetingExistsAlready(Meeting meeting) {
        for (Event event : meetingList) {
            if (meeting.equals(event)) {
                return true;
            }
        }
        return false;
    }

    public String addMeetingToEveryoneTimetable(Meeting meeting) {
        for (Timetable timetable : timetables) {
            try {
                timetable.add(meeting);
            } catch (DuplicateEventException dee) {
                return ERROR_DUPLICATE_EVENT;
            } catch (OverlappingEventException oee) {
                return ERROR_OVERLAPPING_EVENT;
            } catch (Exception e) {
                return "ERROR DETECTED";
            }
        }
        return AddMeetingCommand.addMeetingConfirmation(meeting);
    }

    public String deleteMeetingFromEveryoneTimetable(Meeting meeting) {
        for (Timetable timetable : timetables) {
            deleteMeetingFromTimetable(timetable,meeting);
        }
        deleteMeetingFromMeetingList(meeting);
        return DeleteCommand.deleteFromAllTimetableConfirmation(meeting);
    }

    public void deleteMeetingFromTimetable(Timetable timetable,Meeting meeting) {
        for (int i = 0; i < timetable.size(); i++) {
            if (meeting.equals(timetable.get(i))) {
                timetable.remove(i);
            }
        }
    }

    public void deleteMeetingFromMeetingList(Meeting meeting) {
        for (int i = 0; i < meetingList.size(); i++) {
            if (meeting.equals(meetingList.get(i))) {
                meetingList.remove(i);
            }
        }
    }

    public String listFree(int duration) {
        int[][] busySlots = new int[NUM_DAYS][NUM_MINS];
        for (int i = 0; i < NUM_DAYS; i++) {
            busySlots[i][NUM_MINS - 1] = BUSY;
        }
        for (Timetable timetable : timetables) {
            timetable.populateBusySlots(busySlots);
        }
        return showOutput(busySlots);
    }

    private String showOutput(int[][] busySlots) {
        String freeSlotsString = "";
        boolean isStart = true;
        for (int i = 0; i < NUM_DAYS; i++) {
            for (int j = 0; j < NUM_MINS; j++) {
                if (busySlots[i][j] == FREE && isStart) {
                    freeSlotsString += convertDayIntToString(i + 1);
                    freeSlotsString += " ";
                    freeSlotsString += convertMinsToTime(j);
                    isStart = false;
                }
                if (busySlots[i][j] == BUSY && !isStart) {
                    freeSlotsString += " ";
                    freeSlotsString += convertMinsToTime(j);
                    freeSlotsString += "\n";
                    isStart = true;
                }
            }
        }
        String truncatedFreeSlotsString = freeSlotsString.substring(0, freeSlotsString.length() - 1);
        return truncatedFreeSlotsString;
    }

    private String convertDayIntToString(int numericDay) {
        switch (numericDay) {
        case 1:
            return "Monday";
        case 2:
            return "Tuesday";
        case 3:
            return "Wednesday";
        case 4:
            return "Thursday";
        case 5:
            return "Friday";
        case 6:
            return "Saturday";
        case 7:
            return "Sunday";
        default:
            return "";
        }
    }

    private String convertMinsToTime(int mins) {
        mins += OFFSET;
        int hours = mins / MINS_IN_1_HOUR;
        int minutes = mins % MINS_IN_1_HOUR;
        int timeInt = hours * HOUR_IN_24_HOUR + minutes;
        String timeIn24Hour = String.format("%04d", timeInt);
        return timeIn24Hour;
    }

}
