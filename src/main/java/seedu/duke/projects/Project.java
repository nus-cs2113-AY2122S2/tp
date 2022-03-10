package seedu.duke.projects;

import java.util.ArrayList;

public class Project {
    private String title;
    private ArrayList<Todo> todos;

    public Project(){

    }

    /**
     * Constructs a Project object.
     *
     * @param title The name of the Project object
     */
    public Project(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    /**
     * Sets the todolist of a project
     * @param todos
     */

    public void setTodos(ArrayList<Todo> todos){
        this.todos = todos;
    }

    /**
     * Marks a todo at specific index as done.
     * @param index
     */
    public void markTodoAsDone(int index){
        if (index > todos.size()){
            throw new IndexOutOfBoundsException();
        }
        todos.get(index-1).markAsDone();
        System.out.println("Todo has been marked as done successfully: ");
        System.out.println(todos.get(index-1));
    }
}
