package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class ReadStreamBrokenException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_READ_STREAM_BROKEN;

    public ReadStreamBrokenException() {
        super(ERROR_MESSAGE);
    }
}
