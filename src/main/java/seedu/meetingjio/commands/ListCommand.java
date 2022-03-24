package seedu.meetingjio.commands;

import seedu.meetingjio.exceptions.MissingValueException;
import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_USER;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EMPTY_LIST;
import static seedu.meetingjio.common.ErrorMessages.ERROR_UNSPECIFIED_LIST;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EMPTY_MASTER_TIMETABLE;
import static seedu.meetingjio.timetables.MasterTimetable.timetables;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    private final String name;

    public ListCommand(String name) {
        this.name = name;
    }

    /**
     * List out all entries in the user's timetable.
     * If there are no lessons found, notify user accordingly.
     *
     *
     * @param masterTimetable MasterTimetable
     * @return String containing all of the user's lessons
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        String user = this.name;
        try {
            if (user.length() == 0) {
                throw new MissingValueException();
            } else if (user.equalsIgnoreCase("all")) {
                return listAll();
            } else {
                return listUser(user);
            }
        } catch (MissingValueException mve) {
            return ERROR_UNSPECIFIED_LIST;
        }

    }

    private String listAll() {
        String str = "";
        for (Timetable timetable : timetables) {
            String user = timetable.getName();
            str += user;
            str += '\n';
            str += listUser(user);
            str += '\n';
        }
        if (str.length() == 0) {
            return ERROR_EMPTY_MASTER_TIMETABLE;
        }
        assert str.length() - 1 >= 0 : ERROR_EMPTY_MASTER_TIMETABLE;
        return str.substring(0, str.length() - 1); //remove last newline character
    }

    private String listUser(String user) {
        Timetable timetable;
        try {
            timetable = MasterTimetable.getByName(user);
        } catch (TimetableNotFoundException tnfe) {
            return ERROR_INVALID_USER;
        }
        if (timetable.size() == 0) {
            return ERROR_EMPTY_LIST;
        }
        assert timetable.size() > 0 : ERROR_EMPTY_LIST;
        String str = "";
        for (int i = 0; i < timetable.size(); i++) {
            int listIndex = i + 1;
            str += listIndex + "." + timetable.get(i);
            if (i != timetable.size() - 1) {
                str += '\n';
            }
        }
        return str;
    }


}
