package seedu.duke.parsers;

import java.util.HashMap;

import seedu.duke.commands.Command;
import seedu.duke.commands.ListCommand;
import seedu.duke.exceptions.GeneralParseException;
import seedu.duke.exceptions.InvalidCompulsoryParameterException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.util.StringConstants;

/**
 * This Parser supports the "list" command.
 */
public class ListParser extends Parser {
    private static final String TAG = StringConstants.TAG_COMMAND_WORD;
    // Unescaped Regex for testing:
    // ((?<tag>\w+))?(?<invalid>.*)
    private static final String LIST_FORMAT = "((?<tag>\\w+))?(?<invalid>.*)";

    public ListParser() {
        super();
        this.commandFormat = LIST_FORMAT;
        groupNames.add(TAG);
        groupNames.add(INVALID);
    }

    /**
     * Throws GeneralParseException as the user input does not match the regex.
     * @throws GeneralParseException as it has no compulsory parameters.
     */
    @Override
    public void determineError() throws GeneralParseException {
        throw new GeneralParseException();
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String listArgument = parsedArguments.get(TAG);
        return new ListCommand(listArgument);
    }
}
