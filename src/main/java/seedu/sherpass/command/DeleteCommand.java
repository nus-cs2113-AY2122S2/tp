package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;
import seedu.sherpass.task.TaskList;

import static seedu.sherpass.constant.Message.DELETE_TASK_RESULT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.TAB_INDENT;
import static seedu.sherpass.constant.Message.TASK_COUNT_MESSAGE_1;
import static seedu.sherpass.constant.Message.TASK_COUNT_MESSAGE_2;

public class DeleteCommand extends Command {
    private int deleteIndex;
    private boolean isRepeat;

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "Delete: Deletes a task in the task list.\n"
            + "Usage: delete TASK_NUMBER [/repeat]";

    /**
     * Creates constructor for delete command.
     *
     * @param deleteIndex Contains task description to search for
     */
    public DeleteCommand(int deleteIndex, boolean isRepeat) {
        this.deleteIndex = deleteIndex;
        this.isRepeat = isRepeat;
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
        try {
            String taskToBeRemoved = taskList.getTask(deleteIndex).toString();
            taskList.removeTask(deleteIndex, isRepeat);
            ui.showToUser(DELETE_TASK_RESULT_MESSAGE);
            ui.showToUser(TAB_INDENT + taskToBeRemoved);
            ui.showToUser(TASK_COUNT_MESSAGE_1 + taskList.getSize() + TASK_COUNT_MESSAGE_2);
            storage.writeSaveData(taskList);
        } catch (IndexOutOfBoundsException exception) {
            ui.showError(ERROR_INVALID_INDEX_MESSAGE);
        }
    }
}
