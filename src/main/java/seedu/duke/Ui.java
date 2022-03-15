package seedu.duke;

import java.util.Scanner;

public class Ui {
    // Fixed string output for startup and exit.
    private static final String WELCOME_BANNER =
            "Hello! I'm InvMgr!"
            + "\nWhat can I do for you?";

    private Scanner scannerInput;

    public Ui() {
        scannerInput = new Scanner(System.in);
    }

    /**
     * Prints the welcome message when user starts Duke.
     */
    public void showWelcome() {
        System.out.println(WELCOME_BANNER);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return a line of the user input
     */
    public String readCommand() {
        String userInput = scannerInput.nextLine();
        return userInput;
    }

    /**
     * Prints output.
     *
     * @param output String to print
     */
    public void showOutput(String output) {
        System.out.println(output);
    }
}
