package seedu.duke;

public class RepeatHousekeeperPerformanceNameException extends HotelLiteManagerException{
    private static final String ERROR_MESSAGE = "Error! The performance rating for this housekeeper "
            + " has already been recorded.";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
