package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class NoSuchTaskException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_NO_SUCH_TASK;

    public NoSuchTaskException() {
        super(ERROR_MESSAGE);
    }
}
