package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author heekit73098
public class GeneralParseException extends ModHappyException {
    protected static final String ERROR_MESSAGE = StringConstants.ERROR_PARSE_FAILED;

    public GeneralParseException() {
        super(ERROR_MESSAGE);
    }

    public GeneralParseException(String errorMessage) {
        super(errorMessage);
    }
}
