package cpp;

import cpp.exceptions.IllegalCommandException;
import cpp.projects.commandhandler.CommandHandler;
import cpp.projects.ProjectList;
import cpp.response.Response;

import java.util.Scanner;

public class CsProjPlanner {

    private static ProjectList projectList = new ProjectList();
    private static CommandHandler commandHandler = new CommandHandler();

    /**
     * Main entry-point for the CSProjPlanner application.
     */
    public static void main(String[] args) {
        String input;
        Scanner in = new Scanner(System.in);

        Response.displayWelcome(); //display welcome message

        input = in.nextLine();

        while (!input.equalsIgnoreCase("exit")) {
            assert (projectList != null) : "List of projects does not exist.";
            assert (commandHandler != null) : "Command Handler does not exist.";
            try {
                commandHandler.handleUserInput(projectList, input);
            } catch (IllegalCommandException e) {
                System.out.println(e.getMessage());
            }
            Response.displayNext();
            input = in.nextLine(); //fetch next input from the user
        }

        Response.displayExit();

    }
}
