package seedu.duke.exceptions;

import seedu.duke.ui.TextUi;

public class NoSuchModuleException extends ModHappyException {
    private static final String ERROR_MESSAGE = TextUi.ERROR_NO_SUCH_MODULE;

    public NoSuchModuleException() {
        super(ERROR_MESSAGE);
    }
}