package seedu.duke;

import seedu.duke.events.Event;
import seedu.duke.exceptions.DuplicateEventException;
import seedu.duke.exceptions.OverlappingEventException;

import java.util.ArrayList;

public class Timetable {
    private final ArrayList<Event> list;

    public Timetable() {
        this.list = new ArrayList<>();
    }

    /**
     * Checks if the event to be added has already been added, or if there is a timing clash with an existing event
     *
     * @param event New event to be added
     * @throws DuplicateEventException If identical event has already been added
     * @throws OverlappingEventException If another existing event has a timetable clash
     */
    public void add(Event event) throws DuplicateEventException, OverlappingEventException {
        if (isDuplicate(event)) {
            throw new DuplicateEventException();
        } else if (isOverlap(event)) {
            throw new OverlappingEventException();
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

    /**
     * Checks through all existing events and comparing their timings to the event to be added to ensure that
     * there is no timing clash
     *
     * @param newEvent Event to be added
     * @return true if there is overlap, otherwise false
     */
    private boolean isOverlap(Event newEvent) {
        for (int i = 0; i < list.size(); i++) {
            Event existingEvent = list.get(i);
            if (!existingEvent.day.equals(newEvent.day)) {
                continue;
            }
            boolean startTimeOverlap = newEvent.startTime >= existingEvent.startTime
                    && newEvent.startTime < existingEvent.endTime;
            boolean endTimeOverlap = newEvent.endTime > existingEvent.startTime
                    && newEvent.endTime <= existingEvent.endTime;
            boolean totalOverlap = newEvent.startTime <= existingEvent.startTime
                    && newEvent.endTime >= existingEvent.endTime;
            if (startTimeOverlap || endTimeOverlap || totalOverlap) {
                return true;
            }
        }
        return false;
    }

}
