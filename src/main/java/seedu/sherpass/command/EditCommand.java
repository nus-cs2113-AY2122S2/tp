package seedu.sherpass.command;

import seedu.sherpass.task.TaskList;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import java.time.LocalDate;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = "edit: Edits a task in the task list.\n\n"
            + "To execute the command,\nenter 'edit <task_number> <task_description> "
            + "/by <task_due_date> /do_on <date_to_work_on_task>'.\n\n"
            + "Note:\n\tThe 3 attributes after the task number are optional.\n"
            + "\tYou can use any combination of them but do use them in the correct order:\n"
            + "\t1. <task_description>,\n\t2. /by <task_due_date>, and\n\t3. /do_on <date_to_work_on_task>\n\n"
            + "Examples:\n\tedit 1 /by 23/5/2022\n\tedit 2 homework\n"
            + "\tedit 4 homework /by 23/5/2022 /do_on 20/5/2022\n"
            + "\tedit 3 project /do_on 5/5/2022\n\n"
            + "All task dates must be given in the format:"
            + "\n\t\td/M/yyyy , where\nyear is in 4 digits, month and day in one or two digits.";

    int taskIndex;
    String taskDescription;
    LocalDate byDate;
    LocalDate doOnDate;

    public EditCommand(int taskNumber, String taskDescription, LocalDate byDate, LocalDate doOnDate) {
        this.taskIndex = taskNumber - 1;
        this.taskDescription = taskDescription;
        this.byDate = byDate;
        this.doOnDate = doOnDate;
    }


    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (!taskDescription.trim().isBlank()) {
            taskList.getTasks().get(taskIndex).setTaskDescription(taskDescription);
        }
        if (byDate != null) {
            taskList.getTasks().get(taskIndex).setByDate(byDate);
        }
        if (doOnDate != null) {
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
