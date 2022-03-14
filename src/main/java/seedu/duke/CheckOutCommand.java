package seedu.duke;

public class CheckOutCommand extends Command{
    RoomList roomList;
    int roomId;

    @Override
    public void execute(){ }
    @Override
    public void execute(RoomList list){
        this.roomList = list;
        for(Room room : list.getRoomList()){
            if(room.getRoomId() == roomId){
                room.checkOut();
                // haven't implement ui
                System.out.println("Finished!");
                System.out.println(room);
                return;
            }
        }
    }

    // check valid room number
    public CheckOutCommand(String command){
        roomId = Integer.parseInt(command.trim());
    }
}
