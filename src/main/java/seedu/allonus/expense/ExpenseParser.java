package seedu.allonus.expense;

public class ExpenseParser {
    public static final String DATE_DELIMITER = "d/";
    public static final String AMOUNT_DELIMITER = "a/";
    public static final String CATEGORY_DELIMITER = "c/";
    public static final String REMARKS_DELIMITER = "r/";
    public static final String ALL_DELIMITERS = "[dacr]/";
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
     * Determines the content of the user input by splitting it into fields depending on the delimiters
     * provided.
     *
     * @param userInput the line that is inputted by the user
     * @return list of parameters representing the date, amount, category and remarks
     * @throws IndexOutOfBoundsException if some fields are missing, or wrong delimiters are used
     */
    public static String[] parseNewExpense(String userInput) throws IndexOutOfBoundsException {
        String rawInput = userInput.split(" ", SPLIT_IN_HALF)[EXPENSE_FIELDS].trim();
        assert rawInput != null : ASSERT_INPUT_NOT_NULL;
        if (!rawInput.contains(DATE_DELIMITER) || !rawInput.contains(AMOUNT_DELIMITER)
                || !rawInput.contains(CATEGORY_DELIMITER) || !rawInput.contains(REMARKS_DELIMITER)) {
            throw new IndexOutOfBoundsException();
        }
        try {
            String date = parseKeywordExpense(rawInput, DATE_DELIMITER, ALL_DELIMITERS);
            String amount = parseKeywordExpense(rawInput, AMOUNT_DELIMITER, ALL_DELIMITERS);
            String category = parseKeywordExpense(rawInput, CATEGORY_DELIMITER, ALL_DELIMITERS);
            String remarks = parseKeywordExpense(rawInput, REMARKS_DELIMITER, ALL_DELIMITERS);
            String[] result = {date, amount, category, remarks};
            return result;
        } catch (ExpenseException e) {
            System.out.println(e.getMessage());
        }
        String[] wrongResult = {};
        return wrongResult;
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
            throws IndexOutOfBoundsException, ExpenseException {
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
        String[] stripRightOfDelimiter = rightOfDelimiter.split(rightDelimiter, SPLIT_IN_HALF);
        String result = stripRightOfDelimiter[LEFT_SIDE].trim();
        if (leftDelimiter.equals(AMOUNT_DELIMITER)) {
            float amountCheck = Float.parseFloat(result);
            if (amountCheck < 0) {
                throw new ExpenseException("Amount cannot be negative!");
            }
        }
        assert result != null : ASSERT_RESULT_NOT_NULL;
        if (result.length() == ZERO) {
            throw new IndexOutOfBoundsException();
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

