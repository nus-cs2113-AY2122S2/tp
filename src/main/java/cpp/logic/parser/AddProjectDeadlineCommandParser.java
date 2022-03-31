package cpp.logic.parser;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.AddProjectDeadlineCommand;

public class AddProjectDeadlineCommandParser implements CommandParser<AddProjectDeadlineCommand> {

    @Override
    public AddProjectDeadlineCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length < Constants.THREE_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_ADDPROJDEADLINE_COMMAND_FORMAT);
        }
        return new AddProjectDeadlineCommand(userInput[1], userInput[2]);
    }

}
