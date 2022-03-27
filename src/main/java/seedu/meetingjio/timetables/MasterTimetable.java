package seedu.meetingjio.timetables;

import seedu.meetingjio.commands.AddMeetingCommand;
import seedu.meetingjio.commands.DeleteCommand;
import seedu.meetingjio.events.Event;
import seedu.meetingjio.events.Meeting;
import seedu.meetingjio.exceptions.DuplicateEventException;
import seedu.meetingjio.exceptions.OverlappingEventException;
import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.commands.ListCommand;

import static seedu.meetingjio.common.ErrorMessages.ERROR_NO_FREE_TIMESLOT;

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

    /**
     * Initialise a 7 x 960 array, with the 7 rows indicating each day of the week and the 960 columns indicating the
     * minutes starting from 0800 to 2359 (Constraint here is that free timeslots from 0000 to 0759 are not included).
     * For the last minute (2359) of each day, it will be initialised to 1 (BUSY).
     * For each timetable stored, the method populateBusySlots will be called.
     *
     * @param duration Integer representing the minimum number of hours to filter the free time slots
     * @return freeSlotsString String containing the common timeslots when everyone is free
     */
    public String listFree(int duration) {
        int[][] busySlots = new int[NUM_DAYS][NUM_MINS];
        for (int i = 0; i < NUM_DAYS; i++) {
            busySlots[i][NUM_MINS - 1] = BUSY;
        }
        for (Timetable timetable : timetables) {
            timetable.populateBusySlots(busySlots);
        }
        String freeSlotsString = showOutput(busySlots, duration);
        return freeSlotsString;
    }

    /**
     * Given the finalised busySlots array and the minimum duration, find the timeslots where everyone is free and the
     * duration meets the minimum duration.
     * Each row in busySlots contains 960 0s and 1s, where free timeslots are represented by consecutive 0s.
     * 2 strings are initialised: freeSlotsString to store free slots and newEntry to store each new entry.
     * An integer count and a boolean isStart is also initialised as 0 and true respectively.
     * For each day, iterating from left to right, when a 0 (FREE) is encountered, the respective day and time is added
     * to newEntry, and count will be incremented.
     * For each subsequent 0 (FREE), count will keep incrementing.
     * When a 1 (BUSY) is encountered, the count is checked to determine if the free timeslot exceeds the minimum
     * duration. If it does, the time is added to newEntry, and newEntry is added to freeSlotsString.
     * Subsequently, newEntry and count will reset until the next 0 is encountered, and the process repeats itself.
     *
     * @param busySlots 7 x 960 array representing the timeframe from 0800 to 2359 for each day. 1 indicates BUSY and 0
     *                  indicates FREE
     * @param duration Minimum time required for the free time slot
     * @return truncateString(freeSlotsString) freeSlotsString but truncated without the last newline character
     */
    private String showOutput(int[][] busySlots, int duration) {
        String freeSlotsString = "";
        String newEntry = "";
        boolean isStart = true;
        int count = 0;
        for (int i = 0; i < NUM_DAYS; i++) {
            for (int j = 0; j < NUM_MINS; j++) {
                if (busySlots[i][j] == FREE) {
                    if (isStart) {
                        newEntry += convertDayIntToString(i + 1);
                        newEntry += " ";
                        newEntry += convertMinsToTime(j);
                        isStart = false;
                    }
                    count++;
                }
                if (busySlots[i][j] == BUSY && !isStart) {
                    if (count >= duration * MINS_IN_1_HOUR) {
                        newEntry += " ";
                        newEntry += convertMinsToTime(j);
                        newEntry += "\n";
                        freeSlotsString += newEntry;
                    }
                    count = 0;
                    newEntry = "";
                    isStart = true;
                }
            }
        }
        return truncateString(freeSlotsString);
    }

    /**
     * Remove the last newline character from the string. If the string is empty, return an error message informing the
     * user that there is no free time slot that matches the given requirements.
     *
     * @param freeSlotsString String containing the common timeslots where everyone is free
     * @return truncatedFreeSlotsString freeSlotsString but truncated without the last newline character
     */
    private String truncateString(String freeSlotsString) {
        if (freeSlotsString.length() == 0) {
            return ERROR_NO_FREE_TIMESLOT;
        }
        String truncatedFreeSlotsString = freeSlotsString.substring(0, freeSlotsString.length() - 1);
        return truncatedFreeSlotsString;
    }

    /**
     * Converts an integer (1-7) into its corresponding day, with 1 as Monday and 7 as Sunday.
     *
     * @param numericDay Integer to be converted into a day
     * @return day String indicating the actual day
     */
    private String convertDayIntToString(int numericDay) {
        String day;
        switch (numericDay) {
        case 1:
            day = "Monday";
            break;
        case 2:
            day = "Tuesday";
            break;
        case 3:
            day = "Wednesday";
            break;
        case 4:
            day = "Thursday";
            break;
        case 5:
            day = "Friday";
            break;
        case 6:
            day = "Saturday";
            break;
        case 7:
            day = "Sunday";
            break;
        default:
            day = "";
            break;
        }
        return day;
    }

    /**
     * Convert the number of minutes starting from 0800 to 24-hour format.
     *
     * @param mins The number of minutes starting from 0800
     * @return timeIn24Hour The corresponding time in 24-hour format
     */
    private String convertMinsToTime(int mins) {
        mins += OFFSET;
        int hours = mins / MINS_IN_1_HOUR;
        int minutes = mins % MINS_IN_1_HOUR;
        int timeInt = hours * HOUR_IN_24_HOUR + minutes;
        String timeIn24Hour = String.format("%04d", timeInt);
        return timeIn24Hour;
    }

}
