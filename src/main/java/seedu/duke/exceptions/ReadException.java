package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class ReadException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_READ_FILE;

    public ReadException() {
        super(ERROR_MESSAGE);
    }
}
