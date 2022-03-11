package seedu.duke.exceptions;

/**
 * Signals that the user does not key in input according to command format.
 */

public class IllegalCommandException extends Exception {
    public IllegalCommandException(String s) {
        super(s);
    }
}
