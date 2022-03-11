package seedu.sherpass.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;


    /**
     * Creates a constructor for the parent class of tasks, 'Task'.
     * Accepts only task description
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * Replaces task date with new task date input.
     * Refreshes the task to original state, i.e. unmarked.
     * Current parent class is created as template for child
     * classes to perform this function.
     *
     * @param date Task Date.
     */
    public void resetInput(String date) {
        markAsUndone();
    }

    /**
     * Returns task date. Parent class is created
     * as a template for child classes to perform its own
     * respective functions, i.e. each task date returned is
     * different for each task type.
     *
     * @return White space.
     */
    public String getDate() {
        return " ";
    }

    /**
     * Returns task type. Current parent class is created
     * as a template for child classes to perform its own
     * respective functions.
     *
     * @return Nothing as function is abstract.
     */
    public abstract String getType();


    /**
     * Returns a string version of the task content.
     * Content includes mark status, task type and description.
     * For ease of printing the task.
     *
     * @return Task content.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
