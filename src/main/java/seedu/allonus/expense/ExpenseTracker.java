package seedu.allonus.expense;


import seedu.allonus.AllOnUs;
import seedu.allonus.expense.exceptions.ExpenseAmountException;
import seedu.allonus.expense.exceptions.ExpenseEmptyFieldException;
import seedu.allonus.expense.exceptions.ExpenseMissingFieldException;
import seedu.allonus.expense.exceptions.ExpenseExtraFieldException;
import seedu.allonus.expense.exceptions.ExpenseSurroundSlashSpaceException;
import seedu.allonus.expense.exceptions.ExpenseInvalidYearException;

import seedu.allonus.mode.Mode;
import seedu.allonus.storage.StorageFile;

import seedu.allonus.ui.TextUi;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

import static seedu.allonus.expense.ExpenseParser.reformatDate;
import static seedu.allonus.expense.ExpenseParser.checkContainSlash;
import static seedu.allonus.expense.ExpenseParser.parseDeleteExpense;
import static seedu.allonus.expense.ExpenseParser.parseFindExpense;
import static seedu.allonus.expense.ExpenseParser.parseNewExpense;
import static seedu.allonus.expense.ExpenseParser.parseEditExpense;
import static seedu.allonus.expense.ExpenseParser.isAmountValid;

/**
 * The core function of the expense tracker, which executes user commands based on keywords.
 */
public class ExpenseTracker {
    public static final String INVALID_INPUT = "Invalid input!";
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
    public static final String LOG_MISSING_PARAMETERS = "User possibly entered missing parameters";
    public static final String MSG_MISSING_FIELDS = "Some fields are missing!";
    public static final String LOG_INDEX_OUT_OF_BOUNDS = "User entered an index out of bounds";
    public static final String MSG_ITEM_NOT_FOUND = "Selected item does not exist in the list";
    public static final String LOG_EMPTY_INDEX = "User entered empty index";
    public static final String MSG_EMPTY_INDEX = "Index cannot be empty!";
    public static final String LOG_INVALID_INDEX_TYPE = "User entered invalid non-integer index";
    public static final String MSG_INVALID_INDEX_TYPE = "Please enter a valid integer for the index!";
    public static final String LOG_ADD_INTENT = "User wants to add an expense made";
    public static final String LOG_ADD_INTENT_FROM_FILE = "File wants to add a saved expense made";
    public static final String MSG_EMPTY_LIST = "You haven't added any expenses to your list yet!";
    public static final String LOG_LIST_INTENT = "User wants to list all expenses made";
    public static final String LOG_INVALID_COMMANDS = "User entered invalid commands";
    public static final String MSG_INVALID_COMMANDS = "Invalid command!";
    public static final String MSG_INVALID_EDIT_FIELD = "Invalid field to edit! Valid fields are:\nDATE, AMOUNT, "
            + "CATEGORY, REMARKS";
    public static final String LOG_RETURN_TO_MENU_INTENT = "User wants to return to the main menu";
    public static final String CHOSEN_EXPENSE_TO_EDIT = "Here is the expense record you have chosen to edit:\n";
    public static final String CHOSEN_FIELD_TO_EDIT = "Which field would you like to edit? "
            + "Enter [field] [newValue] or enter 'DONE' when you have finished editing:";
    public static final String EDITING_COMPLETE = "Editing complete!";
    public static final String NEWLY_EDITED_EXPENSE_RECORD = "Here is the newly edited expense record:\n";
    public static final String NEW_DATE_VALUE_SET = "New date value set!";
    public static final String NEW_AMOUNT_VALUE_SET = "New amount value set!";
    public static final String NEW_CATEGORY_VALUE_SET = "New category value set!";
    public static final String MSG_NEW_VALUE_CANNOT_BE_EMPTY = "New value cannot be empty!";
    public static final String NO_TASKS_FOUND = "No tasks found!";
    public static final String MSG_MATCHING_EXPENSES = "Here are the matching expense record(s):\n";
    public static final String MENU_STRING = "menu";
    public static final String MSG_INCORRECT_DATE_FORMAT = "Date field is of incorrect format! Type in YYYY-MM-DD and"
            + " make sure valid dates are used!";
    public static final String LOG_INCORRECT_DATE_FIELD = "User entered invalid date format";
    public static final String LOG_INVALID_AMOUNT = "User tried entering invalid amount";
    public static final String MSG_NUMBERS_ONLY_AMOUNT = "Please enter only numbers for the Amount field!";
    public static final String LOG_NEGATIVE_AMOUNT = "User tried entering negative amount";
    private static final String LOG_FIELDS_BECAME_EMPTY = "User tampered with some fields, making them blank";
    public static final String MSG_NO_EDITS_MADE = "No changes were made to expense record! "
            + "Returning to Expense Tracker...";
    public static final String SAME_NEW_DATE_VALUE = "New date value is the same as current date value!";
    public static final String SAME_NEW_AMOUNT_VALUE = "New amount value is the same as current amount value!";
    public static final String SAME_NEW_CATEGORY_VALUE = "New category value is the same as current category value!";
    public static final String SAME_NEW_REMARKS_SET = "New remarks value is the same as current remarks value!";
    public static final String MSG_NONEMPTY_KEYWORD = "Keyword cannot be empty!";
    public static final int INDEX_OF_NEW_VALUE = 1;
    public static final String EDIT_DONE = "done";
    public static final String KEYWORD_LIST = "list";
    public static final String KEYWORD_REMOVE = "rm";
    public static final String KEYWORD_ADD = "add";
    public static final String KEYWORD_EDIT = "edit";
    public static final String KEYWORD_FIND = "find";
    public static final String KEYWORD_BLANK = "";
    public static final String ALREADY_IN_EXPENSE_TRACKER_MESSAGE = "You are already in the Expense Tracker.";
    public static final String LOG_EMPTY_FIELD = "User input is missing a field";
    public static final String LOG_EXTRA_FIELDS = "User enter multiple copies of the same delimiter";
    public static final String LOG_INVALID_SLASH = "User entered slash in incorrect format";
    public static final String MSG_ONLY_DONE = "Type only 'done' to exit editing!";
    public static final String LOG_NEGATIVE_YEAR = "User entered a negative year value";


    private static void expenseWelcome() {
        TextUi.showToUser(EXPENSE_WELCOME_MESSAGE);
    }

    private static Logger logger = Logger.getLogger("expenseLogger");

    private static StorageFile storageFile = new StorageFile();
    private static boolean isModified = false;


    /**
     * List out all current records in the expense list.
     */
    private static void listExpenses() {
        logger.log(Level.INFO, LOG_LIST_INTENT);
        int noOfItems = Expense.getNoOfItems();
        assert noOfItems >= ZERO : ASSERT_NUMBER_OF_ITEMS_NON_NEGATIVE;
        if (noOfItems == ZERO) {
            TextUi.showToUser(MSG_EMPTY_LIST);
            return;
        }
        String listAsString = "";

        for (int i = ZERO; i < noOfItems; i++) {
            Expense curr = expenseList.get(i);
            assert curr != null : ASSERT_EXPENSE_OBJECT_NOT_NULL;
            String expenseRecord = String.format(" %d. %s\n", i + EXPENSE_INDEX, curr);
            listAsString = listAsString.concat(expenseRecord);
        }
        TextUi.showToUser(LIST_EXPENSE_OUTPUT + listAsString);
    }

    /**
     * Returns current number of items in expense list.
     *
     * @return number of items in expense list.
     */
    public int getExpenseCount() {
        return Expense.getNoOfItems();
    }

    /**
     * Returns logger attribute of this class ExpenseTracker.
     *
     * @return logger, an instance of class <code>Logger</code>, belonging to this <code>ExpenseTracker</code> class.
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Returns current expense list.
     *
     * @return expense list.
     */
    public ArrayList<Expense> getExpenseList() {
        return expenseList;
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
        TextUi.showToUser("Deleted entry: " + toBeDeleted);
        isModified = true;
    }

    /**
     * Adds a record into the list of expenses.
     *
     * @param list    list of expenses itself
     * @param expense the expense object itself to be added
     */
    private static void addExpense(ArrayList<Expense> list, Expense expense, boolean fromCommandLine) {
        if (fromCommandLine) {
            logger.log(Level.INFO, LOG_ADD_INTENT);
        } else {
            logger.log(Level.INFO, LOG_ADD_INTENT_FROM_FILE);
        }
        assert expense != null : ASSERT_EXPENSE_OBJECT_NOT_NULL;
        list.add(expense);
        if (fromCommandLine) {
            TextUi.showToUser("Added " + expense);
        }
        Expense.setNoOfItems(Expense.getNoOfItems() + EXPENSE_INDEX);
        isModified = true;
    }

    /**
     * Edits an existing expense record using its index chosen by the user.
     *
     * @param list  list of expenses itself
     * @param index the index of the item to be edited
     * @param ui    ui object to collect user's inputs
     */
    private static void editExpense(ArrayList<Expense> list, int index, TextUi ui) {
        Expense toBeEdited = list.get(index - 1);
        boolean isEdited = false;
        boolean isFieldEdited = false;
        TextUi.showToUser(CHOSEN_EXPENSE_TO_EDIT + toBeEdited);
        TextUi.showToUser(CHOSEN_FIELD_TO_EDIT);
        String fieldToEdit;
        boolean isFinishedEditing = false;
        while (!isFinishedEditing) {
            if (isFieldEdited) {
                isEdited = true;
            }
            fieldToEdit = ui.getUserInput();
            if (fieldToEdit.trim().equalsIgnoreCase(EDIT_DONE)) {
                isFinishedEditing = true;
                if (isEdited) {
                    TextUi.showToUser(EDITING_COMPLETE);
                    TextUi.showToUser(NEWLY_EDITED_EXPENSE_RECORD + toBeEdited);
                } else {
                    TextUi.showToUser(MSG_NO_EDITS_MADE);
                }
            } else {
                try {
                    isFieldEdited = editField(fieldToEdit, toBeEdited);
                } catch (IndexOutOfBoundsException e) {
                    TextUi.showToUser(INVALID_INPUT);
                }
            }
        }
        isModified = true;
    }


    /**
     * Edits a specific field of a chosen expense record based on user's input.
     *
     * @param fieldToEdit the field chosen by the user to edit
     * @param toBeEdited  the expense record object to be edited
     * @throws IndexOutOfBoundsException if new value is missing (without spaces)
     */
    private static boolean editField(String fieldToEdit, Expense toBeEdited) throws IndexOutOfBoundsException {
        boolean isEdited = false;
        String[] newFields = fieldToEdit.split(" ", 2);
        String field = newFields[ZERO];
        String newValue = newFields[INDEX_OF_NEW_VALUE].trim();
        if (newValue.length() == 0) {
            TextUi.showToUser(MSG_NEW_VALUE_CANNOT_BE_EMPTY);
            return isEdited;
        } else {
            switch (field) {
            case ("date"):
                isEdited = editDateField(toBeEdited, isEdited, newValue);
                break;
            case ("amount"):
                isEdited = editAmountField(toBeEdited, isEdited, newValue);
                break;
            case ("category"):
                isEdited = editCategoryField(toBeEdited, isEdited, newValue);
                break;
            case ("remarks"):
                isEdited = editRemarksField(toBeEdited, isEdited, newValue);
                break;
            case ("done"):
                if (!fieldToEdit.equals("done")) {
                    TextUi.showToUser(MSG_ONLY_DONE);
                }
                return isEdited;
            default:
                TextUi.showToUser(MSG_INVALID_EDIT_FIELD);
                return isEdited;
            }
        }
        return isEdited;
    }

    /**
     * Modifies the Edit field of a specific expense record.
     *
     * @param toBeEdited the expense record to be edited
     * @param isEdited   boolean variable to check if new value is the same as original value
     * @param newValue   the content to replace original value in expense record
     * @return True if record has been edited, false otherwise
     */
    private static boolean editRemarksField(Expense toBeEdited, boolean isEdited, String newValue) {
        try {
            checkContainSlash(newValue);
        } catch (ExpenseSurroundSlashSpaceException e) {
            TextUi.showToUser(e.getMessage());
            return isEdited;
        }
        if (toBeEdited.getRemark().equals(newValue)) {
            TextUi.showToUser(SAME_NEW_REMARKS_SET);
        } else {
            toBeEdited.setRemark(newValue);
            isEdited = true;

            TextUi.showToUser(NEW_REMARKS_VALUE_SET);
        }
        return isEdited;
    }

    /**
     * Modifies the Category field of a specific expense record.
     *
     * @param toBeEdited the expense record to be edited
     * @param isEdited   boolean variable to check if new value is the same as original value
     * @param newValue   the content to replace original value in expense record
     * @return True if record has been edited, false otherwise
     */
    private static boolean editCategoryField(Expense toBeEdited, boolean isEdited, String newValue) {
        try {
            checkContainSlash(newValue);
        } catch (ExpenseSurroundSlashSpaceException e) {
            TextUi.showToUser(e.getMessage());
            return isEdited;
        }
        if (toBeEdited.getCategory().equals(newValue)) {
            TextUi.showToUser(SAME_NEW_CATEGORY_VALUE);
        } else {
            toBeEdited.setCategory(newValue);
            isEdited = true;
            TextUi.showToUser(NEW_CATEGORY_VALUE_SET);
        }
        return isEdited;
    }

    /**
     * Modifies the Amount field of a specific expense record.
     *
     * @param toBeEdited the expense record to be edited
     * @param isEdited   boolean variable to check if new value is the same as original value
     * @param newValue   the content to replace original value in expense record
     * @return True if record has been edited, false otherwise
     */
    private static boolean editAmountField(Expense toBeEdited, boolean isEdited, String newValue) {
        try {
            isAmountValid(newValue);
            if (toBeEdited.getAmount().equals(newValue)) {
                TextUi.showToUser(SAME_NEW_AMOUNT_VALUE);
            } else {
                toBeEdited.setAmount(newValue);
                TextUi.showToUser(NEW_AMOUNT_VALUE_SET);
                isEdited = true;
            }
            return isEdited;
        } catch (ExpenseAmountException e) {
            TextUi.showToUser(e.getMessage());
            return isEdited;
        } catch (NumberFormatException e) {
            TextUi.showToUser(MSG_NUMBERS_ONLY_AMOUNT);
            return isEdited;
        }
    }

    /**
     * Modifies the Date field of a specific expense record.
     *
     * @param toBeEdited the expense record to be edited
     * @param isEdited   boolean variable to check if new value is the same as original value
     * @param newValue   the content to replace original value in expense record
     * @return True if record has been edited, false otherwise
     */
    private static boolean editDateField(Expense toBeEdited, boolean isEdited, String newValue) {
        try {
            String newDate = reformatDate(newValue);
            if (toBeEdited.getDate().equals(newValue)) {
                TextUi.showToUser(SAME_NEW_DATE_VALUE);
            } else {
                toBeEdited.setDate(newDate);
                TextUi.showToUser(NEW_DATE_VALUE_SET);
                isEdited = true;
            }
            return isEdited;
        } catch (DateTimeParseException e) {
            TextUi.showToUser(MSG_INCORRECT_DATE_FORMAT);
            return isEdited;
        } catch (ExpenseInvalidYearException e) {
            TextUi.showToUser(e.getMessage());
            return isEdited;
        }
    }

    /**
     * Looks through the list of expense records and prints out the records that contain a specified keyword.
     *
     * @param stringToFind keyword to look for within each expense record
     */
    private static void findExpense(String stringToFind) {
        boolean isFound = false;
        stringToFind = stringToFind.toLowerCase();
        int noOfItems = Expense.getNoOfItems();
        assert noOfItems >= ZERO : ASSERT_NUMBER_OF_ITEMS_NON_NEGATIVE;
        if (noOfItems == ZERO) {
            TextUi.showToUser(MSG_EMPTY_LIST);
            return;
        }
        String listAsString = "";

        for (int i = ZERO; i < noOfItems; i++) {
            Expense expense = expenseList.get(i);
            assert expense != null : ASSERT_EXPENSE_OBJECT_NOT_NULL;
            String expenseCategory = expense.getCategory().toLowerCase();
            String expenseDate = expense.getDate().toLowerCase();
            String expenseRemark = expense.getRemark().toLowerCase();
            boolean isFoundInCategory = expenseCategory.contains(stringToFind);
            boolean isFoundInDate = expenseDate.contains(stringToFind);
            boolean isFoundInRemarks = expenseRemark.contains(stringToFind);
            if (isFoundInDate || isFoundInCategory || isFoundInRemarks) {
                String expenseRecord = String.format(" %d. %s\n", i + EXPENSE_INDEX, expense);
                listAsString = listAsString.concat(expenseRecord);
                isFound = true;
            }
        }
        if (!isFound) {
            TextUi.showToUser(NO_TASKS_FOUND);
            return;
        }
        TextUi.showToUser(MSG_MATCHING_EXPENSES + listAsString);
    }


    /**
     * Begins executing the Delete method invoked by user's input.
     *
     * @param rawInput the user's input itself
     */
    private static void executeRemove(String rawInput) {
        int index = -1;
        try {
            index = parseDeleteExpense(rawInput);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, LOG_EMPTY_INDEX);
            TextUi.showToUser(MSG_EMPTY_INDEX);
            return;
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, LOG_INVALID_INDEX_TYPE);
            TextUi.showToUser(MSG_INVALID_INDEX_TYPE);
            return;
        }
        try {
            deleteExpense(expenseList, index);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, LOG_INDEX_OUT_OF_BOUNDS);
            TextUi.showToUser(MSG_ITEM_NOT_FOUND);
        }
    }

    /**
     * Begins executing the Add method invoked due to user's input.
     *
     * @param rawInput the user's input itself
     */
    private static void executeAdd(String rawInput, boolean fromCommandLine) {

        try {
            String[] newExpense = parseNewExpense(rawInput);
            assert newExpense != null : ASSERT_EXPENSE_OBJECT_NOT_NULL;
            Expense expense = new Expense(newExpense[DATE_INDEX], newExpense[AMOUNT_INDEX],
                    newExpense[CATEGORY_INDEX], newExpense[REMARKS_INDEX]);
            addExpense(expenseList, expense, fromCommandLine);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, LOG_MISSING_PARAMETERS);
            TextUi.showToUser(MSG_MISSING_FIELDS);
        } catch (DateTimeParseException e) {
            logger.log(Level.WARNING, LOG_INCORRECT_DATE_FIELD);
            TextUi.showToUser(MSG_INCORRECT_DATE_FORMAT);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, LOG_INVALID_AMOUNT);
            TextUi.showToUser(MSG_NUMBERS_ONLY_AMOUNT);
        } catch (ExpenseAmountException e) {
            logger.log(Level.WARNING, LOG_NEGATIVE_AMOUNT);
            TextUi.showToUser(e.getMessage());
        } catch (ExpenseMissingFieldException e) {
            logger.log(Level.WARNING, LOG_EMPTY_FIELD);
            TextUi.showToUser(e.getMessage());
        } catch (ExpenseEmptyFieldException e) {
            logger.log(Level.WARNING, LOG_EMPTY_FIELD);
            TextUi.showToUser(e.getMessage());
        } catch (ExpenseExtraFieldException e) {
            logger.log(Level.WARNING, LOG_EXTRA_FIELDS);
            TextUi.showToUser(e.getMessage());
        } catch (ExpenseSurroundSlashSpaceException e) {
            logger.log(Level.WARNING, LOG_INVALID_SLASH);
            TextUi.showToUser(e.getMessage());
        } catch (ExpenseInvalidYearException e) {
            logger.log(Level.WARNING, LOG_NEGATIVE_YEAR);
            TextUi.showToUser(e.getMessage());
        }

    }

    /**
     * Executes <code>executeAdd</code> method with saved expense entry from data file.
     *
     * @param savedExpense the saved expense entry
     */
    public void loadAdd(String savedExpense) {
        executeAdd(savedExpense, false);
    }

    /**
     * Begins executing the Find method invoked due to user's input.
     *
     * @param rawInput the user's input itself
     */
    private static void executeFind(String rawInput) {
        String stringToFind = "";
        try {
            stringToFind = parseFindExpense(rawInput);
        } catch (IndexOutOfBoundsException e) {
            logger.log(Level.WARNING, LOG_INDEX_OUT_OF_BOUNDS);
            TextUi.showToUser(MSG_NONEMPTY_KEYWORD);
            return;
        }
        findExpense(stringToFind);
    }

    /**
     * Begins executing the Edit method invoked due to user's input.
     *
     * @param ui       ui object to collect user's inputs
     * @param rawInput the user's input itself
     */
    private static void executeEdit(TextUi ui, String rawInput) {
        int index;
        int noOfItems = Expense.getNoOfItems();
        if (noOfItems == 0) {
            TextUi.showToUser(MSG_EMPTY_LIST);
        } else {
            try {
                index = parseEditExpense(rawInput);
            } catch (IndexOutOfBoundsException e) {
                logger.log(Level.WARNING, LOG_INDEX_OUT_OF_BOUNDS);
                TextUi.showToUser(MSG_EMPTY_INDEX);
                return;
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, LOG_INVALID_INDEX_TYPE);
                TextUi.showToUser(MSG_INVALID_INDEX_TYPE);
                return;
            }
            try {
                editExpense(expenseList, index, ui);
            } catch (IndexOutOfBoundsException e) {
                logger.log(Level.WARNING, LOG_INDEX_OUT_OF_BOUNDS);
                TextUi.showToUser(MSG_ITEM_NOT_FOUND);
                return;
            }
        }
    }

    /**
     * Prints a message to inform user they are already in the Expense Tracker.
     */
    private static void printAlreadyInExpenseTrackerMessage(TextUi ui) {
        ui.showToUser(ALREADY_IN_EXPENSE_TRACKER_MESSAGE);
    }

    /**
     * Returns mode of study or contacts manager if the command pertaining to these managers
     * is contained in <code>userInput</code> else returns an unchanged <code>mode</code> value.
     *
     * @param ui        instance of TextUi used for displaying messages to user.
     * @param mode      contains the current value of mode.
     * @param userInput String containing input from user.
     * @return new value of mode.
     */
    public static Mode getMode(TextUi ui, Mode mode, String userInput) {
        if (AllOnUs.isContactsManagerCommand(userInput)) {
            return Mode.CONTACTS_MANAGER;
        } else if (AllOnUs.isStudyManagerCommand(userInput)) {
            return Mode.STUDY_MANAGER;
        } else if (AllOnUs.isExpenseTrackerCommand(userInput)) {
            printAlreadyInExpenseTrackerMessage(ui);
            return Mode.EXPENSE_TRACKER;
        }
        return mode;
    }

    /**
     * Determines which command to execute depending on the keyword supplied.
     *
     * @param ui ui object to collect user's inputs
     * @return mode value pertaining to either menu, study or contact manager.
     */
    public static Mode expenseRunner(TextUi ui) {
        logger.setLevel(Level.SEVERE);
        expenseWelcome();
        String rawInput = ui.getUserInput();

        Mode mode = Mode.MENU;
        boolean isGotoExpenseCommand = false;
        mode = getMode(ui, mode, rawInput);
        if ((mode == Mode.CONTACTS_MANAGER) || (mode == Mode.STUDY_MANAGER)) {
            return mode;
        } else if (mode == Mode.EXPENSE_TRACKER) {
            isGotoExpenseCommand = true;
            mode = Mode.MENU;
        }

        assert rawInput != null : ASSERT_INPUT_NOT_NULL;
        String[] userParameters = rawInput.split(" ", SPLIT_INTO_HALF);
        String firstWord = userParameters[KEYWORD_INDEX];
        String keyWord = firstWord.trim().toLowerCase();
        while (!(keyWord.equals(MENU_STRING))) {
            isModified = false;
            switch (keyWord) {
            case KEYWORD_LIST:
                if (!rawInput.equals(KEYWORD_LIST)) {
                    logger.log(Level.WARNING, LOG_INVALID_COMMANDS);
                    TextUi.showToUser(MSG_INVALID_COMMANDS);
                    break;
                }
                listExpenses();
                break;
            case KEYWORD_REMOVE:
                executeRemove(rawInput);
                break;
            case KEYWORD_ADD:
                executeAdd(rawInput, true);
                break;
            case KEYWORD_EDIT:
                executeEdit(ui, rawInput);
                break;
            case KEYWORD_FIND:
                executeFind(rawInput);
                break;
            case KEYWORD_BLANK:
                break;
            default:
                if (isGotoExpenseCommand) {
                    isGotoExpenseCommand = false;
                    break;
                }
                logger.log(Level.WARNING, LOG_INVALID_COMMANDS);
                TextUi.showToUser(MSG_INVALID_COMMANDS);
            }
            rawInput = ui.getUserInput();
            keyWord = rawInput.split(" ", SPLIT_INTO_HALF)[KEYWORD_INDEX].trim();
            if (isModified) {
                storageFile.saveData();
            }

            mode = getMode(ui, mode, rawInput);
            if ((mode == Mode.CONTACTS_MANAGER) || (mode == Mode.STUDY_MANAGER)) {
                return mode;
            } else if (mode == Mode.EXPENSE_TRACKER) {
                isGotoExpenseCommand = true;
                mode = Mode.MENU;
            }
        }
        logger.log(Level.INFO, LOG_RETURN_TO_MENU_INTENT);
        return mode;
    }
}
