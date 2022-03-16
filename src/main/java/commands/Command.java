package commands;

/**
 * An abstract class representing the basic structure of a command object.
 * Design of the commands is inspired by the AddressBook-Level2 project
 * Link: https://se-education.org/addressbook-level2/
 */
public abstract class Command {
    protected String userInput;

    /**
     * Constructs a Command object and stores the complete user's input into the object.
     *
     * @param userInput A string representing the input the user has entered.
     */
    public Command(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Gets the user input stored in a Command instance.
     *
     * @return A string containing the user input.
     */
    public String getUserInput() {
        return userInput;
    }

    /**
     * Sets the user input to the specified user input.
     *
     * @param userInput The user input to store in the Command object.
     */
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes a certain series of actions based on the implementation of the different commands.
     */
    public abstract void execute();
}
