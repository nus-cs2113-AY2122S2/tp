package seedu.duke.controllers;

import seedu.duke.exceptions.OperationTerminationException;

/**
 * Controller is an abstract class that should be extended by all Controllers.
 *
 * <p>Provides base functionality for printing choices and control implementation.
 */
public abstract class Controller {
    /**
     * Options provided by the controller. They can be selected by index.
     *
     * <p>All controllers should place the exit option as the top-level choice.
     */
    protected final String[] choices;

    /**
     * Creates a Controller with a list of choices.
     *
     * <p>Supply the controller with a Scanner object. You should not create a new Scanner(System.in)
     * object and pass it to the constructor. Instead, create one single global scanner
     * and pass that single scanner to multiple controllers.
     *
     * @param choices Array of strings listing the choices available to the user.
     */
    public Controller(String[] choices) {
        this.choices = choices;
    }

    /**
     * Function that switches between options provided by the current controller.
     *
     * <p>This function MUST be overridden for all controllers.
     *
     * @param choice Option choice.
     * @return Whether to relinquish control or not.
     * @throws OperationTerminationException When option methods throw the same error. This will
     *                                       be caught by the `takeControl` method.
     */
    protected abstract boolean optionSwitcher(int choice) throws OperationTerminationException;

    /**
     * Provides a base control implementation for all controllers.
     *
     * <p>The extended controller MUST override the `optionSwitcher` method for this method to work properly.
     *
     * <p>Controllers may choose to override this method to perform extra operations (such as printing statements)
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
            } catch (OperationTerminationException e) {
                // If user enters terminating character, then we set choice to 0, effectively breaking the user
                // out of the input loop for this controller.
                choice = 0;
            }
            // try-catch block for inner functions.
            try {
                if (this.optionSwitcher(choice)) {
                    break;
                }
            } catch (OperationTerminationException e) {
                System.out.println("Terminating operation!");
            }
        }
    }

    /**
     * Helper method to get a valid choice.
     *
     * @return Returns a valid choice.
     * @throws OperationTerminationException When user inputs terminator.
     */
    protected int getChoice() throws OperationTerminationException {
        while (true) {
            int choice = InputParser.getInteger("Enter choice: ");
            if (choice < 0 || choice > choices.length - 1) {
                System.out.println("Invalid range! Try again...");
                continue;
            }
            return choice;
        }
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