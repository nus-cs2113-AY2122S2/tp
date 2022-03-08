package seedu.duke.util;

public class Parser {
    private static final String DELIMITER_SPACE = " ";
    private static final String DELIMITER_NAME = "/n";
    private static final String DELIMITER_USER_INDEX = "/u";
    private static final String DELIMITER_INCOME = "/i";
    private static final String DELIMITER_EXPENDITURE = "/e";
    private static final String DELIMITER_BACK = "/";

    /**
     * Returns the term surrounded by two delimiters.
     *
     * @param text          The text containing the term to be parsed.
     * @param delimiter_one The delimiter on the left of term.
     * @param delimiter_two The delimiter on the right of term.
     * @return A non-delimiter-surrounded term.
     */
    public static String parseDelimitedTerm(String text, String delimiter_one, String delimiter_two) {
        return null;
    }

    /**
     * Returns command keyword from userInput.
     *
     * @param userInput The user's full input text.
     * @return The command keyword issued.
     */
    public static String parseKeyword(String userInput) {
        return null;
    }

    /**
     * Returns person's name from userInput.
     *
     * @param userInput The user's full input text.
     * @return Person's name.
     */
    public static String parseName(String userInput) {
        return null;
    }

    /**
     * Returns user index from userInput.
     *
     * @param userInput The user's full input text.
     * @return Person's user index.
     */
    public static String parseUserIndex(String userInput) {
        return null;
    }

    /**
     * Returns income from userInput.
     *
     * @param userInput The user's full input text.
     * @return Person's added income.
     */
    public static String parseIncome(String userInput) {
        return null;
    }

    /**
     * Returns expenditure from userInput.
     *
     * @param userInput The user's full input text.
     * @return Person's expenditure amount.
     */
    public static String parseExpenditure(String userInput) {
        return null;
    }

    /**
     * Returns true if text is a valid float.
     *
     * @param amount Text to be checked.
     * @return The float validity.
     */
    public static boolean isValidMoney(String amount) {
        return false;
    }

    /**
     * Returns true if user index is within bounds of Person's quantity.
     *
     * @param userIndex Person's user index.
     * @return The bound's validity of the user index.
     */
    public static boolean isValidUserIndex(String userIndex) {
        return false;
    }
}
