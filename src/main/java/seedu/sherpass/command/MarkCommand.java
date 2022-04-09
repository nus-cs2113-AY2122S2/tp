package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

import static seedu.sherpass.constant.Message.ERROR_INVALID_MARKING_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.MARK_TASK_RESULT_MESSAGE;
import static seedu.sherpass.constant.Message.TAB_INDENT;

public class MarkCommand extends Command {

    private int markIndex;

    /**
     * Creates a constructor for the mark command.
     * Saves index of task to mark.
     *
     * @param markIndex Task index to mark.
     */
    public MarkCommand(int markIndex) {
        this.markIndex = markIndex;
    }


    /**
     * Executes mark command. Marks task as Done.
     *
     * @param taskList Task array.
     * @param ui       Ui for printing
     * @param storage  Overwrite save file for newly marked task.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.markTask(markIndex);
            storage.writeSaveData(taskList);
            ui.showToUser(MARK_TASK_RESULT_MESSAGE);
            ui.showToUser(TAB_INDENT + taskList.getTask(markIndex));
        } catch (IndexOutOfBoundsException exception) {
            ui.showError(ERROR_INVALID_MARKING_INDEX_MESSAGE);
        }
    }
}
