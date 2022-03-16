package seedu.duke;

/**
 * An exception for the "Add Satisfaction [customerName] [satisfactionValue] command.
 * This exception is thrown if the satisfaction value is invalid (i.e. is not an integer
 * between 1 and 5).
 */

public class InvalidSatisfactionValueException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Satisfaction value must be an integer "
            + "between 1 and 5, inclusive.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}
