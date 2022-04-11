package seedu.duke.parsers;

import java.util.HashMap;
import java.util.Objects;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.GeneralParseException;
import seedu.duke.exceptions.EmptyParamException;
import seedu.duke.exceptions.MissingCompulsoryParameterException;
import seedu.duke.exceptions.InvalidCompulsoryParameterException;
import seedu.duke.util.StringConstants;


/**
 * This Parser supports the "add task" command.
 */
public class AddTaskParser extends AddParser {
    private static final String TASK_STR = StringConstants.TASK_STR;
    private static final String TASK_DESCRIPTION_STR = StringConstants.TASK_DESCRIPTION_STR;
    private static final String TASK_ESTIMATED_WORKING_TIME_STR = StringConstants.TASK_ESTIMATED_WORKING_TIME_STR;
    private static final String TASK_NAME = StringConstants.TASK_NAME;
    private static final String TASK_DESCRIPTION = StringConstants.TASK_DESCRIPTION;
    private static final String TASK_ESTIMATED_WORKING_TIME = StringConstants.TASK_ESTIMATED_WORKING_TIME;
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
    private static final String TASK_PARAMETERS_FLAG_NO_NAME = StringConstants.TASK_PARAMETERS_FLAG_NO_NAME;

    public AddTaskParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        commandFormat = ADD_FORMAT;
        groupNames.add(TASK_NAME);
        groupNames.add(TASK_DESCRIPTION);
        groupNames.add(TASK_MODULE);
        groupNames.add(TASK_ESTIMATED_WORKING_TIME);
        groupNames.add(INVALID);
        groupNames.add(MODULE_FLAG);
    }

    //@@author heekit73098
    /**
     * Throws an exception depending on the error of the task name based on the compulsory parameters.
     * It will check if the user input has the task name and if it is wrapped with double quotes.
     * @throws MissingCompulsoryParameterException If the task name is missing in the input.
     * @throws InvalidCompulsoryParameterException If the task name is not wrapped with double quotes
     */
    @Override
    public void determineError() throws MissingCompulsoryParameterException, InvalidCompulsoryParameterException {
        String taskName;
        try {
            taskName = userInput.split(WHITESPACES)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCompulsoryParameterException(TASK_NAME_STR);
        }
        if (!taskName.matches(QUOTED_UNRESTRICTED_STR)) {
            throw new InvalidCompulsoryParameterException(TASK_NAME_STR, taskName);
        }
        throw new InvalidCompulsoryParameterException();
    }

    //@@author Yzkkk
    /**
     * Checks if the entered parameters are empty.
     * @param taskName The name of the task to be added
     * @param taskDescription The description of the task to be added
     * @param estimatedWorkingTime The estimated working time of the task to be added
     * @throws EmptyParamException If any of the parameters are empty
     */
    private void checksForEmptyParams(String taskName, String taskDescription, String estimatedWorkingTime)
            throws EmptyParamException {
        if (taskName.isBlank()) {
            throw new EmptyParamException(TASK_STR);
        }
        if (!Objects.isNull(taskDescription) && taskDescription.isBlank()) {
            throw new EmptyParamException(TASK_DESCRIPTION_STR);
        }
        if (!Objects.isNull(estimatedWorkingTime) && estimatedWorkingTime.isBlank()) {
            throw new EmptyParamException(TASK_ESTIMATED_WORKING_TIME_STR);
        }
    }

    //@@author Yzkkk
    /**
     * Checks if the module code contains errors and throws exceptions for any error.
     * It will first attempt to get the string of module code.
     * Then it checks if the string contains any potential flags.
     * If there are potential flags, the flags are removed, leaving the module code inputted.
     * The inputted module code will then be checked if it is in the correct format of word characters only.
     * @param moduleFlag The string captured if the user wants to input a module code
     * @throws EmptyParamException If no module code is inputted
     * @throws InvalidCompulsoryParameterException If there is a module code,
     *                                             but it is not made up of only word characters
     */
    private void checksForErrorInModuleCode(String moduleFlag, String taskModule,
                                            String invalid, boolean hasTaskParameter)
            throws EmptyParamException, InvalidCompulsoryParameterException {
        if (!Objects.isNull(moduleFlag)) {
            boolean hasEmptyTaskModule = Objects.isNull(taskModule) || taskModule.isBlank();
            if (!hasEmptyTaskModule) {
                return;
            }
            boolean hasEmptyInvalid = Objects.isNull(invalid) || invalid.isBlank();
            if (hasEmptyInvalid || hasTaskParameter) {
                throw new EmptyParamException(MODULE_CODE_STR);
            }
            String error = invalid.split(TASK_PARAMETERS_FLAG_NO_NAME + QUOTED_UNRESTRICTED_STR)[ZEROTH_INDEX];
            throw new InvalidCompulsoryParameterException(MODULE_CODE_STR, error);
        }
    }

    //@@author chooyikai
    /**
     * Parses the user input and extracts the parameters based on the command format.
     * @param userInput User input of the task name, task module, task description, estimated working time
     * @return A new {@code AddCommand} object to add a new task
     * @throws ModHappyException If there is an error parsing the command
     */
    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        this.userInput = userInput;
        HashMap<String, String> parsedArguments = parseString(userInput);
        final String taskName = parsedArguments.get(TASK_NAME);
        final String taskDescription = parsedArguments.get(TASK_DESCRIPTION);
        final String estimatedWorkingTime = parsedArguments.get(TASK_ESTIMATED_WORKING_TIME);
        final String taskModule = parsedArguments.get(TASK_MODULE);
        final String moduleFlag = parsedArguments.get(MODULE_FLAG);
        final String invalid = parsedArguments.get(INVALID);
        if (!Objects.isNull(taskName)) {
            boolean hasTaskParameter = !Objects.isNull(taskDescription) || !Objects.isNull(estimatedWorkingTime);
            checksForErrorInModuleCode(moduleFlag, taskModule, invalid, hasTaskParameter);
            checksForEmptyParams(taskName, taskDescription, estimatedWorkingTime);
            checksForExcessArg();
            return new AddCommand(AddCommand.AddObjectType.TASK, taskName, taskDescription, estimatedWorkingTime,
                    taskModule);
        }
        throw new GeneralParseException();
    }
}
