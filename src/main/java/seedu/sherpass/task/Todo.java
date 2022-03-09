package seedu.sherpass.task;

public class Todo extends Task {

    /**
     * Creates a constructor for the 'todo' task.
     * Todo only accepts a task description.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }


    /**
     * Returns the task type.
     *
     * @return "T" for todo.
     */
    @Override
    public String getType() {
        return "T";
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
        return "[T] " + super.toString();
    }
}
