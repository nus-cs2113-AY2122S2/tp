package seedu.planitarium.exceptions;

/**
 * Thrown to indicate that a string after a delimiter is empty.
 */
public class EmptyStringException extends Exception {
    protected static final String ERROR_MSG = "Empty string after `%s` detected";

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param delimiter the delimiter where string is empty.
     */
    public EmptyStringException(String delimiter) {
        super(String.format(ERROR_MSG, delimiter));
    }
}
