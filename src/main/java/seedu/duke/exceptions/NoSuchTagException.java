package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

/**
 * Exception to be thrown when the user-supplied tag for further actions does not exist.
 */
public class NoSuchTagException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_NO_SUCH_TAG;

    public NoSuchTagException() {
        super(ERROR_MESSAGE);
    }
}