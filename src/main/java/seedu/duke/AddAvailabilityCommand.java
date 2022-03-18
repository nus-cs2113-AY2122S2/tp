package seedu.duke;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Identifies the name and availability of the housekeeper and update availability into the
 * housekeeper list.
 */
public class AddAvailabilityCommand extends Command {
    private String name;
    private String availability;
    private static final String AVAILABILITY_INDICATE = "@";
    private static Logger logger = Logger.getLogger("housekeeperLogger");

    public AddAvailabilityCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        String[] input;
        String inputName;
        String inputAvailability;
        if (commandStringWithoutCommand.isEmpty()) {
            throw new InvalidAvailabilityException();
        }
        try {
            input = extractInput(commandStringWithoutCommand);
            inputName = input[0].trim();
            inputAvailability = input[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Availability Housekeeper command is found to be empty.");
            throw new InvalidAvailabilityException();
        }
        String name = checkValidExtract(inputName);
        String availability = checkValidExtract(inputAvailability);
        assert (!name.isEmpty()) : "Name of Housekeeper should not be empty.";
        assert (!availability.isEmpty()) : "Availability should not be empty";
        setName(inputName);
        setAvailability(inputAvailability);
    }

    /**
     * This method verifies that input given by user is not empty.
     *
     * @param inputGiven Either the name or availability given by user.
     * @return Either the valid Name or Availability.
     * @throws InvalidAvailabilityException When input given is empty.
     */
    private String checkValidExtract(String inputGiven) throws InvalidAvailabilityException {
        if (inputGiven.isEmpty()) {
            throw new InvalidAvailabilityException();
        }
        return inputGiven;
    }

    /**
     * Method used to extract the name and availability of the housekeeper.
     *
     * @param commandStringWithoutCommand Input entered by the user.
     * @return Input consisting of housekeeper name and availability.
     * @throws HotelLiteManagerException When description of availability is invalid.
     */
    private String[] extractInput(String commandStringWithoutCommand) throws HotelLiteManagerException {
        boolean isSymbolIncorrect = !commandStringWithoutCommand.contains(AVAILABILITY_INDICATE);
        if (isSymbolIncorrect) {
            logger.log(Level.WARNING, "Availability Housekeeper command usage is wrong.");
            throw new InvalidAvailabilityException();
        }
        String[] input = commandStringWithoutCommand.split(AVAILABILITY_INDICATE);
        return input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    /**
     * Get the Name of the housekeeper and verify that housekeeper is in records. If in records, add
     * his/her availability into housekeeper list.
     *
     * @param housekeeperList  The list of housekeeper recorded.
     * @param satisfactionList The given list of Satisfaction objects.
     * @param roomList         The given list of Room objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param listOfItems      The given list of Item objects. N/A for this class, but
     *                         must be included for the execution override.
     * @param ui               The user interface for this execution method.
     */
    @Override
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList,
                        AssignmentMap assignmentMap, RoomList roomList,
                        ItemList listOfItems, Ui ui) throws UserExistException {
        String availability = getAvailability();
        String name = getName();
        ui.printMessage("Added " + name + " availability into records");
        housekeeperList.searchAvailability(name, availability);
    }
}
