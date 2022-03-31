package cpp.logic.parser;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.AddProjectDeadlineCommand;

/**
 * Parses input arguments and creates a new AddProjectDeadlineCommand object
 */

public class AddProjectDeadlineCommandParser implements CommandParser<AddProjectDeadlineCommand> {
    /**
     * Parses the given {@code String[]} of arguments in the context of the AddProjectDeadlineCommand
     * and returns an AddProjectDeadlineCommand object for execution.
     * @throws IllegalCommandException if the user input does not conform the expected format
     */

    @Override
    public AddProjectDeadlineCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length < Constants.THREE_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_ADDPROJDEADLINE_COMMAND_FORMAT);
        }
        return new AddProjectDeadlineCommand(userInput[1], userInput[2]);
    }

}
