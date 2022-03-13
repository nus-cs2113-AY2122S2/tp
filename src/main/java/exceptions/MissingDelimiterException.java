package exceptions;

public class MissingDelimiterException extends Exception {
    protected static final String ERROR_MSG = "Missing delimiter `%s`";

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param delimiter the missing delimiter.
     */
    public MissingDelimiterException(String delimiter) {
        super(String.format(ERROR_MSG, delimiter));
    }
}
