package seedu.duke;

public class CheckInCommand extends Command {
    RoomList roomList;
    int roomId;

    @Override
    public void execute(RoomList list, ItemList itemList, Ui ui) throws InvalidRoomNumberException {
        this.roomList = list;
        for (Room room : list.getRoomList()) {
            if (room.getRoomId() == roomId) {
                room.checkIn();
                // haven't implement ui
                System.out.println("Finished!");
                System.out.println(room);
                return;
            }
        }

        throw new InvalidRoomNumberException();
    }

    // check valid room number
    // check the room is vacant or not

    public CheckInCommand(String command) {
        roomId = Integer.parseInt(command.trim());
    }

}
