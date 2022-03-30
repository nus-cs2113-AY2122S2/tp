package seedu.duke.parsers;

import java.util.HashMap;
import java.util.Objects;

import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.GeneralParseException;
import seedu.duke.util.StringConstants;

public class DeleteParser extends Parser {
    private static final String TASK_NUMBER = StringConstants.TASK_NUMBER;
    private static final String TASK_MODULE = StringConstants.TASK_MODULE;
    private static final String MODULE_CODE = StringConstants.MODULE_CODE;
    private static final String TASK_NUMBER_STR = StringConstants.ERROR_TASK_NUMBER_FAILED;

    // Unescaped regex for testing:
    // (task\s+(?<taskNumber>\d+)(\s+-m\s+(?<taskModule>\w+))?|mod\s+(?<moduleCode>\w+))(?<invalid>.*)
    private static final String DELETE_FORMAT = "(task\\s+(?<taskNumber>\\d+)(\\s+-m\\s+(?<taskModule>\\w+))?|"
            + "mod\\s+(?<moduleCode>\\w+))(?<invalid>.*)";

    public DeleteParser() {
        super();
        this.commandFormat = DELETE_FORMAT;
        groupNames.add(TASK_NUMBER);
        groupNames.add(TASK_MODULE);
        groupNames.add(MODULE_CODE);
        groupNames.add(INVALID);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String taskNumberString = parsedArguments.get(TASK_NUMBER);
        String taskModuleString = parsedArguments.get(TASK_MODULE);
        String moduleCode = parsedArguments.get(MODULE_CODE);
        if (!Objects.isNull(moduleCode)) {
            return new DeleteCommand(moduleCode);
        }
        if (!Objects.isNull(taskNumberString)) {
            int taskIndex;
            try {
                taskIndex = Integer.parseInt(taskNumberString) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidNumberException(TASK_NUMBER_STR);
            }
            return new DeleteCommand(taskIndex, taskModuleString);
        }
        throw new ModHappyException();
    }
}
