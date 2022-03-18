package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.TagCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;
import seedu.duke.util.StringConstants;

import java.util.HashMap;

public class TagParser extends Parser {
    public static final String TAG_OPERATION = StringConstants.TAG_OPERATION;
    public static final String TASK_NUMBER = StringConstants.TASK_NUMBER;
    public static final String TASK_MODULE = StringConstants.TASK_MODULE;
    public static final String TAG_DESCRIPTION = StringConstants.TAG_DESCRIPTION;

    //Unescaped Regex for testing:
    //((?<taskOperation>\w+))(\s+(?<taskNumber>\d+))((\s+-m\s+(?<taskModule>\w+))?)(\s+(?<tagDescription>.+))
    private static final String TAG_FORMAT = "((?<tagOperation>\\w+))(\\s+(?<taskNumber>\\d+))"
            + "((\\s+-m\\s+(?<taskModule>\\w+))?)(\\s+(?<tagDescription>.+))";

    public TagParser() {
        super();
        this.commandFormat = TAG_FORMAT;
        groupNames.add(TAG_OPERATION);
        groupNames.add(TASK_NUMBER);
        groupNames.add(TASK_MODULE);
        groupNames.add(TAG_DESCRIPTION);
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        HashMap<String, String> parsedArguments = parseString(userInput);
        String tagOperationString = parsedArguments.get(TAG_OPERATION);
        String taskNumberString = parsedArguments.get(TASK_NUMBER);
        String taskModuleString = parsedArguments.get(TASK_MODULE);
        String tagDescription = parsedArguments.get(TAG_DESCRIPTION);
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskNumberString) - 1;
        } catch (NumberFormatException e) {
            throw new ParseException();
        }
        return new TagCommand(tagOperationString, taskIndex, taskModuleString, tagDescription);
    }

}