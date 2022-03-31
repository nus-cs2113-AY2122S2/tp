package seedu.duke.parsers;

import java.util.HashMap;

import seedu.duke.commands.Command;
import seedu.duke.commands.TagCommand;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.GeneralParseException;
import seedu.duke.util.StringConstants;



public class TagParser extends Parser {
    public static final String TAG_OPERATION = StringConstants.TAG_OPERATION;
    public static final String TASK_NUMBER = StringConstants.TASK_NUMBER;
    public static final String TASK_MODULE = StringConstants.TASK_MODULE;
    public static final String TAG_NAME = StringConstants.TAG_NAME;
    private static final String TASK_NUMBER_STR = StringConstants.ERROR_TASK_NUMBER_FAILED;

    // Unescaped Regex for testing:
    // ((?<tagOperation>\b(add|del)\b)?)(\s+(?<taskNumber>\d+))((\s+-m\s+(?<taskModule>\w+))?)
    // (\s+\"(?<tagName>\w+)\")(?<invalid>.*)
    private static final String TAG_FORMAT = "((?<tagOperation>\\b(add|del)\\b)?)(\\s+(?<taskNumber>\\d+))"
            + "((\\s+-m\\s+(?<taskModule>\\w+))?)(\\s+(?<tagName>\\w+))(?<invalid>.*)";


    public TagParser() {
        super();
        this.commandFormat = TAG_FORMAT;
        groupNames.add(TAG_OPERATION);
        groupNames.add(TASK_NUMBER);
        groupNames.add(TASK_MODULE);
        groupNames.add(TAG_NAME);
        groupNames.add(INVALID);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String tagOperationString = parsedArguments.get(TAG_OPERATION);
        String taskNumberString = parsedArguments.get(TASK_NUMBER);
        String taskModuleString = parsedArguments.get(TASK_MODULE);
        String tagDescription = parsedArguments.get(TAG_NAME);
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskNumberString) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidNumberException(TASK_NUMBER_STR);
        }
        return new TagCommand(tagOperationString, taskIndex, taskModuleString, tagDescription);
    }

}