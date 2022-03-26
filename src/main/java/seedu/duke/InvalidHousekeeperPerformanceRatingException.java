package seedu.duke;

public class InvalidHousekeeperPerformanceRatingException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Housekeeper performance rating must be an integer between "
            + "1 and 5, inclusive.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
