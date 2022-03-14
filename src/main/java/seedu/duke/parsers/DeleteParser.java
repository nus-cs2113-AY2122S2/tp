package seedu.duke.parsers;

import java.util.HashMap;
import java.util.Objects;

import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;

public class DeleteParser extends Parser {

    public static final String TASK_NUMBER = "taskNumber";
    public static final String TASK_MODULE = "taskModule";
    public static final String MODULE_CODE = "moduleCode";
    // Unescaped regex for testing:
    // (/t\s+(?<taskNumber>\d+)(\s+-m\s+(?<taskModule>\w+))?|/m\s+(?<moduleCode>\w+))
    private static final String DELETE_FORMAT = "(/t\\s+(?<taskNumber>\\d+)(\\s+-m\\s+(?<taskModule>\\w+))?"
            + "|/m\\s+(?<moduleCode>\\w+))";

    public DeleteParser() {
        super();
        this.commandFormat = DELETE_FORMAT;
        groupNames.add(TASK_NUMBER);
        groupNames.add(TASK_MODULE);
        groupNames.add(MODULE_CODE);
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
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(taskNumberString);
            } catch (NumberFormatException e) {
                throw new ParseException();
            }
            return new DeleteCommand(taskNumber, taskModuleString);
        }
        throw new ModHappyException();
    }
}
