package seedu.duke;


public class ListContainer {
    private HousekeeperList housekeeperList;
    private HousekeeperPerformanceList housekeeperPerformanceList;
    private SatisfactionList satisfactionList;
    private AssignmentMap assignmentMap;
    private RoomList roomList;
    private ItemList itemList;

    public ListContainer() {
        satisfactionList = new SatisfactionList();
        housekeeperList = new HousekeeperList();
        housekeeperPerformanceList = new HousekeeperPerformanceList();
        assignmentMap = new AssignmentMap();
        roomList = new RoomList();
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
