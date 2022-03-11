package seedu.duke;

import seedu.duke.commandhandler.CommandHandler;
import seedu.duke.projects.ProjectList;

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
        System.out.println("\n"
                + "░█████╗░░██████╗██████╗░██████╗░░█████╗░░░░░░██╗"
                + "\t"
                + "██████╗░██╗░░░░░░█████╗░███╗░░██╗███╗░░██╗███████╗██████╗░\n"
                + "██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔══██╗░░░░░██║"
                + "\t"
                + "██╔══██╗██║░░░░░██╔══██╗████╗░██║████╗░██║██╔════╝██╔══██╗\n"
                + "██║░░╚═╝╚█████╗░██████╔╝██████╔╝██║░░██║░░░░░██║"
                + "\t"
                + "██████╔╝██║░░░░░███████║██╔██╗██║██╔██╗██║█████╗░░██████╔╝\n"
                + "██║░░██╗░╚═══██╗██╔═══╝░██╔══██╗██║░░██║██╗░░██║"
                + "\t"
                + "██╔═══╝░██║░░░░░██╔══██║██║╚████║██║╚████║██╔══╝░░██╔══██╗\n"
                + "╚█████╔╝██████╔╝██║░░░░░██║░░██║╚█████╔╝╚█████╔╝"
                + "\t"
                + "██║░░░░░███████╗██║░░██║██║░╚███║██║░╚███║███████╗██║░░██║\n"
                + "░╚════╝░╚═════╝░╚═╝░░░░░╚═╝░░╚═╝░╚════╝░░╚════╝░"
                + "\t"
                + "╚═╝░░░░░╚══════╝╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚══╝╚══════╝╚═╝░░╚═╝");
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
