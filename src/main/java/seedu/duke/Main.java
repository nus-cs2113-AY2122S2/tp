package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.ExitCommand;
import seedu.duke.parsers.ModHappyParser;
import seedu.duke.tasks.ModuleList;
import seedu.duke.ui.TextUi;

public class Main {
    private TextUi ui;
    private ModHappyParser modHappyParser;
    private ModuleList moduleList;

    /**
     * Main entry-point for the application.
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
            ui = new TextUi();
            ui.showHelloMessage();
            modHappyParser = new ModHappyParser();
            moduleList = new ModuleList();
        } catch (Exception e) {
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
                CommandResult result = command.execute(moduleList);
                ui.showMessage(result.toString());
            } catch (Exception e) {
                ui.showMessage(e);
            }
        } while (command == null || !ExitCommand.isExit);
    }

    /**
     * Prints the goodbye message and exits.
     * See <a href="https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java">addressbook-level2</a>
     * */
    private void exit() {
        ui.showGoodByeMessage();
        System.exit(0);
    }


}
