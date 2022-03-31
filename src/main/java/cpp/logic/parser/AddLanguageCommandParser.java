package cpp.logic.parser;

import cpp.ui.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.AddLanguageCommand;

public class AddLanguageCommandParser implements CommandParser<AddLanguageCommand> {
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
