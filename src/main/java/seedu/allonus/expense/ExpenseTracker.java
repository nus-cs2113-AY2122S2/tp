package seedu.allonus.expense;

import seedu.allonus.ui.TextUi;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import static seedu.allonus.expense.ExpenseParser.parseDeleteExpense;
import static seedu.allonus.expense.ExpenseParser.parseNewExpense;

public class ExpenseTracker {
    private static final String EXPENSE_WELCOME_MESSAGE = "Welcome to Expense Tracker";
    private static final ArrayList<Expense> expenseList = new ArrayList<>();
    public static final String ASSERT_NUMBER_OF_ITEMS_NON_NEGATIVE = "Number of items in the expense list must be at least 0";
    public static final String ASSERT_EXPENSE_OBJECT_NOT_NULL = "Expense object should not be null";
    public static final String LIST_EXPENSE_OUTPUT = "Here are the expenses you have made so far:\n";
    public static final String LOG_DELETE_INTENT = "User wants to delete an expense record";
    public static final String ASSERT_INPUT_NOT_NULL = "User input should not be null";

    private static void expenseWelcome() {
        System.out.println(EXPENSE_WELCOME_MESSAGE);
    }
    private static Logger logger = Logger.getLogger("expenseLogger");

    private static void listExpenses() {
        logger.log(Level.INFO, "User wants to list all expenses made");
        int noOfItems = Expense.getNoOfItems();
        assert noOfItems >= 0: ASSERT_NUMBER_OF_ITEMS_NON_NEGATIVE;
        if (noOfItems == 0) {
            System.out.println("You haven't added any expenses to your list yet!");
            return;
        }

        String listAsString = "";
        for (int i = 0; i < noOfItems; i++) {
            Expense curr = expenseList.get(i);
            assert curr != null: ASSERT_EXPENSE_OBJECT_NOT_NULL;
            listAsString = listAsString.concat(String.format(" %d. %s\n", i + 1, curr));
        }
        System.out.println(LIST_EXPENSE_OUTPUT + listAsString);
    }

    private static void deleteExpense(ArrayList<Expense> list, int index) throws IndexOutOfBoundsException {
        logger.log(Level.INFO, LOG_DELETE_INTENT);
        Expense toBeDeleted = list.get(index - 1);
        list.remove(index - 1);
        Expense.setNoOfItems(Expense.getNoOfItems() - 1);
        System.out.println("Deleted entry: " + toBeDeleted);
    }

    private static void addExpense(ArrayList<Expense> list, Expense e) {
        logger.log(Level.INFO, "User wants to add an expense made");
        assert e != null: ASSERT_EXPENSE_OBJECT_NOT_NULL;
        list.add(e);
        System.out.println("Added " + e);
        Expense.setNoOfItems(Expense.getNoOfItems() + 1);
    }

    public static void expenseRunner(TextUi ui) {
        expenseWelcome();
        String rawInput = ui.getUserInput();
        assert rawInput != null: ASSERT_INPUT_NOT_NULL;
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
                    logger.log(Level.WARNING, "User entered empty index");
                    System.out.println("Index cannot be empty!");
                } catch (NumberFormatException e) {
                    logger.log(Level.WARNING, "User entered invalid non-integer index");
                    System.out.println("Please enter a valid integer for the index!");
                }
                try {
                    deleteExpense(expenseList, index);
                } catch (IndexOutOfBoundsException e) {
                    logger.log(Level.WARNING, "User entered an index out of bounds");
                    System.out.println("Selected item does not exist in the list");
                }
                break;
            case ("add"):
                try {
                    String[] newExpense = parseNewExpense(rawInput);
                    assert newExpense != null: ASSERT_EXPENSE_OBJECT_NOT_NULL;
                    Expense e = new Expense(newExpense[0], newExpense[1], newExpense[2], newExpense[3]);
                    addExpense(expenseList, e);
                } catch (IndexOutOfBoundsException e) {
                    logger.log(Level.WARNING, "User possibly entered empty fields");
                    System.out.println("Some fields are empty! Try again!");
                }
                break;
            default:
                logger.log(Level.WARNING, "User entered invalid commands");
                System.out.println("Invalid command!");
                break;
            }
            rawInput = ui.getUserInput();
            keyWord = rawInput.split(" ", 2)[0].trim();
        }
        logger.log(Level.INFO, "User wants to return to the main menu");
        return;
    }
}
