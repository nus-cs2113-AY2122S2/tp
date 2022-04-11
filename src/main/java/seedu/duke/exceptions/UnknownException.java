package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author Ch40gRv1-Mu
/**
 * Exception to be thrown when command entered by the user is not recognised.
 */
public class UnknownException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_CATCH_UNKNOWN_EXCEPTION;

    public UnknownException(String userInput) {
        super(String.format(ERROR_MESSAGE, userInput));
    }
}
