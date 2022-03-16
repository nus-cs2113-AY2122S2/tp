package seedu.duke;

/**
 * Class that implements execution behavior for listing the information
 * of all rooms with corresponding category.
 * Information contains its type, room id, level and vacancy status
 */
public class CheckRoomByCatCommand extends Command {
    private RoomType type;
    private static String TABLE_HEAD = "Type\t\tRoom Id\t\tlevel\t\tStatus";

    /**
     * Extracts the room type from user input.
     *
     * @param commandStringWithoutCommand contains the information of the room type.
     * @throws InvalidCategoryException If the category is not valid.
     */
    public CheckRoomByCatCommand(String commandStringWithoutCommand) throws InvalidCategoryException {
        switch (commandStringWithoutCommand.trim()) {
        case "single":
            type = RoomType.Single;
            break;
        case "double":
            type = RoomType.Double;
            break;
        case "triple":
            type = RoomType.Triple;
            break;
        case "queen":
            type = RoomType.Queen;
            break;
        case "twin":
            type = RoomType.Twin;
            break;
        case "king":
            type = RoomType.King;
            break;
        default:  // throw !!!!!!!
            throw new InvalidCategoryException();
        }
    }


    @Override
    /**
     * Override of execute command in Command class.
     * Print out all room information with corresponding room type
     * including the information of:
     * type, room number, level and status.
     * @param satisfactionList The given list of Satisfaction objects.
     * @param roomList The given list of Room objects.
     * @param itemList The given list of Item objects.
     * @param ui The user interface for this execution method.
     */
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList, RoomList roomList,
                        ItemList itemList, Ui ui) {
        System.out.println(TABLE_HEAD);
        for (Room room : roomList.getRoomList()) {
            if (room.getType() == type) {
                System.out.println(room);
            }
        }
    }
}

