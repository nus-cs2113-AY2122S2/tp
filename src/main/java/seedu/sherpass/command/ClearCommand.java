package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.task.Task;

import java.util.ArrayList;

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = "Clear: Deletes all tasks in the list.\n"
            + "To execute the command, enter 'clear'.";

    /**
     * Executes the clear command. Deletes all saved tasks in the task array.
     *
     * @param taskList Task array.
     * @param ui       UI for printing messages.
     * @param storage  Overwrite saved data after deleting.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        boolean shouldExecute = ui.readYesNoCommand("Are you sure you want to delete all tasks?\n"
                + "You will not be able to recover them after deleting (Y/N): ");
        if (shouldExecute) {
            ui.showToUser("Understood. Proceeding to delete"
                    + "\nall current tasks in the list..........");
            taskList.deleteAllTasks();
            ui.showLine();
            ui.showToUser("Done! Now you have 0 task in the list.");
            storage.writeSaveData(taskList);
        } else {
            ui.showToUser("Okay, we'll keep it as it is.");
        }
    }
}
