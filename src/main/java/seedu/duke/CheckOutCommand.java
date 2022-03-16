package seedu.duke;

/**
 * Class that checks out a room with corresponding room number
 * and changes its status to be vacant.
 */
public class CheckOutCommand extends Command {
    RoomList roomList;
    int roomId;

    @Override
    /**
     * Override of execute command in Command class.
     * Check out room with corresponding room number.
     * The room status will be changed to vacant
     * After check in, it will print out corresponding room information.
     * @param satisfactionList The given list of Satisfaction objects.
     * @param roomList The given list of Room objects.
     * @param itemList The given list of Item objects.
     * @param ui The user interface for this execution method.
     * @throws InvalidRoomNumberException if the room number is not in the room list.
     */
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList,
                        AssignmentMap assignmentMap, RoomList list,
                        ItemList itemList, Ui ui) throws InvalidRoomNumberException {
        this.roomList = list;
        for (Room room : list.getRoomList()) {
            if (room.getRoomId() == roomId) {
                room.checkOut();
                // haven't implement ui
                System.out.println("Finished!");
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
    public CheckOutCommand(String command) {
        roomId = Integer.parseInt(command.trim());
    }
}
