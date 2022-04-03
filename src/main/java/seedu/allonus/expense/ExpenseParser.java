package seedu.allonus.expense;

import seedu.allonus.expense.exceptions.ExpenseAmountException;
import seedu.allonus.expense.exceptions.ExpenseEmptyFieldException;
import seedu.allonus.expense.exceptions.ExpenseMissingFieldException;
import seedu.allonus.expense.exceptions.ExpenseExtraFieldException;
import seedu.allonus.expense.exceptions.ExpenseSurroundSlashSpaceException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ExpenseParser {
    public static final String DATE_DELIMITER = " d/";
    public static final String AMOUNT_DELIMITER = " a/";
    public static final String CATEGORY_DELIMITER = " c/";
    public static final String REMARKS_DELIMITER = " r/";
    public static final String ALL_DELIMITERS = " [dacr]/";
    public static final String ASSERT_INPUT_NOT_NULL = "User input should not be null";
    public static final String ASSERT_DELIMITER_NOT_NULL = "Delimiter should not be null";
    public static final String ASSERT_RESULT_NOT_NULL = "Result should not be null";
    public static final int EXPENSE_FIELDS = 1;
    public static final int SPLIT_IN_HALF = 2;
    public static final int RIGHT_SIDE = 1;
    public static final int LEFT_SIDE = 0;
    public static final int ZERO = 0;
    public static final int INDEX_TO_BE_PARSED = 1;

    /**
     * Reformats the date field into a valid date object.
     *
     * @param rawDate the date of the expense record entered by the user
     * @return a string of the reformatted date entered by the user
     * @throws DateTimeParseException if user did not enter a valid date format
     */
    public static String reformatDate(String rawDate) throws DateTimeParseException {
        LocalDate dateOfExpense = LocalDate.parse(rawDate);
        String parsedDate = dateOfExpense.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return parsedDate;
    }

    /**
     * Checks if the specified expense amount is a valid integer/float and is non-negative.
     *
     * @param amount the expense amount entered by the user
     * @throws ExpenseAmountException if amount is negative
     */
    public static void isAmountValid(String amount) throws ExpenseAmountException {
        float parsedAmount = Float.parseFloat(amount);
        if (parsedAmount < 0) {
            throw new ExpenseAmountException("Amount cannot be negative!");
        }
    }

    /**
     * Determines the content of the user input by splitting it into fields depending on the delimiters
     * provided.
     *
     * @param userInput the line that is inputted by the user
     * @return list of parameters representing the date, amount, category and remarks
     * @throws IndexOutOfBoundsException if some fields are missing, or wrong delimiters are used
     */
    public static String[] parseNewExpense(String userInput) throws IndexOutOfBoundsException,
            DateTimeParseException, NumberFormatException, ExpenseAmountException, ExpenseMissingFieldException,
            ExpenseEmptyFieldException, ExpenseExtraFieldException, ExpenseSurroundSlashSpaceException {
        String rawInput = userInput.split(" ", SPLIT_IN_HALF)[EXPENSE_FIELDS].trim();
        rawInput = " " + rawInput;
        assert rawInput != null : ASSERT_INPUT_NOT_NULL;
        if (!rawInput.contains(DATE_DELIMITER) || !rawInput.contains(AMOUNT_DELIMITER)
                || !rawInput.contains(CATEGORY_DELIMITER) || !rawInput.contains(REMARKS_DELIMITER)) {
            throw new ExpenseMissingFieldException("Some fields are missing!");
        }
        String date = parseKeywordExpense(rawInput, DATE_DELIMITER, ALL_DELIMITERS);
        date = reformatDate(date);
        String amount = parseKeywordExpense(rawInput, AMOUNT_DELIMITER, ALL_DELIMITERS);
        String category = parseKeywordExpense(rawInput, CATEGORY_DELIMITER, ALL_DELIMITERS);
        String remarks = parseKeywordExpense(rawInput, REMARKS_DELIMITER, ALL_DELIMITERS);
        String[] result = {date, amount, category, remarks};
        return result;
    }

    /**
     * Checks if the user has added too many delimiters in the input.
     *
     * @param userInput the line that is inputted by the user.
     * @param delimiter the current field that is being checked.
     * @throws ExpenseExtraFieldException if any extra delimiters are found in the user's input
     */
    public static void checkNumberOfDelimiters(String userInput, String delimiter) throws ExpenseExtraFieldException {
        int lengthOfRawInput = userInput.length();
        String strippedInput = userInput.replace(delimiter, "");
        int lengthOfStrippedInput = strippedInput.length();
        int lengthOfDelimiter = delimiter.length();
        if (lengthOfRawInput - lengthOfStrippedInput >= lengthOfDelimiter) {
            throw new ExpenseExtraFieldException("Duplicate fields found in input!");
        }
    }

    public static void checkContainSlash(String userInput) throws ExpenseSurroundSlashSpaceException {
        for (int i = 0; i < userInput.length(); i++) {
            lookForIndexOfSlash(userInput, i);
        }
    }

    public static void lookForIndexOfSlash(String userInput, int i) throws ExpenseSurroundSlashSpaceException {
        char c = userInput.charAt(i);
        if (c == '/') {
            checkSlashValidity(userInput, i);
        }
    }

    public static void checkSlashValidity(String userInput, int i) throws ExpenseSurroundSlashSpaceException {
        if (i == 0) {
            throw new ExpenseSurroundSlashSpaceException("/ as first character must be surrounded by white spaces!");
        }
        else if (i == userInput.length() - 1) {
            throw new ExpenseSurroundSlashSpaceException("/ is not allowed as the last non-space character!");
        }
        else {
            char beforeSlash = userInput.charAt(i - 1);
            char afterSlash = userInput.charAt(i + 1);
            if (beforeSlash != ' ' || afterSlash != ' ') {
                throw new ExpenseSurroundSlashSpaceException("/ must be surrounded by white spaces!");
            }
        }
    }

    /**
     * Looks for a specific delimiter within the user's input.
     *
     * @param userInput      the line that is inputted by the user
     * @param leftDelimiter  the delimiter to look for
     * @param rightDelimiter the next delimiter to cut off reading from
     * @return the contents of the field specified by the delimiter
     * @throws IndexOutOfBoundsException if contents supplied is missing
     */
    public static String parseKeywordExpense(String userInput, String leftDelimiter, String rightDelimiter)
            throws ExpenseEmptyFieldException, ExpenseAmountException, ExpenseExtraFieldException,
            ExpenseSurroundSlashSpaceException {
        assert userInput != null : ASSERT_INPUT_NOT_NULL;
        assert leftDelimiter != null : ASSERT_DELIMITER_NOT_NULL;
        assert rightDelimiter != null : ASSERT_DELIMITER_NOT_NULL;
        String[] stripLeftOfDelimiter = userInput.split(leftDelimiter, SPLIT_IN_HALF);
        String rightOfDelimiter;
        if (stripLeftOfDelimiter.length == SPLIT_IN_HALF) {
            rightOfDelimiter = stripLeftOfDelimiter[RIGHT_SIDE];
        } else {
            rightOfDelimiter = stripLeftOfDelimiter[LEFT_SIDE];
        }
        checkNumberOfDelimiters(rightOfDelimiter, leftDelimiter);
        String[] stripRightOfDelimiter = rightOfDelimiter.split(rightDelimiter, SPLIT_IN_HALF);
        checkContainSlash(stripRightOfDelimiter[LEFT_SIDE]);
        String result = stripRightOfDelimiter[LEFT_SIDE].trim();
        if (leftDelimiter.equals(AMOUNT_DELIMITER)) {
            float amountCheck = Float.parseFloat(result);
            if (amountCheck < 0) {
                throw new ExpenseAmountException("Amount cannot be negative!");
            }
        }
        assert result != null : ASSERT_RESULT_NOT_NULL;
        if (result.length() == ZERO) {
            throw new ExpenseEmptyFieldException("Fields cannot be empty!");
        }
        return result;
    }

    /**
     * Parses a Delete command to retrieve the index of the record to be deleted.
     *
     * @param userInput the line that is inputted by the user
     * @return the index of the record to be deleted
     * @throws IndexOutOfBoundsException if some fields are missing
     * @throws NumberFormatException     if index provided is not an integer
     */
    public static int parseDeleteExpense(String userInput) throws IndexOutOfBoundsException, NumberFormatException {
        assert userInput != null : ASSERT_INPUT_NOT_NULL;
        String[] rawInput = userInput.split(" ", SPLIT_IN_HALF);
        int result = Integer.parseInt(rawInput[INDEX_TO_BE_PARSED]);
        return result;
    }

    /**
     * Parses an Edit command to retrieve the index of the record to be edited.
     *
     * @param userInput the line that is inputted by the user
     * @return the index of the record to be edited
     * @throws IndexOutOfBoundsException if some fields are missing
     * @throws NumberFormatException     if index provided is not an integer
     */
    public static int parseEditExpense(String userInput) throws IndexOutOfBoundsException, NumberFormatException {
        assert userInput != null : ASSERT_INPUT_NOT_NULL;
        String[] rawInput = userInput.split(" ", SPLIT_IN_HALF);
        int result = Integer.parseInt(rawInput[INDEX_TO_BE_PARSED]);
        return result;
    }

    /**
     * Parses a Find command to retrieve the keyword to look for.
     *
     * @param userInput the line that is inputted by the user
     * @return the keyword to look for within the list of expense records
     * @throws IndexOutOfBoundsException if keyword is missing
     */
    public static String parseFindExpense(String userInput) throws IndexOutOfBoundsException {
        assert userInput != null : ASSERT_INPUT_NOT_NULL;
        String[] rawInput = userInput.split(" ", SPLIT_IN_HALF);
        String result = rawInput[INDEX_TO_BE_PARSED];
        return result;
    }
}

