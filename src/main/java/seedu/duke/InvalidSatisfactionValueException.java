package seedu.duke;

public class InvalidSatisfactionValueException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Satisfaction value must be between 1 and 5, inclusive.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}
