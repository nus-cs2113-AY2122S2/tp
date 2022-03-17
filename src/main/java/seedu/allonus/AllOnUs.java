package seedu.allonus;

import seedu.allonus.contacts.ContactsManager;
import seedu.allonus.ui.TextUi;

import static seedu.allonus.expense.ExpenseTracker.expenseRunner;
import seedu.allonus.modules.StudyManager;

public class AllOnUs {
    private TextUi ui;

    private static void greet() {
        System.out.println("greeting message");
    }

    private void exit() {
        System.out.println("Goodbye! Hope to see you again...");
        System.exit(0);
    }

    public static boolean isContactsManagerCommand(String userInput) {
        return userInput.equals("goto m/Contacts_Manager");
    }

    public static boolean isStudyManagerCommand(String userInput) {
        return userInput.equals("goto m/Study_Manager");
    }

    public static boolean isExpenseTrackerCommand(String userInput) {
        return userInput.equals("goto m/Expense_Tracker");
    }

    public static boolean isExitCommand(String userInput) {
        return userInput.equals("exit");
    }

    public static boolean isNotEmpty(String userInput) {
        return userInput.length() > 0;
    }

    public static boolean isHelpCommand(String userInput) {
        return userInput.equals("help");
    }

    public static void displayHelp() {
        displayMainMenuFeatures();
    }

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

    public void run() {
        this.ui = new TextUi();
        String userInput;

        ContactsManager contactsManager = new ContactsManager();
        StudyManager studyManager = new StudyManager();

        while (true) {
            System.out.println("Menu:");
            userInput = ui.getUserInput();

            if (isExitCommand(userInput)) {
                exit();
            } else if (isHelpCommand(userInput)) {
                displayHelp();
            } else if (isContactsManagerCommand(userInput)) {
                contactsManager.contactsRunner(ui);
                printMainMenuMessage();
            } else if (isStudyManagerCommand(userInput)) {
                studyManager.studyManagerRunner(ui);
                printMainMenuMessage();
            } else if (isExpenseTrackerCommand(userInput)) {
                expenseRunner(ui);
                printMainMenuMessage();
            } else if (isNotEmpty(userInput)) {
                printInvalidMainMenuCommandMessage();
            } else {
                continue;
            }
        }
    }

    private void printInvalidMainMenuCommandMessage() {
        System.out.println("Oops I am not sure what you mean by this command. Please retry. '"
                + "Enter \"help\" for guidance.");
    }

    private void printMainMenuMessage() {
        System.out.println("Welcome back to the main menu");
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        greet();
        new AllOnUs().run();
    }
}
