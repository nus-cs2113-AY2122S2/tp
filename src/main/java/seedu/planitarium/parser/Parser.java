//@@author 1szheng

package seedu.planitarium.parser;

import seedu.planitarium.ProjectLogger;
import seedu.planitarium.category.Category;
import seedu.planitarium.exceptions.DuplicateDelimiterException;
import seedu.planitarium.exceptions.InvalidIndexException;
import seedu.planitarium.exceptions.InvalidMoneyException;
import seedu.planitarium.exceptions.MissingDelimiterException;
import seedu.planitarium.exceptions.EmptyStringException;
import seedu.planitarium.global.Constants;

import java.util.logging.Level;

public class Parser {
    protected static final String className = Parser.class.getSimpleName();
    protected static final String fileName = className + ".log";
    protected static final ProjectLogger logger = new ProjectLogger(className, fileName);

    protected static final String DELIMITER_SPACE = " ";
    protected static final String DELIMITER_NAME = "/n";
    protected static final String DELIMITER_USER_INDEX = "/u";
    protected static final String DELIMITER_DESCRIPTION = "/d";
    protected static final String DELIMITER_INCOME = "/i";
    protected static final String DELIMITER_EXPENDITURE = "/e";
    protected static final String DELIMITER_RECORD_INDEX = "/r";
    protected static final String DELIMITER_RECURRING_STATUS = "/p";
    protected static final String DELIMITER_CATEGORY_INDEX = "/c";
    protected static final String DELIMITER_GROUP_INDEX = "/g";
    protected static final String DELIMITER_BACK = "/[cringedup]";
    protected static final String EMPTY_STRING = "";
    protected static final String DELIMITER_MONEY = ".";

    protected static final String ASSERT_INPUT_NOT_NULL = "User input should not be null";
    protected static final String ASSERT_MONEY_NOT_NULL = "Money input should not be null";
    protected static final String ASSERT_USER_INDEX_NOT_NULL = "User index should not be null";
    protected static final String ASSERT_EXPENDITURE_INDEX_NOT_NULL = "Expenditure index should not be null";
    protected static final String ASSERT_INCOME_INDEX_NOT_NULL = "Income index should not be null";
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

    protected static final String TYPE_USER_INDEX = "user";
    protected static final String TYPE_GROUP_INDEX = "group";
    protected static final String TYPE_EXPENDITURE_INDEX = "expenditure";
    protected static final String TYPE_INCOME_INDEX = "income";
    protected static final String TYPE_CATEGORY_INDEX = "category";

    /**
     * Returns command keyword from user input.
     *
     * @param userInput The user's full input text.
     * @return The command keyword issued.
     */
    public static String parseCommandType(String userInput) {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        String keyword = userInput.split(DELIMITER_SPACE)[Constants.INDEX_KEYWORD].trim();
        logger.log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, keyword));
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
        logger.log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, name));
        ParserUtility.warnIfNotSpacedForwardSlash(name);
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
        logger.log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, userIndex));
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
        logger.log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, description));
        ParserUtility.warnIfNotSpacedForwardSlash(description);
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
        logger.log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, income));
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
        logger.log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, expenditure));
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
        logger.log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, record));
        return record;
    }

    /**
     * Returns the recurring status from user input.
     *
     * @param userInput The user's full input text.
     * @return The recurring status in boolean.
     * @throws MissingDelimiterException   if user input does not contain delimiter for recurring status.
     * @throws DuplicateDelimiterException if user input contains duplicate delimiters.
     * @throws EmptyStringException        if string after the delimiter is blank.
     */
    public static Boolean parseRecurringStatus(String userInput)
            throws DuplicateDelimiterException, MissingDelimiterException, EmptyStringException {
        assert (userInput != null) : ASSERT_INPUT_NOT_NULL;
        ParserUtility.checkContainsOnlyOneDelimiter(userInput, DELIMITER_RECURRING_STATUS);
        String status = ParserUtility.parseDelimitedTerm(userInput, DELIMITER_RECURRING_STATUS).trim();
        logger.log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, status));
        return status.equalsIgnoreCase("t");
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
        logger.log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, category));
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
        logger.log(Level.INFO, String.format(LOG_PARSED_VALUES, userInput, group));
        return group;
    }

    /**
     * Returns a valid double that is a monetary value.
     *
     * @param amount Text to be checked.
     * @return A valid double for monetary values.
     * @throws InvalidMoneyException if format of text is not a valid double, negative or more than 2 decimal places.
     */
    public static Double getValidMoney(String amount) throws InvalidMoneyException {
        assert (amount != null) : ASSERT_MONEY_NOT_NULL;
        try {
            Double checkMoney = Double.parseDouble(amount);
            ParserUtility.checkNegativeMoney(checkMoney);
            ParserUtility.checkTwoDecimalPlace(amount);
            logger.log(Level.INFO, String.format(LOG_VALID_MONEY, amount, checkMoney));
            return checkMoney;
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, String.format(LOG_INVALID_MONEY, amount));
            throw new InvalidMoneyException(amount);
        }
    }

    /**
     * Returns a valid integer user index that is within membership quantity bounds.
     *
     * @param userIndex       Person's user index.
     * @param numberOfMembers The number of people in a logical grouping.
     * @return A valid integer user index.
     * @throws InvalidIndexException if amount is not a valid integer or out of bounds.
     */
    public static Integer getValidUserIndex(String userIndex, int numberOfMembers) throws InvalidIndexException {
        assert (userIndex != null) : ASSERT_USER_INDEX_NOT_NULL;
        try {
            int checkIndex = Integer.parseInt(userIndex);
            ParserUtility.checkTooHighIndex(checkIndex, numberOfMembers);
            ParserUtility.checkTooLowIndex(checkIndex, Constants.MIN_USER_INDEX);
            logger.log(Level.INFO, String.format(LOG_VALID_INDEX, checkIndex));
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, String.format(LOG_INVALID_INDEX, userIndex));
            throw new InvalidIndexException(TYPE_USER_INDEX,userIndex);
        }
    }

    /**
     * Returns a valid expenditure index that is within expenditure quantity bounds.
     *
     * @param expenditureIndex     Person's expenditure lookup index.
     * @param numberOfExpenditures The number of expenditures of a Person.
     * @return A valid expenditure index.
     * @throws InvalidIndexException if index is not a valid integer or out of bounds.
     */
    public static Integer getValidExpenditureIndex(String expenditureIndex, int numberOfExpenditures)
            throws InvalidIndexException {
        assert (expenditureIndex != null) : ASSERT_EXPENDITURE_INDEX_NOT_NULL;
        try {
            int checkIndex = Integer.parseInt(expenditureIndex);
            ParserUtility.checkTooHighIndex(checkIndex, numberOfExpenditures);
            ParserUtility.checkTooLowIndex(checkIndex, Constants.MIN_EXPENDITURE_INDEX);
            logger.log(Level.INFO, String.format(LOG_VALID_INDEX, checkIndex));
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, String.format(LOG_INVALID_INDEX, expenditureIndex));
            throw new InvalidIndexException(TYPE_EXPENDITURE_INDEX, expenditureIndex);
        }
    }

    /**
     * Returns a valid income index that is within income quantity bounds.
     *
     * @param incomeIndex     Person's income lookup index.
     * @param numberOfIncomes The number of incomes of a Person.
     * @return A valid income index.
     * @throws InvalidIndexException if index is not a valid integer or out of bounds.
     */
    public static Integer getValidIncomeIndex(String incomeIndex, int numberOfIncomes) throws InvalidIndexException {
        assert (incomeIndex != null) : ASSERT_INCOME_INDEX_NOT_NULL;
        try {
            int checkIndex = Integer.parseInt(incomeIndex);
            ParserUtility.checkTooHighIndex(checkIndex, numberOfIncomes);
            ParserUtility.checkTooLowIndex(checkIndex, Constants.MIN_INCOME_INDEX);
            logger.log(Level.INFO, String.format(LOG_VALID_INDEX, checkIndex));
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, String.format(LOG_INVALID_INDEX, incomeIndex));
            throw new InvalidIndexException(TYPE_INCOME_INDEX, incomeIndex);
        }
    }

    /**
     * Returns a valid category index that is within category quantity bounds.
     *
     * @param categoryIndex A category's lookup index.
     * @return A valid category index.
     * @throws InvalidIndexException if index is not a valid integer or out of bounds.
     */
    public static Integer getValidCategoryIndex(String categoryIndex) throws InvalidIndexException {
        assert (categoryIndex != null) : ASSERT_CATEGORY_NOT_NULL;
        try {
            int checkIndex = Integer.parseInt(categoryIndex);
            ParserUtility.checkTooHighIndex(checkIndex, Category.getNumberOfCategories());
            ParserUtility.checkTooLowIndex(checkIndex, Constants.MIN_CATEGORY_INDEX);
            logger.log(Level.INFO, String.format(LOG_VALID_INDEX, checkIndex));
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, String.format(LOG_INVALID_INDEX, categoryIndex));
            throw new InvalidIndexException(TYPE_CATEGORY_INDEX, categoryIndex);
        }
    }

    /**
     * Returns a valid group index that is within group quantity bounds.
     *
     * @param groupIndex A logical group's lookup index.
     * @return A valid group index.
     * @throws InvalidIndexException if index is not a valid integer or out of bounds.
     */
    public static Integer getValidGroupIndex(String groupIndex) throws InvalidIndexException {
        assert (groupIndex != null) : ASSERT_GROUP_NOT_NULL;
        try {
            int checkIndex = Integer.parseInt(groupIndex);
            ParserUtility.checkTooHighIndex(checkIndex, Constants.NUM_GROUPS);
            ParserUtility.checkTooLowIndex(checkIndex, Constants.MIN_GROUP_INDEX);
            logger.log(Level.INFO, String.format(LOG_VALID_INDEX, checkIndex));
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, String.format(LOG_INVALID_INDEX, groupIndex));
            throw new InvalidIndexException(TYPE_GROUP_INDEX, groupIndex);
        }
    }
}
