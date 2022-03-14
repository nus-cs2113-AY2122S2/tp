package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class NoSuchModuleException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_NO_SUCH_MODULE;

    public NoSuchModuleException() {
        super(ERROR_MESSAGE);
    }
}