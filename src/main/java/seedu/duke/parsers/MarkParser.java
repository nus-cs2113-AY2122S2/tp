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
    // (?<flag>(c|u))\s+(?<taskNumber>\d+)(\s+-m\s+(?<taskModule>\w+))?(?<invalid>.*)
    private static final String MARK_FORMAT = "(?<flag>(c|u))\\s+"
            + "(?<taskNumber>\\d+)(\\s+-m\\s+(?<taskModule>\\w+))?(?<invalid>.*)";
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
    }

    /**
     * Determines the error made by the user in the mark command based on the compulsory parameters.
     * It will first check if the flag is present and if it is either c or u.
     * Then it will check if the task number is present and if it is in a positive integer format.
     * @throws InvalidFlagException if the flag is missing, or not c nor u
     * @throws MissingNumberException if the task number is missing
     * @throws InvalidNumberException if the task number is not in a positive integer format
     * @throws InvalidCompulsoryParameterException if the error is none of the above errors
     */
    @Override
    public void determineError() throws InvalidFlagException, MissingNumberException,
            InvalidNumberException, InvalidCompulsoryParameterException {
        String flag;
        try {
            flag = userInput.split(SPACE)[ZEROTH_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidFlagException();
        }
        if (!flag.matches(MARK_COMMAND_FLAGS)) {
            throw new InvalidFlagException(flag);
        }
        String taskNumber;
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
        final int taskIndex = parseIndex(parsedArguments.get(TASK_NUMBER));
        checksForExcessArg();
        switch (commandFlag) {
        case (COMPLETED_FLAG):
            return new MarkCommand(taskIndex, taskModule, true);
        case (UNCOMPLETED_FLAG):
            return new MarkCommand(taskIndex, taskModule, false);
        default:
            throw new GeneralParseException();
        }
    }
}
