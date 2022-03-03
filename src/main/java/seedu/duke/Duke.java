package seedu.duke;

import seedu.duke.data.Profile;
import seedu.duke.ui.TextUI;

/**
 * Entry point of the SplitLah application.
 * Initializes SplitLah and starts interacting with the user.
 */
public class Duke {
    TextUI ui;
    Profile profile;

    public static void main(String[] args) {
        new Duke().run();
    }

    /** Sets up the required objects for application.   */
    public Duke() {
        ui = new TextUI();
        profile = new Profile();
    }

    /** Runs the program until it terminates.  */
    private void run() {
        showWelcomeMessage();
        runProcessLoop();
        showFarewellMessage();
    }

    /** Prints welcome message. */
    private void showWelcomeMessage() {
        ui.printWelcome();
    }

    /** Shows farewell message and exits the program. */
    private void showFarewellMessage() {
        ui.printFarewell();
        System.exit(0);
    }

    /** Reads the user input until the user enters the bye command.  */
    private void runProcessLoop() {
        String userInput;
        do {
            userInput = ui.readNextLine();
        } while (userInput.contains("Bye"));
    }
}
