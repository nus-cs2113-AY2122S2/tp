//@@author ibrahimisramos

package seedu.meetingjio.commands;

import seedu.meetingjio.exceptions.MissingValueException;
import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import java.util.logging.Level;
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

    private String clearTimetableUser(String user, MasterTimetable masterTimetable) {
        Timetable timetable;
        try {
            timetable  = masterTimetable.getByName(this.name);
            clearTimetable(timetable);
        } catch (TimetableNotFoundException tnfe) {
            return ERROR_TIMETABLE_NOT_FOUND_TO_DELETE;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return ERROR_EXCEPTION_NOT_HANDLED;
        }
        return printClearConfirmation(user);
    }

    private String clearAll(MasterTimetable masterTimetable) {
        int numTimetables = masterTimetable.getSize();
        for (int i = 0; i < numTimetables; i++) {
            Timetable timetable = masterTimetable.getByIndex(i);
            clearTimetable(timetable);
        }
        return printAllClearConfirmation();
    }

    private void clearTimetable(Timetable timetable) {
        int numEntries = timetable.size();
        for (int i = 0; i < numEntries; i++) {
            timetable.remove(0);
        }
        assert (timetable.size() == 0) : ERROR_NON_EMPTY_LIST;
    }

    public static String printClearConfirmation(String user) {
        return user + "'s timetable has been cleared";
    }

    public static String printAllClearConfirmation() {
        return "All records of everyone's timetable has been cleared";
    }

}
