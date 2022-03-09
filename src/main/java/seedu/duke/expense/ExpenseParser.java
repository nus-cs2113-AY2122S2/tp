package seedu.duke.expense;

public class ExpenseParser {

    public static String[] parseNewExpense(String userInput) {
        String[] rawInput = userInput.split(" ", 2);
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
