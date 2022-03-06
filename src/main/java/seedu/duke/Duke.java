package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.data.Manager;
import seedu.duke.data.Profile;
import seedu.duke.parser.Parser;
import seedu.duke.ui.TextUI;

/**
 * Entry point of the SplitLah application.
 * Initializes SplitLah and starts interacting with the user.
 */
public class Duke {
    Manager manager;

    public static void main(String[] args) {
        new Duke().run();
    }

    /** Sets up the required objects for application.   */
    public Duke() {
        manager = new Manager();
    }

    /** Runs the program until it terminates.  */
    private void run() {
        showWelcomeMessage();
        runProcessLoop();
        exitApplication();
    }

    /** Prints welcome message. */
    private void showWelcomeMessage() {
        manager.getUi().printWelcome();
    }

    /** Exits the program. */
    private void exitApplication() {
        System.exit(0);
    }

    /** Reads the user input until the user enters the exit command.  */
    private void runProcessLoop() {
        Command command;
        do {
            String userInput = manager.getUi().readNextLine();
            command = Parser.getCommand(userInput);
            command.run(manager);
        } while (!Command.isExitCommand(command));
    }
}
