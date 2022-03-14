package seedu.duke;

public class Room {
    private int roomId;
    private int level;
    private RoomType type; // can implement it as enumeration
    private boolean isVacant;


    public Room(int roomId, int level, RoomType type) {
        this.roomId = roomId;
        this.level = level;
        this.type = type;
        isVacant = true;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getLevel() {
        return level;
    }

    public RoomType getType() {
        return type;
    }

    public boolean getIsVacant() {
        return isVacant;
    }

    public void checkIn(){
        isVacant = false;
    }

    public void checkOut(){
        isVacant = true;
    }

    public String toString(){
        return getType() + "\t\t" +getRoomId() +"\t\t\t\t"+getLevel()+"\t\t"+(getIsVacant()?"Vacant":"Occupied");
    }
}
