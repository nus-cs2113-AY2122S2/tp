package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.ListCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.util.StringConstants;

import java.util.HashMap;

public class ListParser extends Parser {
    private static final String LIST_ARGUMENT = StringConstants.LIST_ARGUMENT;
    //Unescaped Regex for testing:
    //\s*(?<listArgument>\w*)\s*
    private static final String LIST_FORMAT = "\\s*(?<listArgument>\\w*)\\s*";

    public ListParser() {
        super();
        this.commandFormat = LIST_FORMAT;
        groupNames.add(LIST_ARGUMENT);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String listArgument = parsedArguments.get(LIST_ARGUMENT);
        return new ListCommand(listArgument);
    }
}
