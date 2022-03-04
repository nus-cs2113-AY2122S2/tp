package commands;

/**
 * An abstract class representing the basic structure of a command object
 * <p>
 * Design of the commands is inspired by the AddressBook-Level2 project
 * Link: https://se-education.org/addressbook-level2/
 */
public abstract class Command {
    /**
     * Executes the command based on the user input.
     */
    public abstract void execute(String userInput);
}
