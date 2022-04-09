package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author chooyikai
public class InvalidModuleCreditsException extends ModHappyException {
    public static final String ERROR_MESSAGE = StringConstants.ERROR_INVALID_MODULE_CREDITS;

    public InvalidModuleCreditsException(String moduleCode, int modularCredits) {
        super(String.format(ERROR_MESSAGE, moduleCode, modularCredits));
    }
}