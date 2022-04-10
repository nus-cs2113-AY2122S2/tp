package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author heekit73098
public class UnsupportedResultTypeException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_UNSUPPORTED_RESULT_TYPE;

    public UnsupportedResultTypeException(String newValue, String configurationGroupWord) {
        super(String.format(ERROR_MESSAGE, newValue, configurationGroupWord));
    }
}
