package seedu.duke.exception;

public class DuplicateEntryException extends Exception {

    public DuplicateEntryException (String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
