package cpp.logic.parser;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.AddLanguageCommand;

/**
 * Parses input arguments and creates a new AddLanguageCommand object
 */

public class AddLanguageCommandParser implements CommandParser<AddLanguageCommand> {

    /**
     * Parses the given {@code String[]} of arguments in the context of the AddLanguageCommand
     * and returns an AddLanguageCommand object for execution.
     * @throws IllegalCommandException if the user input does not conform the expected format
     */

    @Override
    public AddLanguageCommand parse(String[] userInput) throws IllegalCommandException {
        if (userInput.length < Constants.THREE_ARGUMENTS) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_ADDLANGUAGE_COMMAND_FORMAT);
        }
        String projectName = userInput[1];
        String language = userInput[2];
        for (int i = 3; i < userInput.length; i++) {
            language = language + " " + userInput[i];
        }

        if (language.equals("") || language.equals(" ")) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_ADDLANGUAGE_COMMAND_FORMAT);
        }

        return new AddLanguageCommand(projectName, language);
    }
}
