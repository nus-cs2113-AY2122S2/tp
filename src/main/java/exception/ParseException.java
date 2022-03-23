package exception;

/**
 * Signals that the user input could not be parsed.
 */
public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }
}
