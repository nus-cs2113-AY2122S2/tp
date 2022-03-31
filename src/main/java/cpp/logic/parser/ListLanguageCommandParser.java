package cpp.logic.parser;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.ListLanguageCommand;

import java.util.Arrays;

/**
 * Parses input arguments and creates a new ListLanguageCommand object
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
        if (userInput.length < Constants.TWO_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_LISTLANGUAGE_COMMAND_FORMAT);
        }
        String projectTitle = getProjectTitle(userInput);
        return new ListLanguageCommand(projectTitle);
    }

    private String getProjectTitle(String[] userInput) {
        String[] splitedName = Arrays.copyOfRange(userInput, 1, userInput.length);
        return String.join(" ", splitedName);
    }

}
