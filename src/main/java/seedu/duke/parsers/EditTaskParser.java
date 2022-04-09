package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.EditCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.InvalidCompulsoryParameterException;
import seedu.duke.exceptions.MissingNumberException;
import seedu.duke.exceptions.MissingCompulsoryParameterException;
import seedu.duke.exceptions.InvalidFlagException;
import seedu.duke.exceptions.EmptyParamException;
import seedu.duke.util.StringConstants;

import java.util.HashMap;
import java.util.Objects;

/**
 * This Parser supports the "edit task" command.
 */
public class EditTaskParser extends EditParser {

    private static final String TASK_NUMBER = StringConstants.TASK_NUMBER;
    private static final String TASK_DESCRIPTION = StringConstants.TASK_DESCRIPTION;
    private static final String TASK_ESTIMATED_WORKING_TIME = StringConstants.TASK_ESTIMATED_WORKING_TIME;
    private static final String TASK_NAME_STR = StringConstants.TASK_NAME_STR;
    private static final String TASK_DESCRIPTION_STR = StringConstants.TASK_DESCRIPTION_STR;
    private static final String TASK_ESTIMATED_WORKING_TIME_STR = StringConstants.TASK_ESTIMATED_WORKING_TIME_STR;
    private static final String TASK_MODULE = StringConstants.TASK_MODULE;
    private static final String TASK_NAME = StringConstants.TASK_NAME;
    private static final String EMPTY_STRING = StringConstants.EMPTY_STRING;
    private String userInput;

    // Unescaped regex for testing
    // (task\s+(?<taskNumber>\d+)(\s+-m\s+(?<taskModule>\w+))?(?=\s+(-n|-d|-t)\s+\"[^\"]+\")((\s+-n\s+\"
    // ((?<taskName>[^\"]+)\")?|\s+-d\s+\"((?<taskDescription>[^\"]+)\")?|
    // (\s+-t\s+\"(?<estimatedWorkingTime>[^\"]+)\")?)))(?<invalid>.*)

    /* Explanation for regex:
     * (task\s+(?<taskNumber>\d+)                            -- matches [task taskNumber].
     *
     * (\s+-m\s+(?<taskModule>\w+))?                         -- matches [-m taskModule] if present. Optional
     *                                                          Note that taskModule does not require "", but must be a
     *                                                          single word composed of [a-zA-Z0-9_].
     *
     * (?=\s+(-n|-d|-t)\s+\"[^\"]+\"))                       -- asserts that there must be one task parameter flag
     *
     * ((\s+-n\s+\"((?<taskName>[^\"]+)\")?                  -- matches [-n "taskName"] if present. Optional
     *
     * \s+-d\s+\"((?<taskDescription>[^\"]+)\")?             -- matches [-d "taskDescription"] if present.
     *                                                          Optional
     *
     * (\s+-t\s+\"(?<estimatedWorkingTime>[^\"]+)\")?)))     -- matches [-t "estimatedWorkingTime"] if present. Optional
     *
     *                                                       -- None of the above fields accept " as a valid character.
     *
     * (?<invalid>.*)                                        -- matches [invalid]
     *                                                          Any other excess inputs

     */
    private static final String EDIT_FORMAT = "(task\\s+(?<taskNumber>\\d+)(\\s+"
            + "-m\\s+(?<taskModule>\\w+))?(?=\\s+(-n|-d|-t)\\s+\\\"[^\\\"]+\\\")((\\s+-n\\s+\\\""
            + "((?<taskName>[^\\\"]+)\\\")?|\\s+-d\\s+\\\"((?<taskDescription>[^\\\"]+)\\\")?|(\\s+-t\\s+\\\""
            + "(?<estimatedWorkingTime>[^\\\"]+)\\\")?)))(?<invalid>.*)";
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
        groupNames.add(TASK_DESCRIPTION);
        groupNames.add(TASK_ESTIMATED_WORKING_TIME);
        groupNames.add(TASK_MODULE);
        groupNames.add(TASK_NAME);
        groupNames.add(INVALID);
    }

    /**
     * Determines the error that the user made in the edit task command based of the compulsory parameters.
     * It will first check if the task number is present and if it is in a positive integer format.
     * Then it will check if there is a correct task parameter present and if the task parameter has the correct flag,
     * and it is wrapped in double quotes.
     * Lastly, if the task number and task parameters have no errors, there should be errors in the module code field.
     * The module code will be checked if it is empty or if it is invalid.
     * @throws MissingNumberException if the task number is missing
     * @throws InvalidNumberException if the task number is not in a positive integer format
     * @throws MissingCompulsoryParameterException if the task parameter is missing
     * @throws InvalidCompulsoryParameterException if the task parameter is not wrapped with double quotes
     * @throws EmptyParamException if the module code inputted is empty
     * @throws InvalidFlagException if the flag for the task parameter is incorrect
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

    private String checkErrorInTaskNumber() throws MissingNumberException, InvalidNumberException {
        String taskNumber;
        try {
            taskNumber = userInput.split(SPACE)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingNumberException(TASK_NUMBER_STR);
        }
        if (!taskNumber.matches(POSITIVE_INT)) {
            throw new InvalidNumberException(TASK_NUMBER_STR, taskNumber);
        }
        return taskNumber;
    }

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
     * @return the first incorrect flag, not including the module flag if it is present
     */
    private String getParameterFlag() {
        String parameterFlag = null;
        boolean hasModuleFlag = userInput.contains(TASK_MODULE_FLAG);
        String [] arguments = userInput.split(SPACE);
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
     * Determine the error in the prarameter if the parameter is wrapped in double quotes but with the wrong flag.
     * @throws InvalidFlagException if the incorrect flag is used
     */
    private void determineErrorInParameter() throws InvalidFlagException {
        if (userInput.matches(ANY_TEXT + ANY_FLAG + QUOTED_UNRESTRICTED_STR)) {
            String parameterFlag = getParameterFlag();
            throw new InvalidFlagException(parameterFlag);
        }
    }

    private void reduceErrorScope(String taskNumber) {
        userInput = userInput.replaceFirst(TASK, EMPTY_STRING);
        userInput = userInput.replaceFirst(taskNumber, EMPTY_STRING);
        userInput = userInput.split(TASK_PARAMETERS_FLAGS)[ZEROTH_INDEX];
    }

    /**
     * Determines the error in the module code.
     * It will check if the module code is empty, otherwise the module code is not made up of only word characters.
     * @throws EmptyParamException if the module code supplied is empty
     * @throws InvalidCompulsoryParameterException if module code is not made up of only word characters
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
            throw new InvalidCompulsoryParameterException(MODULE_CODE_STR, userInput.trim());
        }

        throw new InvalidCompulsoryParameterException(MODULE_CODE_STR, moduleCode);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        this.userInput = userInput;
        HashMap<String, String> parsedArguments = parseString(userInput);
        String taskNumberString = parsedArguments.get(TASK_NUMBER);
        String taskModule = parsedArguments.get(TASK_MODULE);
        String taskDescription = parsedArguments.get(TASK_DESCRIPTION);
        String estimatedWorkingTime = parsedArguments.get(TASK_ESTIMATED_WORKING_TIME);
        String taskName = parsedArguments.get(TASK_NAME);
        if (!Objects.isNull(taskNumberString)) {
            checkTaskName(taskName);
            checkTaskDescription(taskDescription);
            checkEstimatedWorkingTime(estimatedWorkingTime);
            final int taskIndex = parseIndex(taskNumberString);
            checksForExcessArg();
            return new EditCommand(taskModule, taskIndex, taskDescription, estimatedWorkingTime, taskName);
        }
        throw new ModHappyException();
    }

    private void checkEstimatedWorkingTime(String estimatedWorkingTime) throws EmptyParamException {
        if (!Objects.isNull(estimatedWorkingTime) && estimatedWorkingTime.isBlank()) {
            throw new EmptyParamException(TASK_ESTIMATED_WORKING_TIME_STR);
        }
    }

    private void checkTaskDescription(String taskDescription) throws EmptyParamException {
        if (!Objects.isNull(taskDescription) && taskDescription.isBlank()) {
            throw new EmptyParamException(TASK_DESCRIPTION_STR);
        }
    }

    private void checkTaskName(String taskName) throws EmptyParamException {
        if (!Objects.isNull(taskName) && taskName.isBlank()) {
            throw new EmptyParamException(TASK_NAME_STR);
        }
    }

}
