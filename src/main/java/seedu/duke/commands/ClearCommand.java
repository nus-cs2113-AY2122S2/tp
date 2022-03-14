package seedu.duke.commands;

import seedu.duke.Timetable;
import seedu.duke.events.Event;

public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";

    @Override
    public void execute(Timetable timetable) {
        for (int i = 0; i < timetable.size(); i++){
            timetable.remove(i);
        }
        printClearConfirmation();
    }

    public static void printClearConfirmation() {
        System.out.println("Your whole timetable has been cleared");

    }
}
