package seedu.duke;

public class EmptyItemNameException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Item Name cannot be empty.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
