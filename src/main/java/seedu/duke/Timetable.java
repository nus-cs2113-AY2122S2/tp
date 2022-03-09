package seedu.duke;

import java.util.ArrayList;

public class Timetable {

    public static final ArrayList<Event> list = new ArrayList<>();

    public static void add (Event event) {
        list.add(event);
    }

    public static int size() {
        return list.size();
    }

    public static Event get(int eventIndex) {
        Event targetEvent = list.get(eventIndex);
        return targetEvent;
    }

}
