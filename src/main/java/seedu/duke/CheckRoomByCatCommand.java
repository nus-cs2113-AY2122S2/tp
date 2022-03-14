package seedu.duke;

public class CheckRoomByCatCommand extends Command {
    private RoomType type;
    private static String TABLE_HEAD = "Type\t\tRoom Id\t\tlevel\t\tStatus";

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
    public void execute(){

    }

    @Override
    public void execute(RoomList list){
        System.out.println(TABLE_HEAD);
        for (Room room : list.getRoomList()) {
            if (room.getType() == type){
                System.out.println(room);
            }
        }
    }
}

