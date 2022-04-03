package seedu.duke;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import seedu.duke.housekeeperlists.HousekeeperList;
import seedu.duke.housekeeperperformancelists.HousekeeperPerformanceList;
import seedu.duke.itemlists.ItemList;
import seedu.duke.eventlists.EventList;
import seedu.duke.eventlists.Event;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.roomlists.Room;
import seedu.duke.roomlists.RoomList;
import seedu.duke.satisfactionlists.SatisfactionList;
import seedu.duke.storage.AssignmentListFileManager;
import seedu.duke.storage.RoomFileManager;
import seedu.duke.storage.EventListFileManager;
import seedu.duke.storage.ItemListFileManager;
import seedu.duke.storage.SatisfactionListFileManager;
import seedu.duke.storage.HousekeeperPerformanceFileManager;
import seedu.duke.storage.HousekeeperFileManager;

public class ListContainer {
    private HousekeeperList housekeeperList;
    private HousekeeperPerformanceList housekeeperPerformanceList;
    private SatisfactionList satisfactionList;
    private AssignmentMap assignmentMap;
    private RoomList roomList;
    private ItemList itemList;
    private EventList eventList;

    public ListContainer() throws IOException, HotelLiteManagerException {
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

        ItemListFileManager itemListFileManager = new ItemListFileManager();
        itemList = itemListFileManager.load();

        HousekeeperFileManager housekeeperFileManager = new HousekeeperFileManager();
        housekeeperList = housekeeperFileManager.load();

        HousekeeperPerformanceFileManager housekeeperPerformanceFileManager = new HousekeeperPerformanceFileManager();
        housekeeperPerformanceList = housekeeperPerformanceFileManager.load();

        SatisfactionListFileManager satisfactionListFileManager = new SatisfactionListFileManager();
        satisfactionList = satisfactionListFileManager.load();

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
