package seedu.planitarium.exceptions;

/**
 * Thrown to indicate that a string has too many of a given delimiter (character sequence).
 */
public class DuplicateDelimiterException extends Exception {
    protected static final String ERROR_MSG = "Too many delimiter `%s`";

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param delimiter the missing delimiter.
     */
    public DuplicateDelimiterException(String delimiter) {
        super(String.format(ERROR_MSG, delimiter));
    }
}
