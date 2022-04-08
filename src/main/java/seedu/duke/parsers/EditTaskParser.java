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
import seedu.duke.util.NumberConstants;
import seedu.duke.util.StringConstants;

import java.util.HashMap;
import java.util.Objects;

/**
 * This Parser supports the "edit" command.
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
    private static final int MINIMUM_INDEX = NumberConstants.MINIMUM_INDEX;
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
     * Determines the error that the user made in its command.
     * @throws ModHappyException based on the type of error made.
     */
    @Override
    public void determineError() throws ModHappyException {
        String taskNumber;
        String taskParameter;
        try {
            taskNumber = userInput.split(SPACE)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingNumberException(TASK_NUMBER_STR);
        }
        if (!taskNumber.matches(POSITIVE_INT)) {
            throw new InvalidNumberException(TASK_NUMBER_STR, taskNumber);
        }
        try {
            taskParameter = userInput.split(TASK_PARAMETERS_FLAGS)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            determineErrorInParameter();
            throw new MissingCompulsoryParameterException(TASK_PARAMETER_STR);
        }
        if (!taskParameter.matches(QUOTED_UNRESTRICTED_STR)) {
            throw new InvalidCompulsoryParameterException(TASK_PARAMETER_STR, taskParameter);
        }
        determineErrorInModuleCode();
    }

    private void determineErrorInModuleCode() throws EmptyParamException, InvalidCompulsoryParameterException {
        String moduleCode;
        assert (userInput.contains(TASK_MODULE_FLAG));
        moduleCode = userInput.split(TASK_MODULE_FLAG)[FIRST_INDEX].split(SPACE)[ZEROTH_INDEX];
        if (moduleCode.matches(ANY_FLAG_NO_WHITESPACE)) {
            throw new EmptyParamException(MODULE_CODE_STR);
        }
        throw new InvalidCompulsoryParameterException(MODULE_CODE_STR, moduleCode);
    }

    private String getParameterFlag() {
        String parameterFlag = null;
        String [] arguments = userInput.split(SPACE);
        for (String argument : arguments) {
            if (argument.matches(ANY_FLAG_NO_WHITESPACE)) {
                parameterFlag = argument;
            }
        }
        assert !Objects.isNull(parameterFlag);
        return parameterFlag;
    }

    private void determineErrorInParameter() throws InvalidFlagException {
        if (userInput.matches(ANY_TEXT + ANY_FLAG + QUOTED_UNRESTRICTED_STR)) {
            String parameterFlag = getParameterFlag();
            throw new InvalidFlagException(parameterFlag);
        }
    }

    private int parseIndex(String taskNumberString) throws InvalidNumberException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskNumberString) - 1;
            if (taskIndex < MINIMUM_INDEX) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(TASK_NUMBER_STR, taskNumberString);
        }
        return taskIndex;
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
        if (!Objects.isNull(estimatedWorkingTime)) {
            if (estimatedWorkingTime.isBlank()) {
                throw new EmptyParamException(TASK_ESTIMATED_WORKING_TIME_STR);
            }
        }
    }

    private void checkTaskDescription(String taskDescription) throws EmptyParamException {
        if (!Objects.isNull(taskDescription)) {
            if (taskDescription.isBlank()) {
                throw new EmptyParamException(TASK_DESCRIPTION_STR);
            }
        }
    }

    private void checkTaskName(String taskName) throws EmptyParamException {
        if (!Objects.isNull(taskName)) {
            if (taskName.isBlank()) {
                throw new EmptyParamException(TASK_NAME_STR);
            }
        }
    }

}
