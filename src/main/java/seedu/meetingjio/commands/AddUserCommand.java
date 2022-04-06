//@@author yanjie1017

package seedu.meetingjio.commands;

import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import seedu.meetingjio.exceptions.DuplicateTimetableException;
import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_USER;

public class AddUserCommand extends Command {
    public static final String COMMAND_WORD = "add_user";

    private final String name;

    public AddUserCommand(String name) {
        this.name = name;
    }

    /**
     * Create new user and add his/her timetable to the master timetable.
     *
     * @param masterTimetable MasterTimetable
     * @return String Add confirmation showing that the user is added
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        try {
            Timetable timetable = new Timetable(name);
            masterTimetable.addTimetable(timetable);
            return addConfirmation(name);
        } catch (DuplicateTimetableException dte) {
            return ERROR_DUPLICATE_USER;
        }
    }

    /**
     * Inform user that new user and timetable has been added.
     *
     * @param name User
     * @return String confirmation of user being added
     */
    private String addConfirmation(String name) {
        return name + "'s timetable is created and added to the master timetable";
    }

}
