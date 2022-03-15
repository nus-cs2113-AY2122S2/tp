package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.EditCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;
import seedu.duke.util.StringConstants;

import java.util.HashMap;
import java.util.Objects;

public class EditParser extends Parser {

    private static final String MODULE_CODE = StringConstants.MODULE_CODE;
    private static final String TASK_NUMBER = StringConstants.TASK_NUMBER;
    private static final String TASK_DESCRIPTION = StringConstants.TASK_DESCRIPTION;
    private static final String ESTIMATED_WORKING_TIME = StringConstants.TASK_WORKING_TIME;
    private static final String MODULE_DESCRIPTION = StringConstants.MODULE_DESCRIPTION;
    private static final String TASK_MODULE = StringConstants.TASK_MODULE;
    private static final String TASK_NAME = StringConstants.TASK_NAME;

    private static final String EDIT_FORMAT = "(/t\\s+(?<taskNumber>\\d+)"
            + "(?=\\s+-n\\s+\\\"[^\\\"]+\\\"|\\s+-d\\s+\\\"[^\\\"]+\\\"|\\s+-t\\s+\\\"[^\\\"]+\\\")"
            + "(\\s+-n\\s+\\\"((?<taskName>[^\\\"]+)\\\")?|\\s+-d\\s+\\\"((?<taskDescription>[^\\\"]+)\\\")?"
            + "|(\\s+-t\\s+\\\"(?<estimatedWorkingTime>[^\\\"]+)\\\")?))(\\s+-m\\s+(?<taskModule>\\w+))?"
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
        groupNames.add(TASK_NAME);
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
        String taskName = parsedArguments.get(TASK_NAME);
        if (!Objects.isNull(moduleCode)) {
            return new EditCommand(moduleCode, moduleDescription);
        }

        if (!Objects.isNull(taskNumberString)) {
            int taskIndex;
            try {
                 taskIndex = Integer.parseInt(taskNumberString) - 1;
            } catch (NumberFormatException e) {
                throw new ParseException();
            }
            return new EditCommand(taskModule, taskIndex, taskDescription, estimatedWorkingTime, taskName);
        }
        throw new ModHappyException();
    }
}
