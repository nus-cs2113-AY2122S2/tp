package seedu.meetingjio.events;

public class Lesson extends Event {
    public Lesson(String title, String day, int startTime, int endTime, String mode) {
        super(title, day, startTime, endTime, mode);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Lesson)) {
            return false;
        }
        Lesson lesson = (Lesson) obj;
        return super.equals(lesson);
    }

    @Override
    public String toString() {
        return "LESSON " + super.toString();
    }
}
