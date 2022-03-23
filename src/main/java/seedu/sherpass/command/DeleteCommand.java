package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.exception.InvalidInputException;

import static seedu.sherpass.constant.Index.DELETE_INDEX;

public class DeleteCommand extends Command {
    private int deleteIndex;

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "Delete: Deletes a task in the task list.\n"
            + "To delete a specific task, enter 'delete <list_index>'.\n\n Here, "
            + "'list_index' denotes the index of a task \n based on the task list under the command 'list'.\n"
            + "\nE.g., 'delete 2' deletes the second task in the task list.\n\n"
            + "Note: You can only delete one task per command input.";


    /**
     * Creates constructor for delete command.
     *
     * @param parsedInput Contains task description to search for
     * @param taskList    Task array.
     * @throws InvalidInputException If input task description is empty.
     */
    public DeleteCommand(String[] parsedInput, TaskList taskList) throws InvalidInputException {
        if (parsedInput[DELETE_INDEX].isBlank()) {
            throw new InvalidInputException();
        }
        if (taskList.isTaskExist(Integer.parseInt(parsedInput[DELETE_INDEX]) - 1)) {
            deleteIndex = Integer.parseInt(parsedInput[DELETE_INDEX]) - 1;
        }
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
        taskList.removeTask(deleteIndex);
        storage.writeSaveData(taskList);
    }
}