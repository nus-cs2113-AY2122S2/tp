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
    private static final String ANSI_GREEN_BOLD = "\u001B[32;1m";
    private static final String ANSI_BRIGHT_GREEN_BOLD = "\u001B[92;1m";
    public static final String ANSI_RESET = "\u001B[0m";
    private static final String LOGO = ANSI_GREEN_BOLD
            + "  ____  _\n"
            + " / ___|| |__   ___ _ __ "
            + ANSI_BRIGHT_GREEN_BOLD + "_ __   __ _ ___ ___\n"
            + ANSI_GREEN_BOLD + " \\___ \\| '_ \\ / _ \\ '__| "
            + ANSI_BRIGHT_GREEN_BOLD + "'_ \\ / _` / __/ __|\n"
            + ANSI_GREEN_BOLD + "  ___) | | | |  __/ |  "
            + ANSI_BRIGHT_GREEN_BOLD + "| |_) | (_| \\__ \\__ \\\n"
            + ANSI_GREEN_BOLD + " |____/|_| |_|\\___|_|  "
            + ANSI_BRIGHT_GREEN_BOLD + "| .__/ \\__,_|___/___/\n"
            + ANSI_GREEN_BOLD + "                       "
            + ANSI_BRIGHT_GREEN_BOLD + "|_|" + ANSI_RESET;



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
                + LOGO + LS + WELCOME_MESSAGE_TWO + LS + PARTITION_LINE;
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
}
