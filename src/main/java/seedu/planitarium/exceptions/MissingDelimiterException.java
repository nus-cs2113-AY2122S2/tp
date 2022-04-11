package seedu.planitarium.exceptions;

/**
 * Thrown to indicate that a string is missing a given delimiter (character sequence).
 */
public class MissingDelimiterException extends PlanITariumException {
    protected static final String ERROR_MSG = "Missing delimiter `%s`";

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param delimiter the missing delimiter.
     */
    public MissingDelimiterException(String delimiter) {
        super(String.format(ERROR_MSG,delimiter));
    }
}
