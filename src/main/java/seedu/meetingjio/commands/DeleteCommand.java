package seedu.meetingjio.commands;

import seedu.meetingjio.events.Event;
import seedu.meetingjio.Timetable;

import static seedu.meetingjio.common.ErrorMessages.ERROR_DELETE_COMMAND_FAILED;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INDEX_OUT_OF_BOUND;

//@@author ibrahimisramos
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
        int size = timetable.size();
        try {
            Event event = timetable.get(index - 1);
            timetable.remove(index - 1);
            assert (timetable.size() == size - 1) : ERROR_DELETE_COMMAND_FAILED;
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
