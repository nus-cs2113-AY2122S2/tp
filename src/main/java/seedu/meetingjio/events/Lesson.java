package seedu.meetingjio.events;

public class Lesson extends Event {
    public Lesson(String name, String title, String day, int startTime, int endTime, String mode) {
        super(name, title, day, startTime, endTime, mode);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Lesson)) {
            return false;
        }
        Lesson lesson = (Lesson) obj;
        return super.equals(lesson);
    }
}
