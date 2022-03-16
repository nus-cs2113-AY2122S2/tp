package seedu.duke;

/**
 * An exception for the "Add Satisfaction [customerName] [satisfactionValue] command.
 * This exception is thrown if the customer name is not provided.
 */

public class EmptySatisfactionCustomerException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Customer name cannot be empty.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
