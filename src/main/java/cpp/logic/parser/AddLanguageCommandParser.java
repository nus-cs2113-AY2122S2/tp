package cpp.logic.parser;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.AddLanguageCommand;

/**
 * Parses input arguments and creates a new AddLanguageCommand object.
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
        int index;
        try {
            index = Integer.parseInt(userInput[1]);
        } catch (NumberFormatException e) {
            throw new IllegalCommandException(Constants.NON_INTEGER_INDEX);
        }
        String language = userInput[2];
        for (int i = 3; i < userInput.length; i++) {
            language = language + " " + userInput[i];
        }

        if (index <= 0) {
            throw new IllegalCommandException(Constants.NEGATIVE_INDEX);
        }
        if (language.equals("") || language.equals(" ")) {
            throw new IllegalCommandException(Constants.MESSAGE_INVALID_ADDLANGUAGE_COMMAND_FORMAT);
        }
        return new AddLanguageCommand(index, language);
    }
}
