package seedu.duke;

import seedu.duke.ui.TextUi;

import static seedu.duke.contacts.ContactsManager.contactsRunner;
import static seedu.duke.expense.ExpenseTracker.expenseRunner;

public class Duke {
    private TextUi ui;

    private static void greet() {
        System.out.println("greeting message");
    }

    private void exit() {
        System.out.println("exit message");
        System.exit(0);
    }

    public static boolean isContacts(String userInput) {
        return userInput.equals("goto m/Contacts_Manager");
    }

    public static boolean isExpense(String userInput) {
        return userInput.equals("goto m/Expense_Tracker");
    }

    public void run() {
        this.ui = new TextUi();
        String userInput;

        while (true) {
            System.out.println("Menu:");
            userInput = ui.getUserInput();

            if (userInput.equals("exit")) {
                exit();
            } else if (isContacts(userInput)) {
                contactsRunner(ui);
                System.out.println("Welcome back to the main menu");
            } else if (isExpense(userInput)) {
                expenseRunner(ui);
                System.out.println("Welcome back to the main menu");
            } else {
                continue;
            }
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        greet();
        new Duke().run();
    }
}
