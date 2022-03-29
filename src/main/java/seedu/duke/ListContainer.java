package seedu.duke;


import seedu.duke.storage.AssignmentListFileManager;
import seedu.duke.storage.EventListFileManager;
import seedu.duke.storage.RoomFileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ListContainer {
    private HousekeeperList housekeeperList;
    private HousekeeperPerformanceList housekeeperPerformanceList;
    private SatisfactionList satisfactionList;
    private AssignmentMap assignmentMap;
    private RoomList roomList;
    private ItemList itemList;
    private EventList eventList;

    public ListContainer() throws IOException {
        satisfactionList = new SatisfactionList();
        housekeeperList = new HousekeeperList();
        housekeeperPerformanceList = new HousekeeperPerformanceList();

        AssignmentListFileManager assignmentListFileManager = new AssignmentListFileManager();
        HashMap<Integer,String> map = new HashMap<>();
        assignmentListFileManager.load(map);
        assignmentMap = new AssignmentMap(map);

        RoomFileManager roomFileManager = new RoomFileManager();
        ArrayList<Room> roomArrayList = new ArrayList<>();
        roomFileManager.load(roomArrayList);
        roomList = new RoomList(roomArrayList);

        EventListFileManager eventFileManager = new EventListFileManager();
        ArrayList<Event> eventArrayList = new ArrayList<>();
        eventFileManager.load(eventArrayList);
        eventList = new EventList(eventArrayList);

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

    public EventList getEventList() {
        return eventList;
    }

}
