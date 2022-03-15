package seedu.duke;

public class CheckRoomByLevelCommand extends Command {
    private int level;
    private static String TABLE_HEAD = "Type\t\tRoom Id\t\tlevel\t\tStatus";

    public CheckRoomByLevelCommand(String commandStringWithoutCommand) {
        level = Integer.parseInt(commandStringWithoutCommand.trim());
    }


    @Override
    public void execute(SatisfactionList satisfactionList, RoomList roomList, ItemList itemList, Ui ui)
            throws InvalidLevelException {
        boolean isValidLevel = false;
        for (Room room : roomList.getRoomList()) {
            if (room.getLevel() == level) {
                isValidLevel = true;
                break;
            }
        }
        if (!isValidLevel) {
            throw new InvalidLevelException();
        }

        System.out.println(TABLE_HEAD);
        for (Room room : roomList.getRoomList()) {
            if (room.getLevel() == level) {
                System.out.println(room);
            }
        }
    }
}
