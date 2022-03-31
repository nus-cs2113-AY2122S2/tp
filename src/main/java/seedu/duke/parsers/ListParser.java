package seedu.duke.parsers;

import java.util.HashMap;

import seedu.duke.commands.Command;
import seedu.duke.commands.ListCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.util.StringConstants;

public class ListParser extends Parser {
    private static final String LIST_ARGUMENT = StringConstants.LIST_ARGUMENT;
    // Unescaped Regex for testing:
    // ((?<listArgument>\w+))?(?<invalid>.*)
    private static final String LIST_FORMAT = "((?<listArgument>\\w+))?(?<invalid>.*)";

    public ListParser() {
        super();
        this.commandFormat = LIST_FORMAT;
        groupNames.add(LIST_ARGUMENT);
        groupNames.add(INVALID);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String listArgument = parsedArguments.get(LIST_ARGUMENT);
        return new ListCommand(listArgument);
    }
}
