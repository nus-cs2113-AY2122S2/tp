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

    public void printColorText(String color, String text) {
        System.out.println("\t " + color + text + COLOR_RESET);
    }

    public void printHelpMessage() {
        System.out.println("[1]. To view all workouts, please enter:");
        printColorText(COLOR_YELLOW, "workout /list");
        System.out.println("\t This will print the list of existing workouts.");
        printLine();
        System.out.println("[2]. To add a workout, please enter: ");
        printColorText(COLOR_YELLOW, "workout /new <exercise name> /reps <no. of repetitions>");
        System.out.println("\t Example: ");
        printColorText(COLOR_YELLOW, "workout /new push up /reps 10");
        System.out.println("\t This will add a workout with 10 reps of push up.");
        printLine();
        System.out.println("[3]. To delete a workout, please enter: ");
        printColorText(COLOR_YELLOW, "workout /delete <index>");
        System.out.println("\t Example: ");
        printColorText(COLOR_YELLOW, "workout /delete 1");
        System.out.println("\t This will delete the workout with index 1 if exists.");
        printLine();
        System.out.println("[4]. To update a workout, please enter: ");
        printColorText(COLOR_YELLOW, "workout /update <index> <quantity>");
        System.out.println("\t Example: ");
        printColorText(COLOR_YELLOW, "workout /update 1 15");
        System.out.println("\t This will update the workout with index 1 to 15 reps if exists.");
    }
}
