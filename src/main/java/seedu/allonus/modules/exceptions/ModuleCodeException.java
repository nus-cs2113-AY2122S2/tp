package seedu.allonus.modules.exceptions;

/**
 * Signifies a missing module code.
 */
public class ModuleCodeException extends Exception {
    public ModuleCodeException(String message) {
        super(message);
    }
}
