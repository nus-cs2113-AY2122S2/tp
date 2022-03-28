package seedu.meetingjio.timetables;

import seedu.meetingjio.commands.AddMeetingCommand;
import seedu.meetingjio.commands.DeleteCommand;
import seedu.meetingjio.events.Event;
import seedu.meetingjio.events.Meeting;
import seedu.meetingjio.exceptions.DuplicateEventException;
import seedu.meetingjio.exceptions.OverlappingEventException;
import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.commands.ListCommand;
import seedu.meetingjio.commands.FreeCommand;

import java.util.ArrayList;

import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_OVERLAPPING_EVENT;

public class MasterTimetable {

    private final ArrayList<Timetable> timetables;
    private final ArrayList<Meeting> meetingList;

    public static final int NUM_DAYS = 7;
    public static final int NUM_MINS = 960;
    public static final int BUSY = 1;

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
     * @return busySlots A 7 x 960 array containing 0s and 1s whereby 0 indicates a time when all users are free
     */
    public int[][] listBusy() {
        int[][] busySlots = new int[NUM_DAYS][NUM_MINS];
        for (int i = 0; i < NUM_DAYS; i++) {
            busySlots[i][NUM_MINS - 1] = BUSY;
        }
        for (Timetable timetable : timetables) {
            timetable.populateBusySlots(busySlots);
        }
        return busySlots;
    }

}
