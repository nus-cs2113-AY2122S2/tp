package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class DuplicateModuleException extends ModHappyException {
    public static final String ERROR_MESSAGE = StringConstants.ERROR_DUPLICATE_MODULE;

    public DuplicateModuleException() {
        super(ERROR_MESSAGE);
    }
}
