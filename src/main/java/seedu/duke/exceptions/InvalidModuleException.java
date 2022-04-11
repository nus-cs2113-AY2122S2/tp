package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author chooyikai
/**
 * Exception to be thrown when invalid module is detected.
 */
public class InvalidModuleException extends ModHappyException {
    public static final String ERROR_MESSAGE = StringConstants.ERROR_INVALID_MODULE;

    public InvalidModuleException() {
        super(ERROR_MESSAGE);
    }
}