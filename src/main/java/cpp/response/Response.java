package cpp.response;

import cpp.Constants;

/**
 * Handles the responses to give to user.
 */
public class Response {
    public static void displayWelcome() {
        System.out.println("\n   ___________ ____               _    ____  __\n"
                + "  / ____/ ___// __ \\_________    (_)  / __ \\/ /___ _____  ____  ___  _____\n"
                + " / /    \\__ \\/ /_/ / ___/ __ \\  / /  / /_/ / / __ `/ __ \\/ __ \\/ _ \\/ ___/\n"
                + "/ /___ ___/ / ____/ /  / /_/ / / /  / ____/ / /_/ / / / / / / /  __/ /\n"
                + "\\____//____/_/   /_/   \\____/_/ /  /_/   /_/\\__,_/_/ /_/_/ /_/\\___/_/\n"
                + "                           /___/");
        System.out.println("\nWelcome to CSProjPlanner\n");
        System.out.println("Enter \"help\" for available commands");
        System.out.println(Constants.SEPARATOR);
        System.out.println("What can I help you with?");
        System.out.println(Constants.SEPARATOR);
    }

    public static void displayNext() {
        System.out.println("What can I help you with next?");
        System.out.println(Constants.SEPARATOR);
    }

    public static void displayExit() {
        System.out.println(Constants.SEPARATOR);
        System.out.println("Bye. Wish you have a nice day.");
        System.out.println(Constants.SEPARATOR);
    }

    public static void printHelp() {
        int count = 1;
        System.out.println(Constants.SEPARATOR);
        System.out.println("Here are all the possible commands:");
        System.out.println(count++ + ". addProject <Project Name>");
        System.out.println(count++ + ". deleteProject <Project Name>");
        System.out.println(count++ + ". addDeadline <Project Name> <Date>");
        System.out.println(count++ + ". todo <Project Index> <Description>");
        System.out.println(count++ + ". mark <Project Index> <Todo Index>");
        System.out.println(count++ + ". listProjects/listProject");
        System.out.println(count++ + ". view <Project Name>");
        System.out.println(count++ + ". exit");
        System.out.println(Constants.SEPARATOR);
    }

    public static void printDefault() {
        System.out.println(Constants.SEPARATOR);
        System.out.println("Unknown command.");
        System.out.println(Constants.SEPARATOR);
    }
}
