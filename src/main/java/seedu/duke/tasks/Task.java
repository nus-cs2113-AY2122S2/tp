package seedu.duke.tasks;

import seedu.duke.util.StringConstants;

import java.util.ArrayList;

public class Task {
    public static final String ICON_UNCOMPLETED = StringConstants.ICON_UNCOMPLETED;
    public static final String ICON_COMPLETED = StringConstants.ICON_COMPLETED;
    public static final String TASK_STRING_NO_DESC_NO_TIME = "%s %s %s";
    public static final String TASK_STRING_WITH_DESC_NO_TIME = "%s %s (%s) %s";
    public static final String TASK_STRING_NO_DESC_WITH_TIME = "%s %s ("
            + StringConstants.ESTIMATED_WORKING_TIME + "%s) %s";
    public static final String TASK_STRING_WITH_DESC_WITH_TIME = "%s %s (%s) ("
            + StringConstants.ESTIMATED_WORKING_TIME + "%s) %s";

    private boolean isTaskDone;
    private String taskName;
    private String taskDescription;
    private String workingTime;
    private TaskParameters taskParameters;
    private ArrayList<String> tags;

    public Task(String taskName, String taskDescription, String workingTime) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
        this.workingTime = workingTime;
        this.taskParameters = getTaskParameterStatus();
        tags = new ArrayList<>();
    }

    public ArrayList<String> getTagList() {
        return tags;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setTaskDescription(String description) {
        this.taskDescription = description;
        this.taskParameters = getTaskParameterStatus();
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
        this.taskParameters = getTaskParameterStatus();
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**.
     * Check what are the tasks parameters input by user
     * @return Task parameters status
     */
    public TaskParameters getTaskParameterStatus() {
        boolean hasTaskDescriptionAndWorkingTime = (taskDescription != null && workingTime != null);
        boolean hasTaskDescriptionOnly = (taskDescription != null);
        boolean hasWorkingTimeOnly = (workingTime != null);
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
                    taskDescription, workingTime, tags);
        case DESCRIPTION_ONLY:
            return String.format(TASK_STRING_WITH_DESC_NO_TIME, taskStatusString, taskName, taskDescription, tags);
        case WORKING_TIME_ONLY:
            return String.format(TASK_STRING_NO_DESC_WITH_TIME, taskStatusString, taskName, workingTime, tags);
        default:
            return String.format(TASK_STRING_NO_DESC_NO_TIME, taskStatusString, taskName, tags);
        }
    }
}
