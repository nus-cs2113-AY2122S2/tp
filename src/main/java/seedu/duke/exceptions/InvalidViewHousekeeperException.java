package seedu.duke.exceptions;

import seedu.duke.exceptions.HotelLiteManagerException;

public class InvalidViewHousekeeperException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command -> view recorded housekeeper.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
