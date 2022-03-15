package seedu.planitarium.parser;

import exceptions.InvalidIndexException;
import exceptions.InvalidMoneyException;
import exceptions.MissingDelimiterException;
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
     * @throws MissingDelimiterException if user input does not contain delimiter for name.
     */
    public static String parseName(String userInput) throws MissingDelimiterException {
        checkContainsDelimiter(userInput, DELIMITER_NAME);
        return parseDelimitedTerm(userInput, DELIMITER_NAME, DELIMITER_BACK).trim();
    }

    /**
     * Returns user index from user input.
     *
     * @param userInput The user's full input text.
     * @return Person's user index.
     * @throws MissingDelimiterException if user input does not contain delimiter for user index.
     */
    public static String parseUserIndex(String userInput) throws MissingDelimiterException {
        checkContainsDelimiter(userInput, DELIMITER_USER_INDEX);
        return parseDelimitedTerm(userInput, DELIMITER_USER_INDEX, DELIMITER_BACK).trim();
    }

    /**
     * Returns description of item from user input.
     *
     * @param userInput The user's full input text.
     * @return An item's description.
     * @throws MissingDelimiterException if user input does not contain delimiter for description.
     */
    public static String parseDescription(String userInput) throws MissingDelimiterException {
        checkContainsDelimiter(userInput, DELIMITER_DESCRIPTION);
        return parseDelimitedTerm(userInput, DELIMITER_DESCRIPTION, DELIMITER_BACK).trim();
    }

    /**
     * Returns income from user input.
     *
     * @param userInput The user's full input text.
     * @return Person's added income.
     * @throws MissingDelimiterException if user input does not contain delimiter for income.
     */
    public static String parseIncome(String userInput) throws MissingDelimiterException {
        checkContainsDelimiter(userInput, DELIMITER_INCOME);
        return parseDelimitedTerm(userInput, DELIMITER_INCOME, DELIMITER_BACK).trim();
    }

    /**
     * Returns expenditure from user input.
     *
     * @param userInput The user's full input text.
     * @return Person's expenditure amount.
     * @throws MissingDelimiterException if user input does not contain delimiter for expenditure.
     */
    public static String parseExpenditure(String userInput) throws MissingDelimiterException {
        checkContainsDelimiter(userInput, DELIMITER_EXPENDITURE);
        return parseDelimitedTerm(userInput, DELIMITER_EXPENDITURE, DELIMITER_BACK).trim();
    }

    /**
     * Returns a record's index from user input.
     *
     * @param userInput The user's full input text.
     * @return A record's index.
     * @throws MissingDelimiterException if user input does not contain delimiter for record index.
     */
    public static String parseRecordIndex(String userInput) throws MissingDelimiterException {
        checkContainsDelimiter(userInput, DELIMITER_RECORD_INDEX);
        return parseDelimitedTerm(userInput, DELIMITER_RECORD_INDEX, DELIMITER_BACK).trim();
    }

    /**
     * Returns a valid double that is a monetary value.
     *
     * @param amount Text to be checked.
     * @return A valid double for monetary values.
     * @throws InvalidMoneyException if format of text is not a valid double, negative or more than 2 decimal places.
     */
    public static double getValidMoney(String amount) throws InvalidMoneyException {
        try {
            double checkMoney = Double.parseDouble(amount);
            if (Double.compare(checkMoney, MONEY_ZERO) < 0) {
                // to be caught immediately within this method
                throw new NumberFormatException();
            }
            //if (amount.contains(DELIMITER_MONEY)) {
            //    String decimalPlace = parseDelimitedTerm(amount, DELIMITER_MONEY, DELIMITER_BACK);
            //    if (decimalPlace.length() > LIMIT_TWO_DECIMAL) {
            // to be caught immediately within this method
            //        throw new NumberFormatException();
            //    }
            //}
            return checkMoney;
        } catch (NumberFormatException e) {
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
        try {
            int checkIndex = Integer.parseInt(userIndex);
            checkTooHighIndex(checkIndex, personList.getNumberOfMembers());
            checkTooLowIndex(checkIndex, MIN_USER_INDEX);
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException(userIndex);
        }
    }

    /**
     * Returns a valid expenditure index that is within expenditure quantity bounds.
     *
     * @param expenditureIndex Person's expenditure lookup index.
     * @param person           Person who may have expenditures.
     * @throws InvalidIndexException if index is not a valid integer or out of bounds.
     */
    public static int getValidExpenditureIndex(String expenditureIndex, Person person)
            throws InvalidIndexException {
        try {
            int checkIndex = Integer.parseInt(expenditureIndex);
            checkTooHighIndex(checkIndex, person.getNumberOfExpenditures());
            checkTooLowIndex(checkIndex, MIN_EXPENDITURE_INDEX);
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException(expenditureIndex);
        }
    }

    /**
     * Returns a valid income index that is within income quantity bounds.
     *
     * @param incomeIndex Person's income lookup index.
     * @param person      Person who may have incomes.
     * @throws InvalidIndexException if index is not a valid integer or out of bounds.
     */
    public static int getValidIncomeIndex(String incomeIndex, Person person)
            throws InvalidIndexException {
        try {
            int checkIndex = Integer.parseInt(incomeIndex);
            checkTooHighIndex(checkIndex, person.getNumberOfIncomes());
            checkTooLowIndex(checkIndex, MIN_INCOME_INDEX);
            return checkIndex;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
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
        if (checkIndex < minIndex) {
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
        if (checkIndex > maxIndex) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Returns without exception if user input contains a given delimiter character sequence.
     *
     * @param userInput User input to be checked.
     * @param delimiter A delimiter used to separate details.
     * @throws MissingDelimiterException if user input does not contain the delimiter.
     */
    private static void checkContainsDelimiter(String userInput, String delimiter) throws MissingDelimiterException {
        if (!userInput.contains(delimiter)) {
            throw new MissingDelimiterException(delimiter);
        }
    }
}
