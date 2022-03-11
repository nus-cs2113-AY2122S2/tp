package seedu.duke.commands;

import seedu.duke.Timetable;

public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";

    @Override
    public void execute(Timetable timetable) {
        timetable.clear();
    }
}
