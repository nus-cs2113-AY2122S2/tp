package seedu.duke;

/**
 * An exception for the "Add Satisfaction [customerName] [satisfactionValue] command.
 * This exception is thrown if the satisfaction value is not provided.
 */

public class EmptySatisfactionValueException extends HotelLiteManagerException {

    private static final String ERROR_MESSAGE = "Error! Satisfaction value cannot be empty."
            + "Also ensure that there is a '/' separating the customer name from the satisfaction rating.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
