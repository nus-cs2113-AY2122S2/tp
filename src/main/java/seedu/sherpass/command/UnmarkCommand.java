package seedu.sherpass.command;

import seedu.sherpass.utills.Storage;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.utills.Ui;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = "Unmark: Marks a task as undone."
            + "\nTo unmark a specific task, enter 'unmark <list_index>'.\n\n Here, "
            + "'list_index' denotes the index of a task \n based on the task list under the command 'list'.\n"
            + "\nE.g., 'unmark 3' unmarks the third task in the task list.\n\n"
            + "Note: You can only unmark one task per command input.";

    private int markIndex;

    /**
     * Creates a constructor for the unmark command.
     * Saves index of task to mark.
     *
     * @param markIndex Task index to mark.
     * @param taskList Task array.
     */
    public UnmarkCommand(int markIndex, TaskList taskList) {
        if (taskList.isTaskExist(markIndex)) {
            this.markIndex = markIndex;
        }
    }

    /**
     * Executes unmark command. Marks task as undone.
     *
     * @param taskList Task array.
     * @param ui Ui for printing
     * @param storage Overwrite save file for newly unmarked task.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (!taskList.isTaskDone(markIndex)) {
            System.out.println("This task was already unmarked!");
            return;
        }
        taskList.unmarkTask(markIndex);
        storage.writeSaveData(taskList);
    }
}