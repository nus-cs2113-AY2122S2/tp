package seedu.meetingjio.timetables;

import seedu.meetingjio.events.Lesson;
import seedu.meetingjio.events.Meeting;
import seedu.meetingjio.events.Event;

import seedu.meetingjio.exceptions.DuplicateEventException;
import seedu.meetingjio.exceptions.OverlappingEventException;

import java.util.ArrayList;

public class Timetable {

    private final String name;
    private ArrayList<Event> list;

    public static final int BUSY = 1;

    public static final int LESSONS_ONLY = 1;
    public static final int MEETINGS_ONLY = 2;

    public static final int HOUR_PARAMETER_IN_24_HOURS = 100;
    public static final int MINS_IN_1_HOUR = 60;

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

    /**
     * Checks through all existing events to the event to be added
     * to ensure that there is no duplicate.
     *
     * @param newEvent Event to be added
     * @return true if there is identical event, otherwise false
     */
    public boolean isDuplicate(Event newEvent) {
        for (Event event : list) {
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
    public boolean isOverlap(Event newEvent) {
        for (Event event : list) {
            if (event.overlaps(newEvent)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValid() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    continue;
                }
                Event a = list.get(i);
                Event b = list.get(j);
                if (a.equals(b)) {
                    return true;
                }
                if (a.overlaps(b)) {
                    return true;
                }
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
        if (event1.getDayInInt() < event2.getDayInInt()) {
            return true;
        } else if (event1.getDayInInt() == event2.getDayInInt() && event1.getStartTime() < event2.getEndTime()) {
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

    /**
     * List contents of the timetable, taking into consideration the constraint supplied.
     * If constraint supplied is LESSONS_ONLY, events that are meetings will not be added to the returned string.
     * If constraint supplied is MEETINGS_ONLY, events that are lessons will not be added to the returned string.
     *
     * @param constraint Integer that is either 0, 1 or 2, indicating the user's constraint
     * @return str String containing the labelled events of the timetable
     */
    public String listTimetable(int constraint) {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            boolean isMeetingGivenListLessons = constraint == LESSONS_ONLY && list.get(i) instanceof Meeting;
            boolean isLessonGivenListMeetings = constraint == MEETINGS_ONLY && list.get(i) instanceof Lesson;
            if (isMeetingGivenListLessons || isLessonGivenListMeetings) {
                continue;
            }
            int listIndex = i + 1;
            str += listIndex + "." + list.get(i);
            str += '\n';
        }
        return str;
    }

    public ArrayList<Event> getList() {
        return list;
    }

    /**
     * For each event in the timetable, obtain its corresponding day, start time and end time in the appropriate format.
     * Indicate 1 (BUSY) for every minute that the user is attending an event.
     *
     * @param busySlots 7 x 1440 array representing the timeframe from 0000 to 2359 for each day. 1 indicates BUSY and 0
     *                  indicates FREE
     */
    public void populateBusySlots(int[][] busySlots) {
        for (Event event : list) {
            int numericDay = event.getDayInInt();
            int numericStartTime = convertTimeToFreeArrayIndex(event.getStartTime());
            int numericEndTime = convertTimeToFreeArrayIndex(event.getEndTime());
            for (int j = numericStartTime; j < numericEndTime; j++) {
                busySlots[numericDay - 1][j] = BUSY;
            }
        }
    }

    /**
     * This helper method takes in a time in 24-hour format, and convert it to the number of minutes starting from 0000.
     *
     * @param time Time to be converted
     * @return timeInMinutes The number of minutes converted from time
     */
    private static int convertTimeToFreeArrayIndex(int time) {
        int hours = time / HOUR_PARAMETER_IN_24_HOURS;
        int minutes = time % HOUR_PARAMETER_IN_24_HOURS;
        int timeInMinutes = hours * MINS_IN_1_HOUR + minutes;
        return timeInMinutes;
    }

    /**
     * This method checks if two timetables are the same.
     *
     * @param obj Object to compare timetable with
     * @return boolean True or false if both timetable objects are the same
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Timetable)) {
            return false;
        }
        Timetable timetable = (Timetable) obj;
        if (!name.equalsIgnoreCase(timetable.name)) {
            return false;
        }
        return true;
    }
}
