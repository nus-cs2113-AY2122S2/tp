package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;

import java.util.HashMap;
import java.util.Objects;

public class EditParser extends Parser {

    public static final String MODULE_CODE = "moduleCode";
    public static final String TASK_NUMBER = "taskNumber";
    public static final String TASK_DESCRIPTION = "taskDescription";
    public static final String ESTIMATED_WORKING_TIME = "estimatedWorkingTime";
    public static final String MODULE_DESCRIPTION = "moduleDescription";
    public static final String TASK_MODULE = "taskModule";

    private static final String EDIT_FORMAT = "(/t\\s+(?<taskNumber>\\d+)(\\s+-m\\s+\\\"(?<taskModule>\\w+)\\\")?"
            + "(\\s+-d\\s+\\\"((?<taskDescription>[^\\\"]+)\\\")?"
            + "|(\\s+-t\\s+\\\"(?<estimatedWorkingTime>[^\\\"]+)\\\")?))"
            + "|(/m\\s+(?<moduleCode>\\w+?(?=(\\s+-d\\s+)|$))(\\s+(-d\\s+\\\"(?<moduleDescription>.+)\\\"))?)";

    public EditParser() {
        super();
        this.commandFormat = EDIT_FORMAT;
        groupNames.add(TASK_NUMBER);
        groupNames.add(MODULE_CODE);
        groupNames.add(TASK_DESCRIPTION);
        groupNames.add(ESTIMATED_WORKING_TIME);
        groupNames.add(MODULE_DESCRIPTION);
        groupNames.add(TASK_MODULE);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String taskNumberString = parsedArguments.get(TASK_NUMBER);
        String moduleCode = parsedArguments.get(MODULE_CODE);
        String taskModule = parsedArguments.get(TASK_MODULE);
        String taskDescription = parsedArguments.get(TASK_DESCRIPTION);
        String estimatedWorkingTime = parsedArguments.get(ESTIMATED_WORKING_TIME);
        String moduleDescription = parsedArguments.get(MODULE_DESCRIPTION);
        if (!Objects.isNull(moduleCode)) {
            return new EditCommand(moduleCode, moduleDescription);
        }

        if (!Objects.isNull(taskNumberString)) {
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(taskNumberString);
            } catch (NumberFormatException e) {
                throw new ParseException();
            }
            return new EditCommand(taskModule, taskNumber, taskDescription, estimatedWorkingTime);
        }
        throw new ModHappyException();
    }
}
