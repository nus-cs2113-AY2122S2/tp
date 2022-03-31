package cpp.model.project;

/**
 * Represents a todo in a Todo.
 */
public class Todo {
    private String description;
    private boolean isDone;
    private Deadline deadline;

    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description + ": " + getDeadline();
    }

    /**
     * Gets the status of a todo.
     *
     * @return todo status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the todo as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the description of a todo.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the done status of a todo.
     *
     * @return todo status isDone as String
     */
    public String getDone() {
        return isDone ? "true" : "false";
    }

    /**
     * Sets a deadline to the Todo.
     *
     * @param deadline Deadline to be added
     */
    public void setDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Gets the deadline of the Todo.
     *
     * @return The deadline of the Todo
     */
    public String getDeadline() {
        if (deadline == null) {
            return "No deadline specified";
        }
        return deadline.toString();
    }
}
