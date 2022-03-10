package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;

import java.io.IOException;
import static seedu.duke.constant.Messages.ERROR_IO_FAILURE_MESSAGE;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises the program.
     * Loading of save file also occurs over here.
     *
     * @param  filePath Location of the save file.
     */
    public Duke(String filePath) {
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
        new Duke("data/sherpass.txt").run();
    }

}
