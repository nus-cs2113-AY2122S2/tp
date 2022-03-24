package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.ERROR_EMPTY_TASKLIST_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INDEX_MESSAGE;

public class DeleteRecurringCommand extends Command {
    private int index;

    public static final String COMMAND_WORD = "deleterecurring";
    public static final String MESSAGE_USAGE = "deleterecurring: delete a recurring task in the task list.\n"
            + "The index of the recurring task must be specified.\n"
            + "\n\nTo execute the command,\nenter 'deleterecurring INDEX'.\n";

    public DeleteRecurringCommand() {

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    private String isValidArgument(TaskList taskList) {
        if (taskList.getTasks().size() == 0) {
            return ERROR_EMPTY_TASKLIST_MESSAGE;
        } else if (!taskList.isTaskExist(index)) {
            return ERROR_INVALID_INDEX_MESSAGE;
        }
        return EMPTY_STRING;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String error = isValidArgument(taskList);
        if (!error.isBlank()) {
            ui.showToUser(error);
            return;
        }

        int identifier = taskList.getTasks().get(index).getIdentifier();
        int count = 0;
        for (int i = taskList.getTasks().size() - 1; i >= 0; i--) {
            if (taskList.getTasks().get(i).getIdentifier() == identifier) {
                taskList.getTasks().remove(i);
                ++count;
            }
        }
        storage.writeSaveData(taskList);
        ui.showToUser("I've removed a total of " + count + " tasks!");
    }
}
