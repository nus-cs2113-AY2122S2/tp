package seedu.meetingjio.timetables;

import seedu.meetingjio.commands.AddMeetingCommand;
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
}
