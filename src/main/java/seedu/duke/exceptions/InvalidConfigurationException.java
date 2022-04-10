package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author chooyikai
/**
 * Exception to be thrown when invalid configuration is found in loaded configuration data.
 */
public class InvalidConfigurationException extends ModHappyException {
    public static final String ERROR_MESSAGE = StringConstants.ERROR_INVALID_CONFIGURATION;

    public InvalidConfigurationException() {
        super(ERROR_MESSAGE);
    }
}
