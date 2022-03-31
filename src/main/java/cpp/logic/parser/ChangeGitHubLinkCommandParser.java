package cpp.logic.parser;

import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.ChangeGitHubLinkCommand;
import cpp.ui.Constants;

public class ChangeGitHubLinkCommandParser implements CommandParser<ChangeGitHubLinkCommand> {

    @Override
    public ChangeGitHubLinkCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length != Constants.THREE_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_CHANGEGITHUBLINK_COMMAND_FORMAT);
        }
        return new ChangeGitHubLinkCommand(userInput[1], userInput[2]);
    }
}
