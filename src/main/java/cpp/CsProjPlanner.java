package cpp;

import cpp.projects.commandhandler.CommandHandler;
import cpp.projects.ProjectList;

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

        welcomeDisplay(); //display welcome message

        input = in.nextLine();

        while (!input.equalsIgnoreCase("exit")) {
            commandHandler.handleUserInput(projectList, input);
            System.out.println("What can I help you next?");
            System.out.println("____________________________________________________________");
            input = in.nextLine(); //fetch next input from the user
        }

        exitDisplay();

    }

    private static void welcomeDisplay() {
        System.out.println("\n   ___________ ____               _    ____  __\n"
                + "  / ____/ ___// __ \\_________    (_)  / __ \\/ /___ _____  ____  ___  _____\n"
                + " / /    \\__ \\/ /_/ / ___/ __ \\  / /  / /_/ / / __ `/ __ \\/ __ \\/ _ \\/ ___/\n"
                + "/ /___ ___/ / ____/ /  / /_/ / / /  / ____/ / /_/ / / / / / / /  __/ /\n"
                + "\\____//____/_/   /_/   \\____/_/ /  /_/   /_/\\__,_/_/ /_/_/ /_/\\___/_/\n"
                + "                           /___/");
        System.out.println("\nWelcome to CSProjPlanner\n");
        System.out.println("____________________________________________________________");
        System.out.println("What can I help you?");
        System.out.println("____________________________________________________________");
    }

    private static void exitDisplay() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Wish you have a nice day.");
        System.out.println("____________________________________________________________");
    }
}
