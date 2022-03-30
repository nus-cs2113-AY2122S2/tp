package seedu.duke;

public class InvalidUpdateItemNameCommandException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command -> Update Item Name OLD NAME / NEW NAME";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
