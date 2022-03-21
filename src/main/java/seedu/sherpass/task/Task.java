package seedu.sherpass.task;

import java.time.LocalDateTime;

import static seedu.sherpass.constant.DateAndTimeFormat.outputWithTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.outputWithoutTimeFormat;

public class Task {

    protected String description;
    protected boolean isDone;
    protected boolean hasByTime;
    protected boolean hasDoOnTime;
    protected LocalDateTime byDate;
    protected LocalDateTime doOnDate;

    /**
     * Creates a constructor for the parent class of tasks, 'Task'.
     * Accepts only task description
     *
     * @param description Description of task.
     */
    public Task(String description, LocalDateTime byDate, LocalDateTime doOnDate,
                boolean hasByTime, boolean hasDoOnTime) {
        this.description = description;
        this.isDone = false;
        this.byDate = byDate;
        this.doOnDate = doOnDate;
        this.hasByTime = hasByTime;
        this.hasDoOnTime = hasDoOnTime;
    }

    /**
     * Returns task description.
     *
     * @return task description.
     */
    public String getDescription() {
        return description;
    }


    /**
     * Returns mark status of task.
     *
     * @return Returns true if task has been marked. False otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns String version of mark status.
     *
     * @return X if task has been marked. White space otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns task date. Parent class is created
     * as a template for child classes to perform its own
     * respective functions, i.e. each task date returned is
     * different for each task type.
     *
     * @return White space.
     */
    public LocalDateTime getByDate() {
        return byDate;
    }

    public LocalDateTime getDoOnDate() {
        return doOnDate;
    }

    public String getByDateString() {
        if (byDate == null) {
            return "";
        } else if (hasByTime) {
            return byDate.format(outputWithTimeFormat);
        }
        return byDate.format(outputWithoutTimeFormat);
    }

    public String getDoOnDateString() {
        if (doOnDate == null) {
            return "";
        } else if (hasDoOnTime) {
            return doOnDate.format(outputWithTimeFormat);
        }
        return doOnDate.format(outputWithoutTimeFormat);
    }

    /**
     * Returns a string version of the task content.
     * Content includes mark status, task type and description.
     * For ease of printing the task.
     *
     * @return Task content.
     */
    @Override
    public String toString() {
        String result = "[" + this.getStatusIcon() + "] " + this.getDescription();
        if (this.byDate != null) {
            result += " (by: " + getByDateString() + ")";
        }
        if (this.doOnDate != null) {
            result += " (reminder on: " + getDoOnDateString() + ")";
        }
        return result;
    }

    public void setTaskDescription(String taskDescription) {
        this.description = taskDescription;
    }

    public void setByDate(LocalDateTime byDate) {
        this.byDate = byDate;
    }

    public void setDoOnDate(LocalDateTime doOnDate) {
        this.doOnDate = doOnDate;
    }

    public boolean getHasByTime() {
        return hasByTime;
    }

    public void setHasByTime(boolean hasByTime) {
        this.hasByTime = hasByTime;
    }

    public boolean getHasDoOnTime() {
        return hasDoOnTime;
    }

    public void setHasDoOnTime(boolean hasDoOnTime) {
        this.hasDoOnTime = hasDoOnTime;
    }
}
