package seedu.allonus;

import seedu.allonus.contacts.ContactsManager;
import seedu.allonus.ui.TextUi;

import static seedu.allonus.expense.ExpenseTracker.expenseRunner;

import seedu.allonus.ui.TextUi;
import seedu.allonus.modules.StudyManager;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Runs the main menu functionality of the AllOnUs application and is also where the main method is located.
 */
public class AllOnUs {
    private TextUi ui;
    private static Logger logger = Logger.getLogger("MenuLog");


    /**
     * Displays the greeting message of this program.
     */
    private static void greet() {
        logger.log(Level.INFO, "Printing greeting message.");
        System.out.println("Welcome to the AllOnUs life management suite. How can we help you today?");
    }

    /**
     * Carries out termination of the program.
     */
    private void exit() {
        System.out.println("Goodbye! Hope to see you again...");
        logger.log(Level.INFO, "Exiting program.");
        System.exit(0);
    }

    /**
     * Checks whether <code>userInput</code> is a "goto m/Contacts_Manager" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is a "goto m/Contacts_Manager" command else <code>false</code>.
     */
    public static boolean isContactsManagerCommand(String userInput) {
        return userInput.equals("goto m/Contacts_Manager");
    }

    /**
     * Checks whether <code>userInput</code> is a "goto m/Study_Manager" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is a "goto m/Study_Manager" command else <code>false</code>.
     */
    public static boolean isStudyManagerCommand(String userInput) {
        return userInput.equals("goto m/Study_Manager");
    }

    /**
     * Checks whether <code>userInput</code> is a "goto m/Expense_Tracker" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is a "goto m/Expense_Tracker" command else <code>false</code>.
     */
    public static boolean isExpenseTrackerCommand(String userInput) {
        return userInput.equals("goto m/Expense_Tracker");
    }

    /**
     * Checks whether <code>userInput</code> is an "exit" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is an "exit" command else <code>false</code>.
     */
    public static boolean isExitCommand(String userInput) {
        return userInput.equals("exit");
    }

    /**
     * Checks whether <code>userInput</code> is empty or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if length of string <code>userInput</code> is not 0 else <code>false</code>.
     */
    public static boolean isNotEmpty(String userInput) {
        return userInput.length() > 0;
    }

    /**
     * Checks whether <code>userInput</code> is a "help" command or not.
     *
     * @param userInput String containing user input.
     * @return <code>true</code> if is a "help" command else <code>false</code>.
     */
    public static boolean isHelpCommand(String userInput) {
        return userInput.equals("help");
    }

    public static void displayHelp() {
        logger.log(Level.INFO, "Displaying help section for main menu features.");
        displayMainMenuFeatures();
    }

    /**
     * Prints a help message that can guide the user.
     */
    private static void displayMainMenuFeatures() {
        System.out.println("Going from menu to section of interest:\n\nAllows "
                + "access to subsections of the application, namely, to the expense tracker, "
                + "contacts manager and task manager.\n\nFormat: goto m/SECTION\n\nExamples:"
                + "\n\n- goto m/Expense_Tracker\n- goto m/Study_Manager\n- goto m/Contacts_Manager"
                + "\n\n\nGoing back to menu:\n\nAllows navigation back to the menu section of the application. "
                + "\n\nFormat: menu\n\nExample:\n\n- menu"
                + "\n\n\nGetting guidance on the usage of the application:\n\nDisplays user guide."
                + "\n\nFormat: help\n\nExample:\n\n- help"
                + "\n\n\nExiting the application:\n\nTerminates the application.\n\nFormat: exit"
                + "\n\nExample:\n\n- exit");
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

        while (true) {
            System.out.println("Menu:");
            try {
                userInput = ui.getUserInput();
            } catch (NoSuchElementException e) {
                logger.log(Level.WARNING, "NoSuchElementException triggered when scanning for user input. "
                        + "This should not happen. Investigation required.");
                continue;
            } catch (IllegalStateException e) {
                logger.log(Level.WARNING, "IllegalStateException triggered when scanning for user input. "
                        + "Scanner object on input stream should not be closed. Investigation required.");
                continue;
            }

            if (isExitCommand(userInput)) {
                logger.log(Level.INFO, "User entered exit command.");
                exit();
            } else if (isHelpCommand(userInput)) {
                logger.log(Level.INFO, "User entered help command.");
                displayHelp();
            } else if (isContactsManagerCommand(userInput)) {
                logger.log(Level.INFO, "User entered command to navigate to Contacts Manager.");
                contactsManager.contactsRunner(ui);

                logger.log(Level.INFO, "User back in Menu.");
                printMainMenuMessage();
            } else if (isStudyManagerCommand(userInput)) {
                logger.log(Level.INFO, "User entered command to navigate to Study Manager.");
                studyManager.studyManagerRunner(ui);

                logger.log(Level.INFO, "User back in Menu.");
                printMainMenuMessage();
            } else if (isExpenseTrackerCommand(userInput)) {
                logger.log(Level.INFO, "User entered command to navigate to Expense Tracker.");
                expenseRunner(ui);

                logger.log(Level.INFO, "User back in Menu.");
                printMainMenuMessage();
            } else if (isNotEmpty(userInput)) {
                logger.log(Level.INFO, "User entered invalid command.");
                printInvalidMainMenuCommandMessage();
            } else {
                logger.log(Level.INFO, "User entered empty command.");
                continue;
            }
        }
    }

    /**
     * Prints a guiding message that addresses an invalid user command.
     */
    private void printInvalidMainMenuCommandMessage() {
        System.out.println("Oops I am not sure what you mean by this command. Please retry. '"
                + "Enter \"help\" for guidance.");
    }

    /**
     * Prints a message that represents what the users will read upon return to menu.
     */
    private void printMainMenuMessage() {
        System.out.println("Welcome back to the main menu");
    }

    /**
     * Main entry-point for the seedu.allonus.AllOnUs application.
     */
    public static void main(String[] args) {
        logger.setLevel(Level.WARNING);
        greet();

        logger.log(Level.INFO, "Entering menu for the first time.");
        new AllOnUs().run();
    }
}
