package seedu.duke.parsers;

import java.util.HashMap;

import seedu.duke.commands.Command;
import seedu.duke.commands.ListCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.util.StringConstants;

/**
 * This Parser supports the "list" command.
 */
public class ListParser extends Parser {
    private static final String TAG = StringConstants.TAG_COMMAND_WORD;
    // Unescaped Regex for testing:
    // ((?<listArgument>\w+))?(?<invalid>.*)
    private static final String LIST_FORMAT = "((?<tag>\\w+))?(?<invalid>.*)";

    public ListParser() {
        super();
        this.commandFormat = LIST_FORMAT;
        groupNames.add(TAG);
        groupNames.add(INVALID);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String listArgument = parsedArguments.get(TAG);
        return new ListCommand(listArgument);
    }
}
