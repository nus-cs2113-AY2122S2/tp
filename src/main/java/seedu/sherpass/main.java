package seedu.sherpass;

import seedu.sherpass.command.Command;
import seedu.sherpass.command.ExitCommand;
import seedu.sherpass.utills.Parser;
import seedu.sherpass.utills.Storage;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.utills.Ui;

import java.io.IOException;
import static seedu.sherpass.constant.Messages.ERROR_IO_FAILURE_MESSAGE;

public class main {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises the program.
     * Loading of save file also occurs over here.
     *
     * @param  filePath Location of the save file.
     */
    public main(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
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
        new main("data/sherpass.txt").run();
    }

}
