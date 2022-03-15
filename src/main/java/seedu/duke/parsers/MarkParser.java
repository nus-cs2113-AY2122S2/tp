package seedu.duke.parsers;

import java.util.HashMap;

import seedu.duke.commands.Command;
import seedu.duke.commands.MarkCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;

/**
 * This Parser supports the "mark" command.
 */
public class MarkParser extends Parser {
    private static final String FLAG = "flag";
    private static final String TASK_INDEX = "taskIndex";
    private static final String TASK_MODULE = "taskModule";
    private static final String COMPLETED_FLAG = "/c";
    private static final String UNCOMPLETED_FLAG = "/u";

    // Unescaped regex for testing:
    // (?<flag>\/(c|u))\s+(?<taskIndex>\d+)(\s+-m\s+(?<taskModule>\w+))?
    private static final String MARK_FORMAT = "(?<flag>\\/(c|u))\\s+(?<taskIndex>\\d+)(\\s+-m\\s+(?<taskModule>\\w+))?";

    public MarkParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        this.commandFormat = MARK_FORMAT;
        groupNames.add(FLAG);
        groupNames.add(TASK_INDEX);
        groupNames.add(TASK_MODULE);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        final String commandFlag = parsedArguments.get(FLAG);
        final String taskModule = parsedArguments.get(TASK_MODULE);
        try {
            // Account for the zero-indexing
            final int taskIndex = Integer.parseInt(parsedArguments.get(TASK_INDEX)) - 1;
            switch (commandFlag) {
            case (COMPLETED_FLAG):
                return new MarkCommand(taskIndex, taskModule, true);
            case (UNCOMPLETED_FLAG):
                return new MarkCommand(taskIndex, taskModule, false);
            default:
                throw new ParseException();
            }
        } catch (NumberFormatException e) {
            throw new ParseException();
        }
    }
}
