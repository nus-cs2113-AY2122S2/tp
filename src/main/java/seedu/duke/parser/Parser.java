package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.InvMgrException;

/**
 * Represents a Parser that is able to parse user input arguments into a {@code Command} of type {@code T}.
 * Note that this is different from InputParser - this is specifically for parsing arguments.
 *
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @throws InvMgrException if {@code userInput} does not conform the expected format.
     */
    T parse(String userInput) throws InvMgrException;
}
