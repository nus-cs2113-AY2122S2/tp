package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class ParseException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_PARSE_FAILED;

    public ParseException() {
        super(ERROR_MESSAGE);
    }
}
