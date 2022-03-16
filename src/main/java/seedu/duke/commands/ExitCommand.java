package seedu.duke.commands;

import seedu.duke.Timetable;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String EXIT_COMMAND = "exit";

    @Override
    public String execute(Timetable timetable) {
        return "";
    }
}
