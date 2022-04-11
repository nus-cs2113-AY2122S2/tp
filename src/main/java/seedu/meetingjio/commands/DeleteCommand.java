//@@author ibrahimisramos

package seedu.meetingjio.commands;

import seedu.meetingjio.events.Event;
import seedu.meetingjio.events.Meeting;
import seedu.meetingjio.exceptions.TimetableNotFoundException;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import java.util.logging.Level;

import static seedu.meetingjio.common.Messages.DELETE_CONFIRMATION;
import static seedu.meetingjio.parser.Parser.logger;

import static seedu.meetingjio.common.ErrorMessages.ERROR_TIMETABLE_NOT_FOUND_TO_DELETE;
import static seedu.meetingjio.common.ErrorMessages.ERROR_EXCEPTION_NOT_HANDLED;
import static seedu.meetingjio.common.ErrorMessages.ERROR_INVALID_INDEX;


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
        try {
            Event event = timetable.get(index - 1);
            if (event instanceof Meeting) {
                return masterTimetable.deleteMeetingFromEveryoneTimetable((Meeting) event);
            } else {
                timetable.remove(index - 1);
                return deleteConfirmation(event);
            }
        } catch (IndexOutOfBoundsException ie) {
            return ERROR_INVALID_INDEX;
        }
    }

    /**
     * Inform user that delete has happened.
     *
     * @param event Event to inform user that said event has been deleted
     *
     * @return String confirmation of event being deleted
     */
    private String deleteConfirmation(Event event) {
        return DELETE_CONFIRMATION + event;
    }

}
