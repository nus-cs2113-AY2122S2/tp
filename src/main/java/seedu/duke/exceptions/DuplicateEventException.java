package seedu.duke.exceptions;

public class DuplicateEventException extends Exception {
    public static final String message = "This event already exists. "
            + "Duplicate event will not be not added to the timetable.";

    public DuplicateEventException() {
        super(message);
    }
}
