package seedu.duke.exceptions;

import seedu.duke.ui.TextUi;

public class NoSuchTaskException extends ModHappyException {
    private static final String ERROR_MESSAGE = TextUi.ERROR_NO_SUCH_TASK;

    public NoSuchTaskException() {
        super(ERROR_MESSAGE);
    }
}
