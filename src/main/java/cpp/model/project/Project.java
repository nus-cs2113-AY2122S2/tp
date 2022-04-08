package cpp.model.project;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Project {
    private String title;
    private ArrayList<Todo> todos;
    private ArrayList<String> webLinks;
    private String gitHubLink;
    private Deadline deadline;
    private ArrayList<String> languages;

    public Project() {
        todos = new ArrayList<Todo>();
        languages = new ArrayList<String>();
    }

    /**
     * Constructs a Project object.
     *
     * @param title The name of the Project object
     */
    public Project(String title) {
        this.title = title;
        todos = new ArrayList<Todo>();
        webLinks = new ArrayList<String>();
        gitHubLink = new String("http://www.github.com");
        languages = new ArrayList<String>();
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
        System.out.println("Todo has been marked as done successfully:");
        System.out.println(todos.get(index - 1));
    }

    /**
     * Adds a Todo to a project.
     *
     * @param todoString the todo to be added
     */
    public void addTodo(String todoString) {
        Todo newTodo = new Todo(todoString);
        todos.add(newTodo);
        System.out.println("Todo: " + todoString + " have been added to project " + getTitle());
    }

    /**
     * Gets the Todo a specified index.
     *
     * @param index index of desired todo
     * @return the Todo
     */
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
     * @param stringDeadline string representation of Deadline
     */
    public void setDeadline(String stringDeadline) {
        try {
            this.deadline = new Deadline(stringDeadline);
        } catch (DateTimeParseException e) {
            throw e;
        }
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
        return deadline.toString();
    }

    public void addTodoDeadline(int index, String stringDeadline) {
        try {
            todos.get(index - 1).setDeadline(new Deadline(stringDeadline));
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Gets the list of all todos in the Project.
     *
     * @return List of all todos
     */
    public ArrayList<Todo> getTodos() {
        return this.todos;
    }

    public ArrayList<String> getLanguages() {
        return this.languages;
    }


    /**
     * Gets the gitHub repo link for this project.
     *
     * @return The GitHub repo link if it exists, empty string otherwise
     */
    public String getGitHubLink() {
        return this.gitHubLink;
    }

    /**
     * Sets the gitHub repo link for this project.
     *
     * @param link The URL of the repo
     */
    public void setGitHubLink(String link) {
        this.gitHubLink = link;
    }

    public void openGit() {
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI(gitHubLink));
        } catch (URISyntaxException e) {
            System.out.println("This project's GitHub link doesn't seem functional.\n"
                    + "Please use the addgit command with a functional URL.");
        } catch (IOException e) {
            System.out.println("The link is functional, but your browser cannot be opened.");
        }
    }

    /**
     * Displays all details of the project.
     */
    public void printDetails() {
        System.out.print("Project Name: " + getTitle() + "\n");
        System.out.print("Deadline: " + getDeadline() + "\n");
        System.out.println("GitHub Repo: " + getGitHubLink());
        int count = 1;
        for (Todo todo: getTodos()) {
            System.out.print("\t" + "[" + count + "]. " + todo.toString() + "\n");
            count++;
        }
    }


    /**
     * Displays all programming languages of the project.
     */
    public void printLanguages() {
        System.out.print("Programming languages for " + getTitle() + ":\n");
        int count = 1;
        for (String language: getLanguages()) {
            System.out.print("\t" + "[" + count + "]. " + language + "\n");
            count++;
        }
    }

    public void addLanguage(String language) {
        assert (languages != null) : "Cannot add languages to this project.";
        languages.add(language);
    }
}
