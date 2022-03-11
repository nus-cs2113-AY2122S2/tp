package seedu.duke.commands;

import seedu.duke.events.Event;
import seedu.duke.Timetable;

import static seedu.duke.ErrorMessages.ERROR_INDEX_OUT_OF_BOUND;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(Timetable timetable) {
        try {
            Event event = timetable.get(index - 1);
            timetable.remove(index - 1);
            printDeleteConfirmation(event);
        } catch (IndexOutOfBoundsException ie) {
            System.out.println(ERROR_INDEX_OUT_OF_BOUND);
        }
    }

    public static void printDeleteConfirmation(Event event) {
        System.out.println("The following event has been deleted from your timetable:");
        System.out.println(event);
    }
}
