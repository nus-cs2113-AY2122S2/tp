package arcs.data.validitychecker;

public class MinimumInputLengthChecker implements RawInputChecker {
    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If true if input is yes or no
     */
    @Override
    public boolean isValid(String line) {
        if (line.length() >= 5) {
            return true;
        } else {
            return false;
        }
    }
}
