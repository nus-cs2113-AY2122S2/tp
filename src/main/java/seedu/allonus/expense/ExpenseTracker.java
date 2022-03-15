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

    private static void deleteExpense(ArrayList<Expense> list, int index) throws IndexOutOfBoundsException {
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
        String rawInput = ui.getUserInput();
        String keyWord = rawInput.split(" ", 2)[0].trim();
        while (!(keyWord.equals("menu"))) {
            switch (keyWord) {
            case ("list"):
                listExpenses();
                break;
            case ("rm"):
                int index = -1;
                try {
                    index = parseDeleteExpense(rawInput);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index cannot be empty!");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid integer for the index!");
                }
                try {
                    deleteExpense(expenseList, index);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Selected item does not exist in the list");
                }
                break;
            case ("add"):
                try {
                    String[] newExpense = parseNewExpense(rawInput);
                    Expense e = new Expense(newExpense[0], newExpense[1], newExpense[2], newExpense[3]);
                    addExpense(expenseList, e);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Some fields are empty! Try again!");
                }
                break;
            default:
                System.out.println("Invalid command!");
                break;
            }
            rawInput = ui.getUserInput();
            keyWord = rawInput.split(" ", 2)[0].trim();
        }
        return;
    }
}
/*
        while (true) {
            userInput = ui.getUserInput();
            if (userInput.equals("menu")) {
                return;
            } else if (userInput.equals("list")) {
                listExpenses();
            } else if (userInput.startsWith("rm")) {
                int index = -1;
                try {
                    index = parseDeleteExpense(userInput);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Index cannot be empty!");
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid integer for the index!");
                    continue;
                }
                try {
                    deleteExpense(expenseList, index);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Selected item does not exist in the list");
                    continue:
                }

            } else if (userInput.startsWith("add")) {
                String[] rawInput = userInput.split(" ", 2);
                String[] newExpense;
                try {
                    newExpense = parseNewExpense(userInput);
                } catch (IndexOutOfBoundsException e)
                {
                    System.out.println("Some fields are empty! Try again!");
                    continue;
                }

                Expense e = new Expense(newExpense[0], newExpense[1], newExpense[2], newExpense[3]);
                addExpense(expenseList, e);
            } else {
                continue;
            }
        }
    }

}
*/