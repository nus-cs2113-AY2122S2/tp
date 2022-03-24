package cpp.model.project;

import java.util.ArrayList;

public class Project {
    private String title;
    private ArrayList<Todo> todos;
    private String deadline;

    public Project() {
        todos = new ArrayList<Todo>();
    }

    /**
     * Constructs a Project object.
     *
     * @param title The name of the Project object
     */
    public Project(String title) {
        this.title = title;
        todos = new ArrayList<Todo>();
    }

    /**
     * Marks a todo at specific index as done.
     *
     * @param index the target index
     */
    public void markTodoAsDone(int index) {
        if (index > todos.size() || index < 1) {
            throw new IndexOutOfBoundsException();
        }
        todos.get(index - 1).markAsDone();
        System.out.println("Todo has been marked as done successfully: ");
        System.out.println(todos.get(index - 1));
    }

    public void addTodo(String todoString) {
        Todo newTodo = new Todo(todoString);
        todos.add(newTodo);
        System.out.println("Todo: " + todoString + " have been added to project " + getTitle());
    }

    public Todo getTodo(int index) {
        return todos.get(index - 1);
    }

    /**
     * Gets the title of a project.
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a deadline to the Project.
     *
     * @param deadline Deadline of the project
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Gets the deadline of the Project.
     *
     * @return The deadline of the Project
     */
    public String getDeadline() {
        if (deadline == null) {
            return "No deadline specified";
        }
        return deadline;
    }

    /**
     * Gets the list of all todos in the Project.
     *
     * @return List of all todos
     */
    public ArrayList<Todo> getTodos() {
        return this.todos;
    }

    /**
     * Displays all details of the project.
     */
    public void printDetails() {
        System.out.print("Project Name: " + getTitle() + "\n");
        System.out.print("Deadline: " + getDeadline() + "\n");
        int count = 1;
        for (Todo todo: getTodos()) {
            System.out.print("\t" + "[" + count + "]. " + todo.toString() + "\n");
            count++;
        }
    }
}
