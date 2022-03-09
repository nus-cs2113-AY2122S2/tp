package seedu.duke.parsers;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;
import seedu.duke.tasks.Task;

import java.util.HashMap;

public class AddParser extends Parser {
    private static final String FLAG = "flag";
    private static final String TASK_NAME = "taskName";
    private static final String TASK_DESCRIPTION = "taskDescription";

    private static final String TASK_FLAG = "/t";
    private static final String MODULE_FLAG = "/m";

    // Unescaped regex for testing:
    // \s*(?<flag>\/(m|t))\s+(?<taskName>.*?(?=(\s+-d\s+)|$))(\s+(-d\s+(?<taskDescription>.+)))?
    // TODO: Add support for -mod argument when integrating Task and Module classes with one another
    private static final String ADD_FORMAT = "\\s*(?<flag>\\/(m|t))\\s+(?<taskName>.*?(?=(\\s+-d\\s+)|$))"
            + "(\\s+(-d\\s+(?<taskDescription>.+)))?";

    public AddParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        commandFormat = ADD_FORMAT;
        groupNames.add(FLAG);
        groupNames.add(TASK_NAME);
        groupNames.add(TASK_DESCRIPTION);
    }

    
    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        final String commandFlag = parsedArguments.get(FLAG);
        switch (commandFlag) {
        case TASK_FLAG:
            final String taskName = parsedArguments.get(TASK_NAME);
            final String taskDescription = parsedArguments.get(TASK_DESCRIPTION);
            if (!taskDescription.equals(EMPTY_STRING)) {
                return new AddCommand(taskName, taskDescription, true);
            }
            return new AddCommand(taskName, null, true);
        case MODULE_FLAG:
            // blah
        default:
            throw new ParseException();
        }
    }
}
