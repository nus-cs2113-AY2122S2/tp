package seedu.duke.exceptions;

public class FailInitializationClassException extends ModHappyException{
    private static final String ERROR_MESSAGE = "This class fail to be initialized 0_0";
    public FailInitializationClassException() {
        super(ERROR_MESSAGE);
    }

    public FailInitializationClassException(String className) {
        super(String.format("%s\n\"%s\"",ERROR_MESSAGE,className));
    }
}
