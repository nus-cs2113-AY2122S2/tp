package seedu.duke.exceptions;

public class InstantiateAbstractClassException extends ModHappyException {
    private static final String ERROR_MESSAGE = "This class is abstract class and cannot initiate it 0_0";

    public InstantiateAbstractClassException() {
        super(ERROR_MESSAGE);
    }

    public InstantiateAbstractClassException(String className) {
        super(String.format("%s\n\"%s\"", ERROR_MESSAGE, className));
    }
}
