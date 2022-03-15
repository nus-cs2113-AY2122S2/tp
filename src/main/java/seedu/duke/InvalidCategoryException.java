package seedu.duke;

public class InvalidCategoryException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Room category is invalid";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}
