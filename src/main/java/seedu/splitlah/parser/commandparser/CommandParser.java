package seedu.splitlah.parser.commandparser;

import seedu.splitlah.command.Command;
import seedu.splitlah.exceptions.InvalidFormatException;

/**
 * Represents a command parser that is able to parse user arguments into an object of a subclass of Command.
 * 
 * @param <T> A subclass of Command class.
 */
public interface CommandParser<T extends Command> {
    
    /**
     * Returns an object of a subclass of Command given a String object representing arguments provided by the user.
     * 
     * @param commandArgs A String object representing arguments provided by the user.
     * @return An object of a subclass of Command representing the parsed instructions of the user.
     */
    public T getCommand(String commandArgs) throws InvalidFormatException;
}
