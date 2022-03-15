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
    public static final String ASSERT_NUMBER_OF_ITEMS_NON_NEGATIVE =
            "Number of items in the expense list must be at least 0";
    public static final String ASSERT_EXPENSE_OBJECT_NOT_NULL = "Expense object should not be null";
    public static final String LIST_EXPENSE_OUTPUT = "Here are the expenses you have made so far:\n";
    public static final String LOG_DELETE_INTENT = "User wants to delete an expense record";
    public static final String ASSERT_INPUT_NOT_NULL = "User input should not be null";
    public static final int ZERO = 0;
    public static final int KEYWORD_INDEX = 0;
    public static final int DATE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public static final int CATEGORY_INDEX = 2;
    public static final int REMARKS_INDEX = 3;
    public static final int SPLIT_INTO_HALF = 2;
    public static final int EXPENSE_INDEX = 1;
    public static final String LOG_EMPTY_FIELDS = "User possibly entered empty fields";
    public static final String MSG_EMPTY_FIELDS = "Some fields are empty! Try again!";
    public static final String LOG_INDEX_OUT_OF_BOUNDS = "User entered an index out of bounds";
    public static final String MSG_ITEM_NOT_FOUND = "Selected item does not exist in the list";
    public static final String LOG_EMPTY_INDEX = "User entered empty index";
    public static final String MSG_EMPTY_INDEX = "Index cannot be empty!";
    public static final String LOG_INVALID_INDEX_TYPE = "User entered invalid non-integer index";
    public static final String MSG_INVALID_INDEX_TYPE = "Please enter a valid integer for the index!";
    public static final String LOG_ADD_INTENT = "User wants to add an expense made";
    public static final String MSG_EMPTY_LIST = "You haven't added any expenses to your list yet!";
    public static final String LOG_LIST_INTENT = "User wants to list all expenses made";
    public static final String LOG_INVALID_COMMANDS = "User entered invalid commands";
    public static final String MSG_INVALID_COMMANDS = "Invalid command!";
    public static final String LOG_RETURN_TO_MENU_INTENT = "User wants to return to the main menu";

    private static void expenseWelcome() {
        System.out.println(EXPENSE_WELCOME_MESSAGE);
    }

    private static Logger logger = Logger.getLogger("expenseLogger");

    private static void listExpenses() {
        logger.log(Level.INFO, LOG_LIST_INTENT);
        int noOfItems = Expense.getNoOfItems();
        assert noOfItems >= ZERO : ASSERT_NUMBER_OF_ITEMS_NON_NEGATIVE;
        if (noOfItems == ZERO) {
            System.out.println(MSG_EMPTY_LIST);
            return;
        }

        String listAsString = "";
        for (int i = ZERO; i < noOfItems; i++) {
            Expense curr = expenseList.get(i);
            assert curr != null : ASSERT_EXPENSE_OBJECT_NOT_NULL;
            listAsString = listAsString.concat(String.format(" %d. %s\n", i + EXPENSE_INDEX, curr));
        }
        System.out.println(LIST_EXPENSE_OUTPUT + listAsString);
    }

    private static void deleteExpense(ArrayList<Expense> list, int index) throws IndexOutOfBoundsException {
        logger.log(Level.INFO, LOG_DELETE_INTENT);
        Expense toBeDeleted = list.get(index - EXPENSE_INDEX);
        list.remove(index - EXPENSE_INDEX);
        Expense.setNoOfItems(Expense.getNoOfItems() - 1);
        System.out.println("Deleted entry: " + toBeDeleted);
    }

    private static void addExpense(ArrayList<Expense> list, Expense e) {
        logger.log(Level.INFO, LOG_ADD_INTENT);
        assert e != null : ASSERT_EXPENSE_OBJECT_NOT_NULL;
        list.add(e);
        System.out.println("Added " + e);
        Expense.setNoOfItems(Expense.getNoOfItems() + EXPENSE_INDEX);
    }

    public static void expenseRunner(TextUi ui) {
        expenseWelcome();
        String rawInput = ui.getUserInput();
        assert rawInput != null : ASSERT_INPUT_NOT_NULL;
        String keyWord = rawInput.split(" ", SPLIT_INTO_HALF)[KEYWORD_INDEX].trim();
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
                    logger.log(Level.WARNING, LOG_EMPTY_INDEX);
                    System.out.println(MSG_EMPTY_INDEX);
                } catch (NumberFormatException e) {
                    logger.log(Level.WARNING, LOG_INVALID_INDEX_TYPE);
                    System.out.println(MSG_INVALID_INDEX_TYPE);
                }
                try {
                    deleteExpense(expenseList, index);
                } catch (IndexOutOfBoundsException e) {
                    logger.log(Level.WARNING, LOG_INDEX_OUT_OF_BOUNDS);
                    System.out.println(MSG_ITEM_NOT_FOUND);
                }
                break;
            case ("add"):
                try {
                    String[] newExpense = parseNewExpense(rawInput);
                    assert newExpense != null : ASSERT_EXPENSE_OBJECT_NOT_NULL;
                    Expense e = new Expense(newExpense[DATE_INDEX], newExpense[AMOUNT_INDEX],
                            newExpense[CATEGORY_INDEX], newExpense[REMARKS_INDEX]);
                    addExpense(expenseList, e);
                } catch (IndexOutOfBoundsException e) {
                    logger.log(Level.WARNING, LOG_EMPTY_FIELDS);
                    System.out.println(MSG_EMPTY_FIELDS);
                }
                break;
            default:
                logger.log(Level.WARNING, LOG_INVALID_COMMANDS);
                System.out.println(MSG_INVALID_COMMANDS);
                break;
            }
            rawInput = ui.getUserInput();
            keyWord = rawInput.split(" ", SPLIT_INTO_HALF)[KEYWORD_INDEX].trim();
        }
        logger.log(Level.INFO, LOG_RETURN_TO_MENU_INTENT);
        return;
    }
}
