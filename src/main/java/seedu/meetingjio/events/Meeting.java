//@@author ibrahimisramos

package seedu.meetingjio.events;

public class Meeting extends Event {

    public Meeting(String title, String day, int startTime, int endTime, String mode) {
        super(title, day, startTime, endTime, mode);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Meeting)) {
            return false;
        }
        Meeting meeting = (Meeting) obj;
        return super.equals(meeting);
    }

    @Override
    public String toString() {
        return "[M] " + super.toString();
    }
}
