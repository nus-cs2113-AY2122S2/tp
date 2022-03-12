package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;
import seedu.sherpass.exception.InputRepeatedException;
import seedu.sherpass.exception.InvalidInputException;

public class EventCommand extends Command {
    String toAddTaskDescription;
    String toAddTaskDate;

    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = "Event: Adds an 'event' task into the task list.\n"
            + "An 'event' contains both a task description \nand a date "
            + "of when the event will happen. \n\nTo execute the command,\n"
            + "enter 'event <task_description> /at <task_date>'.\n"
            + "E.g. event project meeting /at 2022/08/06 1400.\n"
            + "\nAll event dates must be given in the format:"
            + "\n\t\tyyyy/MM/dd [HH:mm], where\n"
            + "year is in 4 digits, month and day in two digits, and an \noptional time in 24 hour format.";

    /**
     * Creates a constructor for event command. Saves task description and date.
     *
     * @param taskDescription Task description.
     * @param taskList Task array.
     * @param dateInput Task Date.
     * @throws InvalidInputException If Task content is empty.
     * @throws InputRepeatedException If task has already been added.
     */
    public EventCommand(String taskDescription, TaskList taskList, String dateInput)
            throws InvalidInputException, InputRepeatedException {
        if (taskDescription.isBlank()) {
            throw new InvalidInputException();
        }
        if (taskList.isTaskAlreadyAdded(taskDescription)) {
            throw new InputRepeatedException();
        }
        toAddTaskDate = dateInput;
        toAddTaskDescription = taskDescription;
    }

    /**
     * Executes the adding of event task.
     *
     * @param taskList Task array.
     * @param ui Ui for printing messages.
     * @param storage To append newly added task to save file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(toAddTaskDescription, toAddTaskDate, COMMAND_WORD);
        storage.appendToFile(toAddTaskDescription, toAddTaskDate, "0", "E");
    }
}
