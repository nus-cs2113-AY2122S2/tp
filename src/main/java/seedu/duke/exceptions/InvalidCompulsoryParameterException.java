package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class InvalidCompulsoryParameterException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_PARSE_INVALID_PARAM;
    private static final String COMPULSORY_PARAMETERS = StringConstants.COMPULSORY_PARAMETERS;

    public InvalidCompulsoryParameterException() {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, COMPULSORY_PARAMETERS));
    }

    public InvalidCompulsoryParameterException(String parameter) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, parameter));
    }
}
