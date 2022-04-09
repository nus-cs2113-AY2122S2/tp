package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author chooyikai
public class InvalidConfigurationException extends ModHappyException {
    public static final String ERROR_MESSAGE = StringConstants.ERROR_INVALID_CONFIGURATION;

    public InvalidConfigurationException() {
        super(ERROR_MESSAGE);
    }
}
