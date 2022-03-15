package seedu.duke;

public class EmptySatisfactionCustomerException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Customer name cannot be empty.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
