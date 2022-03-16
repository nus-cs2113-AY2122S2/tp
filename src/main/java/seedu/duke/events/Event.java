package seedu.duke.events;

public class Event {
    private String name;
    private String title;
    public String day;
    public int startTime;
    public int endTime;
    private String mode;

    public Event(String name, String title, String day, int startTime, int endTime, String mode) {
        this.name = name;
        this.title = title;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.mode = mode;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        if (!name.equals(event.name)) {
            return false;
        }
        if (!title.equals(event.title)) {
            return false;
        }
        if (!day.equals(event.day)) {
            return false;
        }
        if (!mode.equals(event.mode)) {
            return false;
        }
        if (startTime != event.startTime) {
            return false;
        }
        if (endTime != event.endTime) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("NAME: %s\t\tTITLE: %s\t\tDAY: %s\t\tSTART: %04d\t\tEND: %04d\t\tMODE: %s",
                            name, title, day, startTime, endTime, mode);
    }
}
