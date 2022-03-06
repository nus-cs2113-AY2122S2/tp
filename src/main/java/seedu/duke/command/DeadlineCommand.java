package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.exception.InputRepeatedException;
import seedu.duke.exception.InvalidInputException;

public class DeadlineCommand extends Command {
    String toAddTaskContent;
    String toAddTaskDate;

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = "Deadline: Adds a 'deadline' task "
            + "into the task list. \nA 'deadline' contains both a task description \nand a date "
            + "to finish the task by.\n\nTo execute the command,\nenter 'deadline <task_description> "
            + "/by <task_date>'.\nE.g. deadline return book /by 2022/02/03.\n\n"
            + "All deadline dates must be given in the format:\n"
            + "\t\tyyyy/MM/dd [HH:mm], where\n"
            + "year is in 4 digits, month and day in two digits, and an\noptional time in 24 hour format.";

    /**
     * Creates a constructor for deadline command. Saves task description and date.
     *
     * @param taskDescription Task description.
     * @param tasklist Task array.
     * @param dateInput Task Date.
     * @throws InvalidInputException If Task content is empty.
     * @throws InputRepeatedException If task has already been added.
     */
    public DeadlineCommand(String taskDescription, TaskList tasklist, String dateInput)
            throws InvalidInputException, InputRepeatedException {
        if (taskDescription.isBlank()) {
            throw new InvalidInputException();
        }
        if (tasklist.isTaskAlreadyAdded(taskDescription)) {
            throw new InputRepeatedException();
        }
        toAddTaskDate = dateInput;
        toAddTaskContent = taskDescription;
    }


    /**
     * Executes the adding of deadline task.
     *
     * @param taskList Task array.
     * @param ui Ui for printing messages.
     * @param storage To append newly added task to save file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(toAddTaskContent, toAddTaskDate, COMMAND_WORD);
        storage.appendToFile(toAddTaskContent, toAddTaskDate, "0", "D");
    }
}
