package seedu.duke.tasks;

public class Task {
    public static final String ICON_UNCOMPLETED = "( )";
    public static final String ICON_COMPLETED = "(X)";
    public static final String TASK_STRING_NO_DESC = "%s %s";
    public static final String TASK_STRING_WITH_DESC = "%s %s (%s)";

    private boolean isTaskDone;
    private String taskName;
    private String taskDescription;

    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Sets the completion status of the task.
     * @param status new task completion status
     */
    public void setTaskDone(boolean status) {
        isTaskDone = status;
    }

    /**
     * Returns the task as a formatted string.
     */
    @Override
    public String toString() {
        String taskStatusString = isTaskDone ? ICON_COMPLETED : ICON_UNCOMPLETED;
        if (taskDescription == null) {
            return String.format(TASK_STRING_NO_DESC, taskStatusString, taskName);
        }
        return String.format(TASK_STRING_WITH_DESC, taskStatusString, taskName, taskDescription);
    }
}
