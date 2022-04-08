package seedu.duke.parsers;

import java.util.HashMap;

import seedu.duke.commands.Command;
import seedu.duke.commands.TagCommand;
import seedu.duke.exceptions.InvalidCompulsoryParameterException;
import seedu.duke.exceptions.InvalidTagOperationException;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.MissingNumberException;
import seedu.duke.exceptions.MissingCompulsoryParameterException;
import seedu.duke.util.NumberConstants;
import seedu.duke.util.StringConstants;


/**
 * This Parser supports the "tag" command.
 */
public class TagParser extends Parser {
    private static final String TAG_OPERATION = StringConstants.TAG_OPERATION;
    private static final String TASK_NUMBER = StringConstants.TASK_NUMBER;
    private static final String TASK_MODULE = StringConstants.TASK_MODULE;
    private static final String TAG_NAME = StringConstants.TAG_NAME;
    private static final int MINIMUM_INDEX = NumberConstants.MINIMUM_INDEX;
    private String userInput;

    // Unescaped Regex for testing:
    // ((?<tagOperation>\b(add|del)|(?<invalidTagCommand>.*)\b)?)(\s+(?<taskNumber>\d+))((\s+(-m|(?<invalidModFlag>.*))
    // \s+(?<taskModule>\w+))?)(\s+(?<tagName>\w+))(?<invalid>.*)
    private static final String TAG_FORMAT = "((?<tagOperation>\\b(add|del)|(?<invalidTagCommand>.*)\\b)?)"
            + "(\\s+(?<taskNumber>\\d+))((\\s+(-m|(?<invalidModFlag>.*))\\s+(?<taskModule>\\w+))?)"
            + "(\\s+(?<tagName>\\w+))(?<invalid>.*)";
    private static final String TAG_COMMAND_FLAGS = StringConstants.TAG_COMMAND_FLAGS;
    private static final String POSITIVE_INT = StringConstants.POSITIVE_INT;
    private static final String WORD_CHAR_ONLY = StringConstants.WORD_CHAR_ONLY;
    private static final String TASK_MODULE_FLAG = StringConstants.TASK_MODULE_FLAG;

    public TagParser() {
        super();
        this.commandFormat = TAG_FORMAT;
        groupNames.add(TAG_OPERATION);
        groupNames.add(TASK_NUMBER);
        groupNames.add(TASK_MODULE);
        groupNames.add(TAG_NAME);
        groupNames.add(INVALID);
        groupNames.add(INVALID_MOD_FLAG);
        groupNames.add(INVALID_TAG_COMMAND);
    }

    /**
     * Determines the error that the user made in its command.
     * @throws ModHappyException based on the type of error made.
     */
    @Override
    public void determineError() throws ModHappyException {
        determineErrorInTagOperation();
        determineErrorInTaskNumber();
        determineErrorInTagName();
        assertErrorInModuleCode();
    }

    private void determineErrorInTagOperation() throws InvalidTagOperationException {
        String tagOperation;
        try {
            tagOperation = userInput.split(SPACE)[ZEROTH_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTagOperationException();
        }
        if (!tagOperation.matches(TAG_COMMAND_FLAGS)) {
            throw new InvalidTagOperationException(tagOperation);
        }
    }

    private void determineErrorInTaskNumber() throws ModHappyException {
        String taskNumber;
        try {
            taskNumber = userInput.split(SPACE)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingNumberException(TASK_NUMBER_STR);
        }
        if (!taskNumber.matches(POSITIVE_INT)) {
            throw new InvalidNumberException(TASK_NUMBER_STR, taskNumber);
        }
    }

    private void determineErrorInTagName() throws ModHappyException {
        String tagName;
        try {
            if (userInput.contains(TASK_MODULE_FLAG)) {
                tagName = userInput.split(SPACE)[FOURTH_INDEX];
            } else {
                tagName = userInput.split(SPACE)[SECOND_INDEX];
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCompulsoryParameterException(TAG_NAME_STR);
        }
        if (!tagName.matches(WORD_CHAR_ONLY)) {
            throw new InvalidCompulsoryParameterException(TAG_NAME_STR, tagName);
        }
    }

    private void assertErrorInModuleCode() throws ModHappyException {
        assert (userInput.contains(TASK_MODULE_FLAG));
        String moduleCode = userInput.split(TASK_MODULE_FLAG)[FIRST_INDEX].split(SPACE)[ZEROTH_INDEX];
        throw new InvalidCompulsoryParameterException(MODULE_CODE_STR, moduleCode);
    }

    private int parseIndex(String taskNumberString) throws InvalidNumberException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskNumberString) - 1;
            if (taskIndex < MINIMUM_INDEX) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(TASK_NUMBER_STR, taskNumberString);
        }
        return taskIndex;
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        this.userInput = userInput;
        HashMap<String, String> parsedArguments = parseString(userInput);
        String tagOperationString = parsedArguments.get(TAG_OPERATION);
        String taskNumberString = parsedArguments.get(TASK_NUMBER);
        String taskModuleString = parsedArguments.get(TASK_MODULE);
        String tagDescription = parsedArguments.get(TAG_NAME);
        int taskIndex = parseIndex(taskNumberString);
        checksForExcessArg();
        return new TagCommand(tagOperationString, taskIndex, taskModuleString, tagDescription);
    }

}