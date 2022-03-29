package seedu.duke;

public class InvalidAssignmentException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Please add assognmentt with format: assign NAME / ROOMID";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}