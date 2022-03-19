package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class WriteException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_WRITE_FILE;

    public WriteException() {
        super(ERROR_MESSAGE);
    }
}
