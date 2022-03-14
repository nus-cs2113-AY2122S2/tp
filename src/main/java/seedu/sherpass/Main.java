package seedu.sherpass;

import seedu.sherpass.command.Command;
import seedu.sherpass.command.ExitCommand;
import seedu.sherpass.util.Parser;
import seedu.sherpass.util.Reminder;
import seedu.sherpass.util.Storage;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;

import java.io.IOException;
import static seedu.sherpass.constant.Message.ERROR_IO_FAILURE_MESSAGE;

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
            reminder = new Reminder(taskList, ui);
        } catch (IOException e) {
            ui.showToUser(ERROR_IO_FAILURE_MESSAGE);
            System.exit(1);
        }
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
        new Main("data/sherpass.txt").run();
    }

}
