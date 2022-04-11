package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author Ch40gRv1-Mu
/**
 * Exception to be thrown when configuration group entered by the user is not recognised.
 */
public class UnknownConfigurationGroupWordException extends ModHappyException {

    private static final String ERROR_MESSAGE = StringConstants.ERROR_UNKNOWN_CONFIGURATION_GROUP;

    public UnknownConfigurationGroupWordException(String userInput) {
        super(String.format(ERROR_MESSAGE, userInput));
    }
}
