package seedu.sherpass.util;

import java.util.Scanner;

import static seedu.sherpass.constant.Message.GOODBYE_MESSAGE;
import static seedu.sherpass.constant.Message.WELCOME_MESSAGE_ONE;
import static seedu.sherpass.constant.Message.WELCOME_MESSAGE_TWO;


public class Ui {
    private static final Scanner in = new Scanner(System.in);
    private static final String PARTITION_LINE = "______________________________"
            + "______________________________";
    private static final String LS = System.lineSeparator();
    // To KIV for future use
    // private static final String ANSI_GREEN_BOLD = "\u001B[32m";
    // private static final String ANSI_BRIGHT_GREEN_BOLD = "\u001B[92m";
    // public static final String ANSI_RESET = "\u001B[0m";
    private static final String ANOTHER_BLANK_STRING = "";
    private static final String BLANK_STRING = "";

    public static final String LAST_BLANK_STRING = "";
    private static final String LOGO = ANOTHER_BLANK_STRING
            + "  ____  _\n"
            + " / ___|| |__   ___ _ __ "
            + BLANK_STRING + "_ __   __ _ ___ ___\n"
            + ANOTHER_BLANK_STRING + " \\___ \\| '_ \\ / _ \\ '__| "
            + BLANK_STRING + "'_ \\ / _` / __/ __|\n"
            + ANOTHER_BLANK_STRING + "  ___) | | | |  __/ |  "
            + BLANK_STRING + "| |_) | (_| \\__ \\__ \\\n"
            + ANOTHER_BLANK_STRING + " |____/|_| |_|\\___|_|  "
            + BLANK_STRING + "| .__/ \\__,_|___/___/\n"
            + ANOTHER_BLANK_STRING + "                       "
            + BLANK_STRING + "|_|" + LAST_BLANK_STRING;



    /**
     * Returns any user input entered by the user through
     * the program's terminal. User Input is also trimmed to
     * remove white spaces.
     *
     * @return User input
     */
    public String readCommand() {
        return in.nextLine().trim();
    }

    /**
     * Prints a partition line.
     */
    public void showLine() {
        System.out.println(PARTITION_LINE);
    }

    /**
     * Prints a message from the program to the user.
     * Can be in the form of exceptions that have occurred,
     * requests for inputs, command acknowledgement and so on.
     *
     * @param message Message to show to the user.
     */
    public void showToUser(String... message) {
        for (String s : message) {
            System.out.println(s);
        }
    }

    /**
     * Prints a welcome message to greet the user.
     */
    public void showWelcomeMessage() {
        String message = PARTITION_LINE + LS + WELCOME_MESSAGE_ONE
                + LOGO + LS + PARTITION_LINE + LS + WELCOME_MESSAGE_TWO;
        showToUser(message);
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public void printEditTaskMessage(String task) {
        System.out.println("Ok, I've edited this task as such!"
                + "\n  " + task);
    }

    /**
     * Returns a string of repeated characters.
     *
     * @param character The character to print.
     * @param number The number of times to print that character.
     * @return Returns a concatenated string of repeated characters.
     */
    public String getRepeatedCharacters(String character, int number) {
        for (int i = 1; i < number; i++) {
            character += character;
        }
        return character;
    }
}
