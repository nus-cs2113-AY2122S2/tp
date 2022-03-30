package cpp.logic.parser;

import cpp.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.AddDeadlineCommand;

public class AddDeadlineCommandParser implements CommandParser<AddDeadlineCommand> {

    @Override
    public AddDeadlineCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length < Constants.THREE_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_ADDDEADLINE_COMMAND_FORMAT);
        }
        return new AddDeadlineCommand(userInput[1], userInput[2]);
    }

}
