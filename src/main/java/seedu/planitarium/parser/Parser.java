package seedu.planitarium.parser;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.category.Category;
import seedu.planitarium.exceptions.DuplicateDelimiterException;
import seedu.planitarium.exceptions.InvalidIndexException;
import seedu.planitarium.exceptions.InvalidMoneyException;
import seedu.planitarium.exceptions.MissingDelimiterException;
import seedu.planitarium.exceptions.EmptyStringException;
import seedu.planitarium.person.Person;
import seedu.planitarium.person.PersonList;

import java.util.logging.Level;

public class Parser {
    protected static final String className = Parser.class.getSimpleName();
    protected static final String fileName = className + ".log";
    protected static final ProjectLogger logger = new ProjectLogger(className, fileName);

    public static final String DELIMITER_SPACE = " ";
    public static final String DELIMITER_NAME = "/n";
    public static final String DELIMITER_USER_INDEX = "/u";
    public static final String DELIMITER_DESCRIPTION = "/d";
    public static final String DELIMITER_INCOME = "/i";
    public static final String DELIMITER_EXPENDITURE = "/e";
    public static final String DELIMITER_RECORD_INDEX = "/r";
    public static final String DELIMITER_BACK = "/[cringedup]";
    // public static final String DELIMITER_MONEY = ".";
    public static final String DELIMITER_RECURRING_STATUS = "/p";
    public static final String DELIMITER_CATEGORY_INDEX = "/c";
    public static final String DELIMITER_GROUP_INDEX = "/g";
    public static final String EMPTY_STRING = "";
    public static final String RECURRING_STATUS_FALSE = "F";
    public static final String RECURRING_STATUS_TRUE = "T";

    public static final int INDEX_KEYWORD = 0;
    public static final int INDEX_LEFT_NOT_EXIST = 0;
    public static final int INDEX_LEFT_REMOVED = 1;
    public static final int INDEX_RIGHT_REMOVED = 0;
    public static final int LIMIT_TWO_TOKENS = 2;
    // public static final int LIMIT_TWO_DECIMAL = 2;
    public static final int MIN_USER_INDEX = 1;
    public static final int MIN_EXPENDITURE_INDEX = 1;
    public static final int MIN_INCOME_INDEX = 1;
    public static final int MIN_CATEGORY_INDEX = 1;
    public static final int MAX_GROUP_INDEX = 3;
    public static final int MIN_GROUP_INDEX = 1;
    public static final double MONEY_ZERO = 0.0;

    protected static final String ASSERT_INPUT_NOT_NULL = "User input should not be null";
    protected static final String ASSERT_MONEY_NOT_NULL = "Money input should not be null";
    // protected static final String ASSERT_MONEY_NON_NEGATIVE = "Money output should be non-negative";
    protected static final String ASSERT_USER_INDEX_NOT_NULL = "User index should not be null";
    protected static final String ASSERT_EXPENDITURE_INDEX_NOT_NULL = "Expenditure index should not be null";
    protected static final String ASSERT_INCOME_INDEX_NOT_NULL = "Income index should not be null";
    protected static final String ASSERT_STATUS_NOT_NULL = "Recurring status should not be null";
    protected static final String ASSERT_CATEGORY_NOT_NULL = "Category index should not be null";
    protected static final String ASSERT_GROUP_NOT_NULL = "Group index should not be null";

    protected static final String LOG_PARSED_VALUES = "User input '%s' parsed out as '%s'";
    protected static final String LOG_VALID_MONEY = "Converted the string '%s' into '%.2f'";
    protected static final String LOG_INVALID_MONEY = "Invalid money of '%s' caught";
    protected static final String LOG_VALID_INDEX = "Valid index '%d' being returned";
    protected static final String LOG_INVALID_INDEX = "Invalid index of '%s' caught";
    protected static final String LOG_INDEX_TOO_LOW = "Input index '%d' is less than the minimum index of '%d'";
    protected static final String LOG_INDEX_TOO_HIGH = "Input index '%d' is more than the maximum index of '%d'";
    protected static final String LOG_CHECK_INDEX_BOUNDS = "Checking index '%d' for bounds violation";
    protected static final String LOG_MISSING_DELIMITER = "User input '%s' is missing delimiter '%s'";
    protected static final String LOG_TOO_MANY_DELIMITER = "User input '%s' has too many delimiters '%s'";


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
     * @throws EmptyStringException        if string after the delimiter is blank.
     */
    public static String parseName(String userInput)
            throws MissingDelimiterException, DuplicateDelimiterException, EmptyStringException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        ParserUtility.checkContainsOnlyOneDelimiter(userInput, DELIMITER_NAME);
        String name = ParserUtility.parseDelimitedTerm(userInput, DELIMITER_NAME).trim();
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
     * @throws EmptyStringException        if string after the delimiter is blank.
     */
    public static String parseUserIndex(String userInput)
            throws MissingDelimiterException, DuplicateDelimiterException, EmptyStringException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        ParserUtility.checkContainsOnlyOneDelimiter(userInput, DELIMITER_USER_INDEX);
        String userIndex = ParserUtility.parseDelimitedTerm(userInput, DELIMITER_USER_INDEX).trim();
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
     * @throws EmptyStringException        if string after the delimiter is blank.
     */
    public static String parseDescription(String userInput)
            throws MissingDelimiterException, DuplicateDelimiterException, EmptyStringException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        ParserUtility.checkContainsOnlyOneDelimiter(userInput, DELIMITER_DESCRIPTION);
        String description = ParserUtility.parseDelimitedTerm(userInput, DELIMITER_DESCRIPTION).trim();
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
     * @throws EmptyStringException        if string after the delimiter is blank.
     */
    public static String parseIncome(String userInput)
            throws MissingDelimiterException, DuplicateDelimiterException, EmptyStringException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        ParserUtility.checkContainsOnlyOneDelimiter(userInput, DELIMITER_INCOME);
        String income = ParserUtility.parseDelimitedTerm(userInput, DELIMITER_INCOME).trim();
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
     * @throws EmptyStringException        if string after the delimiter is blank.
     */
    public static String parseExpenditure(String userInput)
            throws MissingDelimiterException, DuplicateDelimiterException, EmptyStringException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        ParserUtility.checkContainsOnlyOneDelimiter(userInput, DELIMITER_EXPENDITURE);
        String expenditure = ParserUtility.parseDelimitedTerm(userInput, DELIMITER_EXPENDITURE).trim();
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
     * @throws EmptyStringException        if string after the delimiter is blank.
     */
    public static String parseRecordIndex(String userInput)
            throws MissingDelimiterException, DuplicateDelimiterException, EmptyStringException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        ParserUtility.checkContainsOnlyOneDelimiter(userInput, DELIMITER_RECORD_INDEX);
        String record = ParserUtility.parseDelimitedTerm(userInput, DELIMITER_RECORD_INDEX).trim();
        logger.getLogger().log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, record));
        return record;
    }

    /**
     * Returns the recurring status from user input.
     *
     * @param userInput The user's full input text.
     * @return The recurring status.
     * @throws MissingDelimiterException   if user input does not contain delimiter for recurring status.
     * @throws DuplicateDelimiterException if user input contains duplicate delimiters.
     * @throws EmptyStringException        if string after the delimiter is blank.
     */
    public static boolean parseRecurringStatus(String userInput)
            throws DuplicateDelimiterException, MissingDelimiterException, EmptyStringException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        ParserUtility.checkContainsOnlyOneDelimiter(userInput, DELIMITER_RECURRING_STATUS);
        String status = ParserUtility.parseDelimitedTerm(userInput, DELIMITER_RECURRING_STATUS).trim();
        logger.getLogger().log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, status));
        if (status.equalsIgnoreCase("t")) {
            return true;
        }
        return false;
    }

    /**
     * Returns the category index from user input.
     *
     * @param userInput The user's full input text.
     * @return The category index.
     * @throws MissingDelimiterException   if user input does not contain delimiter for category index.
     * @throws DuplicateDelimiterException if user input contains duplicate delimiters.
     * @throws EmptyStringException        if string after the delimiter is blank.
     */
    public static String parseCategoryIndex(String userInput)
            throws DuplicateDelimiterException, MissingDelimiterException, EmptyStringException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        ParserUtility.checkContainsOnlyOneDelimiter(userInput, DELIMITER_CATEGORY_INDEX);
        String category = ParserUtility.parseDelimitedTerm(userInput, DELIMITER_CATEGORY_INDEX).trim();
        logger.getLogger().log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, category));
        return category;
    }

    /**
     * Returns the group index from user input.
     *
     * @param userInput The user's full input text.
     * @return The group index.
     * @throws MissingDelimiterException   if user input does not contain delimiter for group index.
     * @throws DuplicateDelimiterException if user input contains duplicate delimiters.
     * @throws EmptyStringException        if string after the delimiter is blank.
     */
    public static String parseGroupIndex(String userInput)
            throws DuplicateDelimiterException, MissingDelimiterException, EmptyStringException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        ParserUtility.checkContainsOnlyOneDelimiter(userInput, DELIMITER_GROUP_INDEX);
        String group = ParserUtility.parseDelimitedTerm(userInput, DELIMITER_GROUP_INDEX).trim();
        logger.getLogger().log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, group));
        return group;
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
            ParserUtility.checkNegativeMoney(checkMoney);
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
            ParserUtility.checkTooHighIndex(checkIndex, personList.getNumberOfMembers());
            ParserUtility.checkTooLowIndex(checkIndex, MIN_USER_INDEX);
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
            ParserUtility.checkTooHighIndex(checkIndex, person.getNumberOfExpenditures());
            ParserUtility.checkTooLowIndex(checkIndex, MIN_EXPENDITURE_INDEX);
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
            ParserUtility.checkTooHighIndex(checkIndex, person.getNumberOfIncomes());
            ParserUtility.checkTooLowIndex(checkIndex, MIN_INCOME_INDEX);
            logger.getLogger().log(Level.INFO, String.format(LOG_VALID_INDEX, checkIndex));
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.getLogger().log(Level.WARNING, String.format(LOG_INVALID_INDEX, incomeIndex));
            throw new InvalidIndexException(incomeIndex);
        }
    }

    /**
     * Returns a valid recurrence status for expenses.
     *
     * @param statusText The recurring expense status.
     * @return Recurring status 'T' or 'F'.
     */
    public static String getValidRecurringStatus(String statusText) {
        assert (statusText != null) : ASSERT_STATUS_NOT_NULL;
        String status = RECURRING_STATUS_FALSE;
        if (statusText.equalsIgnoreCase(RECURRING_STATUS_TRUE)) {
            status = RECURRING_STATUS_TRUE;
        }
        return status;
    }

    /**
     * Returns a valid category index that is within category quantity bounds.
     *
     * @param categoryIndex A category's lookup index.
     * @return A valid category index.
     * @throws InvalidIndexException if index is not a valid integer or out of bounds.
     */
    public static int getValidCategoryIndex(String categoryIndex) throws InvalidIndexException {
        assert (categoryIndex != null) : ASSERT_CATEGORY_NOT_NULL;
        try {
            int checkIndex = Integer.parseInt(categoryIndex);
            ParserUtility.checkTooHighIndex(checkIndex, Category.getNumberOfCategories());
            ParserUtility.checkTooLowIndex(checkIndex, MIN_CATEGORY_INDEX);
            logger.getLogger().log(Level.INFO, String.format(LOG_VALID_INDEX, checkIndex));
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.getLogger().log(Level.WARNING, String.format(LOG_INVALID_INDEX, categoryIndex));
            throw new InvalidIndexException(categoryIndex);
        }
    }

    /**
     * Returns a valid group index that is within group quantity bounds.
     *
     * @param groupIndex A logical group's lookup index.
     * @return A valid group index.
     * @throws InvalidIndexException if index is not a valid integer or out of bounds.
     */
    public static int getValidGroupIndex(String groupIndex) throws InvalidIndexException {
        assert (groupIndex != null) : ASSERT_GROUP_NOT_NULL;
        try {
            int checkIndex = Integer.parseInt(groupIndex);
            ParserUtility.checkTooHighIndex(checkIndex, MAX_GROUP_INDEX);
            ParserUtility.checkTooLowIndex(checkIndex, MIN_GROUP_INDEX);
            logger.getLogger().log(Level.INFO, String.format(LOG_VALID_INDEX, checkIndex));
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.getLogger().log(Level.WARNING, String.format(LOG_INVALID_INDEX, groupIndex));
            throw new InvalidIndexException(groupIndex);
        }
    }

}
