package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class WriteStreamBrokenException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_WRITE_STREAM_BROKEN;

    public WriteStreamBrokenException() {
        super(ERROR_MESSAGE);
    }
}
