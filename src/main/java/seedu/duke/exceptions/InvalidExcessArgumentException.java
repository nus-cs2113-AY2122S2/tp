package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class InvalidExcessArgumentException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_EXCESS_ARGUMENT;

    public InvalidExcessArgumentException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, error));
    }

}
