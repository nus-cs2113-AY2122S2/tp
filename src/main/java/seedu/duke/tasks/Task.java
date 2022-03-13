package seedu.duke.tasks;

import seedu.duke.ui.TextUi;

public class Task {
    public static final String ICON_UNCOMPLETED = TextUi.ICON_UNCOMPLETED;
    public static final String ICON_COMPLETED = TextUi.ICON_COMPLETED;
    public static final String TASK_STRING_NO_DESC_NO_TIME = "%s %s";
    public static final String TASK_STRING_WITH_DESC_NO_TIME = "%s %s (%s)";
    public static final String TASK_STRING_NO_DESC_WITH_TIME = "%s %s (" + TextUi.ESTIMATED_WORKING_TIME + "%s)";
    public static final String TASK_STRING_WITH_DESC_WITH_TIME = "%s %s (%s) (" + TextUi.ESTIMATED_WORKING_TIME + "%s)";

    private boolean isTaskDone;
    private String taskName;
    private String taskDescription;
    private String estimatedWorkingTime;
    private TaskParameters taskParameters;

    public Task(String taskName, String taskDescription, String estimatedWorkingTime) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
        this.estimatedWorkingTime = estimatedWorkingTime;
        this.taskParameters = getTaskParameterStatus();
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

    /**.
     * Check what are the tasks parameters input by user
     * @return Task parameters status
     */
    public TaskParameters getTaskParameterStatus() {
        boolean hasTaskDescriptionAndWorkingTime = (taskDescription != null && estimatedWorkingTime != null);
        boolean hasTaskDescriptionOnly = (taskDescription != null);
        boolean hasWorkingTimeOnly = (estimatedWorkingTime != null);
        if (hasTaskDescriptionAndWorkingTime) {
            return TaskParameters.DESCRIPTION_AND_WORKING_TIME;
        } else if (hasTaskDescriptionOnly) {
            return TaskParameters.DESCRIPTION_ONLY;
        } else if (hasWorkingTimeOnly) {
            return TaskParameters.WORKING_TIME_ONLY;
        } else {
            return TaskParameters.NO_DESCRIPTION_OR_WORKING_TIME;
        }
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
        switch (taskParameters) {
        case DESCRIPTION_AND_WORKING_TIME:
            return String.format(TASK_STRING_WITH_DESC_WITH_TIME, taskStatusString, taskName,
                    taskDescription, estimatedWorkingTime);
        case DESCRIPTION_ONLY:
            return String.format(TASK_STRING_WITH_DESC_NO_TIME, taskStatusString, taskName, taskDescription);
        case WORKING_TIME_ONLY:
            return String.format(TASK_STRING_NO_DESC_WITH_TIME, taskStatusString, taskName, estimatedWorkingTime);
        default:
            return String.format(TASK_STRING_NO_DESC_NO_TIME, taskStatusString, taskName);
        }
    }
}
