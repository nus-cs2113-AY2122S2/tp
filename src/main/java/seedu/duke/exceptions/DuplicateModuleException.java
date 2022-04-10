package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author chooyikai
/**
 * Exception to be thrown when duplicated modules are detected.
 */
public class DuplicateModuleException extends ModHappyException {
    public static final String ERROR_MESSAGE = StringConstants.ERROR_DUPLICATE_MODULE;

    public DuplicateModuleException(String moduleCode) {
        super(String.format(ERROR_MESSAGE, moduleCode));
    }
}
