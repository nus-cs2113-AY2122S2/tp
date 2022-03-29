package cpp.logic.parser;

import cpp.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.ViewProjectCommand;

import java.util.Arrays;

public class ViewProjectCommandParser implements CommandParser<ViewProjectCommand> {
    public ViewProjectCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length < Constants.TWO_ARGUMENTS) {
            // TODO: 2022/3/29 need add new format to constants
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_COMMAND_FORMAT);
        }
        String projectTitle = getProjectTitle(userInput);
        return new ViewProjectCommand(projectTitle);

    }

    private String getProjectTitle(String[] userInput) {
        String[] splitedName = Arrays.copyOfRange(userInput, 1, userInput.length);
        return String.join(" ", splitedName);
    }
}
