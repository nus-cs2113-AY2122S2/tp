package seedu.duke.command.assigncommand;

import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidAssignmentException;
import seedu.duke.command.Command;

import seedu.duke.ListContainer;
import seedu.duke.Ui;
import seedu.duke.housekeeperlists.Housekeeper;
import seedu.duke.exceptions.InvalidRoomNumberException;
import seedu.duke.roomlists.RoomList;
import seedu.duke.AssignmentMap;
import seedu.duke.housekeeperlists.HousekeeperList;
import seedu.duke.roomlists.Room;
import seedu.duke.exceptions.InvalidHousekeeperNameException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Identifies the name of the housekeeper and assign to room id
 * housekeeper list.
 */
public class AssignHousekeeperCommand extends Command {
    private String name;
    private String roomID;
    private static final String ASSIGNMENT_INDICATE = "/";
    private static final int ONLY_ONE_FIELD_ENTERED = 1;
    private static Logger logger = Logger.getLogger("Assign Housekeeper");

    public AssignHousekeeperCommand(String commandStringWithoutCommand) throws HotelLiteManagerException {
        if (commandStringWithoutCommand.isEmpty()) {
            throw new InvalidAssignmentException();
        }
        String[] input = extractInput(commandStringWithoutCommand);
        String name = input[0].trim();
        if (input.length == ONLY_ONE_FIELD_ENTERED) {
            throw new InvalidAssignmentException();
        }
        String id = input[1].trim();
        if (id.isEmpty()) {
            throw new InvalidAssignmentException();
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
            throw new InvalidAssignmentException();
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
     * @param ui The user interface for this execution method.
     * @return
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui)
            throws HotelLiteManagerException, IOException {

        final AssignmentMap assignmentMap = listContainer.getAssignmentMap();
        final HousekeeperList housekeeperList = listContainer.getHousekeeperList();
        final RoomList roomList = listContainer.getRoomList();
        String roomID = getroomID();
        assert !roomID.isEmpty() : "ID should not be empty";
        String name = getName();
        assert !name.isEmpty() : "name should not be empty";


        if (!isNameExist(name, housekeeperList)) {
            throw new InvalidHousekeeperNameException();
        }

        int roomIdNumber = 0;

        try {
            roomIdNumber = Integer.parseInt(roomID);
        } catch (Exception e) {
            throw new InvalidRoomNumberException();
        }

        if (!isRoomIdValid(roomIdNumber, roomList)) {
            throw new InvalidRoomNumberException();
        }


        assignmentMap.addAssignment(name, roomIdNumber);
        assignmentMap.save();
        ui.printAssignedHousekeeper(roomID, name);
        ui.printMessage("Assigned " + name + " to room no. " + roomID + ".");
        logger.log(Level.INFO, "end of processing");
    }

    private boolean isRoomIdValid(int roomIdNumber, RoomList roomList) {
        for (Room room : roomList.getRoomList()) {
            if (room.getRoomId() == roomIdNumber) {
                return true;
            }
        }
        return false;
    }

    private boolean isNameExist(String name, HousekeeperList housekeeperList) {
        for (Housekeeper housekeeper : housekeeperList.getHousekeeperList()) {
            if (housekeeper.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
