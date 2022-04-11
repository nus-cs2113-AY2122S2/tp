package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author chooyikai
/**
 * Exception to be thrown when the user-supplied module for further actions does not exist.
 */
public class NoSuchModuleException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_NO_SUCH_MODULE;

    public NoSuchModuleException() {
        super(ERROR_MESSAGE);
    }
}