package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class InvalidTagCommandException extends GeneralParseException {
    private static final String ERROR_STRING_INVALID = StringConstants.ERROR_INVALID_TAG_COMMAND;
    private static final String ERROR_STRING_MISSING = StringConstants.ERROR_MISSING_TAG_COMMAND;

    public InvalidTagCommandException() {
        super(ERROR_MESSAGE + ERROR_STRING_MISSING);
    }

    public InvalidTagCommandException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING_INVALID, error));
    }

}
