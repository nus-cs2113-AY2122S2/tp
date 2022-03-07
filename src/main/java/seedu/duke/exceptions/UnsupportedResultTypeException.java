package seedu.duke.exceptions;

public class UnsupportedResultTypeException extends ModHappyException {
    private static final String ERROR_MESSAGE = "Sorry, I don't understand the result format:";

    public UnsupportedResultTypeException() {
        super(ERROR_MESSAGE);
    }

    public UnsupportedResultTypeException(String userInput) {
        super(String.format("%s\n\"%s\"", ERROR_MESSAGE, userInput));
    }
}
