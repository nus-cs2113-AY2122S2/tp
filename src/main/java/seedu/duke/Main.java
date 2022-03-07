package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.parsers.ModHappyParser;
import seedu.duke.ui.TextUi;

public class Main {

    private static final String EXIT_COMMAND_WORD = "exit";
    private static final String LIST_COMMAND_WORD = "list";
    private static final String MARK_COMMAND_WORD = "mark";
    private static final String ADD_COMMAND_WORD = "add";
    private static final String DELETE_COMMAND_WORD = "del";

    private TextUi ui;
    private ModHappyParser modHappyParser;

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
            this.modHappyParser = new ModHappyParser();
        } catch (ModHappyException e) {
            ui.showInitFailedMessage();
        }
    }

    /**
     * Reads the user command and executes it, until the user calls the exit command.
     * See <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">addressbook-level2</a>
     **/
    private void runCommandLoopUntilExitCommand() {
        Command command = null;
        String userCommandText;
        do {
            try {
                userCommandText = ui.getUserCommand();
                command = modHappyParser.parseCommand(userCommandText);
                CommandResult result = command.execute();
                ui.showMessage(result.toString());
            } catch (Exception e) {
                ui.showMessage(e);
            }
        } while (command == null || !command.getCommandName().equals(EXIT_COMMAND_WORD));
    }

    /**
     * Prints the Goodbye message and exits.
     * See <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">addressbook-level2</a>
     * */
    private void exit() {
        ui.showGoodByeMessage();
        System.exit(0);
    }


}
