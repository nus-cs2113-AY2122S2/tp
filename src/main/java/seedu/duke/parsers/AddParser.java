package seedu.duke.parsers;

import java.util.HashMap;
import java.util.Objects;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;

/**
 * This Parser supports the "add" command.
 */
public class AddParser extends Parser {
    private static final String TASK_NAME = "taskName";
    private static final String TASK_DESCRIPTION = "taskDescription";
    private static final String TASK_WORKING_TIME = "estimatedWorkingTime";
    private static final String TASK_MODULE = "taskModule";
    private static final String MODULE_CODE = "moduleCode";
    private static final String MODULE_DESCRIPTION = "moduleDescription";

    // Unescaped regex for testing (split across a few lines):
    // (/t\s+\"(?<taskName>[^\"]+)\"(\s+-d\s+\"(?<taskDescription>[^\"]+)\")?
    // (\s+-t\s+\"(?<estimatedWorkingTime>[^\"]+)\")?(\s+-m\s+\"(?<taskModule>\w+)\")?
    // |/m\s+(?<moduleCode>\w+?(?=(\s+-d\s+)|$))(\s+(-d\s+\"(?<moduleDescription>.+)\"))?)

    /* Explanation for regex:
     * (/t\s+\"(?<taskName>[^\"]+)\"                     -- matches [/t "taskName"].
     * (\\s+-d\\s+\\\"(?<taskDescription>[^\\\"]+)\\\")? -- matches [-d "taskDescription"] if present. Optional
     * (\s+-t\s+\"(?<estimatedWorkingTime>[^\"]+)\")?    -- matches [-t "estimatedWorkingTime"] if present. Optional
     *                                                   -- None of the above fields accept " as a valid character.
     *
     * (\s+-m\s+\"(?<taskModule>\w+)\")?                 -- matches [-m taskModule] if present. Optional
     *                                                      Note that taskModule does not require "", but must be a
     *                                                      single word composed of [a-zA-Z0-9_].
     *
     * /m\s+(?<moduleCode>\w+?(?=(\s+-d\s+)|$))          -- matches [/m moduleCode]
     *                                                      Same as above, note that moduleCode does not require "",
     *                                                      but must also be a single word composed of [a-zA-Z0-9_].
     *
     * (\s+(-d\s+\"(?<moduleDescription>.+)\"))?)        -- matches [-d "moduleDescription"] if present. Optional
     *                                                      Does not accept " as a valid character.
     */

    private static final String ADD_FORMAT = "(/t\\s+\\\"(?<taskName>[^\\\"]+)\\\"(\\s+-d\\s+\\\""
            + "(?<taskDescription>[^\\\"]+)\\\")?(\\s+-t\\s+\\\"(?<estimatedWorkingTime>[^\\\"]+)\\\")?"
            + "(\\s+-m\\s+(?<taskModule>\\w+))?|/m\\s+(?<moduleCode>\\w+?(?=(\\s+-d\\s+)|$))"
            + "(\\s+(-d\\s+\\\"(?<moduleDescription>.+)\\\"))?)";

    public AddParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        commandFormat = ADD_FORMAT;
        groupNames.add(TASK_NAME);
        groupNames.add(TASK_DESCRIPTION);
        groupNames.add(TASK_MODULE);
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
        final String taskModule = parsedArguments.get(TASK_MODULE);
        final String moduleCode = parsedArguments.get(MODULE_CODE);
        final String moduleDescription = parsedArguments.get(MODULE_DESCRIPTION);
        if (!Objects.isNull(taskName)) {
            return new AddCommand(AddCommand.AddObjectType.TASK, taskName, taskDescription, estimatedWorkingTime,
                    taskModule);
        }
        if (!Objects.isNull(moduleCode)) {
            return new AddCommand(AddCommand.AddObjectType.MODULE, moduleCode, moduleDescription);
        }
        throw new ParseException();
    }
}
