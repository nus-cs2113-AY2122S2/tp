package seedu.duke.exceptions;

import seedu.duke.exceptions.HotelLiteManagerException;

/**
 * An exception that is throw when user input a extra words after "is a new week" command.
 */
public class InvalidNewWeekException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command -> is a new week.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}