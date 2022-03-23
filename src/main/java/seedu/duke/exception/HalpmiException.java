package seedu.duke.exception;

public class HalpmiException extends Exception{
    public HalpmiException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
