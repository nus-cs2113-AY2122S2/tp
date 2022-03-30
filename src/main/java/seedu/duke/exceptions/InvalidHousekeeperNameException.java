package seedu.duke.exceptions;

import seedu.duke.exceptions.HotelLiteManagerException;

public class InvalidHousekeeperNameException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! A housekeeper by this name does not exist in the system.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}