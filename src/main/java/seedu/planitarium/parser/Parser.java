package seedu.planitarium.parser;

import seedu.planitarium.person.Person;
import seedu.planitarium.person.PersonList;

public class Parser {
    public static final String DELIMITER_SPACE = " ";
    public static final String DELIMITER_NAME = "/n";
    public static final String DELIMITER_USER_INDEX = "/u";
    public static final String DELIMITER_DESCRIPTION = "/d";
    public static final String DELIMITER_INCOME = "/i";
    public static final String DELIMITER_EXPENDITURE = "/e";
    public static final String DELIMITER_RECORD_INDEX = "/r";
    public static final String DELIMITER_BACK = "/[ruined]";
    public static final String DELIMITER_MONEY = ".";

    public static final int INDEX_KEYWORD = 0;
    public static final int INDEX_LEFT_NOT_EXIST = 0;
    public static final int INDEX_LEFT_REMOVED = 1;
    public static final int INDEX_RIGHT_REMOVED = 0;
    public static final int LIMIT_TWO_TOKENS = 2;
    public static final int LIMIT_TWO_DECIMAL = 2;
    public static final int MIN_USER_INDEX = 1;
    public static final int MIN_EXPENDITURE_INDEX = 1;
    public static final int MIN_INCOME_INDEX = 1;
    public static final double MONEY_ZERO = 0.0;

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
     * Returns command keyword from user input.
     *
     * @param userInput The user's full input text.
     * @return The command keyword issued.
     */
    public static String parseKeyword(String userInput) {
        return userInput.split(DELIMITER_SPACE)[INDEX_KEYWORD].trim();
    }

    /**
     * Returns person's name from user input.
     *
     * @param userInput The user's full input text.
     * @return Person's name.
     */
    public static String parseName(String userInput) {
        return parseDelimitedTerm(userInput, DELIMITER_NAME, DELIMITER_BACK).trim();
    }

    /**
     * Returns user index from user input.
     *
     * @param userInput The user's full input text.
     * @return Person's user index.
     */
    public static String parseUserIndex(String userInput) {
        return parseDelimitedTerm(userInput, DELIMITER_USER_INDEX, DELIMITER_BACK).trim();
    }

    /**
     * Returns description of item from user input.
     *
     * @param userInput The user's full input text.
     * @return An item's description.
     */
    public static String parseDescription(String userInput) {
        return parseDelimitedTerm(userInput, DELIMITER_DESCRIPTION, DELIMITER_BACK).trim();
    }

    /**
     * Returns income from user input.
     *
     * @param userInput The user's full input text.
     * @return Person's added income.
     */
    public static String parseIncome(String userInput) {
        return parseDelimitedTerm(userInput, DELIMITER_INCOME, DELIMITER_BACK).trim();
    }

    /**
     * Returns expenditure from user input.
     *
     * @param userInput The user's full input text.
     * @return Person's expenditure amount.
     */
    public static String parseExpenditure(String userInput) {
        return parseDelimitedTerm(userInput, DELIMITER_EXPENDITURE, DELIMITER_BACK).trim();
    }

    /**
     * Returns a record's index from user input.
     *
     * @param userInput The user's full input text.
     * @return A record's index.
     */
    public static String parseRecordIndex(String userInput) {
        return parseDelimitedTerm(userInput, DELIMITER_RECORD_INDEX, DELIMITER_BACK).trim();
    }

    /**
     * Returns without exception if text is a valid double.
     *
     * @param amount Text to be checked.
     * @throws NumberFormatException if format of text is not a valid double, negative or more than 2 decimal places.
     */
    public static void checkValidMoney(String amount) throws NumberFormatException {
        double checkMoney = Double.parseDouble(amount);
        if (Double.compare(checkMoney, MONEY_ZERO) < 0) {
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
     * @param userIndex  Person's user index.
     * @param personList A list of Persons.
     * @throws NumberFormatException if amount is not a valid integer.
     * @throws IndexOutOfBoundsException if provided index is out of bounds.
     */
    public static void checkValidUserIndex(String userIndex, PersonList personList)
                throws NumberFormatException, IndexOutOfBoundsException {
        int checkIndex = Integer.parseInt(userIndex);
        checkTooHighIndex(checkIndex, personList.getNumberOfMembers());
        checkTooLowIndex(checkIndex, MIN_USER_INDEX);
    }

    /**
     * Returns without exception if expenditure index is within expenditure quantity bounds.
     *
     * @param expenditureIndex Person's expenditure lookup index.
     * @param person           Person who may have expenditures.
     * @throws NumberFormatException if index is not a valid integer.
     * @throws IndexOutOfBoundsException if provided index is out of bounds.
     */
    public static void checkValidExpenditureIndex(String expenditureIndex, Person person)
            throws NumberFormatException, IndexOutOfBoundsException {
        int checkIndex = Integer.parseInt(expenditureIndex);
        checkTooHighIndex(checkIndex, person.getNumberOfExpenditures());
        checkTooLowIndex(checkIndex, MIN_EXPENDITURE_INDEX);
    }

    /**
     * Returns without exception if income index is within income quantity bounds.
     *
     * @param incomeIndex Person's income lookup index.
     * @param person      Person who has income.
     * @throws NumberFormatException if index is not a valid integer.
     * @throws IndexOutOfBoundsException if provided index is out of bounds.
     */
    public static void checkValidIncomeIndex(String incomeIndex, Person person)
            throws NumberFormatException, IndexOutOfBoundsException {
        int checkIndex = Integer.parseInt(incomeIndex);
        checkTooHighIndex(checkIndex, person.getNumberOfIncomes());
        checkTooLowIndex(checkIndex, MIN_INCOME_INDEX);
    }

    /**
     * Returns without exception if an index is not lower than minimum accepted index value.
     *
     * @param checkIndex The index to be checked for invalid boundary.
     * @param minIndex   The minimum value an index should be.
     */
    private static void checkTooLowIndex(int checkIndex, int minIndex) throws IndexOutOfBoundsException {
        if (checkIndex < minIndex) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns without exception if an index is not higher than current maximum index value.
     *
     * @param checkIndex The index to be checked for invalid boundary.
     * @param maxIndex   The value the index must be less than.
     */
    private static void checkTooHighIndex(int checkIndex, int maxIndex) throws IndexOutOfBoundsException {
        if (checkIndex > maxIndex) {
            throw new IndexOutOfBoundsException();
        }
    }
}
