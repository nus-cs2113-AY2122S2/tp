package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author heekit73098
/**
 * Exception to be thrown when the user inputted additional parameter for command
 * that only takes in a single command word.
 */
public class AdditionalParameterException extends GeneralParseException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_ADDITIONAL_PARAMETER;

    public AdditionalParameterException() {
        super(ERROR_MESSAGE);
    }

}
