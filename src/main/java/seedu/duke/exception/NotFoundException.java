package seedu.duke.exception;

public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
