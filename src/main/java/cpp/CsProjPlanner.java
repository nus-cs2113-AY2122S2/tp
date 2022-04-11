package cpp;

import cpp.exceptions.IllegalCommandException;
import cpp.logic.CommandHandler;
import cpp.model.ProjectList;
import cpp.ui.Constants;
import cpp.ui.Response;
import cpp.storage.Storage;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsProjPlanner {

    private static String loggerName = "CSProjPlanner";

    private static Logger logger = Logger.getLogger(loggerName);

    private static ProjectList projectList = new ProjectList();
    private static CommandHandler commandHandler = new CommandHandler();

    /**
     * Main entry-point for the CSProjPlanner application.
     */
    
    public static void main(String[] args) {
        //read data from hard disk
        projectList = Storage.read();
        logger.log(Level.INFO, "going to receive user input");
        String input;
        Scanner in = new Scanner(System.in);

        Response.displayWelcome(); //display welcome message

        input = in.nextLine();

        String result;

        while (!input.equalsIgnoreCase("exit")) {
            assert (projectList != null) : "List of projects does not exist.";
            assert (commandHandler != null) : "Command Handler does not exist.";
            System.out.println(Constants.SEPARATOR);
            try {
                result = commandHandler.handleUserInput(projectList, input);
            } catch (IllegalCommandException e) {
                result = e.getMessage();
                System.out.println("Execution result: " + result);
            }
            System.out.println(Constants.SEPARATOR);
            Response.displayNext();
            input = in.nextLine(); //fetch next input from the user
        }
        //automatically save data here
        Storage.save(projectList);
        Response.displayExit();
        logger.log(Level.INFO, "Exiting the system...");

    }
}
