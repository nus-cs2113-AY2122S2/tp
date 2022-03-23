package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

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

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (index > taskList.getTasks().size() || index < 0) {
            ui.showToUser("Invalid index!");
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
