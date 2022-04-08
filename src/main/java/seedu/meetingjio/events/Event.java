package seedu.meetingjio.events;

public abstract class Event {
    private String title;
    private String day;
    private int startTime;
    private int endTime;
    private String mode;

    public Event(String title, String day, int startTime, int endTime, String mode) {
        this.title = title;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mode = mode;
    }

    public String getTitle() {
        return title;
    }

    public String getMode() {
        return mode;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public String getDay() {
        return day;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        if (!title.equals(event.title)) {
            return false;
        }
        if (!day.equals(event.day)) {
            return false;
        }
        if (startTime != event.startTime) {
            return false;
        }
        if (endTime != event.endTime) {
            return false;
        }
        assert (title.equals(event.title)) : "The titles are different";
        assert (day.equals(event.day)) : "The days are different";
        assert (startTime == event.startTime) : "The start times are different";
        assert (endTime == event.endTime) : "The end times are different";
        return true;
    }

    @Override
    public String toString() {
        return String.format("TITLE: %s\t\tDAY: %s\t\tSTART: %04d\t\tEND: %04d\t\tMODE: %s",
                            title, day, startTime, endTime, mode);
    }

    /**
     * Checks if the events belongs to the same day and person. If it does not, there is definitely no overlap.
     * If it does, check if there is timing overlap.
     *
     * @param event The new event to be added
     * @return True if the event belongs to the same person and on the same day with timing clash. False otherwise
     */
    public boolean overlaps(Event event) {
        if (!day.equals(event.day)) {
            return false;
        }
        boolean startTimeOverlap = event.startTime >= startTime
                && event.startTime < endTime;
        boolean endTimeOverlap = event.endTime > startTime
                && event.endTime <= endTime;
        boolean totalOverlap = event.startTime <= startTime
                && event.endTime >= endTime;
        if (startTimeOverlap || endTimeOverlap || totalOverlap) {
            assert day.equals(event.day) : "No overlap as events are on different days\n";
            return true;
        }
        return false;
    }

    /**
     * Convert the day of an event to its corresponding number.
     * This method is used to evaluate which event comes first for the sorting algorithm.
     *
     * @return numericDay Integer that corresponds to the day of the event
     */
    public int getDayInInt() {
        int numericDay = 0;
        switch (day.toLowerCase()) {
        case "monday":
            numericDay = 1;
            break;
        case "tuesday":
            numericDay = 2;
            break;
        case "wednesday":
            numericDay = 3;
            break;
        case "thursday":
            numericDay = 4;
            break;
        case "friday":
            numericDay = 5;
            break;
        case "saturday":
            numericDay = 6;
            break;
        case "sunday":
            numericDay = 7;
            break;
        default:
            break;
        }
        return numericDay;
    }

}
