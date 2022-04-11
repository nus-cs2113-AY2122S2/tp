package seedu.planitarium.exceptions;

public class PlanITariumException extends Exception {
    protected static final String ERROR_MSG = "Unknown error is detected from '%s', please check again.";
    protected String error;

    public PlanITariumException(String errorInfo) {
        error = String.format(ERROR_MSG, errorInfo);
    }

    @Override
    public String toString() {
        return error;
    }
}
