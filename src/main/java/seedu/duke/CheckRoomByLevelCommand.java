package seedu.duke;

public class CheckRoomByLevelCommand extends Command {
    private int level;
    private static String TABLE_HEAD = "Type\t\tRoom Id\t\tlevel\t\tStatus";
    public CheckRoomByLevelCommand(String commandStringWithoutCommand) {
        level = Integer.parseInt(commandStringWithoutCommand.trim());
    }


    @Override
    public void execute() throws WrongCommandException {

    }

    @Override
    public void execute(RoomList list) throws WrongCommandException {
        System.out.println(TABLE_HEAD);
        for(Room room : list.getRoomList()){
            if(room.getLevel() == level){
                System.out.println(room);
            }
        }
    }
}
