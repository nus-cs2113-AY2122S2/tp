package seedu.duke.exceptions;

import seedu.duke.exceptions.HotelLiteManagerException;

/**
 * An exception for the "add housekeeper performance [housekeeperName] [performanceRating]" command.
 * This exception is thrown if the user attempts to add a HousekeeperPerformance object that corresponds to
 * a name which has already been stored in the HousekeeperPerformanceList.
 */

public class RepeatHousekeeperPerformanceNameException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The performance rating for this housekeeper"
            + " has already been recorded.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
