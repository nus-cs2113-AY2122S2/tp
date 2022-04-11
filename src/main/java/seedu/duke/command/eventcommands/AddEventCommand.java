package seedu.duke.command.eventcommands;

import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidDescriptionException;
import seedu.duke.command.Command;

import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.eventlists.EventList;
import seedu.duke.exceptions.InvalidEventException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddEventCommand extends Command {
    private String description;
    private String at;
    private static final String REGEX = "[a-zA-Z0-9 ]*";
    private static final String EVENT_INDICATE = "/";
    private static final int NUMBER_OF_FIELDS = 2;
    private static Logger logger = Logger.getLogger("Add Event");

    public AddEventCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (commandStringWithoutCommand.isEmpty()) {
            logger.log(Level.INFO, "empty string");
            throw new InvalidEventException();
        }
        String[] input = extractInput(commandStringWithoutCommand);
        String description = input[0].trim();
        if (input.length != NUMBER_OF_FIELDS) {
            logger.log(Level.INFO, "only one field entered");
            throw new InvalidEventException();
        }

        if (description.equals("")) {
            logger.log(Level.INFO, "no description added");
            throw new InvalidEventException();
        }
        String at = input[1].trim();
        if (at.isEmpty()) {
            throw new InvalidEventException();
        }
        if (!description.matches(REGEX)) {
            throw new InvalidDescriptionException();
        }
        setDescription(description);
        setAt(at);
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
        boolean isSymbolIncorrect = !commandStringWithoutCommand.contains(EVENT_INDICATE);
        if (isSymbolIncorrect) {
            throw new InvalidEventException();
        }
        String[] input = commandStringWithoutCommand.split(EVENT_INDICATE);
        return input;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAt() {
        return this.at;
    }

    public void setAt(String at) {
        this.at = at;
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
        String description = getDescription();
        assert !description.isEmpty() : "description should not be empty";
        String at = getAt();
        assert !at.isEmpty() : "at should not be empty";

        eventList.add(description, at);
        logger.log(Level.INFO, "about to add to file.");
        eventList.save();
        logger.log(Level.INFO, "end of adding event.");
    }

}