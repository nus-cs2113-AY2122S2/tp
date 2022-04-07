package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class MissingNumberException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_MISSING_NUMBER;

    public MissingNumberException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, error));
    }

}
