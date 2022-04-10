package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author heekit73098
/**
 * Exception to be thrown when the user's input does not match the command format.
 */
public class InvalidCompulsoryParameterException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_PARSE_INVALID_PARAM;
    private static final String ERROR_STRING_GENERAL = StringConstants.ERROR_PARSE_INVALID_PARAM_GENERAL;

    public InvalidCompulsoryParameterException() {
        super(ERROR_MESSAGE + ERROR_STRING_GENERAL);
    }

    public InvalidCompulsoryParameterException(String parameter, String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, parameter, error));
    }
}
