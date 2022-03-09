package seedu.planitarium.parser;

public class Parser {
    public static final String DELIMITER_SPACE = " ";
    public static final String DELIMITER_NAME = "/n";
    public static final String DELIMITER_USER_INDEX = "/u";
    public static final String DELIMITER_INCOME = "/i";
    public static final String DELIMITER_EXPENDITURE = "/e";
    public static final String DELIMITER_BACK = "/[unie]";

    public static final int INDEX_KEYWORD = 0;
    public static final int INDEX_LEFT_NOT_EXIST = 0;
    public static final int INDEX_LEFT_REMOVED = 1;
    public static final int INDEX_RIGHT_REMOVED = 0;
    public static final int LIMIT_TWO_TOKENS = 2;

    /**
     * Returns the term surrounded by two delimiters.
     *
     * @param text          The text containing the term to be parsed.
     * @param delimiter_left The delimiter on the left of term.
     * @param delimiter_right The delimiter on the right of term.
     * @return A non-delimiter-surrounded term.
     */
    public static String parseDelimitedTerm(String text, String delimiter_left, String delimiter_right) {
        String[] firstParse = text.split(delimiter_left, LIMIT_TWO_TOKENS);
        String leftRemoved;
        if (firstParse.length == LIMIT_TWO_TOKENS) {
            leftRemoved = firstParse[INDEX_LEFT_REMOVED];
        } else {
            leftRemoved = firstParse[INDEX_LEFT_NOT_EXIST];
        }
        String[] secondParse = leftRemoved.split(delimiter_right, LIMIT_TWO_TOKENS);
        String rightRemoved = secondParse[INDEX_RIGHT_REMOVED];
        return rightRemoved.trim();
    }

    /**
     * Returns command keyword from userInput.
     *
     * @param userInput The user's full input text.
     * @return The command keyword issued.
     */
    public static String parseKeyword(String userInput) {
        String keyword = userInput.split(DELIMITER_SPACE)[INDEX_KEYWORD];
        return keyword.trim();
    }

    /**
     * Returns person's name from userInput.
     *
     * @param userInput The user's full input text.
     * @return Person's name.
     */
    public static String parseName(String userInput) {
        return parseDelimitedTerm(userInput, DELIMITER_NAME, DELIMITER_BACK);
    }

    /**
     * Returns user index from userInput.
     *
     * @param userInput The user's full input text.
     * @return Person's user index.
     */
    public static String parseUserIndex(String userInput) {
        return parseDelimitedTerm(userInput, DELIMITER_USER_INDEX, DELIMITER_BACK);
    }

    /**
     * Returns income from userInput.
     *
     * @param userInput The user's full input text.
     * @return Person's added income.
     */
    public static String parseIncome(String userInput) {
        return parseDelimitedTerm(userInput, DELIMITER_INCOME, DELIMITER_BACK);
    }

    /**
     * Returns expenditure from userInput.
     *
     * @param userInput The user's full input text.
     * @return Person's expenditure amount.
     */
    public static String parseExpenditure(String userInput) {
        return parseDelimitedTerm(userInput, DELIMITER_EXPENDITURE, DELIMITER_BACK);
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
