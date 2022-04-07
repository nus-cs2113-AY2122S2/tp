package seedu.duke.parsers;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.GeneralParseException;
import seedu.duke.exceptions.EmptyParamException;
import seedu.duke.exceptions.MissingCompulsoryParameterException;
import seedu.duke.exceptions.InvalidCompulsoryParameterException;
import seedu.duke.util.StringConstants;

import java.util.HashMap;
import java.util.Objects;

/**
 * This Parser supports the "add" command.
 */
public class AddTaskParser extends AddParser {
    private static final String TASK_STR = StringConstants.TASK_STR;
    private static final String TASK_DESCRIPTION_STR = StringConstants.TASK_DESCRIPTION_STR;
    private static final String TASK_ESTIMATED_WORKING_TIME_STR = StringConstants.TASK_ESTIMATED_WORKING_TIME_STR;
    private static final String TASK_NAME = StringConstants.TASK_NAME;
    private static final String TASK_DESCRIPTION = StringConstants.TASK_DESCRIPTION;
    private static final String TASK_WORKING_TIME = StringConstants.TASK_ESTIMATED_WORKING_TIME;
    private static final String TASK_MODULE = StringConstants.TASK_MODULE;
    private String userInput;

    // Unescaped regex for testing (split across a few lines):
    // (task\s+\"(?<taskName>[^\"]+)\"(\s+(-m\s+(?<taskModule>\w+)))?(\s+-d\s+\"(?<taskDescription>[^\"]+)\")?
    // (\s+-t\s+\"(?<estimatedWorkingTime>[^\"]+)\")?)(?<invalid>.*)

    /* Explanation for regex:
     * (task\s+\"(?<taskName>[^\"]+)\"                   -- matches [task "taskName"].
     * (\s+(-m\s+(?<taskModule>\w+)))?                   -- matches [-m taskModule] if present. Optional
     *                                                      Note that taskModule does not require "", but must be a
     *                                                      single word composed of [a-zA-Z0-9_].
     * (\s+-d\s+\"(?<taskDescription>[^\"]+)\")?         -- matches [-d "taskDescription"] if present. Optional
     * (\s+-t\s+\"(?<estimatedWorkingTime>[^\"]+)\")?)   -- matches [-t "estimatedWorkingTime"] if present. Optional
     *                                                   -- None of the above fields accept " as a valid character.
     *
     * (?<invalid>.*)                                    -- matches [invalid]
     *                                                      Any other excess inputs
     */

    private static final String ADD_FORMAT = "(task\\s+\\\"(?<taskName>[^\\\"]+)\\\"(\\s+(-m\\s+(?<taskModule>\\w+)))?"
            + "(\\s+-d\\s+\\\"(?<taskDescription>[^\\\"]+)\\\")?"
            + "(\\s+-t\\s+\\\"(?<estimatedWorkingTime>[^\\\"]+)\\\")?)(?<invalid>.*)";
    private static final String QUOTED_UNRESTRICTED_STR = StringConstants.QUOTED_UNRESTRICTED_STR;

    public AddTaskParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        commandFormat = ADD_FORMAT;
        groupNames.add(TASK_NAME);
        groupNames.add(TASK_DESCRIPTION);
        groupNames.add(TASK_MODULE);
        groupNames.add(TASK_WORKING_TIME);
        groupNames.add(INVALID);
    }

    /**
     * Throws an exception depending on the error of the task name.
     * @throws ModHappyException based on the error of the task name.
     */
    @Override
    public void determineError() throws ModHappyException {
        String taskName;
        try {
            taskName = userInput.split(SPACE)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCompulsoryParameterException(TASK_NAME_STR);
        }
        if (!taskName.matches(QUOTED_UNRESTRICTED_STR)) {
            throw new InvalidCompulsoryParameterException(TASK_PARAMETER_STR, taskName);
        }
        throw new InvalidCompulsoryParameterException();
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        this.userInput = userInput;
        HashMap<String, String> parsedArguments = parseString(userInput);
        final String taskName = parsedArguments.get(TASK_NAME);
        final String taskDescription = parsedArguments.get(TASK_DESCRIPTION);
        final String estimatedWorkingTime = parsedArguments.get(TASK_WORKING_TIME);
        final String taskModule = parsedArguments.get(TASK_MODULE);
        if (!Objects.isNull(taskName)) {
            if (taskName.isBlank()) {
                throw new EmptyParamException(TASK_STR);
            }
            if (!Objects.isNull(taskDescription)) {
                if (taskDescription.isBlank()) {
                    throw new EmptyParamException(TASK_DESCRIPTION_STR);
                }
            }
            if (!Objects.isNull(estimatedWorkingTime)) {
                if (estimatedWorkingTime.isBlank()) {
                    throw new EmptyParamException(TASK_ESTIMATED_WORKING_TIME_STR);
                }
            }

            return new AddCommand(AddCommand.AddObjectType.TASK, taskName, taskDescription, estimatedWorkingTime,
                    taskModule);
        }
        throw new GeneralParseException();
    }
}
