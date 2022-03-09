package seedu.duke.parsers;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;

import java.util.HashMap;

/**
 * This Parser supports the "add" command.
 */
public class AddParser extends Parser {
    private static final String FLAG = "flag";
    private static final String TASK_NAME = "taskName";
    private static final String TASK_DESCRIPTION = "taskDescription";
    private static final String TASK_WORKING_TIME = "estimatedWorkingTime";
    private static final String MODULE_CODE = "moduleCode";
    private static final String MODULE_DESCRIPTION = "moduleDescription";

    // Unescaped regex for testing (split into two lines):
    // ^\s*(\/t\s+(?<taskName>.+?(?=\s+-d\s+|\s+-t\s+|$))(\s+(-d\s+\"(?<taskDescription>([^\"]*))\")(?=(\s+-t\s+)|$))?
    // (\s+(-t\s+\"(?<estimatedWorkingTime>([^\"]*))\")(?=(\s+-d\s+)|$))?|\/m\s+(?<moduleCode>\w+?(?=(\s+-d\s+)|$))
    // (\s+(-d\s+\"(?<moduleDescription>.+)\"))?)
    // TODO: Add support for -mod argument when integrating Task and Module classes with one another
    private static final String ADD_FORMAT = "\\s*(\\/t\\s+(?<taskName>.+?(?=\\s+-d\\s+|\\s+-t\\s+|$))"
            + "(\\s+(-d\\s+\\\"(?<taskDescription>([^\\\"]*))\\\")(?=(\\s+-t\\s+)|$))?(\\s+(-t\\s+\\\""
            + "(?<estimatedWorkingTime>([^\\\"]*))\\\"))?|\\/m\\s+"
            + "(?<moduleCode>\\w+?(?=(\\s+-d\\s+)|$))(\\s+(-d\\s+\\\"(?<moduleDescription>.+)\\\"))?)";

    public AddParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        commandFormat = ADD_FORMAT;
        groupNames.add(TASK_NAME);
        groupNames.add(TASK_DESCRIPTION);
        groupNames.add(TASK_WORKING_TIME);
        groupNames.add(MODULE_CODE);
        groupNames.add(MODULE_DESCRIPTION);
    }
    
    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        final String taskName = parsedArguments.get(TASK_NAME);
        final String taskDescription = parsedArguments.get(TASK_DESCRIPTION);
        final String estimatedWorkingTime = parsedArguments.get(TASK_WORKING_TIME);
        final String moduleCode = parsedArguments.get(MODULE_CODE);
        final String moduleDescription = parsedArguments.get(MODULE_DESCRIPTION);
        if (!taskName.equals(EMPTY_STRING)) {
            if (!estimatedWorkingTime.equals(EMPTY_STRING) && !taskDescription.equals(EMPTY_STRING)) {
                return new AddCommand(taskName, taskDescription, true, estimatedWorkingTime);
            } else if (!taskDescription.equals(EMPTY_STRING)) {
                return new AddCommand(taskName, taskDescription, true, null);
            } else if (!estimatedWorkingTime.equals(EMPTY_STRING)) {
                return new AddCommand(taskName, null, true, estimatedWorkingTime);
            } else {
                return new AddCommand(taskName, null, true, null);
            }
        }
        if (!moduleCode.equals(EMPTY_STRING)) {
            if (!moduleDescription.equals(EMPTY_STRING)) {
                return new AddCommand(moduleCode, moduleDescription, false, null);
            }
            return new AddCommand(moduleCode, null, false, null);
        }
        throw new ParseException();
    }
}
