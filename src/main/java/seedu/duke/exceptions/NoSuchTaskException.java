package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

/**
 * Exception to be thrown when the user-supplied task for further actions does not exist.
 */
public class NoSuchTaskException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_NO_SUCH_TASK;

    public NoSuchTaskException() {
        super(ERROR_MESSAGE);
    }
}
