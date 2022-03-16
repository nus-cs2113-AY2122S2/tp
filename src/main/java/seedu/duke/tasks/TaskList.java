package seedu.duke.tasks;

import java.util.ArrayList;

import seedu.duke.exceptions.NoSuchTaskException;
import seedu.duke.util.StringConstants;

public class TaskList {
    private static final String LS = System.lineSeparator();
    private static final String ITEMIZE_FORMAT = "%d. %s" + LS;
    private static final String EMPTY_LIST = StringConstants.EMPTY_LIST;

    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Returns the size of the task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds the specified task to the task list, then returns the task for convenience.
     * @param t the task to be added
     */
    public Task addTask(Task t) {
        taskList.add(t);
        return t;
    }

    /**
     * Removes the specified task from the task list.
     * @param index The task number to be removed.
     */
    public Task removeTask(int index) throws NoSuchTaskException {
        if (index >= taskList.size() || index < 0) {
            throw new NoSuchTaskException();
        }
        Task task = getTask(index);
        taskList.remove(index);
        return task;
    }

    public ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * Returns the task stored at the given index in the task list.
     * @param index the index of the task
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Formats all tasks in the task list as a pretty printed string.
     * @param indent string representing the indentation level for each task item
     */
    public String getAllTasks(String indent) {
        String res = "";
        for (int i = 0; i < taskList.size(); i++) {
            res += indent + String.format(ITEMIZE_FORMAT, i + 1, taskList.get(i));
        }
        if (res.length() == 0) {
            res = indent + EMPTY_LIST;
        }
        return res;
    }

}
