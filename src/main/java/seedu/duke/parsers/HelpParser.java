package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.HelpCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.util.StringConstants;

import java.util.HashMap;

public class HelpParser extends Parser {
    private static final String COMMAND_AS_HELP_ARGUMENT = StringConstants.HELP_COMMAND_ARGUMENT;

    //Unescaped regex for testing:
    //\s*(?<command>\w*)\s*
    private static final String HELP_FORMAT = "\\s*(?<command>\\w*)\\s*";

    public HelpParser() {
        super();
        this.commandFormat = HELP_FORMAT;
        groupNames.add(COMMAND_AS_HELP_ARGUMENT);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String command = parsedArguments.get(COMMAND_AS_HELP_ARGUMENT);
        return new HelpCommand(command);
    }
}
