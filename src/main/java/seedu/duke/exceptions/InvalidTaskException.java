package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author chooyikai
public class InvalidTaskException extends ModHappyException {
    public static final String ERROR_MESSAGE = StringConstants.ERROR_INVALID_TASK_DATA;

    public InvalidTaskException() {
        super(ERROR_MESSAGE);
    }
}