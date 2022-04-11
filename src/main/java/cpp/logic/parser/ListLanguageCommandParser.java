package cpp.logic.parser;

import cpp.logic.commands.OpenGitCommand;
import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.ListLanguageCommand;

import java.util.Arrays;

/**
 * Parses input arguments and creates a new ListLanguageCommand object.
 */

public class ListLanguageCommandParser implements CommandParser<ListLanguageCommand> {

    /**
     * Parses the given {@code String[]} of arguments in the context of the ListLanguageCommand
     * and returns an ListLanguageCommand object for execution.
     * @throws IllegalCommandException if the user input does not conform the expected format
     */

    @Override
    public ListLanguageCommand parse(String[] userInput) throws IllegalCommandException {
        assert (userInput != null) : "Cannot list languages for this project.";
        if (userInput.length != Constants.TWO_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_LISTLANGUAGE_COMMAND_FORMAT);
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
        return new ListLanguageCommand(index);
    }

}
