package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class InvalidNumberException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_INVALID_NUMBER;

    public InvalidNumberException(String parameter, String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, parameter, error));
    }

    public InvalidNumberException(String parameter, String error, String help) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, parameter, error) + help);
    }

}
