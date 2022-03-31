package cpp.logic.parser;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.AddProjectCommand;

import java.util.Arrays;

/**
 * Parses input arguments and creates a new AddProjectCommand object.
 */

public class AddProjectCommandParser implements CommandParser<AddProjectCommand> {
    /**
     * Parses the given {@code String[]} of arguments in the context of the AddLanguageCommand
     * and returns an AddProjectCommand object for execution.
     * @throws IllegalCommandException if the user input does not conform the expected format
     */

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
