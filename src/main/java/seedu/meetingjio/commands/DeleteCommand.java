package seedu.meetingjio.commands;

import seedu.meetingjio.events.Event;
import seedu.meetingjio.events.Meeting;
import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import java.util.logging.Level;
import static seedu.meetingjio.parser.Parser.logger;

import static seedu.meetingjio.common.ErrorMessages.ERROR_TIMETABLE_NOT_FOUND_TO_DELETE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EXCEPTION_NOT_HANDLED;
import static seedu.meetingjio.common.ErrorMessages.ERROR_DELETE_COMMAND_FAILED;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INDEX_OUT_OF_BOUND;

//@@author ibrahimisramos
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int index;
    private final String name;

    public DeleteCommand(String name,int index) {
        this.index = index;
        this.name = name;
    }

    /**
     * Execute delete command using the timetable provided.
     *
     * @param masterTimetable MasterTimetable
     *
     */
    public String execute(MasterTimetable masterTimetable) {
        Timetable timetable;
        try {
            timetable  = masterTimetable.getByName(this.name);
        } catch (TimetableNotFoundException tnfe) {
            return ERROR_TIMETABLE_NOT_FOUND_TO_DELETE;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            return ERROR_EXCEPTION_NOT_HANDLED;
        }
        int size = timetable.size();
        try {
            Event event = timetable.get(index - 1);
            if (event instanceof Meeting) {
                return masterTimetable.deleteMeetingFromEveryoneTimetable((Meeting) event);
            } else {
                timetable.remove(index - 1);
                //assert (timetable.size() == size - 1) : ERROR_DELETE_COMMAND_FAILED;
                return deleteConfirmation(event);
            }
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

    public static String deleteFromAllTimetableConfirmation(Meeting event) {
        return "The following event has been deleted from everyone's timetable:\n"
                + event;
    }
}
