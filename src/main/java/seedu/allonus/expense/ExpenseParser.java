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
    public static final int INDEX_TO_BE_DELETED = 1;

    public static String[] parseNewExpense(String userInput) throws IndexOutOfBoundsException {
        String rawInput = userInput.split(" ", SPLIT_IN_HALF)[EXPENSE_FIELDS].trim();
        assert rawInput != null : ASSERT_INPUT_NOT_NULL;
        if (!rawInput.contains(DATE_DELIMITER) || !rawInput.contains(AMOUNT_DELIMITER)
                || !rawInput.contains(CATEGORY_DELIMITER) || !rawInput.contains(REMARKS_DELIMITER)) {
            throw new IndexOutOfBoundsException();
        }
        String date = parseKeywordExpense(rawInput, DATE_DELIMITER, ALL_DELIMITERS);
        String amount = parseKeywordExpense(rawInput, AMOUNT_DELIMITER, ALL_DELIMITERS);
        String category = parseKeywordExpense(rawInput, CATEGORY_DELIMITER, ALL_DELIMITERS);
        String remarks = parseKeywordExpense(rawInput, REMARKS_DELIMITER, ALL_DELIMITERS);
        String[] result = {date, amount, category, remarks};
        return result;
    }

    public static String parseKeywordExpense(String userInput, String leftDelimiter, String rightDelimiter)
            throws IndexOutOfBoundsException {
        assert userInput != null : ASSERT_INPUT_NOT_NULL;
        assert leftDelimiter != null : ASSERT_DELIMITER_NOT_NULL;
        assert rightDelimiter != null : ASSERT_DELIMITER_NOT_NULL;
        String[] stripLeftOfDelimiter = userInput.split(leftDelimiter, SPLIT_IN_HALF);
        String rightOfDelimiter;
        if (stripLeftOfDelimiter.length == SPLIT_IN_HALF) {
            rightOfDelimiter  = stripLeftOfDelimiter[RIGHT_SIDE];
        } else {
            rightOfDelimiter =  stripLeftOfDelimiter[LEFT_SIDE];
        }
        String[] stripRightOfDelimiter = rightOfDelimiter.split(rightDelimiter, SPLIT_IN_HALF);
        String result = stripRightOfDelimiter[LEFT_SIDE].trim();
        assert result != null : ASSERT_RESULT_NOT_NULL;
        if (result.length() == ZERO) {
            throw new IndexOutOfBoundsException();
        }
        return result;

    }

    public static int parseDeleteExpense(String userInput) throws IndexOutOfBoundsException, NumberFormatException {
        assert userInput != null : ASSERT_INPUT_NOT_NULL;
        String[] rawInput = userInput.split(" ", SPLIT_IN_HALF);
        int result = Integer.parseInt(rawInput[INDEX_TO_BE_DELETED]);
        return result;
    }
}
