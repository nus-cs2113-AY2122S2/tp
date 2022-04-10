package cpp.logic.parser;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.ViewProjectCommand;

import java.util.Arrays;

/**
 * Parses input arguments and creates a new ViewProjectCommand object.
 */

public class ViewProjectCommandParser implements CommandParser<ViewProjectCommand> {

    /**
     * Parses the given {@code String[]} of arguments in the context of the ViewProjectCommand
     * and returns an ViewProjectCommand object for execution.
     * @throws IllegalCommandException if the user input does not conform the expected format
     */

    public ViewProjectCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length != Constants.TWO_ARGUMENTS) {
            // TODO: 2022/3/29 need add new format to constants
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_COMMAND_FORMAT);
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
        return new ViewProjectCommand(index);

    }

    private String getProjectTitle(String[] userInput) {
        String[] splitedName = Arrays.copyOfRange(userInput, 1, userInput.length);
        return String.join(" ", splitedName);
    }
}
