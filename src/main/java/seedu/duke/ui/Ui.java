package seedu.duke.ui;

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
     * Prints the welcome message when user starts InvMgr.
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
     * Prints output on stdout based on the given String.
     *
     * @param output String to print
     */
    public void showOutput(String output) {
        System.out.println(output);
    }

    /**
     * Show errors on stderr based on the given String.
     *
     * @param output the output to show
     */
    public void showError(String output) {
        System.err.println(output);
    }

    /**
     * Show errors on stderr based on the message from the given Exception.
     *
     * @param e the associated Exception raised from the error
     */
    public void showError(Exception e) {
        System.err.println(e.getMessage());
    }
}
