//@@author ibrahimisramos

package seedu.meetingjio.commands;

import seedu.meetingjio.exceptions.MissingValueException;
import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import java.util.logging.Level;

import static seedu.meetingjio.common.Messages.CLEAR_ALL_CONFIRMATION;
import static seedu.meetingjio.common.Messages.CLEAR_TIMETABLE_CONFIRMATION;
import static seedu.meetingjio.parser.Parser.logger;

import static seedu.meetingjio.common.ErrorMessages.ERROR_UNSPECIFIED_LIST_CLEAR;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EXCEPTION_NOT_HANDLED;
import static seedu.meetingjio.common.ErrorMessages.ERROR_NON_EMPTY_LIST;
import static seedu.meetingjio.common.ErrorMessages.ERROR_TIMETABLE_NOT_FOUND_TO_DELETE;

public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    private final String name;

    public ClearCommand(String name) {
        this.name = name;
    }

    /**
     * Execute clear command using the timetable provided.
     *
     * @param masterTimetable masterTimetable is the timetable list initialised
     *
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        String user = this.name;
        try {
            if (user.length() == 0) {
                throw new MissingValueException();
            } else if (user.equalsIgnoreCase("all")) {
                return clearAll(masterTimetable);
            } else {
                return clearTimetableUser(user, masterTimetable);
            }
        } catch (MissingValueException mve) {
            return ERROR_UNSPECIFIED_LIST_CLEAR;
        }
    }

    /**
     * Clear timetable of user specified from the masterTimetable list by
     * first removing all the events in that timetable.
     *
     * @param user user to timetable to clear
     * @param masterTimetable masterTimetable is the timetable list initialised
     *
     */
    private String clearTimetableUser(String user, MasterTimetable masterTimetable) {
        Timetable timetable;
        try {
            timetable  = masterTimetable.getByName(user);
            clearTimetable(timetable);
            masterTimetable.removeByName(user);
        } catch (TimetableNotFoundException tnfe) {
            return ERROR_TIMETABLE_NOT_FOUND_TO_DELETE;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return ERROR_EXCEPTION_NOT_HANDLED;
        }
        return printClearConfirmation(user);
    }

    /**
     * This method clears all timetables (from all users) from the masterTimetable List.
     *
     * @param masterTimetable The Master Timetable containing everyone's timetables
     * @return printAllClearConfirmation() A confirmation string that all records have been cleared
     */
    private String clearAll(MasterTimetable masterTimetable) {
        masterTimetable.clearAll();
        return printAllClearConfirmation();
    }

    /**
     * This method clears all the events in the timetable specified.
     *
     * @param timetable The timetable containing multiple events
     */
    private void clearTimetable(Timetable timetable) {
        timetable.clearAll();
        assert (timetable.size() == 0) : ERROR_NON_EMPTY_LIST;
    }

    /**
     * This method informs the user that the specified user they tried to clear has been successfully cleared.
     *
     * @param user The user's timetable that has been cleared.
     *
     * @return user User's string been cleared
     */
    public static String printClearConfirmation(String user) {
        return user + CLEAR_TIMETABLE_CONFIRMATION;
    }

    /**
     * This method informs the user that all the timetables have been cleared.
     *
     * @return String Clear all confirmation
     */
    public static String printAllClearConfirmation() {
        return CLEAR_ALL_CONFIRMATION;
    }

}
