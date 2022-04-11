package cpp.logic.parser;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.MarkCommand;

/**
 * Parses input arguments and creates a new MarkCommand object.
 */

public class MarkCommandParser implements CommandParser<MarkCommand> {

    /**
     * Parses the given {@code String[]} of arguments in the context of the MarkCommand
     * and returns an MarkCommand object for execution.
     * @throws IllegalCommandException if the user input does not conform the expected format
     */


    @Override
    public MarkCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length < Constants.THREE_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_MARK_COMMAND_FORMAT);
        }
        int indexProj;
        int indexTodo;
        try {
            indexProj = Integer.parseInt(userInput[1]);
            indexTodo = Integer.parseInt(userInput[2]);
        } catch (NumberFormatException e) {
            throw new IllegalCommandException(Constants.INDEX_PARSING_ERROR);
        }
        if (indexProj <= 0 || indexTodo <= 0) {
            throw new IllegalCommandException(Constants.NEGATIVE_INDEX);
        }
        return new MarkCommand(indexProj, indexTodo);
    }
}
