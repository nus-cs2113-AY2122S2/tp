package seedu.duke.roomlists;

/**
 * Room class has the parameter of roomId, level type and vacancy status.
 */
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

    public Room(int roomId, int level, RoomType type, String status) {
        this.roomId = roomId;
        this.level = level;
        this.type = type;
        isVacant = (status.equals("Vacant")) ? true : false;
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

    public void checkIn() {
        isVacant = false;
    }

    public void checkOut() {
        isVacant = true;
    }

    public String toString() {
        return String.format("%-15s%-15d%-15d%-15s", getType(), getRoomId(), getLevel(),
                (getIsVacant() ? "Vacant  " : "Occupied"));
    }

    public String toFileString() {
        return getType() + " | " + getRoomId() + " | " + getLevel() + " | " + (getIsVacant() ? "Vacant" : "Occupied");
    }

}
