package seedu.duke.exceptions;

import seedu.duke.HotelLiteManagerException;

public class InvalidHousekeeperProfileException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command -> Add Housekeeper NAME / INT AGE";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
