package commands;

/**
 * An abstract class representing the basic structure of a command object.
 * <p>
 * Design of the commands is inspired by the AddressBook-Level2 project
 * Link: https://se-education.org/addressbook-level2/
 */
public abstract class Command {
    protected String userInput;

    public Command(String userInput) {
        this.userInput = userInput;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public abstract void execute();
}
