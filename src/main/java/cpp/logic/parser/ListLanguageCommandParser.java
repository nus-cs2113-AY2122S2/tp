package cpp.logic.parser;

import cpp.Constants;
import cpp.exceptions.IllegalCommandException;
import cpp.logic.commands.ListLanguageCommand;

import java.util.Arrays;

public class ListLanguageCommandParser implements CommandParser<ListLanguageCommand> {
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
