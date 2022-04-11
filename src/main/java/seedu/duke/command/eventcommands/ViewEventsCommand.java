package seedu.duke.command.eventcommands;

import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.command.Command;

import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.eventlists.EventList;
import seedu.duke.exceptions.InvalidViewEventException;
import seedu.duke.exceptions.InvalidRoomNumberException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewEventsCommand extends Command {
    private static Logger logger = Logger.getLogger("log: View events present in the list of events.");

    public ViewEventsCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (!commandStringWithoutCommand.equals("")) {
            throw new InvalidViewEventException();
        }
        logger.log(Level.INFO, "View events command parsed");
    }


    /**
     * Get the Name of the housekeeper and verify that housekeeper is in records. If in records, add
     * his/her availability into housekeeper list.
     *
     * @param ui The user interface for this execution method.
     * @return
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui)
            throws InvalidRoomNumberException, IOException {
        final EventList eventList = listContainer.getEventList();
        ui.printAllEvents(eventList.getEventList());
        logger.log(Level.INFO, "log: all events displayed");
    }

}