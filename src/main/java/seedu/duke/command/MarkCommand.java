package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = "Mark: Marks a task as done. "
            + "\nTo mark a specific task, enter 'mark <list_index>'.\n\n Here, "
            + "'list_index' denotes the index of a task \n based on the task list under the command 'list'.\n"
            + "\nE.g., 'mark 1' marks the first task in the task list as done.\n\n"
            + "Note: You can only mark one task per command input.";

    private int markIndex;

    /**
     * Creates a constructor for the mark command.
     * Saves index of task to mark.
     *
     * @param markIndex Task index to mark.
     * @param taskList Task array.
     */
    public MarkCommand(int markIndex, TaskList taskList) {
        if (taskList.isTaskExist(markIndex)) {
            this.markIndex = markIndex;
        }
    }


    /**
     * Executes mark command. Marks task as Done.
     *
     * @param taskList Task array.
     * @param ui Ui for printing
     * @param storage Overwrite save file for newly marked task.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isTaskDone(markIndex)) {
            System.out.println("This task has already been marked!");
            return;
        }
        taskList.markTask(markIndex);
        storage.writeSaveData(taskList);
    }
}
