package seedu.duke.exceptions;

import seedu.duke.util.StringConstants;

//@@author Ch40gRv1-Mu
public class WrongDurationFormatException extends ModHappyException {
    private static final String ERROR_MESSAGE = StringConstants.ERROR_WRONG_DURATION_FORMAT;

    public WrongDurationFormatException() {
        super(ERROR_MESSAGE);
    }


}
