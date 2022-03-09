package seedu.duke.tasks;

import seedu.duke.exceptions.NoSuchTaskException;

import java.util.ArrayList;

public class TaskList {
    private static final String LS = System.lineSeparator();
    private static final String ITEMIZE_FORMAT = "%d. %s" + LS;
    private static final String EMPTY_LIST = "(empty)";

    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Returns the size of the task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Adds the specified task to the task list, then returns the task for convenience.
     * @param t the task to be added
     */
    public Task addTask(Task t) {
        list.add(t);
        return t;
    }

    /**
     * Removes the specified task from the task list.
     * @param index The task number to be removed.
     */
    public void removeTask(int index) throws NoSuchTaskException {
        if (index >= list.size()) {
            throw new NoSuchTaskException();
        }
        list.remove(index);
    }

    /**
     * Returns the task stored at the given index in the task list.
     * @param index the index of the task
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Formats all tasks in the task list as a pretty printed string.
     * @param indent string representing the indentation level for each task item
     */
    public String getAllTasks(String indent) {
        String res = "";
        for (int i = 0; i < list.size(); i++) {
            res += indent + String.format(ITEMIZE_FORMAT, i + 1, list.get(i));
        }
        if (res.length() == 0) {
            res = indent + EMPTY_LIST;
        }
        return res;
    }

}
