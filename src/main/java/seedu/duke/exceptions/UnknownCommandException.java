package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class UnknownCommandException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_UNKNOWN_COMMAND + LS + "\"%s\"";

    public UnknownCommandException() {
        super(ERROR_MESSAGE);
    }

    public UnknownCommandException(String userInput) {
        super(String.format(ERROR_MESSAGE, userInput));
    }
}
