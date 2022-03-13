package cpp.projects;

import java.util.ArrayList;

public class Project {
    private String title;
    private ArrayList<Todo> todos;

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

    public String getTitle() {
        return title;
    }


    /**
     * Marks a t/odo at specific index as done.
     * @param index the target index
     */
    public void markTodoAsDone(int index) {
        if (index > todos.size()) {
            throw new IndexOutOfBoundsException();
        }
        todos.get(index - 1).markAsDone();
        System.out.println("Todo has been marked as done successfully: ");
        System.out.println(todos.get(index - 1));
    }

    public void addTodo(String todoString) {
        Todo newTodo = new Todo(todoString);
        todos.add(newTodo);
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

}
