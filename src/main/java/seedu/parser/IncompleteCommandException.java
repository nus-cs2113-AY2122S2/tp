package seedu.parser;

public class IncompleteCommandException extends Exception {
    public static final String NO_PARAMETERS_FOUND = "No parameters found!";

    public IncompleteCommandException(String message) {
        super(message);
    }
}
