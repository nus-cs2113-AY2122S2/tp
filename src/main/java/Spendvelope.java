import commands.Command;
import commands.CommandResult;
import commands.ExitCommand;
import manager.RecordManager;
import ui.TextUi;
import parser.Parser;

public class Spendvelope {

    /** Version info of the program. */
    public static final String VERSION = "Spendvelope - Version 1.0";

    private TextUi ui;
    private RecordManager recordMgr = new RecordManager();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Spendvelope().run();
    }

    /** Runs the program until termination.  */
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, and prints the welcome message.
     *
     */
    private void start() {
        this.ui = new TextUi();
        ui.showWelcomeMessage(VERSION);
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            ui.showResultToUser(result);
        } while (!ExitCommand.isExit(command));
    }


    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(Command command) {
        try {
            command.setData(recordMgr);
            CommandResult result = command.execute();
            return result;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
