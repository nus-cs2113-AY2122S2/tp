package seedu.duke.parsers;

import java.util.HashMap;
import java.util.Objects;

import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;

public class DeleteParser extends Parser {

    public static final String MODULE_CODE = "moduleCode";
    public static final String TASK_NUMBER = "taskNumber";
    //TODO: make the regex stricter in accepting inputs and extend to deleting tasks from modules
    private static final String DELETE_FORMAT = "\\s*(\\/t\\s+(?<taskNumber>\\d+))|\\/m\\s+(?<moduleCode>\\w+)";

    public DeleteParser() {
        super();
        this.commandFormat = DELETE_FORMAT;
        groupNames.add(TASK_NUMBER);
        groupNames.add(MODULE_CODE);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String taskNumberString = parsedArguments.get(TASK_NUMBER);
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
            return new DeleteCommand(taskNumber);
        }
        throw new ModHappyException();
    }
}
