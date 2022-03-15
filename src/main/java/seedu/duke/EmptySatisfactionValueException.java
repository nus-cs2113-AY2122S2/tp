package seedu.duke;

public class EmptySatisfactionValueException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Satisfaction value cannot be empty.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
