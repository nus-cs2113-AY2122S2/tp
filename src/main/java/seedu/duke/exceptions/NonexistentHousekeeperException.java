package seedu.duke.exceptions;

import seedu.duke.exceptions.HotelLiteManagerException;

/**
 * An exception for the "add housekeeper performance [housekeeperName] [performanceRating]" command.
 * This exception is thrown if the user attempts to record a housekeeper performance for a housekeeper
 * that does not exist in the HousekeeperList records.
 */

public class NonexistentHousekeeperException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! You are attempting to record a houseekeeper performance"
            + " for a housekeeper that does not exist in the housekeeper records.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
