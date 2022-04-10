package seedu.duke.exceptions;

import seedu.duke.util.Configuration;
import seedu.duke.util.StringConstants;

//@@author chooyikai
/**
 * Exception to be thrown when invalid configuration value is detected.
 */
public class InvalidConfigurationValueException extends ModHappyException {
    public static final String ERROR_MESSAGE = StringConstants.ERROR_INVALID_CONFIG_VALUE;

    public InvalidConfigurationValueException(Configuration.ConfigurationGroup group, String value) {
        super(String.format(ERROR_MESSAGE, group, value));
    }
}
