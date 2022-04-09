package seedu.meetingjio.timetables;

import seedu.meetingjio.events.Event;
import seedu.meetingjio.events.Lesson;
import seedu.meetingjio.events.Meeting;
import seedu.meetingjio.commands.ListCommand;

import java.util.ArrayList;
import java.util.logging.Level;

import seedu.meetingjio.exceptions.DuplicateEventException;
import seedu.meetingjio.exceptions.OverlappingEventException;
import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.exceptions.DuplicateTimetableException;

import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_NON_EMPTY_LIST;
import static seedu.meetingjio.common.ErrorMessages.ERROR_OVERLAPPING_EVENT;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EXCEPTION_NOT_HANDLED;
import static seedu.meetingjio.common.Messages.*;
import static seedu.meetingjio.parser.Parser.logger;

public class MasterTimetable {

    private final ArrayList<Timetable> timetables;
    private final ArrayList<Meeting> meetingList;

    public static final int NUM_DAYS = 7;
    public static final int NUM_MINS = 1440;
    public static final int BUSY = 1;

    public MasterTimetable() {
        this.timetables = new ArrayList<>();
        this.meetingList = new ArrayList<>();
    }

    /**
     * Get timetable by the specified name.
     *
     * @param name Name
     * @return timetable Timetable that matches the name
     * @throws TimetableNotFoundException if there's no timetable that matches the name
     */
    public Timetable getByName(String name) throws TimetableNotFoundException {
        for (Timetable timetable : timetables) {
            if (name.equalsIgnoreCase(timetable.getName())) {
                return timetable;
            }
        }
        throw new TimetableNotFoundException();
    }

    /**
     * Remove timetable by the specified name.
     * No exception will be thrown if there's no timetable that matches the name.
     *
     * @param name Name
     */
    public void removeByName(String name) {
        try {
            Timetable timetable = getByName(name);
            timetables.remove(timetable);
        } catch (TimetableNotFoundException tnfe) {
            return;
        }
    }

    /**
     * Get timetable at the specified index.
     *
     * @param index Index
     * @return timetable Timetable at the index
     */
    public Timetable getByIndex(int index) {
        return timetables.get(index);
    }

    /**
     * Remove timetable at the specified index.
     *
     * @param index Index
     */
    public void removeByIndex(int index) {
        timetables.remove(index);
    }

    /**
     * Add timetable to the MasterTimetable.
     *
     * @param timetable Timetable to be added
     */
    public void addTimetable(Timetable timetable) throws DuplicateTimetableException {
        if (isDuplicateTimetable(timetable)) {
            throw new DuplicateTimetableException();
        }
        timetables.add(timetable);
    }

    /**
     * Add lesson to the timetable that belongs to the user.
     *
     * @param lesson Lesson to be added
     * @param name Name of the user
     * @throws TimetableNotFoundException If user's timetable doesn't exist
     * @throws DuplicateEventException If identical event has already been added
     * @throws OverlappingEventException If another existing event has a timetable clash
     */
    public void addLesson(Lesson lesson, String name)
            throws TimetableNotFoundException, DuplicateEventException, OverlappingEventException {
        Timetable timetable = getByName(name);
        timetable.add(lesson);
    }

    /**
     * This method iterates through every timetable in the Master Timetable.
     * For each timetable, the name of the person that the timetable belongs to, and the contents of the timetable
     * itself, will be appended to the string.
     * The contents of each timetable is obtained by calling the listUser method from ListCommand
     * Once all timetables have been iterated through, the string is returned
     *
     * @param masterTimetable The Master Timetable containing everyone's timetables
     * @param constraint Integer describing the constraint (all events, lessons only or meetings only)
     * @return str A string containing the labelled timetables of everyone
     */
    public String collateAll(MasterTimetable masterTimetable, int constraint) {
        String str = "";
        for (Timetable timetable : timetables) {
            String user = timetable.getName();
            str += user;
            str += '\n';
            str += ListCommand.listUser(user, masterTimetable, constraint);
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

    /**
     * Does check if the meeting clashes any other events/lessons with any user across all timetables.
     *
     * @param meeting The meeting event that is to be checked
     *
     * @return boolean true if there is another event at the same time as the meeting
     */
    public boolean isMeetingThatClashes(Meeting meeting) {
        for (Timetable timetable : timetables) {
            if (isOverlappingMeeting(timetable, meeting)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Does check if the meeting clashes with any event in the specified user's timetable.
     *
     * @param timetable The timetable to check clash
     * @param meeting The meeting event that is to be checked
     *
     * @return boolean True false if there is another event at the same time as the meeting
     */
    private boolean isOverlappingMeeting(Timetable timetable, Meeting meeting) {
        for (Event event : timetable.getList()) {
            if (meeting.overlaps(event)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Does check if the meeting already exists,as added before.
     *
     * @param meeting The meeting event that is to be checked
     *
     * @return boolean True false if there meeting already exists
     */
    public boolean isPreExistingMeeting(Meeting meeting) {
        for (Timetable timetable : timetables) {
            for (int i = 0; i < timetable.size(); i++) {
                if (timetable.get(i).equals(meeting)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Add meeting to everyone's timetable so that everyone has a record on it.
     *
     * @param meeting The meeting event that is to be added
     *
     * @return String Confirmation of meeting being added to everyone's timetable
     */
    public String addMeetingToEveryoneTimetable(Meeting meeting) {
        for (Timetable timetable : timetables) {
            try {
                timetable.add(meeting);
                meetingList.add(meeting);
            } catch (DuplicateEventException dee) {
                return ERROR_DUPLICATE_EVENT;
            } catch (OverlappingEventException oee) {
                return ERROR_OVERLAPPING_EVENT;
            } catch (Exception e) {
                logger.log(Level.INFO, "Unhandled Exception : " +  e.getMessage());
                return ERROR_EXCEPTION_NOT_HANDLED;
            }
        }
        return addMeetingConfirmation(meeting);
    }

    /**
     * Inform user that meeting has been added.
     *
     * @param meeting The meeting event that has just been added
     *
     * @return String confirmation of meeting added to everyone's timetable
     */
    private String addMeetingConfirmation(Meeting meeting) {
        return MEETING_ADDED_TO_ALL_CONFIRMATION + meeting;
    }

    /**
     * Inform user that meeting has been deleted from everyone's timetable.
     *
     * @param meeting The meeting event that is to be deleted
     *
     * @return String confirmation of meeting added to everyone's timetable
     */
    public String deleteMeetingFromEveryoneTimetable(Meeting meeting) {
        for (Timetable timetable : timetables) {
            deleteMeetingFromTimetable(timetable,meeting);
        }
        deleteMeetingFromMeetingList(meeting);
        return deleteMeetingFromAllTimetableConfirmation(meeting);
    }

    /**
     * Inform user that delete has happened.
     *
     * @param meeting Event to inform user that said event has been deleted
     *
     *
     */
    private String deleteMeetingFromAllTimetableConfirmation(Meeting meeting) {
        return MEETING_CLEARED_FROM_ALL_CONFIRMATION + meeting;
    }

    /**
     * Deletes meeting from specified user's timetable.
     *
     * @param timetable The timetable to delete from
     * @param meeting The meeting event that is to be deleted
     *
     */
    public void deleteMeetingFromTimetable(Timetable timetable,Meeting meeting) {
        for (int i = 0; i < timetable.size(); i++) {
            if (meeting.equals(timetable.get(i))) {
                timetable.remove(i);
            }
        }
    }

    /**
     * Deletes meeting from meetingList.
     *
     * @param meeting The meeting event that is to be deleted
     *
     */
    public void deleteMeetingFromMeetingList(Meeting meeting) {
        for (int i = 0; i < meetingList.size(); i++) {
            if (meeting.equals(meetingList.get(i))) {
                meetingList.remove(i);
            }
        }
    }

    /**
     * Deletes all meetings that exist.
     *
     * @return String Message that all meetings were deleted as new user was added
     *
     */
    public String deleteAllMeetings() {
        for (Timetable timetable : timetables) {
            deleteMeetingsFromTimetable(timetable);
        }
        for (int i = 0; i < meetingList.size(); i++) {
            meetingList.remove(0);
        }
        assert meetingList.size() == 0 : ERROR_NON_EMPTY_LIST;
        return NEW_USER_ADDED_SO_ALL_MEETINGS_DELETED;
    }

    /**
     * Deletes all meetings from specified timetable.
     *
     * @param timetable Specific timetable to perform delete on
     *
     */
    public void deleteMeetingsFromTimetable(Timetable timetable) {
        for (int i = 0; i < timetable.size(); i++) {
            Event event = timetable.get(i);
            if (event instanceof Meeting) {
                timetable.remove(i);
            }
        }
    }

    /**
     * Initialise a 7 x 1440 array, with the 7 rows indicating each day of the week and the 1440 columns indicating the
     * minutes starting from 0000 to 2359.
     * For the last minute (2359) of each day, it will be initialised to 1 (BUSY).
     * For each timetable stored, the method populateBusySlots will be called.
     *
     * @return busySlots A 7 x 1440 array containing 0s and 1s whereby 0 indicates a time when all users are free
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

    /**
     * Checks through all existing timetable to the timetable to be added
     * to ensure that a user will not have more than one entry.
     *
     * @param newTimetable Timetable to be added
     * @return true if there is identical timetable, otherwise false
     */
    private boolean isDuplicateTimetable(Timetable newTimetable) {
        for (int i = 0; i < timetables.size(); i++) {
            Timetable timetable = timetables.get(i);
            if (timetable.equals(newTimetable)) {
                return true;
            }
        }
        return false;
    }

}
