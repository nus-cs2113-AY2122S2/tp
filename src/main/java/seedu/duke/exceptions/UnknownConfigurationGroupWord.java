package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

public class UnknownConfigurationGroupWord extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_UNKNOWN_CONFIGURATION_GROUP;

    public UnknownConfigurationGroupWord(String userInput) {
        super(String.format(ERROR_MESSAGE, userInput));
    }
}
