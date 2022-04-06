package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class InvalidFlagException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_INVALID_FLAG;

    public InvalidFlagException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, error));
    }

}
