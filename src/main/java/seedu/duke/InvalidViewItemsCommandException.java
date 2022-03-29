package seedu.duke;

public class InvalidViewItemsCommandException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command -> view all items";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
