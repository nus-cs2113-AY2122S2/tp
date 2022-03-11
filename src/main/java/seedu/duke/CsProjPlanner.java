package seedu.duke;

import seedu.duke.commandhandler.CommandHandler;
import seedu.duke.exceptions.IllegalCommandException;
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
            try {
                commandHandler.handleUserInput(projectList);
            } catch (IllegalCommandException e) {
                System.out.println("The input does not conform to the required format.");
            }
        }

    }
}
