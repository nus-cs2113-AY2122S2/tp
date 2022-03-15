package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.HelpCommand;
import seedu.duke.exceptions.ModHappyException;

import java.util.HashMap;

public class HelpParser extends Parser {
    private static final String COMMAND_AS_HELP_ARGUMENT = "command";
    private static final String HELP_FORMAT = "\\s*(?<command>.*)";

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
