package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class InvalidInputException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_PARSE_STRING;

    public InvalidInputException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, error));
    }

}
