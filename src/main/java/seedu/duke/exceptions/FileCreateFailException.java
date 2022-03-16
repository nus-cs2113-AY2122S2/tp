package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class FileCreateFailException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_FILE_CREATE_FAIL;

    public FileCreateFailException() {
        super(ERROR_MESSAGE);
    }
}
