package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author heekit73098
public class MissingNumberException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_MISSING_NUMBER;
    private static final String ERROR_STRING_MODULAR_CREDITS = StringConstants.ERROR_MISSING_MODULAR_CREDIT;

    public MissingNumberException() {
        super(ERROR_MESSAGE + ERROR_STRING_MODULAR_CREDITS);
    }

    public MissingNumberException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, error));
    }

}
