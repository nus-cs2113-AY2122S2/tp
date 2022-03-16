package seedu.duke.commands;

import seedu.duke.events.Event;
import seedu.duke.Timetable;

import static seedu.duke.common.ErrorMessages.ERROR_INDEX_OUT_OF_BOUND;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Execute delete command using the timetable provided.
     *
     * @param timetable Timetable object initialised by programme
     *
     */
    public String execute(Timetable timetable) {
        try {
            Event event = timetable.get(index - 1);
            timetable.remove(index - 1);
            return deleteConfirmation(event);
        } catch (IndexOutOfBoundsException ie) {
            return ERROR_INDEX_OUT_OF_BOUND;
        }
    }

    /**
     * Inform user that delete has happened.
     *
     * @param event Event to inform user that said event has been deleted
     *
     */
    private String deleteConfirmation(Event event) {
        return "The following event has been deleted from your timetable:\n"
                + event;
    }
}
