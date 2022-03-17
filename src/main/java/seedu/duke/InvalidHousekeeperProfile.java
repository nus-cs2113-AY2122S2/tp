package seedu.duke;

public class InvalidHousekeeperProfile extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command -> Availability NAME @ INT AGE";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
