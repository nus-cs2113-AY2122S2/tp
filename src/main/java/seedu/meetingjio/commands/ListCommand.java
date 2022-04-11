//@@author angyongming

package seedu.meetingjio.commands;

import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import seedu.meetingjio.exceptions.EmptyMasterTimetableException;
import seedu.meetingjio.exceptions.TimetableNotFoundException;

import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_USER;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EMPTY_LIST;
import static seedu.meetingjio.common.ErrorMessages.ERROR_UNSPECIFIED_LIST;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EMPTY_MASTER_TIMETABLE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_NO_LESSONS;
import static seedu.meetingjio.common.ErrorMessages.ERROR_NO_MEETINGS;

public class ListCommand extends Command {

    public static final String ALL_COMMAND_WORD = "list";
    public static final String LESSON_COMMAND_WORD = "list_lesson";
    public static final String MEETING_COMMAND_WORD = "list_meeting";
    public static final int ALL = 0;
    public static final int LESSONS_ONLY = 1;
    public static final int MEETINGS_ONLY = 2;

    private final String name;
    private final int constraint;

    /**
     * The constructor for ListCommand takes in 2 parameters: name and constraint.
     * There are only 3 values for constraint: 0, 1, 2.
     * If constraint is ALL (0), it indicates that the user wants to list all events (lessons and meetings).
     * If constraint is LESSONS_ONLY (1), it indicates that the user only wants to list lessons.
     * If constraint is MEETINGS_ONLY (2), it indicates that the user only wants to list meetings.
     *
     * @param name Name of user whose timetable is to be printed, or 'all'
     * @param constraint An integer that is either 0, 1 or 2
     */
    public ListCommand(String name, int constraint) {
        this.name = name;
        this.constraint = constraint;
    }

    /**
     * Given the name of a user, list out all entries in the user's timetable.
     * If the supplied parameter is 'all', list out all entries in everyone's timetable.
     * If no parameter is supplied, programme should inform the user and continue running normally.
     *
     * @param masterTimetable MasterTimetable
     * @return String containing the user's events or all users' events
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        String user = this.name;

        try {
            if (user.length() == 0) {
                return ERROR_UNSPECIFIED_LIST;
            } else if (user.equalsIgnoreCase("all")) {
                return listAll(masterTimetable, constraint);
            } else {
                return listUser(user, masterTimetable, constraint);
            }
        } catch (EmptyMasterTimetableException e) {
            return ERROR_EMPTY_MASTER_TIMETABLE;
        }

    }

    /**
     * This method gets the masterTimetable to call the collateAll method to get the combined timetables of everyone.
     * If the masterTimetable is empty (when 'list all' is the user's first input), the programme should inform the user
     * gracefully.
     * The returned string needs to be truncated at the end otherwise there will be an extra newline character.
     *
     * @param masterTimetable The Master Timetable containing everyone's timetables
     * @return truncatedString A string containing the labelled timetables of everyone, without the newline
     *     character at the end. If the string has no contents, an error message is shown to inform the user
     *     accordingly.
     */
    public static String listAll(MasterTimetable masterTimetable, int constraint) throws EmptyMasterTimetableException {
        String str = masterTimetable.collateAll(masterTimetable, constraint);
        if (str.length() == 0) {
            throw new EmptyMasterTimetableException();
        }
        assert str.length() - 1 >= 0 : ERROR_EMPTY_MASTER_TIMETABLE;
        String truncatedString = str.substring(0, str.length() - 1);
        return truncatedString;
    }

    /**
     * This method is called if the user only wants to list a specific user's timetable.
     * This method is also called by the Master Timetable method collateAll, which calls listUser for all users.
     * While executing this method, the timetable is sorted based on the event's day and timing to allow the user to
     * quickly see his/her events easily.
     * The listTimetable method is then called by the Timetable class to obtain the string containing the user's
     * timetable.
     * If the string is empty, an appropriate error message will be returned, informing the user that the timetable is
     * empty.
     *
     * @param user The target user whose timetable is to be shown
     * @param masterTimetable The Master Timetable containing everyone's timetables
     * @param constraint Integer representing the constraint (all events, lessons only or meetings only)
     * @return truncatedString The string containing the user's timetable. If the user does not exist, or the user's
     *     timetable is empty, an appropriate error message will be shown to inform the user accordingly.
     */
    public static String listUser(String user, MasterTimetable masterTimetable, int constraint) {
        Timetable timetable;
        try {
            timetable = masterTimetable.getByName(user);
        } catch (TimetableNotFoundException tnfe) {
            return ERROR_INVALID_USER;
        }
        if (timetable.size() == 0) {
            return ERROR_EMPTY_LIST;
        }
        assert timetable.size() > 0 : ERROR_EMPTY_LIST;
        timetable.sort();
        String str = timetable.listTimetable(constraint);
        if (str.length() == 0) {
            switch (constraint) {
            case ALL:
                return ERROR_EMPTY_LIST;
            case LESSONS_ONLY:
                return ERROR_NO_LESSONS;
            case MEETINGS_ONLY:
                return ERROR_NO_MEETINGS;
            default:
                return ERROR_EMPTY_LIST;
            }
        }
        String truncatedString = str.substring(0, str.length() - 1);
        return truncatedString;
    }


}
