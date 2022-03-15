package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class UnsupportedResultTypeException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_UNSUPPORTED_RESULT_TYPE;

    public UnsupportedResultTypeException() {
        super(ERROR_MESSAGE);
    }

    public UnsupportedResultTypeException(String userInput) {
        super(String.format("%s\n\"%s\"", ERROR_MESSAGE, userInput));
    }
}
