package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author Yzkkk
/**
 * Exception to be thrown when the user-supplied parameter is empty.
 */
public class EmptyParamException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_EMPTY_PARAM;

    public EmptyParamException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, error));
    }

}
