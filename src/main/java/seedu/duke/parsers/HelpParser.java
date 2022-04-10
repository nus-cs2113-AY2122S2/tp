package seedu.duke.parsers;

import java.util.HashMap;

import seedu.duke.commands.Command;
import seedu.duke.commands.HelpCommand;
import seedu.duke.exceptions.GeneralParseException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.util.StringConstants;

/**
 * This Parser supports the "help" command.
 */
public class HelpParser extends Parser {
    private static final String COMMAND_AS_HELP_ARGUMENT = StringConstants.HELP_COMMAND_ARGUMENT;

    // Unescaped regex for testing:
    // (?<command>\w+)?(?<invalid>.*)
    private static final String HELP_FORMAT = "(?<command>\\w+)?(?<invalid>.*)";

    public HelpParser() {
        super();
        this.commandFormat = HELP_FORMAT;
        groupNames.add(COMMAND_AS_HELP_ARGUMENT);
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

    //@@author ngys117
    /**
     * Parses the user input and extracts the parameters based on the command format.
     * @param userInput User input of the command to get a help message
     * @return A new {@code HelpCommand} object to show a help message
     * @throws ModHappyException If there is an error parsing the command
     */
    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String command = parsedArguments.get(COMMAND_AS_HELP_ARGUMENT);
        checksForExcessArg();
        return new HelpCommand(command);
    }
}
