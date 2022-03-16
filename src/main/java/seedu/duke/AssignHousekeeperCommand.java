package seedu.duke;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Identifies the name of the housekeeper and assign to room id
 * housekeeper list.
 */
public class AssignHousekeeperCommand extends Command {
    private String name;
    private String roomID;
    private static final String ASSIGNMENT_INDICATE = "##";
    private static final int ONLY_ONE_FIELD_ENTERED = 1;
    private static Logger logger = Logger.getLogger("Assign Housekeeper");

    public AssignHousekeeperCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (commandStringWithoutCommand.isEmpty()) {
            throw new InvalidAvailabilityException();
        }
        String[] input = extractInput(commandStringWithoutCommand);
        String name = input[0].trim();
        if (input.length == ONLY_ONE_FIELD_ENTERED) {
            throw new InvalidAvailabilityException();
        }
        String id = input[1].trim();
        if (id.isEmpty()) {
            throw new InvalidAvailabilityException();
        }
        setName(name);
        setRoomID(id);
        logger.log(Level.INFO, "Assign Command parsed");
    }

    /**
     * Method used to extract the name and availability of the housekeeper.
     *
     * @param commandStringWithoutCommand Input entered by the user.
     * @return Input consisting of housekeeper name and availability.
     * @throws HotelLiteManagerException When description of availability is invalid.
     */
    private String[] extractInput(String commandStringWithoutCommand) throws HotelLiteManagerException {
        boolean isSymbolIncorrect = !commandStringWithoutCommand.contains(ASSIGNMENT_INDICATE);
        if (isSymbolIncorrect) {
            throw new InvalidAvailabilityException();
        }
        String[] input = commandStringWithoutCommand.split(ASSIGNMENT_INDICATE);
        return input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getroomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
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
                        ItemList listOfItems, Ui ui)
            throws InvalidRoomNumberException, InvalidHousekeeperProfile {
        String roomID = getroomID();
        String name = getName();
        ui.printMessage("Assigned " + name + " to room#" + roomID + ".");
        assignmentMap.addAssignment(name, roomID, housekeeperList, roomList);
        logger.log(Level.INFO, "end of processing");
    }
}
