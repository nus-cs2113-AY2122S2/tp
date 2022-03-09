package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.exceptions.ModHappyException;

import java.util.HashMap;

public class DeleteParser extends Parser {

    public static final String FLAG_GROUP = "flag";
    public static final String FLAG_ARGUMENT_GROUP = "flagArgument";
    public static final String SUB_FLAG_GROUP = "subFlag";
    public static final String SUB_FLAG_ARGUMENT_GROUP = "subFlagArgument";
    //TODO: make the regex stricter in accepting inputs
    private static final String DELETE_FORMAT = "\\s*(?<flag>(\\/(m|t)))\\s+(?<flagArgument>[^\\-]*)\\s*"
            + "((?<subFlag>(-mod)*)\\s+(?<subFlagArgument>.+))*";

    public DeleteParser() {
        super();
        this.commandFormat = DELETE_FORMAT;
        groupNames.add(FLAG_GROUP);
        groupNames.add(FLAG_ARGUMENT_GROUP);
        groupNames.add(SUB_FLAG_GROUP);
        groupNames.add(SUB_FLAG_ARGUMENT_GROUP);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        switch (parsedArguments.get(FLAG_GROUP)) {
        case "/m":
            return new DeleteCommand(parsedArguments.get(FLAG_ARGUMENT_GROUP));
        case "/t":
            return new DeleteCommand(parsedArguments.get(SUB_FLAG_ARGUMENT_GROUP),
                    Integer.parseInt(parsedArguments.get(FLAG_ARGUMENT_GROUP)));
        default:
            throw new ModHappyException();
        }
    }
}
