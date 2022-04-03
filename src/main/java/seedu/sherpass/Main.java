package seedu.sherpass;

import org.json.JSONException;

import seedu.sherpass.command.Command;
import seedu.sherpass.command.ExitCommand;

import seedu.sherpass.exception.InvalidInputException;

import seedu.sherpass.exception.TimeClashException;
import seedu.sherpass.util.parser.Parser;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static seedu.sherpass.constant.Message.ERROR_INVALID_SAVED_FILE_MESSAGE_1;
import static seedu.sherpass.constant.Message.ERROR_INVALID_SAVED_FILE_MESSAGE_2;
import static seedu.sherpass.constant.Message.ERROR_IO_FAILURE_MESSAGE;

public class Main {

    private Storage storage;
    private TaskList taskList;
    private final Ui ui;
    public static Logger LOGGER;

    /**
     * Initialises the program.
     * Loading of save file also occurs over here.
     *
     * @param  filePath Location of the save file.
     */
    public Main(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList();
            storage.load(taskList);
        } catch (IOException e) {
            ui.showToUser(ERROR_IO_FAILURE_MESSAGE);
            System.exit(1);
        } catch (InvalidInputException | JSONException | TimeClashException e) {
            ui.showToUser(e.getMessage());
            boolean shouldWipeFile = ui.readYesNoCommand(ERROR_INVALID_SAVED_FILE_MESSAGE_1);
            if (shouldWipeFile) {
                storage.wipeSaveData();
                taskList = new TaskList();
            } else {
                ui.showToUser(ERROR_INVALID_SAVED_FILE_MESSAGE_2);
                System.exit(1);
            }
        }
    }

    private void initialiseLogger() {
        LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.FINE);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.WARNING);
        LOGGER.addHandler(ch);

        try {
            FileHandler fh = new FileHandler();
            fh.setLevel(Level.ALL);
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File logger not working", e);
        }

    }

    /**
     * Runs the program.
     */
    public void run() {
        initialiseLogger();
        ui.showWelcomeMessage(taskList, ui);

        boolean isExit = false;
        while (!isExit && ui.hasInput()) {
            String fullCommand = ui.readCommand();
            ui.showLine();

            Command c = Parser.parseCommand(fullCommand, ui);
            if (c != null) {
                c.execute(taskList, ui, storage);
                isExit = ExitCommand.isExit(c);
            }
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Main("data/sherpass.json").run();
    }

}
