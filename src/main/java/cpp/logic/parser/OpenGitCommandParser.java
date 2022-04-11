package cpp.logic.parser;

import cpp.logic.commands.ViewProjectCommand;
import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.OpenGitCommand;

import java.util.Arrays;

/**
 * Parses input arguments and creates a new OpenGitCommand object.
 */

public class OpenGitCommandParser implements CommandParser<OpenGitCommand> {

    /**
     * Parses the given {@code String[]} of arguments in the context of the OpenGitCommand
     * and returns an OpenGitCommand object for execution.
     * @throws IllegalCommandException if the user input does not conform the expected format
     */

    @Override
    public OpenGitCommand parse(String[] userInput) throws IllegalCommandException {
        assert (userInput != null) : "Cannot open git for this project.";
        if (userInput.length != Constants.TWO_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_OPENGIT_COMMAND_FORMAT);
        }
        int index;
        try {
            index = Integer.parseInt(userInput[1]);
        } catch (NumberFormatException e) {
            throw new IllegalCommandException(Constants.INDEX_PARSING_ERROR);
        }
        if (index <= 0) {
            throw new IllegalCommandException(Constants.NEGATIVE_INDEX);
        }
        return new OpenGitCommand(index);
    }
}
