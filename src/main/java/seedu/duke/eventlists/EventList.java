package seedu.duke.eventlists;

import seedu.duke.exceptions.EventExistsException;
import seedu.duke.exceptions.InvalidDateException;
import seedu.duke.exceptions.EventDoesNotExist;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.storage.EventListFileManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import seedu.duke.Ui;

/**
 * Represents a data structure containing multiple Event objects.
 * The constructor will initialize all rooms with status vacant
 */
public class EventList {
    private Ui ui;
    private ArrayList<Event> eventList;

    public EventList(ArrayList<Event> eventList) {  // dummy data for initialization
        this.eventList = eventList;
        this.ui = new Ui();
    }

    public ArrayList<Event> getEventList() {
        return this.eventList;
    }

    public void add(String description, String atString) throws InvalidDateException, EventExistsException {
        LocalDate at;
        try {
            at = LocalDate.parse(atString);
        } catch (Exception e) {
            throw new InvalidDateException();
        }
        if (eventExists(description, at)) {
            throw new EventExistsException();
        }
        Event event = new Event(description, at);
        eventList.add(event);
        ui.printEventAdded(event);
    }

    public void delete(String n) throws EventDoesNotExist {
        try {
            int j = Integer.parseInt(n) - 1;
            Event deleted = eventList.get(j);
            eventList.remove(j);
            ui.printEventDeleted(deleted);
        } catch (Exception e) {
            throw new EventDoesNotExist();
        }
    }

    public boolean eventExists(String description, LocalDate at) {
        String atString = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        for (Event e : eventList) {
            String d = e.getDescription();
            String a = e.getAt();
            if (d.equals(description) && a.equals(atString)) {
                return true;
            }
        }
        return false;
    }

    public void save() throws IOException, HotelLiteManagerException {
        EventListFileManager eventFileManager = new EventListFileManager();
        eventFileManager.save(eventList);
    }
}