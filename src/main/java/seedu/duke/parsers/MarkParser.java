package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.MarkCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;
import seedu.duke.ui.TextUi;

import java.util.HashMap;

/**
 * This Parser supports the "mark" command.
 */
public class MarkParser extends Parser {
    private static final String FLAG = TextUi.FLAG;
    private static final String TASK_INDEX = TextUi.TASK_INDEX;
    private static final String COMPLETED_FLAG = TextUi.COMPLETED_FLAG;
    private static final String UNCOMPLETED_FLAG = TextUi.UNCOMPLETED_FLAG;

    // Unescaped regex for testing:
    // \s*(?<flag>\/(c|u))\s+(?<taskIndex>\d+)$
    // TODO: augment this format to support module code parameter
    private static final String MARK_FORMAT = "\\s*(?<flag>\\/(c|u))\\s+(?<taskIndex>\\d+)$";

    public MarkParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        this.commandFormat = MARK_FORMAT;
        groupNames.add(FLAG);
        groupNames.add(TASK_INDEX);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        final String commandFlag = parsedArguments.get(FLAG);
        try {
            // Account for the zero-indexing
            final int taskIndex = Integer.parseInt(parsedArguments.get(TASK_INDEX)) - 1;
            switch (commandFlag) {
            case (COMPLETED_FLAG):
                return new MarkCommand(taskIndex, true);
            case (UNCOMPLETED_FLAG):
                return new MarkCommand(taskIndex, false);
            default:
                throw new ParseException();
            }
        } catch (NumberFormatException e) {
            throw new ParseException();
        }
    }
}
