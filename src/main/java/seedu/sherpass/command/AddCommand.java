package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;
import seedu.sherpass.exception.InputRepeatedException;
import seedu.sherpass.exception.InvalidInputException;

public class AddCommand extends Command {
    String toAddTaskContent;
    String toAddTaskByDate;
    String toAddTaskRemindDate;

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = "add: Adds a task into the task list.\n"
            + "A task contains a task description and an optional dates "
            + "to finish the task by.\n\nTo execute the command,\nenter 'add <task_description> "
            + "/by <task_date> /remind <reminder_date>'.\nE.g. add do assignment /by 2022/02/03 /remind 2022/02/01.\n\n"
            + "All task dates must be given in the format:\n"
            + "\t\tyyyy/MM/dd , where\n"
            + "year is in 4 digits, month and day in two digits.";


    /**
     * Creates constructor for add command. Saves task description.
     *
     * @param taskDescription    Task Description to add.
     * @param taskList           Task array.
     * @param byDate             Task due date.
     * @param remindDate         Task reminder date.
     * @throws InvalidInputException  If task description is empty.
     * @throws InputRepeatedException If task has been added before.
     */
    public AddCommand(String taskDescription, TaskList taskList, String byDate, String remindDate)
            throws InvalidInputException, InputRepeatedException {
        if (taskDescription.isBlank()) {
            throw new InvalidInputException();
        }
        if (taskList.isTaskAlreadyAdded(taskDescription)) {
            throw new InputRepeatedException();
        }
        toAddTaskByDate = byDate;
        toAddTaskRemindDate = remindDate;
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
        taskList.addTask(toAddTaskContent, toAddTaskByDate, toAddTaskRemindDate);
        storage.writeSaveData(taskList);
    }
}
