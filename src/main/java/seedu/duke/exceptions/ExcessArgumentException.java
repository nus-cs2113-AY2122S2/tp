package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author Yzkkk
/**
 * Exception to be thrown when the user supplied an excess argument.
 */
public class ExcessArgumentException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_EXCESS_ARGUMENT;

    public ExcessArgumentException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, error));
    }

}
