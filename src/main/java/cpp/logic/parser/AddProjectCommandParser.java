package cpp.logic.parser;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.AddProjectCommand;

import java.util.Arrays;

public class AddProjectCommandParser implements CommandParser<AddProjectCommand> {

    @Override
    public AddProjectCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length < Constants.TWO_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_ADDPROJECT_COMMAND_FORMAT);
        }
        String projectTitle = getProjectTitle(userInput);
        return new AddProjectCommand(projectTitle);
    }

    private String getProjectTitle(String[] userInput) {
        String[] splitedName = Arrays.copyOfRange(userInput, 1, userInput.length);
        return String.join(" ", splitedName);
    }
}
