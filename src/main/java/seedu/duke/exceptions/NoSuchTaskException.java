package seedu.duke.exceptions;

public class NoSuchTaskException extends ModHappyException {
    private static final String ERROR_MESSAGE = "Sorry, no such task exists ._.";

    public NoSuchTaskException() {
        super(ERROR_MESSAGE);
    }
}
