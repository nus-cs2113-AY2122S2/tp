package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author Yzkkk
public class InvalidModuleGradeException extends GeneralParseException {
    private static final String ERROR_STRING_INVALID = StringConstants.ERROR_INVALID_MODULE_GRADE;
    private static final String ERROR_STRING_MISSING = StringConstants.ERROR_MISSING_MODULE_GRADE;

    //@@author heekit73098
    public InvalidModuleGradeException() {
        super(ERROR_MESSAGE + ERROR_STRING_MISSING);
    }

    public InvalidModuleGradeException(String error) {
        super(ERROR_MESSAGE + String.format(ERROR_STRING_INVALID, error));
    }

}
