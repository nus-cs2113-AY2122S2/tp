package seedu.duke;

public class InvalidLevelException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Category is invalid";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
