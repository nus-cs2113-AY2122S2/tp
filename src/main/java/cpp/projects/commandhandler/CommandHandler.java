package cpp.projects.commandhandler;

import cpp.projects.ProjectList;

import java.util.Scanner;

public class CommandHandler {

    /** Scanner for user input. */
    public Scanner sc;
    /** Command entered by user. */
    public String line;
    /** All words of command entered by user. */
    public String[] commands;

    /**
     * Constructs new CommandHandler object.
     *
     */
    public CommandHandler() {

    }

    /**
     * Handles all non-exit commands given to program by user.
     *
     * @param projectList ProjectList for commands to work with
     */
    public void handleUserInput(ProjectList projectList, String userInput) {
        commands = userInput.split(" ");

        switch (commands[0].toLowerCase()) {
        case "addproject": //add a project into list
            projectList.addProject(commands[1]);
            System.out.println("____________________________________________________________");
            System.out.println(commands[1] + " Added.");
            System.out.println("____________________________________________________________");
            break;
        case "deleteproject": //delete a project based on its name
            projectList.deleteProject(commands[1]);
            System.out.println("____________________________________________________________");
            System.out.println(commands[1] + " Deleted.");
            System.out.println("____________________________________________________________");
            break;
        case "listprojects": //view all project(s) by name
            projectList.printProject();
            break;
        default:
            System.out.println("____________________________________________________________");
            System.out.println("Unknown command.");
            System.out.println("____________________________________________________________");
            break;
        }

    }
}
