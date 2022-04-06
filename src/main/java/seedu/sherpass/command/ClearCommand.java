package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

import static seedu.sherpass.constant.Message.CLEAR_COMMAND_CANCEL_MESSAGE;
import static seedu.sherpass.constant.Message.CLEAR_COMMAND_CONFIRMATION_MESSAGE;
import static seedu.sherpass.constant.Message.CLEAR_COMMAND_CONFIRMED_MESSAGE;
import static seedu.sherpass.constant.Message.CLEAR_COMMAND_RESULT_MESSAGE;

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
        boolean shouldExecute = ui.readYesNoCommand(CLEAR_COMMAND_CONFIRMATION_MESSAGE);
        if (shouldExecute) {
            ui.showToUser(CLEAR_COMMAND_CONFIRMED_MESSAGE);
            taskList.deleteAllTasks();
            storage.writeSaveData(taskList);
            ui.showLine();
            ui.showToUser(CLEAR_COMMAND_RESULT_MESSAGE);
        } else {
            ui.showToUser(CLEAR_COMMAND_CANCEL_MESSAGE);
        }
    }
}
