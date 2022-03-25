package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class GpaNotComputableException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_MODULE_LIST_EMPTY;

    public GpaNotComputableException() {
        super(ERROR_MESSAGE);
    }
}
