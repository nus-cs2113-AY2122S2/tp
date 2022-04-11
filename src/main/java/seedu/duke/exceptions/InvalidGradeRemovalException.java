package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author heekit73098
/**
 * Exception to be thrown when user wants to remove non-existing grade of a module.
 */
public class InvalidGradeRemovalException extends ModHappyException {
    public static final String ERROR_MESSAGE = StringConstants.ERROR_GRADE_REMOVAL_FAILED;

    public InvalidGradeRemovalException() {
        super(ERROR_MESSAGE);
    }
}