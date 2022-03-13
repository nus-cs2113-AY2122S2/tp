package seedu.allonus.expense;

public class ExpenseParser {
    public static final String DATE_DELIMITER = "d/";
    public static final String AMOUNT_DELIMITER = "a/";
    public static final String CATEGORY_DELIMITER = "c/";
    public static final String REMARKS_DELIMITER = "r/";
    public static final String ALL_DELIMITERS = "[dacr]/";

    public static String[] parseNewExpense(String userInput) throws IndexOutOfBoundsException {
        String rawInput = userInput.split(" ", 2)[1].trim();

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
        String[] stripLeftOfDelimiter = userInput.split(leftDelimiter, 2);
        String rightOfDelimiter;
        if (stripLeftOfDelimiter.length == 2) {
            rightOfDelimiter  = stripLeftOfDelimiter[1];
        } else {
            rightOfDelimiter =  stripLeftOfDelimiter[0];
        }
        String[] stripRightOfDelimiter = rightOfDelimiter.split(rightDelimiter, 2);
        String result = stripRightOfDelimiter[0].trim();
        if (result.length() == 0) {
            throw new IndexOutOfBoundsException();
        }
        return result;

    }

    public static int parseDeleteExpense(String userInput) throws IndexOutOfBoundsException, NumberFormatException {
        String[] rawInput = userInput.split(" ", 2);
        int result = Integer.parseInt(rawInput[1]);
        return result;
    }
}
