package seedu.duke;

import seedu.duke.events.Event;
import seedu.duke.exceptions.DuplicateEventException;

import java.util.ArrayList;

public class Timetable {
    private final ArrayList<Event> list;

    public Timetable() {
        this.list = new ArrayList<>();
    }

    public void add(Event event) throws DuplicateEventException {
        if (isDuplicate(event)) {
            throw new DuplicateEventException();
        }
        list.add(event);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public int size() {
        return list.size();
    }

    public Event get(int eventIndex) {
        Event targetEvent = list.get(eventIndex);
        return targetEvent;
    }

    public void clear() {
        list.clear();
    }

    private boolean isDuplicate(Event newEvent) {
        for (int i = 0; i < list.size(); i++) {
            Event event = list.get(i);
            if (event.equals(newEvent)) {
                return true;
            }
        }
        return false;
    }

}
