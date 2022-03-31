package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class InvalidNumberException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_PARSE_INT_FAILED;

    public InvalidNumberException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, error));
    }

}
