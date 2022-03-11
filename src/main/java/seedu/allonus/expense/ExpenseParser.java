package seedu.allonus.expense;

public class ExpenseParser {
    public static final String DATE_DELIMITER = "/d";
    public static final String AMOUNT_DELIMITER = "/a";
    public static final String CATEGORY_DELIMITER = "/c";
    public static final String REMARKS_DELIMITER = "/r";
    public static final String END = "/r";


    // /d date /a amount /c category /r remarks

    public static String[] parseNewExpense(String userInput) {
        String[] rawInput = userInput.split(" ", 2);
        //String date = parseExpenseDate(DATE_DELIMITER, rawInput);
        //String amount = parseExpenseAmount(AMOUNT_DELIMITER, rawInput);
        //String category = parseExpenseCategory(CATEGORY_DELIMITER, rawInput);
        //String remarks = parseExpenseRemarks(REMARKS_DELIMITER, rawInput);
        String[] parameters = rawInput[1].split(" ", 4);
        String date = parameters[0].substring(2);
        String amount = parameters[1].substring(2);
        String category = parameters[2].substring(2);
        String remarks = parameters[3].substring(2);

        String[] result = {date, amount, category, remarks};
        return result;
    }


    public static int parseDeleteExpense(String userInput) {
        String[] rawInput = userInput.split(" ", 2);
        int result = Integer.parseInt(rawInput[1]);
        return result;
    }
}
