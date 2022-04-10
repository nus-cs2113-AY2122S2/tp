package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author heekit73098
public class InvalidGradeRemovalException extends ModHappyException {
    public static final String ERROR_MESSAGE = StringConstants.ERROR_GRADE_REMOVAL;

    public InvalidGradeRemovalException() {
        super(ERROR_MESSAGE);
    }
}