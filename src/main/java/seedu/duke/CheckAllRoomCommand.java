package seedu.duke;

public class CheckAllRoomCommand extends Command {
    private RoomList roomList;
    private static String TABLE_HEAD = "Type\t\tRoom Id\t\tlevel\t\tStatus";

    public CheckAllRoomCommand() {
    }

    @Override
    public void execute(HousekeeperList housekeeperList, SatisfactionList satisfactionList, RoomList roomList,
                        ItemList itemList, Ui ui) {
        this.roomList = new RoomList();
        System.out.println(TABLE_HEAD);
        for (Room room : roomList.getRoomList()) {
            System.out.println(room.toString());
        }
    }

}
