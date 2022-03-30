package seedu.duke;

import seedu.duke.command.Command;

public class InvalidViewItemsWithZeroPaxCommandException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command -> view items with zero pax";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
