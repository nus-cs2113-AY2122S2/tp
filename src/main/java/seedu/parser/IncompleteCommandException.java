package seedu.parser;

/**
 * Signal that given data does not include necessary arguments
 * Adapted from: https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/data/exception/
 */
public class IncompleteCommandException extends Exception {
    /**
     * @param message contains information on failed constraints
     */
    public IncompleteCommandException(String message) {
        super(message);
    }
}
