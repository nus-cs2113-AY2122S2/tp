package seedu.duke;

public class InvalidAgeException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! AGE must be integer";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}