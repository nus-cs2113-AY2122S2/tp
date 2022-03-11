package seedu.duke.commands;

import seedu.duke.Timetable;
import seedu.duke.events.Event;

public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";

    @Override
    public String execute(Timetable timetable) {
        timetable.clear();
        return clearConfirmation();
    }

    private String clearConfirmation() {
        return "The timetable has been cleared.\n";
    }
}
