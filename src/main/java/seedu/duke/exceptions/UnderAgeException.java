package seedu.duke.exceptions;

import seedu.duke.exceptions.HotelLiteManagerException;

public class UnderAgeException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The user is underage(<21) and not allowed to work.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}