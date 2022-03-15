package seedu.duke;

public class EmptyItemPaxException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Item Pax cannot be empty.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
