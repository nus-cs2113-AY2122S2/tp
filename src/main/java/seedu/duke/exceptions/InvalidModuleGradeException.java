package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class InvalidModuleGradeException extends GeneralParseException {
    private static final String ERROR_STRING = StringConstants.ERROR_INVALID_MODULE_GRADE;

    public InvalidModuleGradeException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING, error));
    }

}
