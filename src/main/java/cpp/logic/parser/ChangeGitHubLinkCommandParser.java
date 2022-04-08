package cpp.logic.parser;

import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.ChangeGitHubLinkCommand;
import cpp.ui.Constants;

/**
 * Parses input arguments and creates a new ChangeGitHubLinkCommand object.
 */

public class ChangeGitHubLinkCommandParser implements CommandParser<ChangeGitHubLinkCommand> {
    /**
     * Parses the given {@code String[]} of arguments in the context of the ChangeGitHubLinkCommand
     * and returns an ChangeGitHubLinkCommand object for execution.
     * @throws IllegalCommandException if the user input does not conform the expected format
     */
    @Override
    public ChangeGitHubLinkCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length != Constants.THREE_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_CHANGEGITHUBLINK_COMMAND_FORMAT);
        }
        int index;
        try {
            index = Integer.parseInt(userInput[1]);
        } catch (NumberFormatException e) {
            throw new IllegalCommandException(Constants.NON_INTEGER_INDEX);
        }
        if (index <= 0) {
            throw new IllegalCommandException(Constants.NEGATIVE_INDEX);
        }
        return new ChangeGitHubLinkCommand(index, userInput[2]);
    }
}
