package seedu.duke;

/**
 * Exception for the "Add Satisfaction [customerName] [satisfactionValue]" command.
 * This exception is thrown if a Satisfaction instance with the given customer name
 * already exists. Ensures that the same customer cannot have multiple satisfaction
 * values attributed to it.
 */

public class RepeatCustomerException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The given customer's satisfaction value has "
            + "already been recorded.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
