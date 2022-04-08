package cpp.logic.parser;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.ProjectDeadlineCommand;

/**
 * Parses input arguments and creates a new AddProjectDeadlineCommand object.
 */

public class ProjectDeadlineCommandParser implements CommandParser<ProjectDeadlineCommand> {
    /**
     * Parses the given {@code String[]} of arguments in the context of the AddProjectDeadlineCommand
     * and returns an AddProjectDeadlineCommand object for execution.
     * @throws IllegalCommandException if the user input does not conform the expected format
     */

    @Override
    public ProjectDeadlineCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length < Constants.THREE_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_PROJDEADLINE_COMMAND_FORMAT);
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
        return new ProjectDeadlineCommand(index, userInput[2]);
    }

}
