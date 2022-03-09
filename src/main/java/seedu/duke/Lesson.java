package seedu.duke;

public class Lesson extends Event {
    public Lesson(String name, String title, String day, int startTime, int endTime, String mode) {
        super(name, title, day, startTime, endTime, mode);
    }

    public Lesson(String[] description) {
        this(description[0], description[1], description[2],
                Integer.parseInt(description[3]), Integer.parseInt(description[4]), description[5]);
    }
}
