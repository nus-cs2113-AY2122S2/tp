package seedu.duke;

import seedu.duke.commandhandler.CommandHandler;
import seedu.duke.projects.ProjectList;

public class CsProjPlanner {

    private static ProjectList projectList = new ProjectList();
    private static CommandHandler commandHandler = new CommandHandler();

    /**
     * Main entry-point for the CSProjPlanner application.
     */
    public static void main(String[] args) {

        System.out.println("Welcome to CSProjPlanner\n");

        while (true) {
            commandHandler.handleUserInput(projectList);
        }

    }
}
