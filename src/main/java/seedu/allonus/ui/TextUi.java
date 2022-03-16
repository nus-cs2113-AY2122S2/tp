package seedu.allonus.ui;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class handles input interactions with the user. It comprises mainly of a method that reads command line use input.
 */
public class TextUi {
    private final Scanner in;

    public TextUi() {
        this(System.in);
    }

    public TextUi(InputStream in) {
        this.in = new Scanner(in);
    }

    /**
     * This method returns a string containing user input from the command line.
     *
     * @return string containing user input.
     * @see IllegalStateException
     * @see NoSuchElementException
     */
    public String getUserInput() throws IllegalStateException, NoSuchElementException {
        String userInput = in.nextLine();
        return userInput;
    }
}
