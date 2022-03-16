package seedu.duke;

public class InvalidHousekeeperProfile extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Invalid Command -> Availability NAME @ M,T,W,T,F,S,S";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
