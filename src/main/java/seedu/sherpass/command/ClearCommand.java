package seedu.sherpass.command;

import seedu.sherpass.utills.Storage;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.utills.Ui;
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
     * @param ui UI for printing messages.
     * @param storage Overwrite saved data after deleting.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> tempArray = taskList.getTasks();
        if (tempArray.size() <= 0) {
            ui.showToUser("There are no tasks to clear!");
            return;
        }

        ui.showToUser("Are you sure you want to delete all tasks? [Y/N]\n"
                + "You will not be able to recover them after deleting.");
        ui.showLine();
        while (true) {
            String input = ui.readCommand();
            ui.showLine();
            if (input.trim().equalsIgnoreCase("Y")
                    || input.trim().equalsIgnoreCase("Yes")) {
                ui.showToUser("Understood. Proceeding to delete"
                        + "\nall current tasks in the list..........");
                taskList.deleteAllTasks(ui);
                break;
            }
            if (input.trim().equalsIgnoreCase("N")
                    || input.trim().equalsIgnoreCase("No")) {
                ui.showToUser("Okay, we'll keep it as it is.");
                break;
            }
            ui.showToUser("Please confirm your choice with either Y (Yes) or N (No).");
            ui.showLine();
        }
        storage.writeSaveData(taskList);
    }
}
