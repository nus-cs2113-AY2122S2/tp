package seedu.duke.exceptions;

import seedu.duke.exceptions.HotelLiteManagerException;

public class NameNotStringException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Name entered has digit(s) and/or symbol(s).";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
