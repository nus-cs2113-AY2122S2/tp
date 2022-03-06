package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.ui.TextUi;

public class Main {

    private TextUi ui;
    
    /**
     * Main entry-point for the java.duke.Duke application.
     * See <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">addressbook-level2</a>
     */
    public static void main(String[] args) {
        new Main().run(args);
    }

    /**
     * Runs the program until termination.
     * See <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">addressbook-level2</a>
     **/
    public void run(String[] args) {
        start(args);
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects.
     *
     * @param args arguments supplied by the user at program launch.
     *
     */
    private void start(String[] args) {
        try {
            this.ui = new TextUi();
            ui.showHelloMessage();
        } catch (ModHappyException e) {
            ui.showInitFailedMessage();
        }
    }

    /**
     * Reads the user command and executes it, until the user calls the exit command.
     **/
    private void runCommandLoopUntilExitCommand() {
        Command command;
        String userCommandText;
        do {
            userCommandText = ui.getUserCommand();
            // To be optimised later
            ui.showMessage(userCommandText);
        } while (!userCommandText.equals("exit"));
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        ui.showGoodByeMessage();
        System.exit(0);
    }


}
