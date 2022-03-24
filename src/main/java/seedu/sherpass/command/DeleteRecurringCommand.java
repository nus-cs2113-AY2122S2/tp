package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.task.TaskLogic;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

public class DeleteRecurringCommand extends Command {
    private int index;

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
        String error = TaskLogic.checkValidDeleteArgument(taskList, index);
        if (!error.isBlank()) {
            ui.showToUser(error);
            return;
        }

        int identifier = taskList.getTasks().get(index).getIdentifier();
        int count = 0;
        for (int i = taskList.getTasks().size() - 1; i >= 0; i--) {
            if (i < index) {
                break;
            }
            if (taskList.getTasks().get(i).getIdentifier() == identifier) {
                taskList.getTasks().remove(i);
                ++count;
            }
        }
        storage.writeSaveData(taskList);
        ui.showToUser("I've removed a total of " + count + " tasks!");
    }
}
