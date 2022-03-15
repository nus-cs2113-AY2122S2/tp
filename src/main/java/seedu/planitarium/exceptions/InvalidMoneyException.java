package seedu.planitarium.exceptions;

/**
 * Thrown to indicate that a string cannot be converted into a valid monetary value in double.
 */
public class InvalidMoneyException extends Exception {
    protected static final String ERROR_MSG = "Invalid money value: `%s`";

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param text the invalid money text.
     */
    public InvalidMoneyException(String text) {
        super(String.format(ERROR_MSG, text));
    }
}
