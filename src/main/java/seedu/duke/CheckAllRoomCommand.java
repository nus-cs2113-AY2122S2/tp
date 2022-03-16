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

    @Override
    /**
     * Override of execute command in Command class.
     * Print out all room information including:
     * type, room number, level and status.
     * @param satisfactionList The given list of Satisfaction objects.
     * @param roomList The given list of Room objects.
     * @param itemList The given list of Item objects.
     * @param ui The user interface for this execution method.
     */
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList, RoomList roomList,
                        ItemList itemList, Ui ui) {
        this.roomList = new RoomList();
        System.out.println(TABLE_HEAD);
        for (Room room : roomList.getRoomList()) {
            System.out.println(room.toString());
        }
    }

}
