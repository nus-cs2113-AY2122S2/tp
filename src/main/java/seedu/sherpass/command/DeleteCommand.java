package seedu.sherpass.command;

import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;
import seedu.sherpass.task.TaskList;

import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INDEX_MESSAGE;

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
            String repeatKeyWord = (isRepeat ? " repeated" : EMPTY_STRING);
            taskList.removeTask(deleteIndex, isRepeat);
            ui.showToUser("Okay. I've removed this" + repeatKeyWord + " task:\n  "
                    + taskToBeRemoved + "\nNow you have " + taskList.getSize() + " task(s) in the list.");
            storage.writeSaveData(taskList);
        } catch (IndexOutOfBoundsException exception) {
            ui.showToUser(ERROR_INVALID_INDEX_MESSAGE);
        } catch (InvalidInputException exception) {
            ui.showToUser(exception.getMessage());
        }
    }
}