package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author  Ch40gRv1-Mu
/**
 * Exception to be thrown when the storage file does not exist and cannot be created.
 */
public class FileCreateFailException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_FILE_CREATE_FAIL;

    public FileCreateFailException() {
        super(ERROR_MESSAGE);
    }

}
