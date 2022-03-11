package seedu.duke;

import seedu.duke.events.Event;

import java.util.ArrayList;

public class Timetable {
    private final ArrayList<Event> list;
    public Timetable() {
        this.list = new ArrayList<>();
    }

    public void add(Event event) {
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

}
