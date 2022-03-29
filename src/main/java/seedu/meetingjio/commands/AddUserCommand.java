package seedu.meetingjio.commands;

import seedu.meetingjio.events.Meeting;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_MEETING;
import static seedu.meetingjio.common.ErrorMessages.ERROR_OVERLAPPING_MEETING;

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
        } catch (DuplicateTimetableException dte) {
            return "ERROR DETECTED";
        }

    }

}
