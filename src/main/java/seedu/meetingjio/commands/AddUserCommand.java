//@@author yanjie1017

package seedu.meetingjio.commands;

import seedu.meetingjio.events.Event;
import seedu.meetingjio.exceptions.DuplicateEventException;
import seedu.meetingjio.exceptions.OverlappingEventException;
import seedu.meetingjio.timetables.MasterTimetable;
import seedu.meetingjio.timetables.Timetable;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.meetingjio.exceptions.DuplicateTimetableException;

import static seedu.meetingjio.common.ErrorMessages.ERROR_DUPLICATE_USER;

public class AddUserCommand extends Command {
    public static final String COMMAND_WORD = "add_user";

    private final String name;
    public static Logger logger = Logger.getLogger(AddUserCommand.class.getName());

    public AddUserCommand(String name) {
        this.name = name;
    }

    /**
     * Create new user and add his/her timetable to the master timetable.
     * All the existing meetings will be added to the new timetable.
     *
     * @param masterTimetable The collection of everyone's timetable
     * @return String Add confirmation showing that the user is added
     */
    @Override
    public String execute(MasterTimetable masterTimetable) {
        try {
            Timetable timetable = new Timetable(name);
            try {
                for (Event meeting : masterTimetable.getMeetingList()) {
                    timetable.add(meeting);
                }
            } catch (DuplicateEventException | OverlappingEventException ee) {
                logger.log(Level.INFO, "Meeting list contains invalid meeting(s).");
            }
            masterTimetable.addTimetable(timetable);
            return addConfirmation(name);
        } catch (DuplicateTimetableException dte) {
            return ERROR_DUPLICATE_USER;
        }
    }

    /**
     * Inform user that new user and timetable has been added.
     *
     * @param name User
     * @return String confirmation of user being added
     */
    private String addConfirmation(String name) {
        return name + "'s timetable is created and added to the master timetable";
    }

}
