package seedu.duke;

/**
 * Contains common parsing helper methods.
 */

public class ParsingUtils {
    private static final String DELIMITER = "/";

    /**
     * Counts the number of delimiters in a string.
     * @param string The given string.
     * @return The number of slashes in the string.
     */
    public int countSlashes(String string) {
        int slashCount = 0;
        for (int i = 0; i < string.length(); i++) {
            String curChar = Character.toString(string.charAt(i));
            if (curChar.equals(DELIMITER)) {
                slashCount += 1;
            }
        }
        return slashCount;
    }

    /**
     * Checks if a string contains only alphabetical characters.
     * Useful for checking the validity of names.
     * @param string The given string.
     * @return A boolean (true if the string contains only alphabetical characters, false otherwise).
     */
    public boolean isAlpha(String string) {
        String stringWithoutSpaces = string.replaceAll("\\s+", "");
        return stringWithoutSpaces.matches("[a-zA-Z]+");
    }
}
