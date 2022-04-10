package seedu.duke.parsers;

import java.util.HashMap;
import java.util.Objects;

import seedu.duke.commands.Command;
import seedu.duke.commands.EditCommand;
import seedu.duke.data.TaskParameters;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.InvalidCompulsoryParameterException;
import seedu.duke.exceptions.MissingNumberException;
import seedu.duke.exceptions.MissingCompulsoryParameterException;
import seedu.duke.exceptions.InvalidFlagException;
import seedu.duke.exceptions.EmptyParamException;
import seedu.duke.util.StringConstants;


//@@author heekit73098
/**
 * This Parser supports the "edit task" command.
 */
public class EditTaskParser extends EditParser {

    private static final String TASK_NUMBER = StringConstants.TASK_NUMBER;
    private static final String TASK_NAME_STR = StringConstants.TASK_NAME_STR;
    private static final String TASK_PARAMETER_FLAG = StringConstants.TASK_PARAMETER_FLAG;
    private static final String TASK_PARAMETER = StringConstants.TASK_PARAMETER;
    private static final String TASK_MODULE = StringConstants.TASK_MODULE;
    private static final String EMPTY_STRING = StringConstants.EMPTY_STRING;
    private static final String MODULE_FIELD_STR = StringConstants.MODULE_FIELD_STR;
    private static final String TASK_NAME_FLAG = StringConstants.TASK_NAME_FLAG;
    private static final String TASK_DESCRIPTION_FLAG = StringConstants.TASK_DESCRIPTION_FLAG;
    private static final String TASK_ESTIMATED_WORKING_TIME_FLAG = StringConstants.TASK_ESTIMATED_WORKING_TIME_FLAG;
    private String userInput;

    // Unescaped regex for testing
    // (task\s+(?<taskNumber>\d+)(\s+-m\s+(?<taskModule>\w+))?(?=\s+(-n|-d|-t)\s+\"[^\"]*\")(\s+(?<taskParameterFlag>
    // (-n|-d|-t))\s+\"(?<taskParameter>[^\"]*)\"))(?<invalid>.*)

    /* Explanation for regex:
     * (task\s+(?<taskNumber>\d+)                            -- matches [task taskNumber].
     *
     * (\s+-m\s+(?<taskModule>\w+))?                         -- matches [-m taskModule] if present. Optional
     *                                                          Note that taskModule does not require "", but must be a
     *                                                          single word composed of [a-zA-Z0-9_].
     *
     * (?=\s+(-n|-d|-t)\s+\"[^\"]*\"))                       -- asserts that there must be one task parameter flag.
     *
     * (?<taskParameterFlag>(-n|-d|-t))                      -- matches [(-n|-d|-t)] the parameter flag.
     *
     * \"(?<taskParameter>[^\"]*)\"                          -- matches ["taskParameter"].
     *
     * (?<invalid>.*)                                        -- matches [invalid]
     *                                                          Any other excess inputs

     */
    private static final String EDIT_FORMAT = "(task\\s+(?<taskNumber>\\d+)(\\s+-m\\s+(?<taskModule>\\w+))?"
            + "(?=\\s+(-n|-d|-t)\\s+\\\"[^\\\"]*\\\")(\\s+(?<taskParameterFlag>(-n|-d|-t))\\s+\\\""
            + "(?<taskParameter>[^\\\"]*)\\\"))(?<invalid>.*)";
    private static final String POSITIVE_INT = StringConstants.POSITIVE_INT;
    private static final String QUOTED_UNRESTRICTED_STR = StringConstants.QUOTED_UNRESTRICTED_STR;
    private static final String TASK_PARAMETERS_FLAGS = StringConstants.TASK_PARAMETERS_FLAG;
    private static final String TASK_MODULE_FLAG = StringConstants.TASK_MODULE_FLAG;
    private static final String ANY_TEXT = StringConstants.ANY_TEXT;
    private static final String ANY_FLAG = StringConstants.ANY_FLAG;
    private static final String ANY_FLAG_NO_WHITESPACE = StringConstants.ANY_FLAG_NO_WHITESPACE;

    public EditTaskParser() {
        super();
        this.commandFormat = EDIT_FORMAT;
        groupNames.add(TASK_NUMBER);
        groupNames.add(TASK_PARAMETER_FLAG);
        groupNames.add(TASK_PARAMETER);
        groupNames.add(TASK_MODULE);
        groupNames.add(INVALID);
    }

    /**
     * Determines the error that the user made in the edit task command based of the compulsory parameters.
     * It will first check if the task number is present and if it is in a positive integer format.
     * Then it will check if there is a correct task parameter present and if the task parameter has the correct flag,
     * and it is wrapped in double quotes.
     * Lastly, if the task number and task parameters have no errors, there should be errors in the module code field.
     * The module code will be checked if it is empty or if it is invalid.
     * @throws MissingNumberException If the task number is missing
     * @throws InvalidNumberException If the task number is not in a positive integer format
     * @throws MissingCompulsoryParameterException If the task parameter is missing
     * @throws InvalidCompulsoryParameterException If the task parameter is not wrapped with double quotes
     * @throws EmptyParamException If the module code inputted is empty
     * @throws InvalidFlagException If the flag for the task parameter is incorrect
     */
    @Override
    public void determineError() throws MissingNumberException, InvalidNumberException,
            MissingCompulsoryParameterException, InvalidCompulsoryParameterException,
            EmptyParamException, InvalidFlagException {
        String taskNumber = checkErrorInTaskNumber();
        checkErrorInTaskParameter();
        reduceErrorScope(taskNumber);
        determineErrorInModuleCode();
    }

    /**
     * Checks if the error is in task number.
     * It checks if the task number is present, and if it is in a positive integer format.
     * @return The string representation of the task number if it is present
     * @throws MissingNumberException If the task number is missing
     * @throws InvalidNumberException If the task number is not in positive integer format
     */
    private String checkErrorInTaskNumber() throws MissingNumberException, InvalidNumberException {
        String taskNumber;
        try {
            taskNumber = userInput.split(WHITESPACES)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingNumberException(TASK_NUMBER_STR);
        }
        if (!taskNumber.matches(POSITIVE_INT)) {
            throw new InvalidNumberException(TASK_NUMBER_STR, taskNumber);
        }
        return taskNumber;
    }

    /**
     * Checks if the error is in the task parameter.
     * It checks if the task parameter is present, and if the flag and parameter is correctly formatted.
     * @throws MissingCompulsoryParameterException If the task parameter is missing
     * @throws InvalidCompulsoryParameterException If the task parameter is not wrapped in double quotes
     * @throws InvalidFlagException If the flag used to represent the parameter is incorrect
     */
    private void checkErrorInTaskParameter() throws MissingCompulsoryParameterException,
            InvalidCompulsoryParameterException, InvalidFlagException {
        String taskParameter;
        try {
            taskParameter = userInput.split(TASK_PARAMETERS_FLAGS)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            determineErrorInParameter();
            throw new MissingCompulsoryParameterException(TASK_PARAMETER_STR);
        }
        if (!taskParameter.matches(QUOTED_UNRESTRICTED_STR)) {
            throw new InvalidCompulsoryParameterException(TASK_PARAMETER_STR, taskParameter);
        }
    }

    /**
     * Gets the incorrect flag.
     * @return The first incorrect flag, not including the module flag if it is present
     */
    private String getParameterFlag() {
        String parameterFlag = null;
        boolean hasModuleFlag = userInput.contains(TASK_MODULE_FLAG);
        String [] arguments = userInput.split(WHITESPACES);
        for (String argument : arguments) {
            if (hasModuleFlag && argument.equals(TASK_MODULE_FLAG.trim())) {
                hasModuleFlag = false;
                continue;
            }
            if (argument.matches(ANY_FLAG_NO_WHITESPACE)) {
                parameterFlag = argument;
                break;
            }
        }
        assert !Objects.isNull(parameterFlag);
        return parameterFlag;
    }

    /**
     * Determine the error in the parameter if the parameter is wrapped in double quotes but with the wrong flag.
     * @throws InvalidFlagException If the incorrect flag is used
     */
    private void determineErrorInParameter() throws InvalidFlagException {
        if (userInput.matches(ANY_TEXT + ANY_FLAG + QUOTED_UNRESTRICTED_STR)) {
            String parameterFlag = getParameterFlag();
            throw new InvalidFlagException(parameterFlag);
        }
    }

    /**
     * Narrows the scope of the error by eliminating the correct areas in the string.
     * @param taskNumber The string representation of the task number
     */
    private void reduceErrorScope(String taskNumber) {
        userInput = userInput.replaceFirst(TASK, EMPTY_STRING);
        userInput = userInput.replaceFirst(taskNumber, EMPTY_STRING);
        userInput = userInput.split(TASK_PARAMETERS_FLAGS)[ZEROTH_INDEX];
    }

    /**
     * Determines the error in the module code.
     * It will check if the module code is empty, otherwise the module code is not made up of only word characters.
     * @throws EmptyParamException If the module code supplied is empty
     * @throws InvalidCompulsoryParameterException If module code is not made up of only word characters
     *                                             or if there is invalid input in where the field should be
     */
    private void determineErrorInModuleCode() throws EmptyParamException, InvalidCompulsoryParameterException {
        String moduleCode;
        try {
            moduleCode = userInput.split(TASK_MODULE_FLAG)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            if (userInput.contains(TASK_MODULE_FLAG.trim())) {
                throw new EmptyParamException(MODULE_CODE_STR);
            }
            throw new InvalidCompulsoryParameterException(MODULE_FIELD_STR, userInput.trim());
        }
        String inputBeforeModuleCode = userInput.split(TASK_MODULE_FLAG.trim())[ZEROTH_INDEX];
        if (!inputBeforeModuleCode.isBlank()) {
            throw new InvalidCompulsoryParameterException(MODULE_FIELD_STR, userInput.trim());
        }

        throw new InvalidCompulsoryParameterException(MODULE_CODE_STR, moduleCode);
    }

    /**
     * Gets the enumeration of the task parameter inputted.
     * @param taskParameterFlag The flag inputted to represent the parameter
     * @return The enumeration of the task parameter inputted
     * @throws InvalidFlagException If the flag inputted is invalid
     */
    private TaskParameters getTaskParameter(String taskParameterFlag) throws InvalidFlagException {
        switch (taskParameterFlag) {
        case TASK_NAME_FLAG:
            return TaskParameters.TASK_NAME_ONLY;
        case TASK_DESCRIPTION_FLAG:
            return TaskParameters.DESCRIPTION_ONLY;
        case TASK_ESTIMATED_WORKING_TIME_FLAG:
            return TaskParameters.WORKING_TIME_ONLY;
        default:
            throw new InvalidFlagException(taskParameterFlag);
        }
    }


    /**
     * Parses the user input and extracts the parameters based on the command format.
     * @param userInput User input of the task number, task module and a task parameter
     * @return A new {@code EditCommand} object to edit a task parameter
     * @throws ModHappyException If there is an error parsing the command
     */
    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        this.userInput = userInput;
        HashMap<String, String> parsedArguments = parseString(userInput);
        String taskNumberString = parsedArguments.get(TASK_NUMBER);
        String taskModule = parsedArguments.get(TASK_MODULE);
        String taskParameterFlag = parsedArguments.get(TASK_PARAMETER_FLAG);
        String taskParameter = parsedArguments.get(TASK_PARAMETER);
        if (!Objects.isNull(taskNumberString)) {
            TaskParameters taskParameterType = getTaskParameter(taskParameterFlag);
            checkTaskName(taskParameter, taskParameterType);
            final int taskIndex = parseIndex(taskNumberString);
            checksForExcessArg();
            return new EditCommand(taskModule, taskIndex, taskParameter, taskParameterType);
        }
        throw new ModHappyException();
    }

    /**
     * Checks if the task name inputted is empty.
     * @param taskName The name of the task to be edited
     * @param taskParametersType The enumeration of the task parameter that was inputted
     * @throws EmptyParamException If the task name inputted is empty
     */
    private void checkTaskName(String taskName, TaskParameters taskParametersType) throws EmptyParamException {
        if (taskParametersType != TaskParameters.TASK_NAME_ONLY) {
            return;
        }
        if (!Objects.isNull(taskName) && taskName.isBlank()) {
            throw new EmptyParamException(TASK_NAME_STR);
        }
    }

}
