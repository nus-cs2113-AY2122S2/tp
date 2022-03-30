package seedu.duke.exceptions;

import seedu.duke.exceptions.HotelLiteManagerException;

public class NameNotStringException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Name entered has digits and symbol.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
