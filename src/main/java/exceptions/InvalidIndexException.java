package exceptions;

public class InvalidIndexException extends Exception {
    protected static final String ERROR_MSG = "Invalid index: `%s`";

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param text the invalid index text.
     */
    public InvalidIndexException(String text) {
        super(String.format(ERROR_MSG, text));
    }
}
