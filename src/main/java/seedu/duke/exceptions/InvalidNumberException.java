package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author heekit73098
public class InvalidNumberException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_INVALID_NUMBER;
    private static final String ERROR_STRING_MODULAR_CREDIT = StringConstants.ERROR_INVALID_MODULAR_CREDIT;

    public InvalidNumberException(String parameter, String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, parameter, error));
    }

    /**
     * Thrown when the modular credit is invalid.
     * @param error the invalid modular credit string
     */
    public InvalidNumberException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING_MODULAR_CREDIT, error));
    }

}
