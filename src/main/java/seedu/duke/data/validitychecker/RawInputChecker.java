package seedu.duke.data.validitychecker;

/**
 * Checks the validity of a raw input by the user.
 */
public interface RawInputChecker {

    /**
     * Checks input validity.
     *
     * @param line Line to check.
     * @return If input is valid.
     */
    boolean isValid(String line);
}