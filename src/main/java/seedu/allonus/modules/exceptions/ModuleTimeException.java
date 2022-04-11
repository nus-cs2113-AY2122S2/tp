package seedu.allonus.modules.exceptions;

/**
 * Exception to signify missing module time.
 */
public class ModuleTimeException extends Exception {
    public ModuleTimeException(String message) {
        super(message);
    }
}
