package seedu.allonus.expense;

import seedu.allonus.ui.TextUi;

import java.util.ArrayList;

import static seedu.allonus.expense.ExpenseParser.parseDeleteExpense;
import static seedu.allonus.expense.ExpenseParser.parseNewExpense;

public class ExpenseTracker {
    private static final String EXPENSE_WELCOME_MESSAGE = "Welcome to Expense Tracker";
    private static final ArrayList<Expense> expenseList = new ArrayList<>();

    private static void expenseWelcome() {
        System.out.println(EXPENSE_WELCOME_MESSAGE);
    }

    private static void listExpenses() {
        int noOfItems = Expense.getNoOfItems();
        if (noOfItems == 0) {
            System.out.println("You haven't added any expenses to your list yet!");
            return;
        }

        String listAsString = "";
        for (int i = 0; i < noOfItems; i++) {
            Expense curr = expenseList.get(i);
            listAsString = listAsString.concat(String.format(" %d. %s\n", i + 1, curr));
        }
        System.out.println("Here are the expenses you have made so far:\n" + listAsString);
    }

    private static void deleteExpense(ArrayList<Expense> list, int index) {
        Expense toBeDeleted = list.get(index - 1);
        list.remove(index - 1);
        Expense.setNoOfItems(Expense.getNoOfItems() - 1);
        System.out.println("Deleted entry: " + toBeDeleted);
    }

    private static void addExpense(ArrayList<Expense> list, Expense e) {
        list.add(e);
        System.out.println("Added " + e);
        Expense.setNoOfItems(Expense.getNoOfItems() + 1);
    }

    public static void expenseRunner(TextUi ui) {
        expenseWelcome();
        String userInput;
        while (true) {
            userInput = ui.getUserInput();
            if (userInput.equals("menu")) {
                return;
            } else if (userInput.equals("list")) {
                listExpenses();
            } else if (userInput.startsWith("rm")) {
                int index = parseDeleteExpense(userInput);
                deleteExpense(expenseList, index);
            } else if (userInput.startsWith("add")) {
                //return some array here, split the user input into date, amount, category, remark
                String[] rawInput = userInput.split(" ", 2);
                String[] newExpense = parseNewExpense(userInput);
                Expense e = new Expense(newExpense[0], newExpense[1], newExpense[2], newExpense[3]);
                addExpense(expenseList, e);
            } else {
                continue;
            }
        }
    }

}
