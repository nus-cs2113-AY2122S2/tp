package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = "List: Displays a list of tasks "
            + "added and shows \nwhether or not certain tasks are marked.";


    /**
     * Executes the list command. List all tasks availble in task array.
     *
     * @param taskList Task array.
     * @param ui Ui for printing task content.
     * @param storage Storage for overwriting/appending save data. Not used in this method.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.printAllTasks(ui);
    }

}
