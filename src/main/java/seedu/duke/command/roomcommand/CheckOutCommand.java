package seedu.duke.command.roomcommand;

import java.io.IOException;

import seedu.duke.AssignmentMap;
import seedu.duke.InvalidRoomNumberException;
import seedu.duke.ListContainer;
import seedu.duke.Room;
import seedu.duke.RoomList;
import seedu.duke.Ui;
import seedu.duke.command.Command;
import seedu.duke.command.RoomHelper;

/**
 * Class that checks out a room with corresponding room number
 * and changes its status to be vacant.
 */
public class CheckOutCommand extends Command {
    RoomList roomList;
    int roomId;

    /**
     * Override of execute command in Command class.
     * Check out room with corresponding room number.
     * The room status will be changed to vacant
     * After check in, it will print out corresponding room information.
     *
     * @param listContainer The object containing the necessary data structure.
     * @param ui            The object that deals with user interface for the program.
     * @throws InvalidRoomNumberException if the room number is not inside the room list.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws InvalidRoomNumberException, IOException {
        this.roomList = listContainer.getRoomList();
        AssignmentMap assignmentMap = listContainer.getAssignmentMap();
        for (Room room : roomList.getRoomList()) {
            if (room.getRoomId() == roomId) {
                room.checkOut();
                ui.printTableHeader();
                System.out.println(room + "\t\t\t"
                        + assignmentMap.getHouseKeeperNameByRoom(room.getRoomId()));
                roomList.save();
                return;
            }
        }
        throw new InvalidRoomNumberException();
    }

    /**
     * Constructor that initializes the roomId.
     *
     * @param command contains the roomId.
     */
    public CheckOutCommand(String command) throws InvalidRoomNumberException {
        if (!RoomHelper.isValidIntNumber(command.trim())) {
            throw new InvalidRoomNumberException();
        }
        roomId = Integer.parseInt(command.trim());
    }
}
