package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class InvalidModuleException extends ModHappyException {
    public static final String ERROR_MESSAGE = StringConstants.ERROR_INVALID_MODULE;

    public InvalidModuleException(String moduleCode, int modularCredits) {
        super(String.format(ERROR_MESSAGE, moduleCode, modularCredits));
    }
}