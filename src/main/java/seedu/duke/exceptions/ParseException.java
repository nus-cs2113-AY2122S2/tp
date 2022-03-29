package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

/**
 * Exception to be thrown when parsing of user's input failed.
 */
public class ParseException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_PARSE_FAILED;

    public ParseException() {
        super(ERROR_MESSAGE);
    }
}
