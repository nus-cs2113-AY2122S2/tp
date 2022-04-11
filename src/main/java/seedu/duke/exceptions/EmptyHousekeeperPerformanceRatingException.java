package seedu.duke.exceptions;

public class EmptyHousekeeperPerformanceRatingException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Housekeeper performance cannot be empty.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
