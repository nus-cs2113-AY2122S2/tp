package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class NoSuchTagException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_NO_SUCH_TAG;

    public NoSuchTagException() {
        super(ERROR_MESSAGE);
    }
}