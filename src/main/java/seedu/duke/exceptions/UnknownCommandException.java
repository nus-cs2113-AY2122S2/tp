package seedu.duke.exceptions;

public class UnknownCommandException extends ModHappyException {
    private static final String ERROR_MESSAGE = "Sorry, I don't understand the following command:" + LS + "\"%s\"";

    public UnknownCommandException() {
        super(ERROR_MESSAGE);
    }

    public UnknownCommandException(String userInput) {
        super(String.format(ERROR_MESSAGE, userInput));
    }
}
