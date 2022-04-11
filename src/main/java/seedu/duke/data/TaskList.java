package seedu.duke.data;

import java.util.ArrayList;

import seedu.duke.exceptions.NoSuchTagException;
import seedu.duke.exceptions.NoSuchTaskException;
import seedu.duke.util.StringConstants;

//@@author chooyikai
public class TaskList {
    private static final String LS = StringConstants.LS;
    private static final String ITEMIZE_FORMAT = "%d. %s" + LS;
    private static final String EMPTY_LIST = StringConstants.EMPTY_LIST;
    private static final String HIDDEN_TASKS_COUNT = StringConstants.HIDDEN_TASKS_COUNT;

    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Gets the size of the task list.
     * @return The size of the task list
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Adds the specified task to the task list, then returns the task for convenience.
     * @param t The task to be added.
     * @return The task added
     */
    public Task addTask(Task t) {
        taskList.add(t);
        return t;
    }

    //@@author ngys117
    /**
     * Removes the specified task from the task list.
     * @param index The index of task to be removed
     * @return The task removed
     * @throws NoSuchTaskException If the task does not exist
     */
    public Task removeTask(int index) throws NoSuchTaskException {
        if (index >= taskList.size()) {
            throw new NoSuchTaskException();
        }
        Task task = getTask(index);
        taskList.remove(index);
        return task;
    }

    /**
     * Adds tag to the task list.
     *
     * @param tagDescription The description of tag that is inputted by user.
     * @param index The index of task to be added with tag
     * @return The task which the tag was added
     * @throws NoSuchTaskException If the user-supplied index is out of bounds
     */
    public Task addTag(String tagDescription, int index) throws NoSuchTaskException {
        if (index >= taskList.size()) {
            throw new NoSuchTaskException();
        }
        Task task = getTask(index);
        ArrayList<String> tags = task.getTagList();
        tags.add(tagDescription);
        return task;
    }

    /**
     * Removes tag from the task list.
     *
     * @param tagDescription The description of tag that is inputted by user
     * @param index The index of task to remove the tag
     * @throws NoSuchTaskException If the user-supplied index is out of bounds
     * @throws NoSuchTagException If the user-supplied tag to be removed does not exist
     */
    public Task removeTag(String tagDescription, int index) throws NoSuchTaskException, NoSuchTagException {
        if (index >= taskList.size()) {
            throw new NoSuchTaskException();
        }
        Task task = getTask(index);
        ArrayList<String> tags = task.getTagList();
        if (!tags.remove(tagDescription)) {
            throw new NoSuchTagException();
        }
        return task;
    }

    //@@author chooyikai
    public void setList(ArrayList<Task> list) {
        taskList = list;
    }

    /**
     * Returns the task stored at the given index in the task list.
     * @param index The index of the task
     * @return The task at the given index
     * @throws NoSuchTaskException If the task does not exist
     */
    public Task getTask(int index) throws NoSuchTaskException {
        if (index >= taskList.size() || index < 0) {
            throw new NoSuchTaskException();
        }
        return taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Formats all tasks in the task list as a pretty printed string.
     *
     * @param indent String representing the indentation level for each task item
     * @param showCompletedTasks Whether completed tasks should be listed
     */
    public String getAllTasks(String indent, boolean showCompletedTasks) {
        StringBuilder res = new StringBuilder();
        int numHiddenTasks = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (showCompletedTasks || !taskList.get(i).getTaskDone()) {
                res.append(indent).append(String.format(ITEMIZE_FORMAT, i + 1, taskList.get(i)));
            } else {
                numHiddenTasks++;
            }
        }
        if (res.length() == 0) {
            res.append(indent).append(EMPTY_LIST).append(LS);
        }
        if (!showCompletedTasks && numHiddenTasks > 0) {
            res.append(indent).append(String.format(HIDDEN_TASKS_COUNT, numHiddenTasks)).append(LS);
        }
        return res.toString();
    }

    /**
     * Formats all tasks in the task list with a matching tag as a pretty printed string.
     *
     * @param indent String representing the indentation level for each task item
     * @param tag The tag to be matched
     * @param showCompletedTasks Whether completed tasks should be listed
     */
    public String getTasksWithTag(String indent, String tag, boolean showCompletedTasks) {
        StringBuilder res = new StringBuilder();
        int numHiddenTasks = 0;
        for (int i = 0; i < taskList.size(); i++) {
            if (!taskList.get(i).getTagList().contains(tag)) {
                // Skip this task as it does not contain the tag we want
                continue;
            }
            if (showCompletedTasks || !taskList.get(i).getTaskDone()) {
                res.append(indent).append(String.format(ITEMIZE_FORMAT, i + 1, taskList.get(i)));
            } else {
                numHiddenTasks++;
            }

        }
        if (res.length() == 0) {
            res.append(indent).append(EMPTY_LIST).append(LS);
        }
        if (!showCompletedTasks && numHiddenTasks > 0) {
            res.append(indent).append(String.format(HIDDEN_TASKS_COUNT, numHiddenTasks)).append(LS);
        }
        return res.toString();
    }

    public void reset() {
        taskList.clear();
    }
}
