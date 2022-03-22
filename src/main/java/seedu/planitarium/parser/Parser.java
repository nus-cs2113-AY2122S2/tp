package seedu.planitarium.parser;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.exceptions.DuplicateDelimiterException;
import seedu.planitarium.exceptions.InvalidIndexException;
import seedu.planitarium.exceptions.InvalidMoneyException;
import seedu.planitarium.exceptions.MissingDelimiterException;
import seedu.planitarium.person.Person;
import seedu.planitarium.person.PersonList;

import java.util.logging.Level;

public class Parser {
    private static final String className = Parser.class.getSimpleName();
    private static final String fileName = className + ".log";
    private static final ProjectLogger logger = new ProjectLogger(className, fileName);

    public static final String DELIMITER_SPACE = " ";
    public static final String DELIMITER_NAME = "/n";
    public static final String DELIMITER_USER_INDEX = "/u";
    public static final String DELIMITER_DESCRIPTION = "/d";
    public static final String DELIMITER_INCOME = "/i";
    public static final String DELIMITER_EXPENDITURE = "/e";
    public static final String DELIMITER_RECORD_INDEX = "/r";
    public static final String DELIMITER_BACK = "/[ruined]";
    // public static final String DELIMITER_MONEY = ".";
    public static final String EMPTY_STRING = "";

    public static final int INDEX_KEYWORD = 0;
    public static final int INDEX_LEFT_NOT_EXIST = 0;
    public static final int INDEX_LEFT_REMOVED = 1;
    public static final int INDEX_RIGHT_REMOVED = 0;
    public static final int LIMIT_TWO_TOKENS = 2;
    // public static final int LIMIT_TWO_DECIMAL = 2;
    public static final int MIN_USER_INDEX = 1;
    public static final int MIN_EXPENDITURE_INDEX = 1;
    public static final int MIN_INCOME_INDEX = 1;
    public static final double MONEY_ZERO = 0.0;

    private static final String ASSERT_INPUT_NOT_NULL = "User input should not be null";
    private static final String ASSERT_OUTPUT_NOT_NULL = "Parsed output should not be null";
    private static final String ASSERT_MONEY_NOT_NULL = "Money input should not be null";
    // private static final String ASSERT_MONEY_NON_NEGATIVE = "Money output should be non-negative";
    private static final String ASSERT_USER_INDEX_NOT_NULL = "User index should not be null";
    private static final String ASSERT_EXPENDITURE_INDEX_NOT_NULL = "Expenditure index should not be null";
    private static final String ASSERT_INCOME_INDEX_NOT_NULL = "Income index should not be null";

    private static final String LOG_PARSED_VALUES = "User input '%s' parsed out as '%s'";
    private static final String LOG_VALID_MONEY = "Converted the string '%s' into '%.2f'";
    private static final String LOG_INVALID_MONEY = "Invalid money of '%s' caught";
    private static final String LOG_VALID_INDEX = "Valid index '%d' being returned";
    private static final String LOG_INVALID_INDEX = "Invalid index of '%s' caught";
    private static final String LOG_INDEX_TOO_LOW = "Input index '%d' is less than the minimum index of '%d'";
    private static final String LOG_INDEX_TOO_HIGH = "Input index '%d' is more than the maximum index of '%d'";
    private static final String LOG_CHECK_INDEX_BOUNDS = "Checking index '%d' for bounds violation";
    private static final String LOG_MISSING_DELIMITER = "User input '%s' is missing delimiter '%s'";
    private static final String LOG_TOO_MANY_DELIMITER = "User input '%s' has too many delimiters '%s'";


    /**
     * Returns the term surrounded by two delimiters.
     *
     * @param text           The text containing the term to be parsed.
     * @param delimiterLeft  The delimiter on the left of term.
     * @param delimiterRight The delimiter on the right of term.
     * @return A non-delimiter-surrounded term.
     */
    public static String parseDelimitedTerm(String text, String delimiterLeft, String delimiterRight) {
        String[] firstParse = text.split(delimiterLeft, LIMIT_TWO_TOKENS);
        String leftRemoved;
        if (firstParse.length == LIMIT_TWO_TOKENS) {
            leftRemoved = firstParse[INDEX_LEFT_REMOVED];
        } else {
            leftRemoved = firstParse[INDEX_LEFT_NOT_EXIST];
        }
        String[] secondParse = leftRemoved.split(delimiterRight, LIMIT_TWO_TOKENS);
        String rightRemoved = secondParse[INDEX_RIGHT_REMOVED];

        assert rightRemoved != null : ASSERT_OUTPUT_NOT_NULL;
        return rightRemoved.trim();
    }

    /**
     * Returns command keyword from user input.
     *
     * @param userInput The user's full input text.
     * @return The command keyword issued.
     */
    public static String parseKeyword(String userInput) {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        String keyword = userInput.split(DELIMITER_SPACE)[INDEX_KEYWORD].trim();
        logger.getLogger().log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, keyword));
        return keyword;
    }

    /**
     * Returns person's name from user input.
     *
     * @param userInput The user's full input text.
     * @return Person's name.
     * @throws MissingDelimiterException   if user input does not contain delimiter for name.
     * @throws DuplicateDelimiterException if user input contains duplicate delimiters.
     */
    public static String parseName(String userInput) throws MissingDelimiterException, DuplicateDelimiterException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        checkContainsOnlyOneDelimiter(userInput, DELIMITER_NAME);
        String name = parseDelimitedTerm(userInput, DELIMITER_NAME, DELIMITER_BACK).trim();
        logger.getLogger().log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, name));
        return name;
    }

    /**
     * Returns user index from user input.
     *
     * @param userInput The user's full input text.
     * @return Person's user index.
     * @throws MissingDelimiterException   if user input does not contain delimiter for user index.
     * @throws DuplicateDelimiterException if user input contains duplicate delimiters.
     */
    public static String parseUserIndex(String userInput)
            throws MissingDelimiterException, DuplicateDelimiterException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        checkContainsOnlyOneDelimiter(userInput, DELIMITER_USER_INDEX);
        String userIndex = parseDelimitedTerm(userInput, DELIMITER_USER_INDEX, DELIMITER_BACK).trim();
        logger.getLogger().log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, userIndex));
        return userIndex;
    }

    /**
     * Returns description of item from user input.
     *
     * @param userInput The user's full input text.
     * @return An item's description.
     * @throws MissingDelimiterException   if user input does not contain delimiter for description.
     * @throws DuplicateDelimiterException if user input contains duplicate delimiters.
     */
    public static String parseDescription(String userInput)
            throws MissingDelimiterException, DuplicateDelimiterException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        checkContainsOnlyOneDelimiter(userInput, DELIMITER_DESCRIPTION);
        String description = parseDelimitedTerm(userInput, DELIMITER_DESCRIPTION, DELIMITER_BACK).trim();
        logger.getLogger().log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, description));
        return description;
    }

    /**
     * Returns income from user input.
     *
     * @param userInput The user's full input text.
     * @return Person's added income.
     * @throws MissingDelimiterException   if user input does not contain delimiter for income.
     * @throws DuplicateDelimiterException if user input contains duplicate delimiters.
     */
    public static String parseIncome(String userInput)
            throws MissingDelimiterException, DuplicateDelimiterException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        checkContainsOnlyOneDelimiter(userInput, DELIMITER_INCOME);
        String income = parseDelimitedTerm(userInput, DELIMITER_INCOME, DELIMITER_BACK).trim();
        logger.getLogger().log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, income));
        return income;
    }

    /**
     * Returns expenditure from user input.
     *
     * @param userInput The user's full input text.
     * @return Person's expenditure amount.
     * @throws MissingDelimiterException   if user input does not contain delimiter for expenditure.
     * @throws DuplicateDelimiterException if user input contains duplicate delimiters.
     */
    public static String parseExpenditure(String userInput)
            throws MissingDelimiterException, DuplicateDelimiterException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        checkContainsOnlyOneDelimiter(userInput, DELIMITER_EXPENDITURE);
        String expenditure = parseDelimitedTerm(userInput, DELIMITER_EXPENDITURE, DELIMITER_BACK).trim();
        logger.getLogger().log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, expenditure));
        return expenditure;
    }

    /**
     * Returns a record's index from user input.
     *
     * @param userInput The user's full input text.
     * @return A record's index.
     * @throws MissingDelimiterException   if user input does not contain delimiter for record index.
     * @throws DuplicateDelimiterException if user input contains duplicate delimiters.
     */
    public static String parseRecordIndex(String userInput)
            throws MissingDelimiterException, DuplicateDelimiterException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        checkContainsOnlyOneDelimiter(userInput, DELIMITER_RECORD_INDEX);
        String record = parseDelimitedTerm(userInput, DELIMITER_RECORD_INDEX, DELIMITER_BACK).trim();
        logger.getLogger().log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, record));
        return record;
    }

    /**
     * Returns a valid double that is a monetary value.
     *
     * @param amount Text to be checked.
     * @return A valid double for monetary values.
     * @throws InvalidMoneyException if format of text is not a valid double, negative or more than 2 decimal places.
     */
    public static double getValidMoney(String amount) throws InvalidMoneyException {
        assert (amount != null) : ASSERT_MONEY_NOT_NULL;
        try {
            double checkMoney = Double.parseDouble(amount);
            if (Double.compare(checkMoney, MONEY_ZERO) < 0) {
                // to be caught immediately within this method
                throw new NumberFormatException();
            }
            //if (amount.contains(DELIMITER_MONEY)) {
            //    String decimalPlace = parseDelimitedTerm(amount, DELIMITER_MONEY, DELIMITER_BACK);
            //    if (decimalPlace.length() > LIMIT_TWO_DECIMAL) {
            //        to be caught immediately within this method
            //        throw new NumberFormatException();
            //    }
            //}
            logger.getLogger().log(Level.INFO, String.format(LOG_VALID_MONEY, amount, checkMoney));
            return checkMoney;
        } catch (NumberFormatException e) {
            logger.getLogger().log(Level.WARNING, String.format(LOG_INVALID_MONEY, amount));
            throw new InvalidMoneyException(amount);
        }
    }

    /**
     * Returns a valid integer user index that is within membership quantity bounds.
     *
     * @param userIndex  Person's user index.
     * @param personList A list of Persons.
     * @return A valid integer user index.
     * @throws InvalidIndexException if amount is not a valid integer or out of bounds.
     */
    public static int getValidUserIndex(String userIndex, PersonList personList) throws InvalidIndexException {
        assert (userIndex != null) : ASSERT_USER_INDEX_NOT_NULL;
        try {
            int checkIndex = Integer.parseInt(userIndex);
            checkTooHighIndex(checkIndex, personList.getNumberOfMembers());
            checkTooLowIndex(checkIndex, MIN_USER_INDEX);
            logger.getLogger().log(Level.INFO, String.format(LOG_VALID_INDEX, checkIndex));
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.getLogger().log(Level.WARNING, String.format(LOG_INVALID_INDEX, userIndex));
            throw new InvalidIndexException(userIndex);
        }
    }

    /**
     * Returns a valid expenditure index that is within expenditure quantity bounds.
     *
     * @param expenditureIndex Person's expenditure lookup index.
     * @param person           Person who may have expenditures.
     * @return A valid expenditure index.
     * @throws InvalidIndexException if index is not a valid integer or out of bounds.
     */
    public static int getValidExpenditureIndex(String expenditureIndex, Person person) throws InvalidIndexException {
        assert (expenditureIndex != null) : ASSERT_EXPENDITURE_INDEX_NOT_NULL;
        try {
            int checkIndex = Integer.parseInt(expenditureIndex);
            checkTooHighIndex(checkIndex, person.getNumberOfExpenditures());
            checkTooLowIndex(checkIndex, MIN_EXPENDITURE_INDEX);
            logger.getLogger().log(Level.INFO, String.format(LOG_VALID_INDEX, checkIndex));
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.getLogger().log(Level.WARNING, String.format(LOG_INVALID_INDEX, expenditureIndex));
            throw new InvalidIndexException(expenditureIndex);
        }
    }

    /**
     * Returns a valid income index that is within income quantity bounds.
     *
     * @param incomeIndex Person's income lookup index.
     * @param person      Person who may have incomes.
     * @return A valid income index.
     * @throws InvalidIndexException if index is not a valid integer or out of bounds.
     */
    public static int getValidIncomeIndex(String incomeIndex, Person person) throws InvalidIndexException {
        assert (incomeIndex != null) : ASSERT_INCOME_INDEX_NOT_NULL;
        try {
            int checkIndex = Integer.parseInt(incomeIndex);
            checkTooHighIndex(checkIndex, person.getNumberOfIncomes());
            checkTooLowIndex(checkIndex, MIN_INCOME_INDEX);
            logger.getLogger().log(Level.INFO, String.format(LOG_VALID_INDEX, checkIndex));
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.getLogger().log(Level.WARNING, String.format(LOG_INVALID_INDEX, incomeIndex));
            throw new InvalidIndexException(incomeIndex);
        }
    }

    /**
     * Returns without exception if an index is not lower than minimum accepted index value.
     *
     * @param checkIndex The index to be checked for invalid boundary.
     * @param minIndex   The minimum value that an index can be.
     * @throws IndexOutOfBoundsException if provided index is less than indicated minimum.
     */
    private static void checkTooLowIndex(int checkIndex, int minIndex) throws IndexOutOfBoundsException {
        logger.getLogger().log(Level.INFO, String.format(LOG_CHECK_INDEX_BOUNDS, checkIndex));
        if (checkIndex < minIndex) {
            logger.getLogger().log(Level.WARNING, String.format(LOG_INDEX_TOO_LOW, checkIndex, minIndex));
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns without exception if an index is not higher than current maximum index value.
     *
     * @param checkIndex The index to be checked for invalid boundary.
     * @param maxIndex   The maximum value that an index can be.
     * @throws IndexOutOfBoundsException if provided index is more than indicated maximum.
     */
    private static void checkTooHighIndex(int checkIndex, int maxIndex) throws IndexOutOfBoundsException {
        logger.getLogger().log(Level.INFO, String.format(LOG_CHECK_INDEX_BOUNDS, checkIndex));
        if (checkIndex > maxIndex) {
            logger.getLogger().log(Level.WARNING, String.format(LOG_INDEX_TOO_HIGH, checkIndex, maxIndex));
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns without exception if user input contains one occurrence of a given delimiter.
     *
     * @param userInput User input to be checked.
     * @param delimiter A delimiter used to separate details.
     * @throws MissingDelimiterException if user input does not contain the delimiter.
     */
    private static void checkContainsOnlyOneDelimiter(String userInput, String delimiter)
            throws MissingDelimiterException, DuplicateDelimiterException {
        int inputLengthWithDelimiter = userInput.length();
        int inputLengthNoDelimiter = userInput.replace(delimiter, EMPTY_STRING).length();
        int lengthOfDelimiter = delimiter.length();

        if ((inputLengthWithDelimiter - inputLengthNoDelimiter) < lengthOfDelimiter) {
            logger.getLogger().log(Level.WARNING, String.format(LOG_MISSING_DELIMITER, userInput, delimiter));
            throw new MissingDelimiterException(delimiter);
        }
        if ((inputLengthWithDelimiter - inputLengthNoDelimiter) > lengthOfDelimiter) {
            logger.getLogger().log(Level.WARNING, String.format(LOG_TOO_MANY_DELIMITER, userInput, delimiter));
            throw new DuplicateDelimiterException(delimiter);
        }
    }
}
