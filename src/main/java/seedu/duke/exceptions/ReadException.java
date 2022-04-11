package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author chooyikai
/**
 * Exception to be thrown when an error was encountered during reading of storage file.
 */
public class ReadException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_READ_FILE;

    public ReadException() {
        super(ERROR_MESSAGE);
    }

    public ReadException(String additionalMessage) {
        super(ERROR_MESSAGE + additionalMessage);
    }
}
