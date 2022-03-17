package seedu.duke.commands;

import seedu.duke.data.exception.ArcsException;

public abstract class Command {

    /**
     * Command error message.
     */
    public static final String COMMAND_ERROR = "I don't understand what you have entered.";

    /**
     * Exit status.
     */
    private static boolean isDoneUsingApp = false;

    private static boolean isAtMainMenu = true;
    /**
     * Returns applications's exit status.
     *
     * @return Exit status.
     */
    public boolean isDoneUsingApp() {
        return isDoneUsingApp;
    }

    /**
     * Sets application's exit status to true.
     */
    protected void setIsDoneUsingApp() {
        isDoneUsingApp = true;
    }

    public boolean isIsAtMainMenu() {
        return isAtMainMenu;
    }

    protected void setIsAtMainMenu() {
        isAtMainMenu = true;
    }

    /**
     * Executes command.
     *
     * @throws ArcsException If command is invalid.
     */
    public abstract void execute() throws ArcsException;
}


