package seedu.duke.exceptions;

import seedu.duke.ui.TextUi;

public class UnknownCommandException extends ModHappyException {
    private static final String ERROR_MESSAGE = TextUi.ERROR_UNKNOWN_COMMAND + LS + "\"%s\"";

    public UnknownCommandException() {
        super(ERROR_MESSAGE);
    }

    public UnknownCommandException(String userInput) {
        super(String.format(ERROR_MESSAGE, userInput));
    }
}
