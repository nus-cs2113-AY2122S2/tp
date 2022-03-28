package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.exception.InvalidInputException;

public class DeleteCommand extends Command {
    private int deleteIndex;

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "Delete: Deletes a task in the task list.\n"
            + "Usage: delete TASK_NUMBER [/repeat]";

    /**
     * Creates constructor for delete command.
     *
     * @param deleteIndex Contains task description to search for
     * @param taskList    Task array.
     * @throws InvalidInputException If there is no task present in task list
     *                               that corresponds to given delete index.
     */
    public DeleteCommand(int deleteIndex, TaskList taskList) throws InvalidInputException {
        if (taskList.isTaskNotExist(deleteIndex)) {
            throw new InvalidInputException();
        }
        this.deleteIndex = deleteIndex;
    }

    /**
     * Executes the deletion of a task.
     *
     * @param taskList Task array.
     * @param ui       Ui for printing messages.
     * @param storage  Overwrites save file after editing contents in task array.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.removeTask(deleteIndex, ui);
        storage.writeSaveData(taskList);
    }
}
