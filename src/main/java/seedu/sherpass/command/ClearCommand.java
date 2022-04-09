package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

import java.time.LocalDateTime;

import static seedu.sherpass.constant.Message.CLEAR_ALL_COMMAND_CONFIRMATION_MESSAGE;
import static seedu.sherpass.constant.Message.CLEAR_ALL_COMMAND_CONFIRMED_MESSAGE;
import static seedu.sherpass.constant.Message.CLEAR_ALL_COMMAND_CANCEL_MESSAGE;
import static seedu.sherpass.constant.Message.CLEAR_ALL_COMMAND_RESULT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_CLEAR_MESSAGE;
import static seedu.sherpass.constant.CommandMessage.MESSAGE_USAGE_CLEAR;


public class ClearCommand extends Command {

    private String selection;

    public ClearCommand(String selection) {
        this.selection = selection.trim();
    }

    /**
     * Executes the clear command.
     * Deletes either all tasks, only expired tasks, or only completed tasks in the task array.
     *
     * @param taskList Task array.
     * @param ui       UI for printing messages.
     * @param storage  Overwrite saved data after deleting.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        switch (selection) {
        case "all":
            clearAll(taskList, ui, storage);
            break;
        case "expired":
            //Fallthrough
        case "done":
            clearExpiredOrDone(taskList, ui, storage);
            break;
        default:
            ui.showError(ERROR_INVALID_CLEAR_MESSAGE);
            ui.showLine();
            ui.showToUser(MESSAGE_USAGE_CLEAR);
            break;
        }
    }

    private void clearAll(TaskList taskList, Ui ui, Storage storage) {
        boolean shouldExecute = ui.readYesNoCommand(CLEAR_ALL_COMMAND_CONFIRMATION_MESSAGE);
        if (shouldExecute) {
            ui.showToUser(CLEAR_ALL_COMMAND_CONFIRMED_MESSAGE);
            taskList.deleteAllTasks();
            storage.writeSaveData(taskList);
            ui.showLine();
            ui.showToUser(CLEAR_ALL_COMMAND_RESULT_MESSAGE);
        } else {
            ui.showToUser(CLEAR_ALL_COMMAND_CANCEL_MESSAGE);
        }
    }

    private void clearExpiredOrDone(TaskList taskList, Ui ui, Storage storage) {
        int originalTaskListSize = taskList.getSize();
        LocalDateTime currentDateTime = LocalDateTime.now();
        boolean conditionToRemoveTask;
        int taskIndex = 0;
        while (taskIndex != taskList.getSize()) {
            assert taskIndex < taskList.getSize();
            if (selection.equals("expired")) {
                conditionToRemoveTask = currentDateTime.isAfter(taskList.getTask(taskIndex).getDoOnStartDateTime());
            } else {
                conditionToRemoveTask = taskList.getTask(taskIndex).isDone();
            }
            if (conditionToRemoveTask) {
                taskList.removeTask(taskIndex, false);
                taskIndex -= 1;
            }
            taskIndex += 1;
        }
        if (taskList.getSize() != originalTaskListSize) {
            storage.writeSaveData(taskList);
            ui.showClearRemoveTask(originalTaskListSize, taskList.getSize());
        } else {
            ui.showClearFailToRemoveTask(selection);
        }
    }
}
