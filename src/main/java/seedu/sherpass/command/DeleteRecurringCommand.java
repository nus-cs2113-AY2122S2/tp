package seedu.sherpass.command;

import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

public class DeleteRecurringCommand extends Command {
    private int index;

    public static final String COMMAND_WORD = "deleterecurring";
    public static final String MESSAGE_USAGE = "deleterecurring: Edit a recurring task in the task list.\n"
            + "A recurring task contains the index of the task to be edited.\n"
            + "You can edit the task description and the date of the task."
            + "\n\nTo execute the command,\nenter 'editrecurring INDEX [TASK_DESCRIPTION] [/do DATE]'.\n"
            + "E.g. editrecurring 1 weekly revision /do 21/3/2022 09:00\n\n"
            + "All task dates must be given in the format: d/M/yyyy [HH:mm] , where\n"
            + "year is in 4 digits, month and day in one or two digits."
            + "\nTime can also be optionally specified.";

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
