package seedu.duke.exceptions;

import seedu.duke.ui.TextUi;

public class ParseException extends ModHappyException {
    private static final String ERROR_MESSAGE = TextUi.ERROR_PARSE_FAILED;

    public ParseException() {
        super(ERROR_MESSAGE);
    }
}
