package seedu.duke.expense;

public class ExpenseParser {
    public static final String DATE_DELIMITER = "/d";
    public static final String AMOUNT_DELIMITER = "/a";
    public static final String CATEGORY_DELIMITER = "/c";
    public static final String REMARKS_DELIMITER = "/r";
    public static final String END_DELIMITER = "/[dacr]";


    // /d date /a amount /c category /r remarks

    public static String[] parseNewExpense(String userInput) {
        String rawInput = userInput.split(" ", 2)[1].trim();
        String date = parseExpenseDelimiters(END_DELIMITER, DATE_DELIMITER, rawInput);
        String amount = parseExpenseDelimiters(END_DELIMITER, AMOUNT_DELIMITER, rawInput);
        String category = parseExpenseDelimiters(END_DELIMITER, CATEGORY_DELIMITER, rawInput);
        String remarks = parseExpenseDelimiters(END_DELIMITER, REMARKS_DELIMITER, rawInput);

        String[] result = {date, amount, category, remarks};
        return result;
    }

    private static String parseExpenseDelimiters(String end, String delimiter, String rawInput) {
        String[] stripLeftAway =  rawInput.split(delimiter, 2);
        String rightInput;
        if (stripLeftAway.length == 2) {
            rightInput = stripLeftAway[1];
        } else {
            rightInput = stripLeftAway[0];
        }
        String[] stripRightAway = rightInput.split(end, 2);
        String result = stripRightAway[0].trim();
        return result;

    }



    public static int parseDeleteExpense(String userInput) {
        String[] rawInput = userInput.split(" ", 2);
        int result = Integer.parseInt(rawInput[1]);
        return result;
    }
}
