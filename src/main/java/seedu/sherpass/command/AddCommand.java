package seedu.sherpass.command;

import seedu.sherpass.task.Task;
import seedu.sherpass.util.Storage;
import seedu.sherpass.util.Ui;

import seedu.sherpass.task.TaskList;

import seedu.sherpass.exception.InputRepeatedException;
import seedu.sherpass.exception.InvalidInputException;

import java.time.LocalDateTime;

public class AddCommand extends Command {
    String toAddTaskContent;
    LocalDateTime toAddTaskByDate;
    LocalDateTime toAddTaskDoOnDate;

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = "Add: Adds a task into the task list.\n"
            + "Usage: add TASK_DESCRIPTION /do DATE /start START_TIME /end END_TIME /repeat\n"
            + "       add TASK_DESCRIPTION /do DATE /start START_TIME /end END_TIME [/by DEADLINE]";


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
    public AddCommand(String taskDescription, TaskList taskList, LocalDateTime byDate, LocalDateTime doOnDate)
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
        Task newTask = new Task(taskList.generateIdentifier(),  toAddTaskContent, toAddTaskByDate,
                toAddTaskDoOnDate, toAddTaskDoOnDate, null);
        taskList.addTask(newTask);
        ui.showToUser("Got it. I've added " + taskList.getTasks().size() + " tasks:\n " + newTask
                + "\nNow you have " + taskList.getTasks().size() + " task(s) in the list.");
        storage.writeSaveData(taskList);
    }
}
