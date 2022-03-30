package seedu.duke;

public class InvalidUpdateItemPaxCommandException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command -> Update Item Pax ITEM NAME / NEW PAX";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
