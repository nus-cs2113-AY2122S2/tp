package seedu.duke.parser;

import seedu.duke.commands.DeleteCommand;
import seedu.duke.exceptions.InvMgrException;

/**
 * Parses input arguments and creates a new DeleteCommand object.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws InvMgrException if the user input does not conform to the expected format
     */
    public DeleteCommand parse(String args) throws InvMgrException {
        int index = ParserUtils.parseIndex(args) - 1;
        return new DeleteCommand(index);
    }

}

