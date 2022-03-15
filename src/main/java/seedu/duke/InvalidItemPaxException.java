package seedu.duke;

public class InvalidItemPaxException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Item Pax is invalid";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
