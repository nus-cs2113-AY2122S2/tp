package seedu.duke;

import java.util.ArrayList;

public class EventList {

    public static final ArrayList<Event> list = new ArrayList<>();

    public static void add (Event event) {
        list.add(event);
    }

}
