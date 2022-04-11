package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author Yzkkk
/**
 * Exception to be thrown when user inputted an invalid tag operation.
 */
public class InvalidTagOperationException extends GeneralParseException {
    private static final String ERROR_STRING_INVALID = StringConstants.ERROR_INVALID_TAG_OPERATION;
    private static final String ERROR_STRING_MISSING = StringConstants.ERROR_MISSING_TAG_OPERATION;

    public InvalidTagOperationException() {
        super(ERROR_MESSAGE + ERROR_STRING_MISSING);
    }

    public InvalidTagOperationException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING_INVALID, error));
    }
}
