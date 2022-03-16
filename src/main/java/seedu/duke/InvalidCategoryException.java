package seedu.duke;

/**
 * An exception to check room type.
 * This exception is thrown if the room type is beyond
 * {single, double, triple, queen, twin, king}.
 */
public class InvalidCategoryException extends HotelLiteManagerException {
    private static final String ERROR_MESSAGE = "Error! Room category is invalid";

    @Override
    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }

}
