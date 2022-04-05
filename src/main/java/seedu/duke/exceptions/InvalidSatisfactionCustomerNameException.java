package seedu.duke.exceptions;

public class InvalidSatisfactionCustomerNameException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Customer's name must only contain alphabetical characters.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
