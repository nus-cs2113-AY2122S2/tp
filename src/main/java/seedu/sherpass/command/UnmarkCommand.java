package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

import static seedu.sherpass.constant.Message.ERROR_INVALID_MARKING_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.TAB_INDENT;
import static seedu.sherpass.constant.Message.UNMARK_TASK_RESULT_MESSAGE;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = "Unmark: Marks a task as undone."
            + "\nTo unmark a specific task, enter 'unmark <task_number>'.\n\n Here, "
            + "'task_number' denotes the index of a task \n based on the task list under the command 'show all'.\n"
            + "\nE.g., 'unmark 3' unmarks the third task in the task list.\n\n"
            + "Note: You can only unmark one task per command input.";

    private int markIndex;

    /**
     * Creates a constructor for the unmark command.
     * Saves index of task to mark.
     *
     * @param markIndex Task index to mark.
     */
    public UnmarkCommand(int markIndex) {
        this.markIndex = markIndex;
    }

    /**
     * Executes unmark command. Marks task as undone.
     *
     * @param taskList Task array.
     * @param ui       Ui for printing
     * @param storage  Overwrite save file for newly unmarked task.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.unmarkTask(markIndex);
            storage.writeSaveData(taskList);
            ui.showToUser(UNMARK_TASK_RESULT_MESSAGE);
            ui.showToUser(TAB_INDENT + taskList.getTask(markIndex));
        } catch (IndexOutOfBoundsException exception) {
            ui.showToUser(ERROR_INVALID_MARKING_INDEX_MESSAGE);
        }
    }
}
