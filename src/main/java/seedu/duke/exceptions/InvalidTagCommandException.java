package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class InvalidTagCommandException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_INVALID_TAG_COMMAND;

    public InvalidTagCommandException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, error));
    }

}
