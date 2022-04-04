package seedu.duke.parsers;

import java.util.HashMap;
import java.util.Objects;

import seedu.duke.commands.Command;
import seedu.duke.commands.EditCommand;
import seedu.duke.exceptions.EmptyParamException;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.GeneralParseException;
import seedu.duke.util.StringConstants;

/**
 * This Parser supports the "edit" command.
 */
public class EditParser extends Parser {

    private static final String TASK_NUMBER = StringConstants.TASK_NUMBER;
    private static final String TASK_DESCRIPTION = StringConstants.TASK_DESCRIPTION;
    private static final String TASK_ESTIMATED_WORKING_TIME = StringConstants.TASK_ESTIMATED_WORKING_TIME;
    private static final String TASK_NAME_STR = StringConstants.TASK_NAME_STR;
    private static final String TASK_DESCRIPTION_STR = StringConstants.TASK_DESCRIPTION_STR;
    private static final String TASK_ESTIMATED_WORKING_TIME_STR = StringConstants.TASK_ESTIMATED_WORKING_TIME_STR;
    private static final String MODULE_CODE = StringConstants.MODULE_CODE;
    private static final String MODULE_DESCRIPTION_STR = StringConstants.MODULE_DESCRIPTION_STR;
    private static final String MODULE_DESCRIPTION = StringConstants.MODULE_DESCRIPTION;
    private static final String TASK_MODULE = StringConstants.TASK_MODULE;
    private static final String TASK_NAME = StringConstants.TASK_NAME;
    private static final String TASK_NUMBER_STR = StringConstants.ERROR_TASK_NUMBER_FAILED;

    // Unescaped regex for testing
    // ((task\s+(?<taskNumber>\d+)(\s+-m\s+(?<taskModule>\w+))?(?=\s+-n\s+\"[^\"]+\"|
    // \s+-d\s+\"[^\"]+\"|\s+-t\s+\"[^\"]+\")(\s+-n\s+\"((?<taskName>[^\"]+)\")?|\s+-d\s+\"
    // ((?<taskDescription>[^\"]+)\")?|(\s+-t\s+\"(?<estimatedWorkingTime>[^\"]+)\")?))|(mod\s+
    // (?<moduleCode>\w+?(?=(\s+-d\s+)))(\s+(-d\s+\"(?<moduleDescription>.+)\"))))(?<invalid>.*)

    /* Explanation for regex:
     * ((task\s+(?<taskNumber>\d+|(?<invalidNumber>.*))      -- matches [task taskNumber].
     *
     * (\s+(-m|(?<invalidModFlag>.*))\s+(?<taskModule>\w+))? -- matches [-m taskModule] if present. Optional
     *                                                          Note that taskModule does not require "", but must be a
     *                                                          single word composed of [a-zA-Z0-9_].
     *
     * (?=\s+-n\s+\"[^\"]+\"|\s+-d\s+\"[^\"]+\"              -- matches flags -n, -d, -t.
     * |\s+-t\s+\"[^\"]+\")
     *
     * (\s+(-n|(?<invalidTaskNameFlag>.*))                   -- matches [-n "taskName"] or
     * \s+\"(?<taskName>[^\"]+)\")?                             [<invalidTaskNameFlag> "taskName"] if present. Optional
     *
     *
     *
     * (\s+(-d|(?<invalidTaskDesFlag>.*))                    -- matches [-d "taskDescription"] or
     * \s+\"(?<taskDescription>[^\"]+)\")?                      [<invalidTaskDesFlag> "taskDescription"] if present.
     *                                                          Optional
     *
     * (\s+(-t|(?<invalidTimeFlag>.*))                       -- matches [-t "estimatedWorkingTime"] if present. Optional
     * \s+\"(?<estimatedWorkingTime>[^\"]+)\")?)                [<invalidTimeFlag> "estimatedWorkingTime"] if present.
     *                                                          Optional
     *
     *                                                       -- None of the above fields accept " as a valid character.
     *
     * (mod\s+(?<moduleCode>\w+?(?=(\s+-d\s+)))              -- matches [mod moduleCode], matches flag -d
     *
     * (\s+(-d|(?<invalidModDesFlag>.*))                     -- matches [-d "taskDescription"] or
     * \s+\"(?<moduleDescription>.+)\")?)                       [<invalidTaskDesFlag> "taskDescription"] if present.
     *
     * (?<invalid>.*)                                        -- matches [invalid]
     *                                                          Any other excess inputs

     */
    private static final String EDIT_FORMAT = "(task\\s+(?<taskNumber>\\d+|(?<invalidNumber>.*))"
            + "(\\s+(-m|(?<invalidModFlag>.*))\\s+(?<taskModule>\\w+))?"
            + "(?=\\s+-n\\s+\\\"[^\\\"]+\\\"|\\s+-d\\s+\\\"[^\\\"]+\\\"|\\s+-t\\s+\\\"[^\\\"]+\\\")"
            + "(\\s+(-n|(?<invalidTaskNameFlag>.*))\\s+\\\""
            + "(?<taskName>[^\\\"]+)\\\")?|(\\s+(-d|(?<invalidTaskDesFlag>.*))\\s+\\\""
            + "(?<taskDescription>[^\\\"]+)\\\")?|(\\s+(-t|(?<invalidTimeFlag>.*))\\s+\\\""
            + "(?<estimatedWorkingTime>[^\\\"]+)\\\")?)|(mod\\s+(?<moduleCode>\\w+?(?=(\\s+-d\\s+)))"
            + "(\\s+(-d|(?<invalidModDesFlag>.*))\\s+\\\"(?<moduleDescription>.+)\\\")?)(?<invalid>.*)";

    public EditParser() {
        super();
        this.commandFormat = EDIT_FORMAT;
        groupNames.add(TASK_NUMBER);
        groupNames.add(MODULE_CODE);
        groupNames.add(TASK_DESCRIPTION);
        groupNames.add(TASK_ESTIMATED_WORKING_TIME);
        groupNames.add(MODULE_DESCRIPTION);
        groupNames.add(TASK_MODULE);
        groupNames.add(TASK_NAME);
        groupNames.add(INVALID);
        groupNames.add(INVALID_NUMBER);
        groupNames.add(INVALID_TASK_NAME_FLAG);
        groupNames.add(INVALID_TASK_DES_FLAG);
        groupNames.add(INVALID_TIME_FLAG);
        groupNames.add(INVALID_MOD_FLAG);
        groupNames.add(INVALID_MOD_DES_FLAG);

    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String taskNumberString = parsedArguments.get(TASK_NUMBER);
        String moduleCode = parsedArguments.get(MODULE_CODE);
        String taskModule = parsedArguments.get(TASK_MODULE);
        String taskDescription = parsedArguments.get(TASK_DESCRIPTION);
        String estimatedWorkingTime = parsedArguments.get(TASK_ESTIMATED_WORKING_TIME);
        String moduleDescription = parsedArguments.get(MODULE_DESCRIPTION);
        String taskName = parsedArguments.get(TASK_NAME);
        if (!Objects.isNull(moduleCode)) {
            if (!Objects.isNull(moduleDescription)) {
                if (moduleDescription.isBlank()) {
                    throw new EmptyParamException(MODULE_DESCRIPTION_STR);
                }
            }
            return new EditCommand(moduleCode, moduleDescription);
        }
        if (!Objects.isNull(taskNumberString)) {
            int taskIndex;
            try {
                taskIndex = Integer.parseInt(taskNumberString) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidNumberException(TASK_NUMBER_STR);
            }
            if (!Objects.isNull(taskName)) {
                if (taskName.isBlank()) {
                    throw new EmptyParamException(TASK_NAME_STR);
                }
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
            return new EditCommand(taskModule, taskIndex, taskDescription, estimatedWorkingTime, taskName);
        }
        throw new ModHappyException();
    }
}
