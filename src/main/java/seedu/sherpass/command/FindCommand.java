package seedu.sherpass.command;

import seedu.sherpass.util.Storage;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;
import seedu.sherpass.exception.InvalidInputException;

import static seedu.sherpass.constant.Message.ERROR_SYSTEM_FAULT_MESSAGE;

public class FindCommand extends Command {
    private String findTaskByDescription;
    private String findTaskByDate;

    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = "Find: Finds related task descriptions \nbased on the given input.\n"
            + "\nTo search for a task, \nenter 'find <keyword>', e.g. 'find book'";


    /**
     * Creates a constrcutor for the FindCommand class.
     * Saves task description and task date for searching in "execute" method.
     *
     * @param taskDescription Task Description to search.
     * @param taskDate Task Date to search
     * @throws InvalidInputException If task description is empty.
     */
    public FindCommand(String taskDescription, String taskDate) throws InvalidInputException {
        if (taskDate == null) {
            if (taskDescription.isBlank()) {
                throw new InvalidInputException();
            }
            findTaskByDescription = taskDescription;
        }
        findTaskByDate = taskDate;
    }

    private String convertToTaskType(Ui ui) {
        switch (findTaskByDescription.toLowerCase()) {
        case "/event":
            return "E";
        case "/deadline":
            return "D";
        case "/todo":
            return "T";
        default:
            ui.showToUser(ERROR_SYSTEM_FAULT_MESSAGE);
            System.exit(1);
        }
        return " ";
    }


    /**
     * Executes the find command. Searches and prints relevant tasks
     * that match the search keyword.
     *
     * @param taskList Task array.
     * @param ui Ui for printing messages.
     * @param storage Storage for overwriting/appending save data. Not used in this method.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (findTaskByDate != null) {
            taskList.findByTaskDate(findTaskByDate, ui);
            return;
        }
        if (findTaskByDescription.equals("/event") || findTaskByDescription.equals("/deadline")
                || findTaskByDescription.equals("/todo")) {
            taskList.findByTaskType(convertToTaskType(ui), ui);
            return;
        }
        taskList.findByTaskDescription(findTaskByDescription, ui);
    }

}
