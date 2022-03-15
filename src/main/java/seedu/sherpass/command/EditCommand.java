package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    int taskIndex;
    String taskDescription;
    String byDate;
    String doOnDate;

    public EditCommand(int taskNumber, String taskDescription, String byDate, String doOnDate) {
        this.taskIndex = taskNumber - 1;
        this.taskDescription = taskDescription;
        this.byDate = byDate;
        this.doOnDate = doOnDate;
    }


    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (!taskDescription.trim().isBlank()) {
            taskList.getTasks().get(taskIndex).setTaskDescription(taskDescription);
        }
        if (!byDate.trim().isBlank()) {
            taskList.getTasks().get(taskIndex).setByDate(byDate);
        }
        if (!doOnDate.trim().isBlank()) {
            taskList.getTasks().get(taskIndex).setDoOnDate(doOnDate);
        }
        printEditTaskMessage(taskList, taskIndex);
        storage.writeSaveData(taskList);
    }

    private void printEditTaskMessage(TaskList taskList, int taskIndex) {
        System.out.println("Ok, I've edited this task as such!"
                + "\n  " + taskList.getTasks().get(taskIndex));
    }

}
