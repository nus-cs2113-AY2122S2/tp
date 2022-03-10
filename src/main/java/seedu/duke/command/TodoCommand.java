package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.exception.InputRepeatedException;
import seedu.duke.exception.InvalidInputException;

import static seedu.duke.constant.Indexes.TASK_DESCRIPTION_INDEX_TODO;

public class TodoCommand extends Command {
    String[] toAdd;
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = "Todo: Adds a 'todo' task into the task list."
            + "\nA 'todo' contains only a task description. \n\nTo add other features to your task, "
            + "such as date and time, \nuse either 'deadline' or 'event'.\n\nTo execute the command, \n"
            + "enter 'todo <task_description>', e.g. todo read book.";


    /**
     * Creates constructor for todo command. Saves task description.
     *
     * @param toAdd Task Description to add.
     * @param taskList Task array.
     * @throws InvalidInputException If task description is empty.
     * @throws InputRepeatedException If task has been added before.
     */
    public TodoCommand(String[] toAdd, TaskList taskList)
            throws InvalidInputException, InputRepeatedException {
        if (toAdd[TASK_DESCRIPTION_INDEX_TODO].isBlank()) {
            throw new InvalidInputException();
        }
        if (taskList.isTaskAlreadyAdded(toAdd[TASK_DESCRIPTION_INDEX_TODO].trim())) {
            throw new InputRepeatedException();
        }
        this.toAdd = toAdd;
    }

    /**
     * Executes the adding of todo task.
     *
     * @param taskList Task array.
     * @param ui Ui for printing messages.
     * @param storage To append newly added task to save file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(toAdd[TASK_DESCRIPTION_INDEX_TODO].trim(), " ", COMMAND_WORD);
        storage.writeSaveData(taskList);
    }
}
