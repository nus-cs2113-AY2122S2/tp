package seedu.meetingjio.commands;

import seedu.meetingjio.events.Lesson;
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
     * Execute AddUser command using the timetable provided.
     *
     * @param masterTimetable MasterTimetable
     *
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
     *
     */
    private String addConfirmation(String name) {
        return name + "'s timetable is created and added to the master timetable";
    }

}
