package seedu.duke.command.eventcommands;

import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.command.Command;

import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.eventlists.EventList;
import seedu.duke.exceptions.InvalidDeleteEventException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteEventCommand extends Command {
    private String index;
    private static final String EVENT_INDICATE = "/";
    private static final int ONLY_ONE_FIELD_ENTERED = 1;
    private static Logger logger = Logger.getLogger("Add Event");

    public DeleteEventCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (commandStringWithoutCommand.isEmpty()) {
            logger.log(Level.INFO, "empty string");
            throw new InvalidDeleteEventException();
        }
        String[] input = extractInput(commandStringWithoutCommand);
        String n = input[0].trim();
        if (input.length != ONLY_ONE_FIELD_ENTERED) {
            logger.log(Level.INFO, "more than one field entered");
            throw new InvalidDeleteEventException();
        }

        setIndex(n);
        logger.log(Level.INFO, "Event command parsed");
    }

    /**
     * Method used to extract the name and availability of the housekeeper.
     *
     * @param commandStringWithoutCommand Input entered by the user.
     * @return Input consisting of housekeeper name and availability.
     * @throws HotelLiteManagerException When description of availability is invalid.
     */
    private String[] extractInput(String commandStringWithoutCommand) throws HotelLiteManagerException {
        String[] input = commandStringWithoutCommand.split(EVENT_INDICATE);
        return input;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
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
            throws HotelLiteManagerException, IOException {

        final EventList eventList = listContainer.getEventList();
        String n = getIndex();
        assert !n.isEmpty() : "event number should not be empty";

        eventList.delete(n);
        logger.log(Level.INFO, "about to update file.");
        eventList.save();
        logger.log(Level.INFO, "end of deleting event.");
    }

}