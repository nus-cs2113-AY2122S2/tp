package seedu.duke.exceptions;

public class UnkownCommandException extends ModHappyException {
    private static final String ERROR_MESSAGE = "Sorry, I don't understand the following command(,,•́ . •̀,,) :";

    public UnkownCommandException() {
        super(ERROR_MESSAGE);
    }

    public UnkownCommandException(String userInput) {
        super(String.format("%s\n\"%s\"", ERROR_MESSAGE, userInput));
    }
}
