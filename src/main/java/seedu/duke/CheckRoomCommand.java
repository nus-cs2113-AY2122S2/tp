package seedu.duke;

public class CheckRoomCommand extends Command {
    private int roomId;

    public CheckRoomCommand(String commandStringWithoutCommand) {
        roomId = Integer.parseInt(commandStringWithoutCommand.trim());
    }

    @Override
    // check if the room id is correct
    public void execute(SatisfactionList satisfactionList, RoomList roomList, ItemList itemList, Ui ui) {
        for (Room room : roomList.getRoomList()) {
            if (room.getRoomId() == roomId) {
                System.out.println(room);
                return;
            }
        }
    }
}

