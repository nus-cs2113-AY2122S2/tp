package arcs.data.validitychecker;

/**
 * Check if user entered yes or no.
 */
public class YesNoChecker implements RawInputChecker {

    /**
     * Check input validity.
     *
     * @param line Line to check.
     * @return If true if input is yes or no
     */
    @Override
    public boolean isValid(String line) {
        if (line.equalsIgnoreCase("YES")) {
            return true;
        }
        else if (line.equalsIgnoreCase("NO")) {
            return true;
        }
        else {
            return false;
        }
    }
}