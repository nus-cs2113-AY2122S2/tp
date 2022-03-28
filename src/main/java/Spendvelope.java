import commands.Command;
import commands.CommandResult;
import commands.ExitCommand;

import manager.LimitManager;
import manager.RecordManager;

import manager.Storage;
import records.Record;
import ui.TextUi;

import parser.Parser;

import java.io.IOException;
import java.util.ArrayList;

import static constants.SpendvelopeConstants.VERSION;

/** Main class for the Spendvelope app. */
public class Spendvelope {
    private static final String filePath = "data/records.txt";
    private final TextUi ui = TextUi.getTextUiInstance();
    private final RecordManager recordMgr = new RecordManager(storage);
    private final LimitManager limitMgr = LimitManager.getLimitManagerInstance();
    private static final Storage storage = new Storage(filePath);

    /** Main entry-point for the application. */
    public static void main(String[] args) throws IOException {
        new Spendvelope().run();
    }

    /** Runs the program until termination. */
    public void run() throws IOException {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /** Sets up the required objects, and prints the welcome message. */
    private void start() throws IOException {
        ui.showWelcomeMessage(VERSION);
        recordMgr.loadRecordlist();
    }

    /** Prints the goodbye message and exits. */
    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the exit command. */
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
     * @param command User command
     * @return Result of the command
     */
    private CommandResult executeCommand(Command command) {
        try {
            command.setData(recordMgr);
            command.setLimitManager(limitMgr);

            CommandResult result = command.execute();
            recordMgr.saveRecordlist();
            return result;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
