package seedu.duke.exceptions;

public class NewItemPaxSameAsCurrentPaxException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The new item pax is the same as the current item pax.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}