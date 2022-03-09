package seedu.duke;

public class Event {
    private String name;
    private String title;
    private String day;
    private int startTime;
    private int endTime;
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
    public String toString() {
        return String.format("NAME: %s\t\tTITLE: %s\t\tDAY: %s\t\tSTART: %04d\t\tEND: %04d\t\tMODE: %s",
                            name, title, day, startTime, endTime, mode);
    }
}
