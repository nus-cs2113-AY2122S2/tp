package seedu.allonus.expense;


import seedu.allonus.ui.TextUi;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

import static seedu.allonus.expense.ExpenseParser.parseDeleteExpense;
import static seedu.allonus.expense.ExpenseParser.parseEditExpense;
import static seedu.allonus.expense.ExpenseParser.parseNewExpense;
import static seedu.allonus.expense.ExpenseParser.parseFindExpense;

/**
 * The core function of the expense tracker, which executes user commands based on keywords.
 */
public class ExpenseTracker {
    public static final String INCORRECT_NUMBER_OF_FIELDS = "Incorrect number of fields!";
    public static final String NEW_REMARKS_VALUE_SET = "New remarks value set!";
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
    public static final String CHOSEN_EXPENSE_TO_EDIT = "Here is the expense record you have chosen to edit:\n";
    public static final String CHOSEN_FIELD_TO_EDIT = "Which field would you like to edit? "
            +
            "Enter [field] [newValue] or enter 'DONE' when you have finished editing:";
    public static final String EDITING_COMPLETE = "Editing complete!";
    public static final String NEWLY_EDITED_EXPENSE_RECORD = "Here is the newly edited expense record:\n";
    public static final String NEW_DATE_VALUE_SET = "New date value set!";
    public static final String NEW_AMOUNT_VALUE_SET = "New amount value set!";
    public static final String NEW_CATEGORY_VALUE_SET = "New category value set!";
    public static final String MSG_NEW_VALUE_CANNOT_BE_EMPTY = "New value cannot be empty!";
    public static final String NO_TASKS_FOUND = "No tasks found!";

    private static void expenseWelcome() {
        System.out.println(EXPENSE_WELCOME_MESSAGE);
    }

    private static Logger logger = Logger.getLogger("expenseLogger");


    /**
     * List out all current records in the expense list.
     */
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

    /**
     * Deletes a record from the list of expenses.
     *
     * @param list  list of expenses itself
     * @param index the index of the item to be deleted
     * @throws IndexOutOfBoundsException if the expense record is not found
     */
    private static void deleteExpense(ArrayList<Expense> list, int index) throws IndexOutOfBoundsException {
        logger.log(Level.INFO, LOG_DELETE_INTENT);
        Expense toBeDeleted = list.get(index - EXPENSE_INDEX);
        list.remove(index - EXPENSE_INDEX);
        Expense.setNoOfItems(Expense.getNoOfItems() - 1);
        System.out.println("Deleted entry: " + toBeDeleted);
    }

    /**
     * Adds a record into the list of expenses.
     *
     * @param list list of expenses itself
     * @param e    the expense object itself to be added
     */
    private static void addExpense(ArrayList<Expense> list, Expense e) {
        logger.log(Level.INFO, LOG_ADD_INTENT);
        assert e != null : ASSERT_EXPENSE_OBJECT_NOT_NULL;
        list.add(e);
        System.out.println("Added " + e);
        Expense.setNoOfItems(Expense.getNoOfItems() + EXPENSE_INDEX);
    }

    /**
     * Edits an existing expense record using its index chosen by the user.
     *
     * @param list  list of expenses itself
     * @param index the index of the item to be edited
     * @param ui    ui object to collect user's inputs
     * @throws IndexOutOfBoundsException if the expense record is not found
     */
    private static void editExpense(ArrayList<Expense> list, int index, TextUi ui) {
        Expense toBeEdited = list.get(index - 1);
        System.out.println(CHOSEN_EXPENSE_TO_EDIT + toBeEdited);
        System.out.println(CHOSEN_FIELD_TO_EDIT);
        String fieldToEdit;
        boolean isFinishedEditing = false;
        while (!isFinishedEditing) {
            fieldToEdit = ui.getUserInput();
            if (fieldToEdit.trim().equalsIgnoreCase("done")) {
                isFinishedEditing = true;
                System.out.println(EDITING_COMPLETE);
                System.out.println(NEWLY_EDITED_EXPENSE_RECORD + toBeEdited);
            } else {
                try {
                    editField(fieldToEdit, toBeEdited);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(INCORRECT_NUMBER_OF_FIELDS);
                }
            }
        }
    }


    /**
     * Edits a specific field of a chosen expense record based on user's input.
     *
     * @param fieldToEdit the field chosen by the user to edit
     * @param toBeEdited  the expense record object to be edited
     * @throws IndexOutOfBoundsException if new value is missing (without spaces)
     */
    private static void editField(String fieldToEdit, Expense toBeEdited) throws IndexOutOfBoundsException {
        String[] newFields = fieldToEdit.split(" ");
        String field = newFields[0];
        String newValue = newFields[1].trim();
        if (newValue.length() == 0) {
            System.out.println(MSG_NEW_VALUE_CANNOT_BE_EMPTY);
            return;
        } else {
            switch (field) {
            case ("date"):
                toBeEdited.setDate(newFields[1]);
                System.out.println(NEW_DATE_VALUE_SET);
                break;
            case ("amount"):
                toBeEdited.setAmount(newFields[1]);
                System.out.println(NEW_AMOUNT_VALUE_SET);
                break;
            case ("category"):
                toBeEdited.setCategory(newFields[1]);
                System.out.println(NEW_CATEGORY_VALUE_SET);
                break;
            case ("remarks"):
                toBeEdited.setRemark(newFields[1]);
                System.out.println(NEW_REMARKS_VALUE_SET);
                break;
            case ("done"):
                return;
            default:
                System.out.println(MSG_INVALID_COMMANDS);
                return;
            }
        }
    }

    /**
     * Looks through the list of expense records and prints out the records that contain a specified keyword.
     *
     * @param list         list of expenses itself
     * @param stringToFind keyword to look for within each expense record
     */
    private static void findExpense(ArrayList<Expense> list, String stringToFind) {
        boolean isFound = false;
        stringToFind = stringToFind.toLowerCase();
        for (Expense expense : list) {
            String expenseCategory = expense.getCategory().toLowerCase();
            String expenseDate = expense.getDate().toLowerCase();
            String expenseRemark = expense.getRemark().toLowerCase();
            if (expenseCategory.contains(stringToFind) || expenseDate.contains(stringToFind)
                    || expenseRemark.contains(stringToFind)) {
                isFound = true;
                System.out.println("Here are the matching expense records:\n" + expense);
            }
        }
        if (!isFound) {
            System.out.println(NO_TASKS_FOUND);
        }
    }

    /**
     * Determines which command to execute depending on the keyword supplied.
     *
     * @param ui the user input itself
     * @throws ExpenseException if an invalid keyword is supplied
     */
    public static void expenseRunner(TextUi ui) {
        logger.setLevel(Level.SEVERE);
        expenseWelcome();
        String rawInput = ui.getUserInput();
        assert rawInput != null : ASSERT_INPUT_NOT_NULL;
        String keyWord = rawInput.split(" ", SPLIT_INTO_HALF)[KEYWORD_INDEX].trim().toLowerCase();
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
            case ("edit"):
                index = -1;
                int noOfItems = Expense.getNoOfItems();
                if (noOfItems == 0) {
                    System.out.println(MSG_EMPTY_LIST);
                } else {
                    try {
                        index = parseEditExpense(rawInput);
                        editExpense(expenseList, index, ui);
                    } catch (IndexOutOfBoundsException e) {
                        logger.log(Level.WARNING, LOG_INDEX_OUT_OF_BOUNDS);
                        System.out.println(MSG_EMPTY_INDEX);
                    } catch (NumberFormatException e) {
                        logger.log(Level.WARNING, LOG_INVALID_INDEX_TYPE);
                        System.out.println(MSG_INVALID_INDEX_TYPE);
                    }
                }
                break;
            case ("find"):
                String stringToFind;
                try {
                    stringToFind = parseFindExpense(rawInput);
                    findExpense(expenseList, stringToFind);
                } catch (IndexOutOfBoundsException e) {
                    logger.log(Level.WARNING, LOG_INDEX_OUT_OF_BOUNDS);
                    System.out.println("Keyword cannot be empty!");
                }
                break;
            default:
                logger.log(Level.WARNING, LOG_INVALID_COMMANDS);
                System.out.println(MSG_INVALID_COMMANDS);
            }
            rawInput = ui.getUserInput();
            keyWord = rawInput.split(" ", SPLIT_INTO_HALF)[KEYWORD_INDEX].trim();
        }
        logger.log(Level.INFO, LOG_RETURN_TO_MENU_INTENT);
        return;
    }


}
