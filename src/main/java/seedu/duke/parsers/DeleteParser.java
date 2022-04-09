package seedu.duke.parsers;

import java.util.HashMap;
import java.util.Objects;

import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.MissingNumberException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.InvalidCompulsoryParameterException;
import seedu.duke.exceptions.MissingCompulsoryParameterException;
import seedu.duke.exceptions.UnknownCommandException;
import seedu.duke.util.StringConstants;

/**
 * This Parser supports the "del" command.
 */
public class DeleteParser extends Parser {
    private static final String TASK_NUMBER = StringConstants.TASK_NUMBER;
    private static final String TASK_MODULE = StringConstants.TASK_MODULE;
    private static final String MODULE_CODE = StringConstants.MODULE_CODE;
    private String userInput;

    // Unescaped regex for testing:
    // (task\\s+(?<taskNumber>\\d+)(\\s+-m\\s+(?<taskModule>\\w+))?|mod\\s+(?<moduleCode>\\w+))(?<invalid>.*)
    private static final String DELETE_FORMAT = "(task\\s+(?<taskNumber>\\d+)(\\s+-m\\s+(?<taskModule>\\w+))?"
            + "|mod\\s+(?<moduleCode>\\w+))(?<invalid>.*)";
    private static final String POSITIVE_INT = StringConstants.POSITIVE_INT;
    private static final String WORD_CHAR_ONLY = StringConstants.WORD_CHAR_ONLY;

    public DeleteParser() {
        super();
        this.commandFormat = DELETE_FORMAT;
        groupNames.add(TASK_NUMBER);
        groupNames.add(TASK_MODULE);
        groupNames.add(MODULE_CODE);
        groupNames.add(INVALID);
    }

    //@@author heekit73098
    /**
     * Determines the error that the user made in its command based on the compulsory parameters.
     * It will first determine the object type the command is trying to delete,
     * and then check for errors within each specific command.
     * @throws MissingNumberException if the task number is missing for a del task command
     * @throws MissingCompulsoryParameterException if the module code is missing for a del mod command
     * @throws InvalidNumberException if the task number is not in a positive integer format for a del task command
     * @throws InvalidCompulsoryParameterException if the module code is not made up of all word characters
     *                                             for a del mod command
     * @throws UnknownCommandException if the object type specified is not mod or task
     */
    @Override
    public void determineError() throws MissingNumberException, MissingCompulsoryParameterException,
            InvalidNumberException, InvalidCompulsoryParameterException, UnknownCommandException {
        String type = userInput.split(WHITESPACES)[ZEROTH_INDEX];
        switch (type) {
        case TASK:
            determineErrorForTask();
            break;
        case MODULE:
            determineErrorForModule();
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Determines the error of the del tag command based on the compulsory parameters.
     * It will check if the task number is present and if it is in a positive integer format.
     * @throws MissingNumberException if the task number is missing
     * @throws InvalidNumberException if the task number is not in a positive integer format
     */
    public void determineErrorForTask() throws MissingNumberException, InvalidNumberException {
        String taskNumber;
        try {
            taskNumber = userInput.split(WHITESPACES)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingNumberException(TASK_NUMBER_STR);
        }
        if (!taskNumber.matches(POSITIVE_INT)) {
            throw new InvalidNumberException(TASK_NUMBER_STR, taskNumber);
        }
    }

    /**
     * Determines the error for a del mod command, based on its compulsory parameters.
     * It will check if the module code is present and if it is made up only of word characters.
     * @throws MissingCompulsoryParameterException if the module code is missing
     * @throws InvalidCompulsoryParameterException if the module code is not made up of word characters only
     */
    public void determineErrorForModule() throws MissingCompulsoryParameterException,
            InvalidCompulsoryParameterException {
        String moduleCode;
        try {
            moduleCode = userInput.split(WHITESPACES)[FIRST_INDEX];
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCompulsoryParameterException(MODULE_CODE_STR);
        }
        if (!moduleCode.matches(WORD_CHAR_ONLY)) {
            throw new InvalidCompulsoryParameterException(MODULE_CODE_STR, moduleCode);
        }
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        this.userInput = userInput;
        HashMap<String, String> parsedArguments = parseString(userInput);
        String taskNumberString = parsedArguments.get(TASK_NUMBER);
        String taskModuleString = parsedArguments.get(TASK_MODULE);
        String moduleCode = parsedArguments.get(MODULE_CODE);
        checksForExcessArg();
        if (!Objects.isNull(moduleCode)) {
            return new DeleteCommand(moduleCode);
        }
        if (!Objects.isNull(taskNumberString)) {
            int taskIndex = parseIndex(taskNumberString);
            return new DeleteCommand(taskIndex, taskModuleString);
        }
        throw new ModHappyException();
    }
}
