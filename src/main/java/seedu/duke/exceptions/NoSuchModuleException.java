package seedu.duke.exceptions;

public class NoSuchModuleException extends ModHappyException {
    private static final String ERROR_MESSAGE = "Sorry, no such module exists ._.";

    public NoSuchModuleException() {
        super(ERROR_MESSAGE);
    }
}