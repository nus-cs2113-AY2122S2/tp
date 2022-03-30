package seedu.duke.parsers;

import java.util.HashMap;

import seedu.duke.commands.Command;
import seedu.duke.commands.MarkCommand;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.GeneralParseException;
import seedu.duke.util.StringConstants;

/**
 * This Parser supports the "mark" command.
 */
public class MarkParser extends Parser {
    private static final String FLAG = StringConstants.FLAG;
    private static final String TASK_NUMBER = StringConstants.TASK_NUMBER;
    private static final String TASK_MODULE = StringConstants.TASK_MODULE;
    private static final String COMPLETED_FLAG = StringConstants.COMPLETED_FLAG;
    private static final String UNCOMPLETED_FLAG = StringConstants.UNCOMPLETED_FLAG;
    private static final String TASK_NUMBER_STR = StringConstants.ERROR_TASK_NUMBER_FAILED;

    // Unescaped regex for testing:
    // (?<flag>(c|u))\s+(?<taskNumber>\d+)(\s+-m\s+(?<taskModule>\w+))?(?<invalid>.*)
    private static final String MARK_FORMAT = "(?<flag>(c|u))\\s+(?<taskNumber>\\d+)(\\s+-m\\s+"
            + "(?<taskModule>\\w+))?(?<invalid>.*)";

    public MarkParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        this.commandFormat = MARK_FORMAT;
        groupNames.add(FLAG);
        groupNames.add(TASK_NUMBER);
        groupNames.add(TASK_MODULE);
        groupNames.add(INVALID);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        final String commandFlag = parsedArguments.get(FLAG);
        final String taskModule = parsedArguments.get(TASK_MODULE);
        try {
            // Account for the zero-indexing
            final int taskIndex = Integer.parseInt(parsedArguments.get(TASK_NUMBER)) - 1;
            switch (commandFlag) {
            case (COMPLETED_FLAG):
                return new MarkCommand(taskIndex, taskModule, true);
            case (UNCOMPLETED_FLAG):
                return new MarkCommand(taskIndex, taskModule, false);
            default:
                throw new GeneralParseException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(TASK_NUMBER_STR);
        }
    }
}
