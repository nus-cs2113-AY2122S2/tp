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
    private static final String MODULE_CODE = "moduleCode";
    private static final String MODULE_DESCRIPTION = "moduleDescription";

    private static final String TASK_FLAG = "/t";
    private static final String MODULE_FLAG = "/m";

    // Unescaped regex for testing (split into two lines):
    // ^\s*(\/t\s+(?<taskName>.+?(?=(\s+-d\s+)|$))(\s+(-d\s+(?<taskDescription>.+)))?|
    // \/m\s+(?<moduleCode>\w+?(?=(\s+-d\s+)|$))(\s+(-d\s+(?<moduleDescription>.+)))?)
    // TODO: Add support for -mod argument when integrating Task and Module classes with one another
    private static final String ADD_FORMAT = "^\\s*(\\/t\\s+(?<taskName>.+?(?=(\\s+-d\\s+)|$))"
            + "(\\s+(-d\\s+(?<taskDescription>.+)))?|\\/m\\s+(?<moduleCode>\\w+?(?=(\\s+-d\\s+)|$))"
            + "(\\s+(-d\\s+(?<moduleDescription>.+)))?)";

    public AddParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        commandFormat = ADD_FORMAT;
        groupNames.add(TASK_NAME);
        groupNames.add(TASK_DESCRIPTION);
        groupNames.add(MODULE_CODE);
        groupNames.add(MODULE_DESCRIPTION);
    }

    
    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        final String taskName = parsedArguments.get(TASK_NAME);
        final String taskDescription = parsedArguments.get(TASK_DESCRIPTION);
        final String moduleCode = parsedArguments.get(MODULE_CODE);
        final String moduleDescription = parsedArguments.get(MODULE_DESCRIPTION);
        if (!taskName.equals(EMPTY_STRING)) {
            if (!taskDescription.equals(EMPTY_STRING)) {
                return new AddCommand(taskName, taskDescription, true);
            }
            return new AddCommand(taskName, null, true);
        }
        if (!moduleCode.equals(EMPTY_STRING)) {
            if (!moduleDescription.equals(EMPTY_STRING)) {
                return new AddCommand(moduleCode, moduleDescription, false);
            }
            return new AddCommand(moduleCode, null, false);
        }
        throw new ParseException();
    }
}
