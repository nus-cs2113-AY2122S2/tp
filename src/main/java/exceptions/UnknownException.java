package exceptions;

public class UnknownException extends Exception {
    protected static final String ERROR_MSG = "Sorry, please check your input again";

    @Override
    public String toString() {
        return ERROR_MSG;
    }
}
