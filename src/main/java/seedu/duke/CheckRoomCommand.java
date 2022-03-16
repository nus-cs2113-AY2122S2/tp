package seedu.duke;

/**
 * Class that implements execution behavior to list room with corresponding
 * room number.
 * The information includes its type, room id, level and vacancy status.
 */
public class CheckRoomCommand extends Command {
    private int roomId;

    /**
     * Extracts the room level from user input.
     *
     * @param commandStringWithoutCommand contains the information of room number.
     */
    public CheckRoomCommand(String commandStringWithoutCommand) {
        roomId = Integer.parseInt(commandStringWithoutCommand.trim());
    }

    @Override
    /**
     * Override of execute command in Command class.
     * Print out the room information with corresponding room number
     * including information of:
     * type, room number, level and status.
     * @param satisfactionList The given list of Satisfaction objects.
     * @param roomList The given list of Room objects.
     * @param itemList The given list of Item objects.
     * @param ui The user interface for this execution method.
     */
    public void execute(SatisfactionList satisfactionList, RoomList roomList, ItemList itemList, Ui ui)
            throws InvalidRoomNumberException {
        for (Room room : roomList.getRoomList()) {
            if (room.getRoomId() == roomId) {
                System.out.println(room);
                return;
            }
        }
        throw new InvalidRoomNumberException();
    }
}

