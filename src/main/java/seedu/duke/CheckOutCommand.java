package seedu.duke;

import java.io.IOException;

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
     * @param listContainer asd
     * @param ui The user interface for this execution method.
     * @throws InvalidRoomNumberException if the room number is not in the room list.
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
                        + assignmentMap.getHouseKeeperNameByRoom(room));
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
    public CheckOutCommand(String command) {
        roomId = Integer.parseInt(command.trim());
    }
}
