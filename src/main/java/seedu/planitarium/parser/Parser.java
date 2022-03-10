package seedu.planitarium.parser;

import seedu.planitarium.person.PersonList;

public class Parser {
    public static final String DELIMITER_SPACE = " ";
    public static final String DELIMITER_NAME = "/n";
    public static final String DELIMITER_USER_INDEX = "/u";
    public static final String DELIMITER_INCOME = "/i";
    public static final String DELIMITER_EXPENDITURE = "/e";
    public static final String DELIMITER_BACK = "/[unie]";
    public static final String DELIMITER_MONEY = ".";

    public static final int INDEX_KEYWORD = 0;
    public static final int INDEX_LEFT_NOT_EXIST = 0;
    public static final int INDEX_LEFT_REMOVED = 1;
    public static final int INDEX_RIGHT_REMOVED = 0;
    public static final int LIMIT_TWO_TOKENS = 2;
    public static final int LIMIT_TWO_DECIMAL = 2;
    public static final int MIN_USER_INDEX = 0;
    public static final float MONEY_ZERO = 0.0f;

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
     * Returns without exception if text is a valid float.
     *
     * @param amount Text to be checked.
     * @throws NumberFormatException if format of text is not a valid float, negative or more than 2 decimal places.
     */
    public static void isValidMoney(String amount) throws NumberFormatException {
        float checkMoney = Float.parseFloat(amount);
        if (Float.compare(checkMoney, MONEY_ZERO) < 0) {
            throw new NumberFormatException();
        }
        if (amount.contains(DELIMITER_MONEY)) {
            String decimalPlace = parseDelimitedTerm(amount, DELIMITER_MONEY, DELIMITER_BACK);
            if (decimalPlace.length() > LIMIT_TWO_DECIMAL) {
                throw new NumberFormatException();
            }
        }
    }

    /**
     * Returns without exception if user index is within membership quantity bounds.
     *
     * @param userIndex Person's user index.
     * @throws NullPointerException if amount is not a valid integer.
     * @throws IndexOutOfBoundsException if provided index is out of bounds.
     */
    public static void isValidUserIndex(String userIndex) throws NumberFormatException, IndexOutOfBoundsException {
        int checkIndex = Integer.parseInt(userIndex);
        if (checkIndex < MIN_USER_INDEX || checkIndex >= PersonList.getNumberOfMembers()) {
            throw new IndexOutOfBoundsException();
        }
    }
}
