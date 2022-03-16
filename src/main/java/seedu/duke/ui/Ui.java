package seedu.duke.ui;

import java.util.Scanner;
import static seedu.duke.common.Messages.WELCOME_MESSAGE;
import static seedu.duke.common.Messages.HELP_MESSAGE;
import static seedu.duke.common.Messages.DIVIDER;
import seedu.duke.exceptions.InvMgrException;

/**
 * Handles the UI of the application
 * */
public class Ui {

    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays message(s) to user
     *
     * @param message Message to be displayed to user
     * */
    public void showMessages(String... message) {
        for (String m : message) {
            System.out.println(m);
        }
    }

    /**
     * Prints the welcome message when user starts InvMgr.
     */
    public void showWelcomeMessage() {
        showMessages(
                DIVIDER,
                WELCOME_MESSAGE,
                DIVIDER,
                HELP_MESSAGE,
                DIVIDER);
    }

    /**
     * Shows dividing line between messages
     * */
    public void showDivider() {
        showMessages(DIVIDER);
    }

    /**
     * Gets user's raw input in the CLI
     * */
    public String getRawUserInput() {
        System.out.println("Enter command: ");
        return scanner.nextLine();
    }

    public void showError(InvMgrException e) {
        System.out.println(e.getMessage());
    }
}
