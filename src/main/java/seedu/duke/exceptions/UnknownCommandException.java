package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author Ch40gRv1-Mu
/**
 * Exception to be thrown when command word entered by the user is not recognised.
 */
public class UnknownCommandException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_UNKNOWN_COMMAND + LS + "\"%s\"";

    public UnknownCommandException() {
        super(ERROR_MESSAGE);
    }

    public UnknownCommandException(String userInput) {
        super(String.format(ERROR_MESSAGE, userInput));
    }
}
