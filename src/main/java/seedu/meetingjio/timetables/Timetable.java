package seedu.meetingjio.timetables;

import seedu.meetingjio.events.Event;
import seedu.meetingjio.exceptions.DuplicateEventException;
import seedu.meetingjio.exceptions.OverlappingEventException;

import seedu.meetingjio.commands.FreeCommand;

import java.util.ArrayList;

public class Timetable {

    private final String name;
    private ArrayList<Event> list;

    public static final int BUSY = 1;

    public Timetable(String name) {
        this.name = name;
        this.list = new ArrayList<>();
    }

    /**
     * Checks if the event to be added has already been added, or if there is a timing clash with an existing event.
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

    public String getName() {
        return name;
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
     * there is no timing clash.
     *
     * @param newEvent Event to be added
     * @return true if there is overlap, otherwise false
     */
    private boolean isOverlap(Event newEvent) {
        for (int i = 0; i < list.size(); i++) {
            Event event = list.get(i);
            if (event.overlaps(newEvent)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Given 2 events, this method is used to compare which event comes first.
     * This is harnessed by tapping on the getDay method for the Event class, to compare the events' day.
     * As there will be no overlap between each event's timing, checking which event's startTime is earlier is
     * sufficient to determine which event comes first if both occur on the same day.
     *
     * @param event1 The first event to be compared
     * @param event2 The second event to be compared
     * @return true if Event E1 comes earlier than Event E2, false otherwise
     */
    private boolean isEarlier(Event event1, Event event2) {
        if (event1.getDay() < event2.getDay()) {
            return true;
        } else if (event1.getDay() == event2.getDay() && event1.startTime < event2.startTime) {
            return true;
        }
        return false;
    }

    /**
     * This sorting algorithm works by adding the very first event in the timetable's list to the temporary ArrayList.
     * There will be no error expected as prior to this method, it has already been asserted that there is at least one
     * event in the timetable's list.
     * Subsequently, the next event in the timetable's list will be compared with each event in the temporary ArrayList,
     * and inserted at the appropriate index.
     * This will continue until there are no more events left in the timetable's list.
     * Finally, the timetable's list will be replaced with the temporary ArrayList, which is a sorted version of the
     * timetable's list.
     */
    public void sort() {
        ArrayList<Event> tempList = new ArrayList<>();
        tempList.add(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (isEarlier(list.get(i), tempList.get(j))) {
                    tempList.add(j, list.get(i));
                    break;
                }
            }
            if (tempList.size() == i) {
                tempList.add(list.get(i));
            }
        }
        list = tempList;
    }

    public ArrayList<Event> getList() {
        return list;
    }

    public void setList(ArrayList<Event> list) {
        this.list = list;
    }

    /**
     * For each event in the timetable, obtain its corresponding day, start time and end time in the appropriate format.
     * Indicate 1 (BUSY) for every minute that the user is attending an event.
     *
     * @param busySlots 7 x 960 array representing the timeframe from 0800 to 2359 for each day. 1 indicates BUSY and 0
     *                  indicates FREE
     */
    public void populateBusySlots(int[][] busySlots) {
        for (int i = 0; i < list.size(); i++) {
            Event event = list.get(i);
            int numericDay = event.getDay();
            int numericStartTime = FreeCommand.convertTimeToFreeArrayIndex(event.startTime);
            int numericEndTime = FreeCommand.convertTimeToFreeArrayIndex(event.endTime);
            for (int j = numericStartTime; j < numericEndTime; j++) {
                busySlots[numericDay - 1][j] = BUSY;
            }
        }
    }

}
