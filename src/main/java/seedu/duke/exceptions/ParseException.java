package seedu.duke.exceptions;

public class ParseException extends ModHappyException {
    private static final String ERROR_MESSAGE = "This parse failed 0_0";

    public ParseException() {
        super(ERROR_MESSAGE);
    }
}
