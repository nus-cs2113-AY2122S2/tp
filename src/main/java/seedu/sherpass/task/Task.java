package seedu.sherpass.task;

import java.time.LocalDateTime;

import static seedu.sherpass.constant.DateAndTimeFormat.outputWithTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.outputWithoutTimeFormat;
import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.WHITESPACE;

public class Task {
    protected String description;
    protected int identifier;
    protected boolean isDone;
    protected LocalDateTime byDateTime;
    protected LocalDateTime doOnStartDateTime;
    protected LocalDateTime doOnEndDateTime;

    protected int index;

    /**
     * Creates an object for the class 'Task'.
     * Accepts only task description.
     *
     * @param identifier  Identity number of a repeated task.
     * @param description Description of task.
     */
    public Task(int identifier, String description, LocalDateTime byDateTime,
                LocalDateTime doOnStartDateTime, LocalDateTime doOnEndDateTime) {
        this.identifier = identifier;
        this.description = description;
        this.byDateTime = byDateTime;
        this.doOnStartDateTime = doOnStartDateTime;
        this.doOnEndDateTime = doOnEndDateTime;
        this.isDone = false;
        this.index = 0;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Returns the time range for when the task is taking place.
     * Format of time is in 24 hours.
     *
     * @return Returns a range of time when the task occurs.
     */
    public String getDoOnDateTimePeriod() {
        return doOnStartDateTime.toLocalTime().toString()
                + " - " + doOnEndDateTime.toLocalTime().toString();
    }

    public String getDescription() {
        return description;
    }

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

    public void markAsDone() {
        isDone = true;
    }

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
    public LocalDateTime getByDateTime() {
        return byDateTime;
    }

    public LocalDateTime getDoOnStartDateTime() {
        return doOnStartDateTime;
    }

    /**
     * Returns the by date in String format.
     *
     * @return Returns if byDate contains a parsed date. Otherwise, returns a blank string (no whitespace).
     */
    public String getByDateString() {
        if (byDateTime != null) {
            return byDateTime.format(outputWithTimeFormat);
        }
        return EMPTY_STRING;
    }

    /**
     * Returns a string version of the do on date (includes the time period of when the task occurs).
     *
     * @return do on date in string format.
     */
    public String getDoOnDateString() {
        return doOnStartDateTime.format(outputWithoutTimeFormat) + WHITESPACE + getDoOnDateTimePeriod();
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
        String result = index + ". [" + this.getStatusIcon() + "] " + this.getDescription();
        if (this.doOnStartDateTime != null) {
            result += " (to do on: " + getDoOnDateString() + ")";
        }
        if (this.byDateTime != null) {
            result += " (by: " + getByDateString() + ")";
        }
        return result;
    }

    public String printTask() {
        String result = this.getDescription() + " (to do on: " + getDoOnDateString() + ")";
        if (this.byDateTime != null) {
            result += " (by: " + getByDateString() + ")";
        }
        return result;
    }

    public void setTaskDescription(String taskDescription) {
        this.description = taskDescription;
    }

    public void setByDateTime(LocalDateTime byDateTime) {
        this.byDateTime = byDateTime;
    }

    public void setDoOnStartDateTime(LocalDateTime doOnStartDateTime) {
        this.doOnStartDateTime = doOnStartDateTime;
    }

    public void setDoOnEndDateTime(LocalDateTime doOnEndDateTime) {
        this.doOnEndDateTime = doOnEndDateTime;
    }

    public LocalDateTime getDoOnEndDateTime() {
        return doOnEndDateTime;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Task)) {
            return false;
        }

        Task t = (Task) o;
        return description.equals(t.getDescription())
                && doOnStartDateTime.equals(t.getDoOnStartDateTime())
                && doOnEndDateTime.equals(t.getDoOnEndDateTime())
                && identifier == (t.getIdentifier())
                && byDateTime.equals(t.getByDateTime());
    }

    public Task copy() {
        return new Task(this.identifier, this.description, this.byDateTime,
                this.doOnStartDateTime, this.doOnEndDateTime);
    }
}