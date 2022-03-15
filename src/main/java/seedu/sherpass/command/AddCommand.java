package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

import seedu.sherpass.exception.InputRepeatedException;
import seedu.sherpass.exception.InvalidInputException;

import java.time.LocalDate;

public class AddCommand extends Command {
    String toAddTaskContent;
    String toAddTaskByDate;
    String toAddTaskDoOnDate;

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = "add: Adds a task into the task list.\n"
            + "A task contains a task description and an optional dates "
            + "to finish the task by.\n\nTo execute the command,\nenter 'add <task_description> "
            + "/by <task_due_date> /do_on <date_to_work_on_task>'.\n"
            + "E.g. add do assignment /by 2022/02/03 /do_on 2022/02/01.\n\n"
            + "All task dates must be given in the format:\n"
            + "\t\tyyyy/MM/dd , where\n"
            + "year is in 4 digits, month and day in two digits.";


    /**
     * Creates constructor for add command. Saves task description.
     *
     * @param taskDescription    Task Description to add.
     * @param taskList           Task array.
     * @param byDate             Task due date.
     * @param doOnDate           Date to work on task.
     * @throws InvalidInputException  If task description is empty.
     * @throws InputRepeatedException If task has been added before.
     */
    public AddCommand(String taskDescription, TaskList taskList, String byDate, String doOnDate)
            throws InvalidInputException, InputRepeatedException {
        if (taskDescription.isBlank()) {
            throw new InvalidInputException();
        }
        if (taskList.isTaskAlreadyAdded(taskDescription)) {
            throw new InputRepeatedException();
        }
        toAddTaskByDate = byDate;
        toAddTaskDoOnDate = doOnDate;
        toAddTaskContent = taskDescription;
    }

    /**
     * Executes the adding of add task.
     *
     * @param taskList Task array.
     * @param ui       Ui for printing messages.
     * @param storage  To append newly added task to save file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(toAddTaskContent, toAddTaskByDate, toAddTaskDoOnDate);
        storage.writeSaveData(taskList);
    }
}
