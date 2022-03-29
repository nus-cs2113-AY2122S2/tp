package seedu.duke;

import seedu.duke.storage.EventListFileManager;
import seedu.duke.storage.RoomFileManager;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a data structure containing multiple Event objects.
 * The constructor will initialize all rooms with status vacant
 */
public class EventList {
    private ArrayList<Event> eventList;

    public EventList(ArrayList<Event> eventList) {  // dummy data for initialization
        this.eventList = eventList;
    }

    public ArrayList<Event> getEventList() {
        return this.eventList;
    }

    public void save() throws IOException {
        EventListFileManager eventFileManager = new EventListFileManager();
        eventFileManager.save(eventList);
    }
}