package seedu.duke.projects;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a list of projects.
 */

public class ProjectList {
    private ArrayList<Project> projectList;

    /**
     * Constructs a ProjectList object.
     *
     */
    public ProjectList() {
        projectList = new ArrayList<Project>();
    }

    /**
     * Adds a new Project to projectList.
     *
     * @param title Name of the Project
     */
    public void addProject(String title) {
        projectList.add(new Project(title));
    }

    /**
     * Deletes a Project from projectList.
     *
     * @param title Name of the project
     */
    public void deleteProject(String title) {
        projectList.remove(title);
    }

    /**
     * Add a todo to one project in projectList.
     * @param indexString Index of the project
     */

    public void addTodoToProject(String indexString, String todoString) {
        int index;
        try{
            index = Integer.parseInt(indexString);
        }
        catch (NumberFormatException e){
            System.out.println("The project index is not an integer!");
            return;
        }

        Project targetProject = new Project();
        targetProject = projectList.get(index-1);

        targetProject.addTodo(todoString);
        System.out.println("Todos have been added to project " + targetProject.getTitle());
    }

    /**
     * Marks a todo in a project as done.
     * @param indexStringProj Index of the project.
     * @param indexStringTodo Index of the todo.
     */

    public void markTodoAsDone(String indexStringProj, String indexStringTodo) {
        int indexProj, indexTodo;
        try {
            indexProj = Integer.parseInt(indexStringProj);
            indexTodo = Integer.parseInt(indexStringTodo);
        }catch (NumberFormatException e){
            System.out.println("The input is not an integer!");
            return;
        }
        Project targetProject = projectList.get(indexProj-1);
        targetProject.markTodoAsDone(indexTodo);
    }

    public int getSize(){
        return projectList.size();
    }
}
