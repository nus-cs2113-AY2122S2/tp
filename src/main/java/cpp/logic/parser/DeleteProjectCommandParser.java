package cpp.logic.parser;


import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.DeleteProjectCommand;

import java.util.Arrays;

public class DeleteProjectCommandParser implements CommandParser<DeleteProjectCommand> {

    @Override
    public DeleteProjectCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length < Constants.TWO_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_DELETEPROJECT_COMMAND_FORMAT);
        }
        String projectToDelete = getProjectTitle(userInput);
        return new DeleteProjectCommand(projectToDelete);
    }

    private String getProjectTitle(String[] userInput) {
        String[] splitedName = Arrays.copyOfRange(userInput, 1, userInput.length);
        return String.join(" ", splitedName);
    }
}
