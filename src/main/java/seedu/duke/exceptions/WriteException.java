package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

/**
 * Exception to be thrown when an error was encountered during writing of the storage file.
 */
public class WriteException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_WRITE_FILE;

    public WriteException() {
        super(ERROR_MESSAGE);
    }
}
