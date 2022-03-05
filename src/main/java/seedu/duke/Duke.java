package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.data.Profile;
import seedu.duke.parser.Parser;
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
        exitApplication();
    }

    /** Prints welcome message. */
    private void showWelcomeMessage() {
        ui.printWelcome();
    }

    /** Exits the program. */
    private void exitApplication() {
        System.exit(0);
    }

    /** Reads the user input until the user enters the exit command.  */
    private void runProcessLoop() {
        Command command;
        do {
            String userInput = ui.readNextLine();
            command = Parser.getCommand(userInput);
            command.run(ui, profile);
        } while (!ExitCommand.isExitCommand(command));
    }
}
