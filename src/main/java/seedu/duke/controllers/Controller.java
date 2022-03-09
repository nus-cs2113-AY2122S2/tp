package seedu.duke.controllers;

import java.util.Scanner;

/**
 * Controller is an abstract class that should be extended by all Controllers.
 * <p>
 * Provides base functionality for printing choices and control implementation.
 */
public abstract class Controller {
    /**
     * This character tells the current command to terminate.
     */
    protected static final String TERMINATOR = "-";
    /**
     * Options provided by the controller. They can be selected by index.
     * <p>
     * All controllers should place the exit option as the top-level choice.
     */
    protected final String[] choices;
    /**
     * The global scanner object that should be used by all Controllers in the application.
     */
    protected final Scanner scanner;

    /**
     * Creates a Controller with a list of choices.
     * <p>
     * Supply the controller with a Scanner object. You should not create a new Scanner(System.in)
     * object and pass it to the constructor. Instead, create one single global scanner
     * and pass that single scanner to multiple controllers.
     *
     * @param choices Array of strings listing the choices available to the user.
     * @param scanner Scanner for System.in.
     */
    public Controller(String[] choices, Scanner scanner) {
        this.choices = choices;
        this.scanner = scanner;
    }

    /**
     * Function that switches between options provided by the current controller.
     * <p>
     * This function MUST be overridden for all controllers.
     *
     * @param choice Option choice.
     * @return Whether to relinquish control or not.
     * @throws IllegalArgumentException When option methods throw the same error. This will
     *                                  be caught by the `takeControl` method.
     */
    protected abstract boolean optionSwitcher(int choice) throws IllegalArgumentException;

    /**
     * Provides a base control implementation for all controllers.
     * <p>
     * The extended controller MUST override the `optionSwitcher` method for this method to work properly.
     * <p>
     * Controllers may choose to override this method to perform extra operations (such as printing statements)
     * before entering the input loop.
     */
    public void takeControl() {
        // Print the options provided.
        System.out.println(this);

        int choice;
        while (true) {
            System.out.println("*".repeat(30));
            // Get input from user.
            // try-catch block for getting choice.
            try {
                choice = this.getChoice();
            } catch (IllegalArgumentException e) {
                // If user enters terminating character, then we set choice to 0, effectively breaking the user
                // out of the input loop for this controller.
                choice = 0;
            }
            // try-catch block for inner functions.
            try {
                if (this.optionSwitcher(choice)) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Terminating operation!");
            }
        }
    }

    /**
     * Helper method to get a valid choice.
     *
     * @return Returns a valid choice.
     * @throws IllegalArgumentException When user inputs terminator.
     */
    protected int getChoice() throws IllegalArgumentException {
        while (true) {
            try {
                String line = this.getString("Enter choice: ").toLowerCase();
                int choice = Integer.parseInt(line);
                if (choice < 0 || choice > choices.length - 1) {
                    throw new NumberFormatException("Invalid range!");
                }
                return choice;
            } catch (NumberFormatException e) {
                System.out.printf("Error parsing index - %s\n", e.getMessage());
            }
        }
    }

    /**
     * Helper method to get a valid string.
     *
     * @param msg Message to show user when getting input.
     * @return Returns a valid string.
     * @throws IllegalArgumentException When user inputs terminator.
     */
    protected String getString(String msg) throws IllegalArgumentException {
        System.out.print(msg);
        String line = scanner.nextLine();
        if (line.equals(TERMINATOR)) {
            throw new IllegalArgumentException();
        }
        return line;
    }

    /**
     * Returns a string representing the choices available to the current user in the controller.
     *
     * @return Representative string of the controller.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < choices.length; index++) {
            builder.append(String.format("(%d) %s\n", index, choices[index]));
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}