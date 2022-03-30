package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class UnknownConfigurationGroupWordException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_UNKNOWN_CONFIGURATION_GROUP;

    public UnknownConfigurationGroupWordException(String userInput) {
        super(String.format(ERROR_MESSAGE, userInput));
    }
}
