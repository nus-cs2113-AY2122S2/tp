package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

import java.time.LocalDate;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = "Edit: Edits a task in the task list.\n\n"
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

    /**
     * Creates constructor for edit command.
     * Saves index of task to edit, task description, task due date, and date to work on task.
     *
     * @param taskList           Task array.
     * @param taskNumber         Task number to edit.
     * @param taskDescription    Task Description to edit.
     * @param byDate             Task due date.
     * @param doOnDate           Date to work on task.
     * @throws IndexOutOfBoundsException If the task index is out of bounds.
     */
    public EditCommand(TaskList taskList, int taskNumber, String taskDescription, LocalDate byDate, LocalDate doOnDate)
            throws IndexOutOfBoundsException {

        if (!taskList.isTaskExist(taskNumber - 1)) {
            throw new IndexOutOfBoundsException();
        }
        this.taskIndex = taskNumber - 1;
        this.taskDescription = taskDescription;
        this.byDate = byDate;
        this.doOnDate = doOnDate;
    }

    /**
     * Executes the editing of a task.
     *
     * @param taskList Task array.
     * @param ui       Ui for printing messages.
     * @param storage  To append newly added task to save file.
     */
    @Override
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

    /**
     * Prints out the task edit message.
     *
     * @param taskList  Task array.
     * @param taskIndex Index of task edited
     */
    private void printEditTaskMessage(TaskList taskList, int taskIndex) {
        System.out.println("Ok, I've edited this task as such!"
                + "\n  " + taskList.getTasks().get(taskIndex));
    }

}
