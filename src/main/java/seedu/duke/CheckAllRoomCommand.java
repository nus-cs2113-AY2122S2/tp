package seedu.duke;


/**
 * Class that implements execution behavior for listing all room.
 * information with its type, room id, level and vacancy status.
 */
public class CheckAllRoomCommand extends Command {
    private RoomList roomList;
    private static String TABLE_HEAD = "Type\t\tRoom Id\t\tlevel\t\tStatus";

    public CheckAllRoomCommand() {
    }

    /**
     * Override of execute command in Command class.
     * Print out all room information including:
     * type, room number, level and status.
     * @param listContainer
     * @param ui The user interface for this execution method.
     */
    @Override
    public void execute(ListContainer listContainer, Ui ui) {
        this.roomList = listContainer.getRoomList();
        System.out.println(TABLE_HEAD);
        for (Room room : roomList.getRoomList()) {
            System.out.println(room.toString());
        }
    }

}
