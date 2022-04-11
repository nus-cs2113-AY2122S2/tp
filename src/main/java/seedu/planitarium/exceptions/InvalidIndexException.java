package seedu.planitarium.exceptions;

/**
 * Thrown to indicate that a string cannot be converted into a valid integer index or index is out of bounds.
 */
public class InvalidIndexException extends PlanITariumException {
    protected static final String ERROR_MSG = "Invalid %s index `%s`";

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param type the type of index
     * @param index the invalid index
     */
    public InvalidIndexException(String type, String index) {
        super(String.format(ERROR_MSG, type, index));
    }
}
