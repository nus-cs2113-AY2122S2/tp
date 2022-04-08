package cpp.model;

import cpp.ui.Constants;
import cpp.exceptions.NegativeIndexException;
import cpp.model.project.Project;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
     * It will check if the project has existed already in the list by using findProjectIndex() method.
     *
     * @param title Name of the Project
     */
    public void addProject(String title) {
        int index = findProjectIndex(title);
        if (index == Constants.PROJECT_NOT_FOUND) { //this is a new project
            projectList.add(new Project(title));
            System.out.println(title + " added.");
        } else {
            System.out.println("Failed to add " + title + ". You have added this project before!");
        }
    }

    /**
     * Deletes a Project from projectList.
     *
     * @param title Name of the project
     */
    public void deleteProject(String title) throws IndexOutOfBoundsException {
        int index = findProjectIndex(title);
        if (index > projectList.size() || index < 0) {
            throw new IndexOutOfBoundsException(Constants.INDEX_OUT_OF_RANGE);
        }
        projectList.remove(index);
        System.out.println(title + " deleted.");
    }

    /**
     * prints all projects in the projectList.
     */
    public void printProject() {
        if (projectList.size() == 0) { //no project in the list, print another message
            System.out.println("You have not added any projects yet!");
            return;
        }
        int count = 1;
        System.out.println("Here are all current project(s) in your list:");
        for (Project project: projectList) {
            System.out.println("[" + count + "] " + project.getTitle() + " (" + project.getDeadline() + ")");
            count++;
        }
    }

    /**
     * Adds a todo to one project in projectList.
     *
     * @param index Index of the project
     * @param todoString The todo task the user wants to add to the project
     */
    public void addTodoToProject(int index, String todoString) throws IndexOutOfBoundsException {
        assert (todoString != null && !todoString.equals("")) : "Cannot add todo string to a project.";
        if (index > projectList.size()) {
            throw new IndexOutOfBoundsException();
        }
        Project targetProject;
        targetProject = projectList.get(index - 1);
        assert targetProject != null : "The target project does not exist!";
        targetProject.addTodo(todoString);
    }

    /**
     * Marks a todo in a project as done.
     *
     * @param indexProj Index of the project.
     * @param indexTodo Index of the todo.
     */
    public void markTodoAsDone(int indexProj, int indexTodo) throws IndexOutOfBoundsException {
        if (indexProj > projectList.size()) {
            throw new IndexOutOfBoundsException();
        }
        Project targetProject = projectList.get(indexProj - 1);
        targetProject.markTodoAsDone(indexTodo);
    }

    /**
     * Adds a deadline to a specified Project.
     *
     * @param index Index of the project.
     * @param deadline Deadline for the project
     */
    public void addProjectDeadline(int index, String deadline) throws IndexOutOfBoundsException {
        assert (deadline != null && !deadline.equals("")) : "Cannot add deadline.";
        if (index > projectList.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index != Constants.PROJECT_NOT_FOUND) {
            Project project = projectList.get(index - 1);
            try {
                project.setDeadline(deadline);
                System.out.println("Deadline set for " + project.getTitle() + ": " + deadline);
            } catch (DateTimeParseException e) {
                System.out.println("Improper format. Please type it in yyyy-mm-dd format.");
            }
        } else {
            System.out.println("Sorry! There was no project with that name.");
        }
    }

    /**
     * Changes the GitHub repo link of a Project.
     *
     * @param index Index of the project
     * @param githubLink New GitHub repo link for the project
     */
    public void addGithubLink(int index, String githubLink) {
        assert (githubLink != null && githubLink.equals("")) : "Cannot add deadline.";
        if (index != Constants.PROJECT_NOT_FOUND) {
            Project project = projectList.get(index - 1);
            try {
                if ((!githubLink.startsWith("http://") && !githubLink.startsWith("https://"))) {
                    throw new URISyntaxException(githubLink, "invalid format");
                }
                project.setGitHubLink(githubLink);
                System.out.println("The Github Repo for " + project.getTitle() + " has been changed to: " + githubLink);
            } catch (URISyntaxException e) {
                System.out.println(Constants.MESSAGE_INVALID_LINK_FORMAT);
            }
        } else {
            System.out.println("Sorry! There was no project with that name.");
        }

    }
    
    /**
     * Opens the GitHub repo in the user's primary browser.
     *
     * @param title The project title to have its repo opened
     */
    public void openGit(String title) {
        assert (title != null && !title.equals("")) : "Cannot view the project.";
        int index = findProjectIndex(title);
        if (index == Constants.PROJECT_NOT_FOUND) { //project not found
            System.out.println("Sorry! There was no project with that name.");
        } else {
            Project project = projectList.get(index);
            project.openGit();
        }
    }

    /**
     * Adds a deadline to a specified Todo.
     *
     * @param indexProj Index of the project.
     * @param indexTodo Index of the todo.
     * @param deadline Deadline for the todo
     */
    public void addTodoDeadline(int indexProj, int indexTodo, String deadline) {
        if (indexProj > projectList.size()) {
            throw new IndexOutOfBoundsException();
        }
        Project targetProject = projectList.get(indexProj - 1);

        try {
            targetProject.addTodoDeadline(indexTodo, deadline);
            String todoString = targetProject.getTodos().get(indexTodo - 1).getDescription();
            System.out.println("Deadline set for " + todoString + ": " + deadline);
        } catch (DateTimeParseException e) {
            System.out.println("Improper format. Please type it in yyyy-mm-dd format.");
        }
    }

    /**
     * Gets the total number of projects in the list (for test use).
     *
     * @return total number of projects in the list
     */
    public int getProjectNo() {
        return projectList.size();
    }

    /**
     * Gets certain project based on given index.
     *
     * @param index index of a given project
     * @return target project with given index
     */
    public Project getProject(int index) {
        assert (index >= 0 && index < projectList.size()) : "Index out of range!";
        if (index > projectList.size()) {
            return null;
        }
        return projectList.get(index);
    }

    private int findProjectIndex(String name) {
        assert (name != null && !name.equals("")) : "Cannot findProjectIndex.";
        int count = 0;

        for (Project project : projectList) {
            if (project.getTitle().equalsIgnoreCase(name)) { //find the index of the project
                return count;
            }
            count++;
        }
        return Constants.PROJECT_NOT_FOUND;
    }

    /**
     * Displays all details of a specified Project.
     *
     * @param title Name of the project
     */
    public void view(String title) {
        assert (title != null && !title.equals("")) : "Cannot view the project.";
        int index = findProjectIndex(title);
        if (index == Constants.PROJECT_NOT_FOUND) { //project not found
            System.out.println("Sorry! There was no project with that name.");
        } else {
            Project project = projectList.get(index);
            project.printDetails();
            listLanguages(title);
        }

    }

    /**
     * Displays all programming languages of a specified Project.
     *
     * @param title Name of the project
     */
    public void listLanguages(String title) {
        assert (title != null && !title.equals("")) : "Cannot list languages for this project.";
        int index = findProjectIndex(title);
        if (index == Constants.PROJECT_NOT_FOUND) { //project not found
            System.out.println("Sorry! There was no project with that name.");
        } else {
            Project project = projectList.get(index);
            project.printLanguages();
        }
    }

    public void addLanguages(int index, String language) {
        assert (language != null && !language.equals("")) : "Cannot list languages for this project.";
        if (index == Constants.PROJECT_NOT_FOUND) {
            System.out.println(Constants.INVALID_PROJECT_NAME);
        } else {
            Project project = projectList.get(index - 1);
            project.addLanguage(language);
            System.out.println(language + " language added.");
        }
    }

}
