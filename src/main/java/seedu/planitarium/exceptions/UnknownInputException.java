package seedu.planitarium.exceptions;

public class UnknownInputException extends PlanITariumException {
    protected static final String ERROR_MSG = "Unknown instruction is detected. Error is detected from '%s'. "
            + "Please check your input again.";
    protected String error;

    public UnknownInputException(String errorInfo) {
        super(String.format(ERROR_MSG, errorInfo));
    }
}
