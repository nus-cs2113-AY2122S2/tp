package seedu.meetingjio.commands;

import seedu.meetingjio.exceptions.MissingValueException;
import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

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

    private final String name;
    private final int constraint;

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
     * @return String containing the user's lessons or all users' lessons
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        String user = this.name;
        
        if (user.length() == 0) {
            return ERROR_UNSPECIFIED_LIST;
        } else if (user.equalsIgnoreCase("all")) {
            return listAll(masterTimetable, constraint);
        } else {
            return listUser(user, masterTimetable, constraint);
        }

    }

    /**
     * This method gets the masterTimetable to call the collateAll method to get the combined timetables of everyone.
     * If the masterTimetable is empty (eg when 'list all' is the user's first input), the programme should inform
     * the user gracefully.
     * The returned string needs to be truncated at the end otherwise there will be an extra newline character.
     *
     * @param masterTimetable The Master Timetable containing everyone's timetables
     * @return truncatedString A string containing the labelled timetables of everyone, without the newline
     *     character at the end. If the string has no contents, an error message is shown to inform the user
     *     accordingly.
     */
    public static String listAll(MasterTimetable masterTimetable, int constraint) {
        String str = masterTimetable.collateAll(masterTimetable, constraint);
        if (str.length() == 0) {
            return ERROR_EMPTY_MASTER_TIMETABLE;
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
     *
     * @param user The target user whose timetable is to be shown
     * @param masterTimetable The Master Timetable containing everyone's timetables
     * @return str The string containing the user's timetable. If the user does not exist, or the user's timetable is
     *     empty, an appropriate error message will be shown to inform the user accordingly.
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
            case 0:
                return ERROR_EMPTY_LIST;
            case 1:
                return ERROR_NO_LESSONS;
            case 2:
                return ERROR_NO_MEETINGS;
            }
        }
        String truncatedString = str.substring(0, str.length() - 1);
        return truncatedString;
    }


}
