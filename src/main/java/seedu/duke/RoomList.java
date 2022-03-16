package seedu.duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents a data structure containing multiple Satisfaction objects.
 * The constructor will initialize all rooms with status vacant
 */
public class RoomList {
    private ArrayList<Room> roomList = new ArrayList<>();

    public RoomList() {  // dummy data for initialization
        Room r1 = new Room(101, 1, RoomType.Single);
        roomList.add(r1);
        r1 = new Room(102, 1, RoomType.Single);
        roomList.add(r1);
        r1 = new Room(103, 1, RoomType.Double);
        roomList.add(r1);
        r1 = new Room(201, 2, RoomType.Double);
        roomList.add(r1);
        r1 = new Room(201, 2, RoomType.Triple);
        roomList.add(r1);
        r1 = new Room(202, 2, RoomType.Triple);
        roomList.add(r1);
        r1 = new Room(203, 2, RoomType.Queen);
        roomList.add(r1);
        r1 = new Room(301, 3, RoomType.Queen);
        roomList.add(r1);
        r1 = new Room(302, 3, RoomType.King);
        roomList.add(r1);
        r1 = new Room(303, 3, RoomType.King);
        roomList.add(r1);
        r1 = new Room(401, 4, RoomType.Twin);
        roomList.add(r1);
        r1 = new Room(402, 4, RoomType.Twin);
        roomList.add(r1);
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }
}
