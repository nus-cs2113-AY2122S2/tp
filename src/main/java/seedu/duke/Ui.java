package seedu.duke;

import java.util.Scanner;

/**
 * Represents the User Interface component of the program.
 * The Ui object handles all interactions with the user such as printing the outputs to the user
 * as well as reading in the user inputs .
 */
public class Ui {
    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Returns a string containing the user input.
     *
     * @return User input
     */
    public String readUserInput() {
        String userInput = in.nextLine();
        return userInput;
    }

}
