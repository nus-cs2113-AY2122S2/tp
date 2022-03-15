package cpp.response;

import cpp.Constants;

/**
 * Handles the responses to give to user
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
        System.out.println(Constants.SEPARATOR);
        System.out.println("What can I help you?");
        System.out.println(Constants.SEPARATOR);
    }

    public static void displayNext() {
        System.out.println("What can I help you next?");
        System.out.println(Constants.SEPARATOR);
    }

    public static void displayExit() {
        System.out.println(Constants.SEPARATOR);
        System.out.println("Bye. Wish you have a nice day.");
        System.out.println(Constants.SEPARATOR);
    }

    public static void printAddProject(String title) {
        System.out.println(Constants.SEPARATOR);
        System.out.println(title + " added.");
        System.out.println(Constants.SEPARATOR);
    }

    public static void printDeleteProject(String title) {
        System.out.println(Constants.SEPARATOR);
        System.out.println(title + " deleted.");
        System.out.println(Constants.SEPARATOR);
    }

    public static void printDefault() {
        System.out.println(Constants.SEPARATOR);
        System.out.println("Unknown command.");
        System.out.println(Constants.SEPARATOR);
    }
}
