package cpp.ui;

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
        System.out.println("Here are all the possible commands:");
        System.out.println(count++ + ". addproject [Project Name]");
        System.out.println(count++ + ". deleteproject [Project Name]");
        System.out.println(count++ + ". changegit [Project Index] [GitHub URL]");
        System.out.println(count++ + ". opengit [Project Index]");
        System.out.println(count++ + ". projdeadline [Project Index] [Date]");
        System.out.println(count++ + ". tododeadline [Project Index] [Todo Index] [Date]");
        System.out.println(count++ + ". todo [Project Index] [Description]");
        System.out.println(count++ + ". mark [Project Index] [Todo Index]");
        System.out.println(count++ + ". listprojects/listproject");
        System.out.println(count++ + ". addlanguage [Project Index] [Language]");
        System.out.println(count++ + ". listlanguages [Project Index]");
        System.out.println(count++ + ". view [Project Index]");
        System.out.println(count++ + ". exit");
    }

    public static void invalidHelp() {
        System.out.println("Sorry the \"help\" command is only one argument");
    }

    public static String addProjectSuccessfully(String name) {
        return ("The project " + name + " has been added successfully.");
    }

    public static String addTodoSuccessfully(String todo) {
        return ("Todo " + todo + " has been added successfully.");
    }

    public static String addTodoUnsuccessfully(String todo) {
        return ("Unsuccessful to add todo " + todo + " Please check the target todo index.");
    }

    public static String markTodoSuccessfully() {
        return ("Todo has been marked as done.");
    }

    public static String markTodoUnsuccessfully() {
        return ("Unsuccessful operation. Please check the target indexes.");
    }

    public static String addLanguageSuccessfully(String projectTitle, String language) {
        return ("Language added to " + projectTitle + ": " + language);
    }

    public static String addLanguageUnsuccessfully() {
        return ("Unsuccessful addition of language");
    }

    public static String listLanguageSuccessfully() {
        return ("Languages have been listed successfully.");
    }

    public static String listLanguageUnsuccessfully() {
        return "ListLanguage command unsuccessful.";
    }

    public static String deleteProjectSuccessfully(String projectTitle) {
        return (projectTitle + " deleted successfully.");
    }

    public static String deleteProjectUnsuccessfully(String projectTitle) {
        return ("Unsuccessful deletion of project " + projectTitle);
    }

    public static String listProjectsSuccessfully() {
        return ("Projects have been listed successfully.");
    }

    public static String viewCommandExecuted() {
        return ("View command has been executed successfully.");
    }

    public static String viewCommandUnsuccessfully() {
        return "Viewing command unsuccessful";
    }

    public static String projectDeadlineSuccessfully(String projectTitle, String deadline) {
        return "Deadline added to " + projectTitle + ": " + deadline;
    }

    public static String deadlineUnsuccessfully() {
        return "Unsuccessful addition of Deadline.";
    }

    public static String todoDeadlineSuccessfully(String todoDescription, String deadline) {
        return "Deadline added to " + todoDescription + ": " + deadline;
    }

    public static String addGithubLinkSuccessfully(String projectTitle, String githubLink) {
        return "Github repo link " + githubLink + " added (or changed) in " + projectTitle + ".";
    }

    public static String addGithubLinkUnsuccessfully() {
        return "Github repo link addition (or change) was unsuccessful.";
    }

    public static String openGitCommandExecuted() {
        return "Open Git Command Executed";
    }

    public static String openGitSuccessfully(String projectTitle) {
        return "Github link opened for " + projectTitle;
    }

    public static String openGitUnsuccessfully(int projectIndex) {
        return "Github link was unsuccessful for " + projectIndex;
    }
}
