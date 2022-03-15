package seedu.sherpass;

import org.json.JSONException;

import seedu.sherpass.command.Command;
import seedu.sherpass.command.ExitCommand;

import seedu.sherpass.exception.InvalidInputException;

import seedu.sherpass.util.Parser;
import seedu.sherpass.util.Reminder;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

import static seedu.sherpass.constant.Message.ERROR_IO_FAILURE_MESSAGE;

import java.io.IOException;

public class Main {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Reminder reminder;

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
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showToUser(ERROR_IO_FAILURE_MESSAGE);
            System.exit(1);
        } catch (InvalidInputException | JSONException e) {
            storage.handleCorruptedSave(ui);
            taskList = new TaskList();
        }
        reminder = new Reminder(taskList, ui);
    }

    /**
     * Runs the program.
     */
    public void run() {
        ui.showWelcomeMessage();
        reminder.showReminders();

        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine();

            Command c = Parser.parseCommand(fullCommand, taskList);
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
