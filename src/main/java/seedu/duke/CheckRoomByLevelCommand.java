package seedu.duke;

public class CheckRoomByLevelCommand extends Command {
    private int level;
    private static String TABLE_HEAD = "Type\t\tRoom Id\t\tlevel\t\tStatus";

    public CheckRoomByLevelCommand(String commandStringWithoutCommand) {
        level = Integer.parseInt(commandStringWithoutCommand.trim());
    }


    @Override
    public void execute() {

    }

    @Override
    public void execute(RoomList list) throws InvalidLevelException {
        boolean isValidLevel = false;
        for (Room room : list.getRoomList()) {
            if (room.getLevel() == level) {
                isValidLevel = true;
                break;
            }
        }
        if (!isValidLevel) {
            throw new InvalidLevelException();
        }

        System.out.println(TABLE_HEAD);
        for (Room room : list.getRoomList()) {
            if (room.getLevel() == level) {
                System.out.println(room);
            }
        }
    }
}
