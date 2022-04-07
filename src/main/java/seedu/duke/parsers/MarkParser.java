package seedu.duke.parsers;

import java.util.HashMap;

import seedu.duke.commands.Command;
import seedu.duke.commands.MarkCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.InvalidFlagException;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.MissingNumberException;
import seedu.duke.exceptions.InvalidCompulsoryParameterException;
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
    private static final String TASK_NUMBER_STR = StringConstants.TASK_NUMBER_STR;
    private String userInput;

    // Unescaped regex for testing:
    // (?<flag>(c|u)|(?<invalidMarkFlag>.*))\s+(?<taskNumber>\d+|(?<invalidNumber>.*))
    // (\s+-m\s+(?<taskModule>\w+))?(?<invalid>.*)
    private static final String MARK_FORMAT = "(?<flag>(c|u)|(?<invalidMarkFlag>.*))\\s+"
            + "(?<taskNumber>\\d+|(?<invalidNumber>.*))(\\s+-m\\s+"
            + "(?<taskModule>\\w+))?(?<invalid>.*)";
    private static final String MARK_COMMAND_FLAGS = StringConstants.MARK_COMMAND_FLAGS;
    private static final String POSITIVE_INT = StringConstants.POSITIVE_INT;

    public MarkParser() {
        super();
        // See also https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
        this.commandFormat = MARK_FORMAT;
        groupNames.add(FLAG);
        groupNames.add(TASK_NUMBER);
        groupNames.add(TASK_MODULE);
        groupNames.add(INVALID);
        groupNames.add(INVALID_MARK_FLAG);
        groupNames.add(INVALID_NUMBER);
    }

    /**
     * Determines the error that the user made in its command.
     * @throws ModHappyException based on the type of error made.
     */
    @Override
    public void determineError() throws ModHappyException {
        String flag;
        String taskNumber;
        try {
            flag = userInput.split(SPACE)[ZEROTH_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidFlagException();
        }
        if (!flag.matches(MARK_COMMAND_FLAGS)) {
            throw new InvalidFlagException(flag);
        }
        try {
            taskNumber = userInput.split(SPACE)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingNumberException(TASK_NUMBER_STR);
        }
        if (!taskNumber.matches(POSITIVE_INT)) {
            throw new InvalidNumberException(TASK_NUMBER_STR, taskNumber);
        }
        throw new InvalidCompulsoryParameterException();
    }

    /**
     * Parses user's input for "mark" command.
     *
     * @param userInput User input of completed flag or uncompleted flag, task index and task module.
     * @throws ModHappyException if completed flag or uncompleted flag is not detected
     */
    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        this.userInput = userInput;
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
            throw new InvalidNumberException(TASK_NUMBER_STR, parsedArguments.get(TASK_NUMBER));
        }
    }
}
