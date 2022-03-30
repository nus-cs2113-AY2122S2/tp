package seedu.duke;

import seedu.duke.storage.RoomFileManager;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a data structure containing multiple Satisfaction objects.
 * The constructor will initialize all rooms with status vacant
 */
public class RoomList {
    private ArrayList<Room> roomList;

    public RoomList(ArrayList<Room> roomArrayList) {  // dummy data for initialization
        roomList = roomArrayList;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void save() throws IOException, HotelLiteManagerException {
        RoomFileManager roomFileManager = new RoomFileManager();
        roomFileManager.save(roomList);
    }
}
