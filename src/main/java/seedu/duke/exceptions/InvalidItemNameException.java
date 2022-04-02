package seedu.duke.exceptions;

public class InvalidItemNameException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! The item name is invalid. Item Names can only contain "
            + "alphabetical characters, numbers, blank spaces or apostrophes.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
