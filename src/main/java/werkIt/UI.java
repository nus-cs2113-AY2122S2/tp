package werkIt;

import data.workouts.Workout;

import java.util.Scanner;

import static textcolors.TextColor.COLOR_RESET;
import static textcolors.TextColor.COLOR_YELLOW;

/**
 * This class contains all the user interface-related texts and methods for the WerkIt! Application.
 */
public class UI {
    // WerkIt ASCII Banner Logo Art
    public static final String WERKIT_BANNER_LOGO = " __        __        _    ___ _   _ \n" +
            " \\ \\      / /__ _ __| | _|_ _| |_| |\n" +
            "  \\ \\ /\\ / / _ \\ '__| |/ /| || __| |\n" +
            "   \\ V  V /  __/ |  |   < | || |_|_|\n" +
            "    \\_/\\_/ \\___|_|  |_|\\_\\___|\\__(_)\n" +
            "                                    ";

    public static final String WELCOME_MESSAGE = "Welcome to WerkIt!, your personal exercise planner.";
    public static final String GOODBYE_MESSAGE = "Thank you for using WerkIt! See you again soon...";
    // The default parameters for printing the formatting lines
    public static final int DEFAULT_LINE_LENGTH = 70;
    public static final String DEFAULT_LINE_CHAR = "-";
    // Prompt symbol
    public static final String PROMPT_SYMBOL = ">";
    // Workout-related messages
    public static final String NEW_WORKOUT_CREATED_MESSAGE = "Alright, the following workout has been created:";

    // Scanner object for reading in user input from standard input
    Scanner inputReader = new Scanner(System.in);

    /**
     * Prints a line on the console based on the default parameters defined in this Java class.
     * <p>
     * Source: Team Member Alan Low's iP codebase
     * Link: https://github.com/alanlowzies/ip/blob/8556dd6a5106d190f5ac0458c6d2c34f98737a91/src/main/java/sora/SoraUI.java
     */
    public void printLine() {
        // Prints a line based on the default parameters
        printLine(DEFAULT_LINE_LENGTH, DEFAULT_LINE_CHAR);
    }

    /**
     * Prints a line on the console based on the specified length and the character/symbol to use
     * <p>
     * Source: Team Member Alan Low's iP codebase
     * Link: https://github.com/alanlowzies/ip/blob/8556dd6a5106d190f5ac0458c6d2c34f98737a91/src/main/java/sora/SoraUI.java
     *
     * @param lineLen The length of the line (measured in 'number of characters').
     * @param character The character that should be used to print the line.
     */
    public void printLine(int lineLen, String character) {
        // Generate a line of whitespaces
        String lineOfWhitespaces = String.format("%" + lineLen + "s", " ");

        // Replace each whitespace with the specified character
        String lineOfChars = lineOfWhitespaces.replaceAll(" ", character);

        // Finally, print out the line of characters
        System.out.println(lineOfChars);
    }

    public void printGreetings() {
        printLine(DEFAULT_LINE_LENGTH, "=");
        System.out.println(WERKIT_BANNER_LOGO);
        System.out.println(WELCOME_MESSAGE);
        printLine();
    }

    public void printGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
        printLine(DEFAULT_LINE_LENGTH, "=");
    }

    /**
     * Prints a message that prompts the user to enter a command
     * <p>
     * Method adapted from Team Member Alan Low's iP codebase.
     * Link: https://github.com/alanlowzies/ip/blob/8556dd6a5106d190f5ac0458c6d2c34f98737a91/src/main/java/sora/SoraUI.java
     */
    public void printUserInputPrompt(boolean isFirstPrompt) {
        printLine();
        if (isFirstPrompt) {
            System.out.println("Now then, what can I do for you today?");
        }
        else {
            System.out.println("What's next?");
        }

        printLine();
        System.out.print(PROMPT_SYMBOL + " ");
    }

    /**
     * Reads in a line of the user from the standard input, trims the input, and returns it as a String.
     * <p>
     * Method adapted from Team Member Alan Low's iP codebase.
     * Link: https://github.com/alanlowzies/ip/blob/8556dd6a5106d190f5ac0458c6d2c34f98737a91/src/main/java/sora/SoraParser.java
     */
    protected String getUserInput() {
        String userInput = inputReader.nextLine();
        String userInputTrimmed = userInput.trim();
        printLine();

        return userInputTrimmed;
    }

    /**
     * Prints a message when a new workout has been created. The newly created workout will also be displayed.
     *
     * @param newWorkout
     */
    public void printNewWorkoutCreatedMessage(Workout newWorkout) {
        System.out.println(NEW_WORKOUT_CREATED_MESSAGE);
        System.out.println();
        System.out.println("\t" + newWorkout.toString());
        System.out.println();
    }

    /**
     * Prints the text in colors pre-defined in TextColor class.
     * @param color ANSI color codes defined in TextColor class ONLY.
     * @param text The string text that needs to be colored.
     */
    public void printColorText(String color, String text) {
        System.out.println(color + text + COLOR_RESET);
    }

    /**
     * Prints the help messages. To be updated.
     */
    public void printHelpMessage() {
        printListHelp();
        printLine();
        printWorkoutAddHelp();
        printLine();
        printWorkoutDeleteHelp();
        printLine();
        printWorkoutUpdateHelp();
        printLine();
        printExitHelp();
    }

    /**
     * Prints help message for 'list' command.
     */
    public void printListHelp() {
        System.out.println("\t To view all workouts, please enter:");
        printColorText(COLOR_YELLOW, "\t workout /list");
        System.out.println("\t This will print all the existing workouts.");
    }

    /**
     * Prints help message for 'workout /new' command.
     */
    public void printWorkoutAddHelp() {
        System.out.println("\t To add a workout, please enter: ");
        printColorText(COLOR_YELLOW, "\t workout /new <exercise name> /reps <no. of repetitions>");
        System.out.println("\t Example: ");
        printColorText(COLOR_YELLOW, "\t workout /new push up /reps 10");
        System.out.println("\t This will add a workout with 10 reps of push up.");
    }

    /**
     * Prints help message for 'workout /delete' command.
     */
    public void printWorkoutDeleteHelp() {
        System.out.println("\t To delete a workout, please enter: ");
        printColorText(COLOR_YELLOW, "\t workout /delete <index>");
        System.out.println("\t Example: ");
        printColorText(COLOR_YELLOW, "\t workout /delete 1");
        System.out.println("\t This will delete the workout with index 1 if exists.");
    }

    /**
     * Prints help message for 'workout /update' command.
     */
    public void printWorkoutUpdateHelp() {
        System.out.println("\t To update a workout, please enter: ");
        printColorText(COLOR_YELLOW, "\t workout /update <index> <quantity>");
        System.out.println("\t Example: ");
        printColorText(COLOR_YELLOW, "\t workout /update 1 15");
        System.out.println("\t This will update the workout with index 1 to 15 reps if exists.");
    }

    /**
     * Prints help message for 'exit' command.
     */
    public void printExitHelp() {
        System.out.println("\t To exit werkIt, please enter: ");
        printColorText(COLOR_YELLOW, "\t exit");
        System.out.println("\t This will exit werkIt.");
    }
}
