package seedu.duke;


import seedu.duke.storage.RoomFileManager;

import java.io.IOException;
import java.util.ArrayList;

public class ListContainer {
    private HousekeeperList housekeeperList;
    private HousekeeperPerformanceList housekeeperPerformanceList;
    private SatisfactionList satisfactionList;
    private AssignmentMap assignmentMap;
    private RoomList roomList;
    private ItemList itemList;

    public ListContainer() throws IOException {
        satisfactionList = new SatisfactionList();
        housekeeperList = new HousekeeperList();
        housekeeperPerformanceList = new HousekeeperPerformanceList();
        assignmentMap = new AssignmentMap();
        RoomFileManager roomFileManager = new RoomFileManager();
        ArrayList<Room> roomArrayList = new ArrayList<>();
        roomFileManager.load(roomArrayList);
        roomList = new RoomList(roomArrayList);
        itemList = new ItemList();
    }


    public HousekeeperList getHousekeeperList() {
        return housekeeperList;
    }

    public HousekeeperPerformanceList getHousekeeperPerformanceList() {
        return housekeeperPerformanceList;
    }

    public SatisfactionList getSatisfactionList() {
        return satisfactionList;
    }

    public AssignmentMap getAssignmentMap() {
        return assignmentMap;
    }

    public RoomList getRoomList() {
        return roomList;
    }

    public ItemList getItemList() {
        return itemList;
    }
}
