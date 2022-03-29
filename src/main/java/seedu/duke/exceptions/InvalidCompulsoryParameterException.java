package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class InvalidCompulsoryParameterException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_PARSE_INVALID_PARAM;

    public InvalidCompulsoryParameterException() {
        super(ERROR_MESSAGE + ERROR_STRING);
    }

}
