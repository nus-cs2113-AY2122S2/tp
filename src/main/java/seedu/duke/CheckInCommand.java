package seedu.duke;

/**
 * Class that checks in a room and changes the status
 * of the room to be occupied.
 */

public class CheckInCommand extends Command {
    RoomList roomList;
    int roomId;

    /**
     * Override of execute command in Command class.
     * Check in room with corresponding room number.
     * The room status will be changed to occupied.
     * After check in, it will print out corresponding room information.
     * @param listContainer
     * @param ui The user interface for this execution method.
     * @throws InvalidRoomNumberException if the room number is not in the room list.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) throws InvalidRoomNumberException {
        this.roomList = listContainer.getRoomList();
        for (Room room : roomList.getRoomList()) {
            if (room.getRoomId() == roomId) {
                room.checkIn();
                System.out.println(room);
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
    public CheckInCommand(String command) {
        roomId = Integer.parseInt(command.trim());
    }

}
