package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

import static seedu.duke.util.StringConstants.SUGGESTION_UNKNOWN_CONFIGURATION_GROUP;

public class UnkownConfigurationGroupWord extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_UNKNOWN_CONFIGURATION_GROUP;

    public UnkownConfigurationGroupWord() {
        super(ERROR_MESSAGE);
    }

    public UnkownConfigurationGroupWord(String userInput) {
        super(String.format("%s\n\"%s\"\n%s", ERROR_MESSAGE, userInput, SUGGESTION_UNKNOWN_CONFIGURATION_GROUP));
    }
}
