package seedu.duke.tasks;

public class Task {
    public static final String ICON_UNCOMPLETED = "( )";
    public static final String ICON_COMPLETED = "(X)";
    public static final String TASK_STRING_NO_DESC_NO_TIME = "%s %s";
    public static final String TASK_STRING_WITH_DESC_NO_TIME = "%s %s (%s)";
    public static final String TASK_STRING_NO_DESC_WITH_TIME = "%s %s (Estimated Working Time: %s)";
    public static final String TASK_STRING_WITH_DESC_WITH_TIME = "%s %s (%s) (Estimated Working Time: %s)";

    private boolean isTaskDone;
    private String taskName;
    private String taskDescription;
    private String estimatedWorkingTime;

    public Task(String taskName, String taskDescription, String estimatedWorkingTime) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
        this.estimatedWorkingTime = estimatedWorkingTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getEstimatedWorkingTime() {
        return estimatedWorkingTime;
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
        if (taskDescription != null && estimatedWorkingTime != null) {
            return String.format(TASK_STRING_WITH_DESC_WITH_TIME, taskStatusString, taskName, taskDescription, estimatedWorkingTime);
        }
        else if (taskDescription != null) {
            return String.format(TASK_STRING_WITH_DESC_NO_TIME, taskStatusString, taskName, taskDescription);
        }
        else if (estimatedWorkingTime != null) {
            return String.format(TASK_STRING_NO_DESC_WITH_TIME, taskStatusString, taskName, estimatedWorkingTime);
        }
        else {
            return String.format(TASK_STRING_NO_DESC_NO_TIME, taskStatusString, taskName);
        }

    }
}
