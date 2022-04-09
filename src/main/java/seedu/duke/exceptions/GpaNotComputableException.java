package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

/**
 * Exception to be thrown when the module list is empty therefore GPA is not computable.
 */
public class GpaNotComputableException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_MODULE_LIST_EMPTY;

    public GpaNotComputableException() {
        super(ERROR_MESSAGE);
    }
}
