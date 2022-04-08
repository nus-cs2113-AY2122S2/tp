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
    private static final String MODULE_FLAG = StringConstants.MODULE_FLAG;
    private static final String MODULE_CODE_STR = StringConstants.MODULE_CODE_STR;
    private String userInput;

    // Unescaped regex for testing (split across a few lines):
    // (task\s+\"(?<taskName>[^\"]*)\"(\s+((?<moduleFlag>-m)\s+(?<taskModule>\w*)))?(\s+-d\s+\"
    // (?<taskDescription>[^\"]*)\")?(\s+-t\s+\"(?<estimatedWorkingTime>[^\"]*)\")?)(?<invalid>.*)

    /* Explanation for regex:
     * (task\s+\"(?<taskName>[^\"]*)\"                   -- matches [task "taskName"].
     * (?<moduleFlag>-m)                                 -- matches [-m] if present. Optional
     * (?<taskModule>\w*)?                               -- matches [taskModule] if present. Optional
     *                                                      if this is present, it must be paired with -m
     *                                                      Note that taskModule does not require "", but must be a
     *                                                      single word composed of [a-zA-Z0-9_].
     * (\s+-d\s+\"(?<taskDescription>[^\"]*)\")?         -- matches [-d "taskDescription"] if present. Optional
     * (\s+-t\s+\"(?<estimatedWorkingTime>[^\"]+)\")?)   -- matches [-t "estimatedWorkingTime"] if present. Optional
     *                                                   -- None of the above fields accept " as a valid character.
     *
     * (?<invalid>.*)                                    -- matches [invalid]
     *                                                      Any other excess inputs
     */

    private static final String ADD_FORMAT = "(task\\s+\\\"(?<taskName>[^\\\"]*)\\\"(\\s+((?<moduleFlag>-m)\\s+"
            + "(?<taskModule>\\w*)))?(\\s+-d\\s+\\\"(?<taskDescription>[^\\\"]*)\\\")?(\\s+-t\\s+\\\""
            + "(?<estimatedWorkingTime>[^\\\"]*)\\\")?)(?<invalid>.*)";
    private static final String QUOTED_UNRESTRICTED_STR = StringConstants.QUOTED_UNRESTRICTED_STR;
    private static final String ANY_FLAG_TRIMMED = StringConstants.ANY_FLAG_TRIMMED;
    private static final String ANY_FLAG = StringConstants.ANY_FLAG;
    private static final String ANY_TEXT = StringConstants.ANY_TEXT;
    private static final String DASH = StringConstants.DASH;
    private static final String WORD_CHAR_ONLY = StringConstants.WORD_CHAR_ONLY;
    private static final String TASK_MODULE_FLAG = StringConstants.TASK_MODULE_FLAG;

    public AddTaskParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        commandFormat = ADD_FORMAT;
        groupNames.add(TASK_NAME);
        groupNames.add(TASK_DESCRIPTION);
        groupNames.add(TASK_MODULE);
        groupNames.add(TASK_WORKING_TIME);
        groupNames.add(INVALID);
        groupNames.add(MODULE_FLAG);
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
            throw new InvalidCompulsoryParameterException(TASK_NAME_STR, taskName);
        }
        throw new InvalidCompulsoryParameterException();
    }


    private void checksForErrorInModuleCode(String moduleFlag) throws ModHappyException {
        if (!Objects.isNull(moduleFlag)) {
            String moduleCode;
            try {
                moduleCode = userInput.split(TASK_MODULE_FLAG)[FIRST_INDEX];
            } catch (IndexOutOfBoundsException e) {
                throw new EmptyParamException(MODULE_CODE_STR);
            }
            if (moduleCode.contains(SPACE + DASH)) {
                moduleCode = moduleCode.split(SPACE + DASH)[ZEROTH_INDEX];
            }
            if (moduleCode.matches(ANY_FLAG_TRIMMED + QUOTED_UNRESTRICTED_STR)) {
                throw new EmptyParamException(MODULE_CODE_STR);
            }
            if (!moduleCode.matches(WORD_CHAR_ONLY)) {
                throw new InvalidCompulsoryParameterException(MODULE_CODE_STR, moduleCode);
            }
        }
    }

    private void checksForEmptyParams(String taskName, String taskDescription, String estimatedWorkingTime) throws EmptyParamException {
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
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        this.userInput = userInput;
        HashMap<String, String> parsedArguments = parseString(userInput);
        final String taskName = parsedArguments.get(TASK_NAME);
        final String taskDescription = parsedArguments.get(TASK_DESCRIPTION);
        final String estimatedWorkingTime = parsedArguments.get(TASK_WORKING_TIME);
        final String taskModule = parsedArguments.get(TASK_MODULE);
        final String moduleFlag = parsedArguments.get(MODULE_FLAG);
        if (!Objects.isNull(taskName)) {
            checksForErrorInModuleCode(moduleFlag);
            checksForEmptyParams(taskName, taskDescription, estimatedWorkingTime);
            checksForExcessArg();
            return new AddCommand(AddCommand.AddObjectType.TASK, taskName, taskDescription, estimatedWorkingTime,
                    taskModule);
        }
        throw new GeneralParseException();
    }
}
