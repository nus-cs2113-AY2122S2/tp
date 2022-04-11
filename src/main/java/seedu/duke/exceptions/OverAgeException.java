package seedu.duke.exceptions;

import seedu.duke.exceptions.HotelLiteManagerException;

public class OverAgeException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Unable to accept housekeeper age 61 and above.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}