package seedu.meetingjio.commands;

import seedu.meetingjio.timetables.MasterTimetable;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String EXIT_COMMAND = "exit";

    @Override
    public String execute(MasterTimetable masterTimetable) {
        return "";
    }
}
