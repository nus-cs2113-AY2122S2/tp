package seedu.duke;

public class ChcekRoomCommand extends Command {
    private int roomId;
    public ChcekRoomCommand(String commandStringWithoutCommand) {
        roomId = Integer.parseInt(commandStringWithoutCommand.trim());
    }

    @Override
    public void execute(){

    }

    @Override
    // check if the room id is correct
    public void execute(RoomList list) {
        for(Room room : list.getRoomList()) {
            if (room.getRoomId() == roomId) {
                System.out.println(room);
                return;
            }
        }
    }
}

