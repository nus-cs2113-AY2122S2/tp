//@@author OzairHasan

package seedu.allonus;

import seedu.allonus.contacts.ContactsManager;
import seedu.allonus.expense.ExpenseTracker;

import seedu.allonus.mode.Mode;
import seedu.allonus.storage.StorageFile;

import seedu.allonus.ui.TextUi;

import seedu.allonus.modules.StudyManager;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Runs the main menu functionality of the AllOnUs application and is also where the main method is located.
 */
public class AllOnUs {
    public static final String FILE_NAME = "allonusData.txt";
    public static final String MENU_LOGGER_NAME = "MenuLog";
    public static final String LOG_GREETING_MESSAGE = "Printing greeting message.";
    public static final String GREETING_MESSAGE = "Welcome to the AllOnUs life management suite. "
            + "How can we help you today?";
    public static final String GOODBYE_MESSAGE = "Goodbye! Hope to see you again...";
    public static final String LOG_EXIT = "Exiting program.";
    public static final String CONTACTS_MANAGER_COMMAND = "goto m/Contacts_Manager";
    public static final String STUDY_MANAGER_COMMAND = "goto m/Study_Manager";
    public static final String EXPENSE_TRACKER_COMMAND = "goto m/Expense_Tracker";
    public static final String EXIT_COMMAND = "exit";
    public static final int LENGTH_OF_EMPTY_COMMAND = 0;
    public static final String HELP_COMMAND = "help";
    public static final String LOG_DISPLAY_OF_HELP_SECTION = "Displaying help section for main menu features.";
    public static final String MAIN_MENU_FEATURES_TEXT = "Going from menu to section of interest:\n\nAllows "
            + "access to subsections of the application, namely, to the expense tracker, "
            + "contacts manager and task manager.\n\nFormat: goto m/SECTION\n\nExamples:"
            + "\n\n- goto m/Expense_Tracker\n- goto m/Study_Manager\n- goto m/Contacts_Manager"
            + "\n\n\nGoing back to menu:\n\nAllows navigation back to the menu section of the application. "
            + "\n\nFormat: menu\n\nExample:\n\n- menu"
            + "\n\n\nGetting guidance on the usage of the application:\n\nDisplays user guide."
            + "\n\nFormat: help\n\nExample:\n\n- help"
            + "\n\n\nExiting the application:\n\nTerminates the application.\n\nFormat: exit"
            + "\n\nExample:\n\n- exit";
    public static final String MENU_HEADER = "MENU:";
    public static final String LOG_NO_SUCH_ELEMENT_EXCEPTION = "NoSuchElementException triggered when "
            + "scanning for user input. This should not happen. Investigation required.";
    public static final String LOG_ILLEGAL_STATE_EXCEPTION = "IllegalStateException triggered when "
            + "scanning for user input. Scanner object on input stream should not be closed. "
            + "Investigation required.";
    public static final String LOG_EXIT_COMMAND = "User entered exit command.";
    public static final String LOG_HELP_COMMAND = "User entered help command.";
    public static final String LOG_CONTACTS_MANAGER_COMMAND = "User entered command to navigate to Contacts Manager.";
    public static final String LOG_USER_IN_MENU = "User back in Menu.";
    public static final String LOG_STUDY_MANAGER_COMMAND = "User entered command to navigate to Study Manager.";
    public static final String LOG_EXPENSE_TRACKER_COMMAND = "User entered command to navigate to Expense Tracker.";
    public static final String LOG_INVALID_COMMAND = "User entered invalid command.";
    public static final String LOG_EMPTY_COMMAND = "User entered empty command.";
    public static final String ASSERT_SHOULD_BE_EXIT_COMMAND = "Loop broke without an exit command from user.";
    public static final String INVALID_COMMAND_MESSAGE = "Oops I am not sure what you mean by this command. "
            + "Please retry. Enter \"help\" for guidance.";
    public static final String RETURN_TO_MENU_MESSAGE = "Welcome back to the main menu";
    public static final String LOG_FIRST_ENTRY_TO_MENU = "Entering menu for the first time.";
    public static final String LOG_MENU_COMMAND_IN_MENU = "User entered menu command while within menu.";
    public static final String ALREADY_IN_MENU_MESSAGE = "You are already in the menu. Please try another command.";
    public static final String MENU_COMMAND = "menu";
    public static final String LOG_DIRECT_ACCESS_TO_CONTACTS_MANAGER = "Accessing ContactsManager Runner through a "
            + "tracker or manager, not from menu.";
    public static final String LOG_DIRECT_ACCESS_TO_STUDY_MANAGER = "Accessing StudyManager Runner through a "
            + "tracker or manager, not from menu.";
    public static final String LOG_DIRECT_ACCESS_TO_EXPENSE_TRACKER = "Accessing ExpenseTracker Runner through a "
            + "tracker or manager, not from menu.";
    public static final String ASSERT_MODE_MENU = "The mode value should be 0 at this point.";

    private TextUi ui;
    private static Logger logger = Logger.getLogger(MENU_LOGGER_NAME);


    /**
     * Displays the greeting message of this program.
     */
    private void greet() {
        logger.log(Level.INFO, LOG_GREETING_MESSAGE);
        ui.showToUser(GREETING_MESSAGE);
    }

    /**
     * Carries out termination of the program.
     */
    private void exit() {
        logger.log(Level.INFO, LOG_EXIT);
        ui.showToUser(GOODBYE_MESSAGE);
    }

    /**
     * Checks whether <code>userInput</code> is the command contained in string
     * <code>command</code> or not.
     *
     * @param userInput String containing user input.
     * @param command String containing command to be compared with.
     * @return <code>true</code> if inputs are equal to each other else <code>false</code>.
     */
    public static boolean areEqual(String userInput, String command) {
        String lowerCaseUI = userInput.toLowerCase();
        String lowerCaseCommand = command.toLowerCase();
        if (!lowerCaseUI.equals(lowerCaseCommand)) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether <code>userInput</code> is a "goto m/Contacts_Manager" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is a "goto m/Contacts_Manager" command else <code>false</code>.
     */
    public static boolean isContactsManagerCommand(String userInput) {
        return areEqual(userInput, CONTACTS_MANAGER_COMMAND);
    }

    /**
     * Checks whether <code>userInput</code> is a "goto m/Study_Manager" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is a "goto m/Study_Manager" command else <code>false</code>.
     */
    public static boolean isStudyManagerCommand(String userInput) {
        return areEqual(userInput, STUDY_MANAGER_COMMAND);
    }

    /**
     * Checks whether <code>userInput</code> is a "goto m/Expense_Tracker" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is a "goto m/Expense_Tracker" command else <code>false</code>.
     */
    public static boolean isExpenseTrackerCommand(String userInput) {
        return areEqual(userInput, EXPENSE_TRACKER_COMMAND);
    }

    /**
     * Checks whether <code>userInput</code> is an "exit" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is an "exit" command else <code>false</code>.
     */
    public static boolean isExitCommand(String userInput) {
        return areEqual(userInput, EXIT_COMMAND);
    }

    /**
     * Checks whether <code>userInput</code> is empty or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if length of string <code>userInput</code> is not 0 else <code>false</code>.
     */
    public static boolean isNotEmpty(String userInput) {
        return userInput.length() > LENGTH_OF_EMPTY_COMMAND;
    }

    /**
     * Checks whether <code>userInput</code> is a "help" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is a "help" command else <code>false</code>.
     */
    public static boolean isHelpCommand(String userInput) {
        return areEqual(userInput, HELP_COMMAND);
    }

    /**
     * Checks whether <code>userInput</code> is a "menu" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is a "menu" command else <code>false</code>.
     */
    public static boolean isMenuCommand(String userInput) {
        return areEqual(userInput, MENU_COMMAND);
    }

    public void displayHelp() {
        logger.log(Level.INFO, LOG_DISPLAY_OF_HELP_SECTION);
        displayMainMenuFeatures();
    }

    /**
     * Checks whether <code>mode</code> is mode for contacts manager.
     *
     * @param mode String containing user input.
     * @return <code>true</code> if <code>mode</code> is mode for contacts manager
     *     else <code>false</code>.
     */
    public static boolean isContactsManagerMode(Mode mode) {
        return mode == Mode.CONTACTS_MANAGER;
    }

    /**
     * Checks whether <code>mode</code> is mode for contacts manager.
     *
     * @param mode String containing user input.
     * @return <code>true</code> if <code>mode</code> is mode for contacts manager
     *     else <code>false</code>.
     */
    public static boolean isStudyManagerMode(Mode mode) {
        return mode == Mode.STUDY_MANAGER;
    }

    /**
     * Checks whether <code>mode</code> is mode for contacts manager.
     *
     * @param mode String containing user input.
     * @return <code>true</code> if <code>mode</code> is mode for contacts manager
     *     else <code>false</code>.
     */
    public static boolean isExpenseTrackerMode(Mode mode) {
        return mode == Mode.EXPENSE_TRACKER;
    }

    /**
     * Prints a help message that can guide the user.
     */
    public void displayMainMenuFeatures() {
        ui.showToUser(MAIN_MENU_FEATURES_TEXT);
    }

    /**
     * Runs the functionality of the main menu of the program.
     *
     * @see IllegalStateException
     * @see NoSuchElementException
     */
    public void run() {
        this.ui = new TextUi();
        String userInput;

        ContactsManager contactsManager = new ContactsManager();
        StudyManager studyManager = new StudyManager();
        ExpenseTracker expenseTracker = new ExpenseTracker();

        StorageFile.setFields(contactsManager, expenseTracker, studyManager, FILE_NAME);
        StorageFile storageFile = new StorageFile();
        storageFile.loadData();

        logger.log(Level.INFO, LOG_FIRST_ENTRY_TO_MENU);
        Mode mode = Mode.MENU;

        while (true) {

            if (isContactsManagerMode(mode)) {
                logger.log(Level.INFO, LOG_DIRECT_ACCESS_TO_CONTACTS_MANAGER);
                mode = contactsManager.contactsRunner(ui);
                printMainMenuMessage(mode);
                continue;
            } else if (isStudyManagerMode(mode)) {
                logger.log(Level.INFO, LOG_DIRECT_ACCESS_TO_STUDY_MANAGER);
                mode = studyManager.studyManagerRunner(ui);
                printMainMenuMessage(mode);
                continue;
            } else if (isExpenseTrackerMode(mode)) {
                logger.log(Level.INFO, LOG_DIRECT_ACCESS_TO_EXPENSE_TRACKER);
                mode = expenseTracker.expenseRunner(ui);
                printMainMenuMessage(mode);
                continue;
            }
            assert mode == Mode.MENU : ASSERT_MODE_MENU;

            ui.showToUser(MENU_HEADER);
            try {
                userInput = ui.getUserInput();
            } catch (NoSuchElementException e) {
                logger.log(Level.WARNING, LOG_NO_SUCH_ELEMENT_EXCEPTION);
                continue;
            } catch (IllegalStateException e) {
                logger.log(Level.WARNING, LOG_ILLEGAL_STATE_EXCEPTION);
                continue;
            }

            if (isExitCommand(userInput)) {
                logger.log(Level.INFO, LOG_EXIT_COMMAND);
                break;
            } else if (isHelpCommand(userInput)) {
                logger.log(Level.INFO, LOG_HELP_COMMAND);
                displayHelp();
            } else if (isContactsManagerCommand(userInput)) {
                logger.log(Level.INFO, LOG_CONTACTS_MANAGER_COMMAND);
                mode = contactsManager.contactsRunner(ui);

                logger.log(Level.INFO, LOG_USER_IN_MENU);
                printMainMenuMessage(mode);
            } else if (isStudyManagerCommand(userInput)) {
                logger.log(Level.INFO, LOG_STUDY_MANAGER_COMMAND);
                mode = studyManager.studyManagerRunner(ui);

                logger.log(Level.INFO, LOG_USER_IN_MENU);
                printMainMenuMessage(mode);
            } else if (isExpenseTrackerCommand(userInput)) {
                logger.log(Level.INFO, LOG_EXPENSE_TRACKER_COMMAND);
                mode = expenseTracker.expenseRunner(ui);

                logger.log(Level.INFO, LOG_USER_IN_MENU);
                printMainMenuMessage(mode);
            } else if (isMenuCommand(userInput)) {
                logger.log(Level.INFO, LOG_MENU_COMMAND_IN_MENU);
                logger.log(Level.INFO, LOG_USER_IN_MENU);
                printAlreadyInMenuMessage();
            } else if (isNotEmpty(userInput)) {
                logger.log(Level.INFO, LOG_INVALID_COMMAND);
                printInvalidMainMenuCommandMessage();
            } else {
                logger.log(Level.INFO, LOG_EMPTY_COMMAND);
                continue;
            }
        }
        assert userInput != EXIT_COMMAND : ASSERT_SHOULD_BE_EXIT_COMMAND;
    }

    /**
     * Prints a guiding message that addresses an invalid user command.
     */
    private void printInvalidMainMenuCommandMessage() {
        ui.showToUser(INVALID_COMMAND_MESSAGE);
    }

    /**
     * Prints a message to inform user they are already in the menu.
     */
    private void printAlreadyInMenuMessage() {
        ui.showToUser(ALREADY_IN_MENU_MESSAGE);
    }

    /**
     * Prints a message that represents what the users will read upon return to menu.
     * @param mode contains value pertaining to whether current mode is for menu, for either
     *     of the managers or for the tracker.
     */
    private void printMainMenuMessage(Mode mode) {
        if (mode == Mode.MENU) {
            ui.showToUser(RETURN_TO_MENU_MESSAGE);
        }
    }

    /**
     * Main entry-point for the seedu.allonus.AllOnUs application.
     */
    public static void main(String[] args) {
        logger.setLevel(Level.WARNING);
        AllOnUs allonus = new AllOnUs();

        allonus.greet();

        allonus.run();

        allonus.exit();
    }
}

//@@author
