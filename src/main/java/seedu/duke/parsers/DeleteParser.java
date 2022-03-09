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

        DeleteCommand deleteCommand = new DeleteCommand();
        DeleteParser deleteParser = new DeleteParser();
        HashMap<String, String> parsedArguments = deleteParser.parseString(userInput);
        String moduleCode;
        int taskNumber;

        switch (parsedArguments.get(FLAG_GROUP)) {
        case "/m":
            moduleCode = parsedArguments.get(FLAG_ARGUMENT_GROUP);
            deleteCommand.setModuleCode(moduleCode);
            break;
        case "/t":
            taskNumber = Integer.parseInt(parsedArguments.get(FLAG_ARGUMENT_GROUP));
            deleteCommand.setTaskNumber(taskNumber);
            moduleCode = parsedArguments.get(SUB_FLAG_ARGUMENT_GROUP);
            deleteCommand.setModuleCode(moduleCode);
            break;
        default:
            throw new ModHappyException();
        }
        return deleteCommand;
    }
}
