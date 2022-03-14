package seedu.duke.parsers;

import java.util.HashMap;
import java.util.Objects;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;
import seedu.duke.util.StringConstants;

/**
 * This Parser supports the "add" command.
 */
public class AddParser extends Parser {
    private static final String TASK_NAME = StringConstants.TASK_NAME;
    private static final String TASK_DESCRIPTION = StringConstants.TASK_DESCRIPTION;
    private static final String TASK_WORKING_TIME = StringConstants.TASK_WORKING_TIME;
    private static final String MODULE_CODE = StringConstants.MODULE_CODE;
    private static final String MODULE_DESCRIPTION = StringConstants.MODULE_DESCRIPTION;

    // Unescaped regex for testing (split into two lines):
    // \s*(\/t\s+(?<taskName>.+?(?=\s+-d\s+|\s+-t\s+|$))(\s+(-d\s+\"(?<taskDescription>([^\"]*))\")(?=(\s+-t\s+)|$))?
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
        if (!Objects.equals(taskName, NULL_FIELD)) {
            return new AddCommand(taskName, taskDescription, true, estimatedWorkingTime);
        }
        if (!Objects.equals(moduleCode, NULL_FIELD)) {
            return new AddCommand(moduleCode, moduleDescription, false, NULL_FIELD);
        }
        throw new ParseException();
    }
}
