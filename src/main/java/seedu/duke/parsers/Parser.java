package seedu.duke.parsers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.InvalidFlagException;
import seedu.duke.exceptions.InvalidModuleGradeException;
import seedu.duke.exceptions.ExcessArgumentException;
import seedu.duke.exceptions.InvalidTagOperationException;

import seedu.duke.exceptions.ModHappyException;
import seedu.duke.util.StringConstants;
import seedu.duke.util.NumberConstants;

/**
 * Represents a Parser that parse a {@code Command}.
 */
public abstract class Parser {
    protected static final String EXIT_COMMAND_WORD = StringConstants.EXIT_COMMAND_WORD;
    protected static final String ADD_COMMAND_WORD = StringConstants.ADD_COMMAND_WORD;
    protected static final String DELETE_COMMAND_WORD = StringConstants.DELETE_COMMAND_WORD;
    protected static final String GPA_COMMAND_WORD = StringConstants.GPA_COMMAND_WORD;
    protected static final String GRADE_COMMAND_WORD = StringConstants.GRADE_COMMAND_WORD;
    protected static final String LIST_COMMAND_WORD = StringConstants.LIST_COMMAND_WORD;
    protected static final String MARK_COMMAND_WORD = StringConstants.MARK_COMMAND_WORD;
    protected static final String EDIT_COMMAND_WORD = StringConstants.EDIT_COMMAND_WORD;
    protected static final String RESET_COMMAND_WORD = StringConstants.RESET_COMMAND_WORD;
    protected static final String HELP_COMMAND_WORD = StringConstants.HELP_COMMAND_WORD;
    protected static final String SAVE_COMMAND_WORD = StringConstants.SAVE_COMMAND_WORD;
    protected static final String TAG_COMMAND_WORD = StringConstants.TAG_COMMAND_WORD;
    protected static final String OPTION_COMMAND_WORD = StringConstants.OPTION_COMMAND_WORD;

    protected static final String INVALID = StringConstants.INVALID;
    protected static final String INVALID_MOD_FLAG = StringConstants.INVALID_MOD_FLAG;
    protected static final String INVALID_TASK_NAME_FLAG = StringConstants.INVALID_TASK_NAME_FLAG;
    protected static final String INVALID_TASK_DES_FLAG = StringConstants.INVALID_TASK_DES_FLAG;
    protected static final String INVALID_MOD_DES_FLAG = StringConstants.INVALID_MOD_DES_FLAG;
    protected static final String INVALID_TIME_FLAG = StringConstants.INVALID_TIME_FLAG;
    protected static final String INVALID_MARK_FLAG = StringConstants.INVALID_MARK_FLAG;
    protected static final String INVALID_MODULE_GRADE = StringConstants.INVALID_MODULE_GRADE;
    protected static final String INVALID_NUMBER = StringConstants.INVALID_NUMBER;
    protected static final String INVALID_TAG_COMMAND = StringConstants.INVALID_TAG_COMMAND;

    protected static final String SPACE = StringConstants.SPACE;
    protected static final String TASK = StringConstants.TASK_STR;
    protected static final String MODULE = StringConstants.MODULE_STR;
    protected static final String TASK_NAME_STR = StringConstants.TASK_NAME_STR;
    protected static final String TASK_NUMBER_STR = StringConstants.TASK_NUMBER_STR;
    protected static final String MODULAR_CREDIT_STR = StringConstants.MODULAR_CREDIT_STR;
    protected static final String MODULE_CODE_STR = StringConstants.MODULE_CODE_STR;
    protected static final String MODULE_DESCRIPTION_STR = StringConstants.MODULE_DESCRIPTION_STR;
    protected static final String TASK_PARAMETER_STR = StringConstants.TASK_PARAMETER_STR;
    protected static final String TAG_NAME_STR = StringConstants.TAG_NAME_STR;
    protected static final int ZEROTH_INDEX = NumberConstants.ZEROTH_INDEX;
    protected static final int FIRST_INDEX = NumberConstants.FIRST_INDEX;
    protected static final int SECOND_INDEX = NumberConstants.SECOND_INDEX;
    protected static final int FOURTH_INDEX = NumberConstants.FOURTH_INDEX;
    protected static final int MINIMUM_INDEX = NumberConstants.MINIMUM_INDEX;


    protected String commandFormat;
    protected HashMap<String, String> parsedCommand;
    protected HashSet<String> groupNames;

    public Parser() {
        groupNames = new HashSet<String>();
        parsedCommand = new HashMap<>();
    }

    /**
     * Parses the provided user input and returns the relevant Command object.
     */
    public abstract Command parseCommand(String userInput) throws ModHappyException;

    /**
     * Parses the provided user input and returns the relevant Command object.
     */
    public abstract void determineError() throws ModHappyException;

    /**
     * Parses string into groups based on commandFormat.
     * @throws ModHappyException if the provided string does not match the pattern
     */
    public HashMap<String, String> parseString(String userInput) throws ModHappyException {
        final Pattern commandPattern = Pattern.compile(commandFormat);
        final Matcher matcher = commandPattern.matcher(userInput.trim());
        if (!matcher.matches()) {
            determineError();
        }
        for (Object groupName : groupNames) {
            try {
                parsedCommand.put(groupName.toString(), matcher.group(groupName.toString()).trim());
            } catch (Exception e) {
                parsedCommand.put(groupName.toString(), null);
            }
        }
        checkForInvalidStrings();
        return parsedCommand;
    }

    /**
     * Checks for strings that are parsed into groups based on commandFormat, but are essentially invalid.
     */
    private void checkForInvalidStrings() throws InvalidFlagException,
            InvalidModuleGradeException, InvalidTagOperationException {
        checksForInvalidModFlag();
        checksForInvalidTaskName();
        checksForInvalidTaskDescriptionFlag();
        checksForInvalidModDescriptionFlag();
        checksForInvalidTimeFlag();
        checksForInvalidTagCommand();
        checksForInvalidModuleGrade();
    }


    private void checksForInvalidModuleGrade() throws InvalidModuleGradeException {
        if (groupNames.contains(INVALID_MODULE_GRADE)) {
            String invalidInput = parsedCommand.get(INVALID_MODULE_GRADE);
            if (!Objects.isNull(invalidInput) && !invalidInput.isBlank()) {
                throw new InvalidModuleGradeException(invalidInput);
            }
        }
    }

    private void checksForInvalidTagCommand() throws InvalidTagOperationException {
        if (groupNames.contains(INVALID_TAG_COMMAND)) {
            String invalidInput = parsedCommand.get(INVALID_TAG_COMMAND);
            if (!Objects.isNull(invalidInput) && !invalidInput.isBlank()) {
                throw new InvalidTagOperationException(invalidInput);
            }
        }
    }

    private void checksForInvalidTimeFlag() throws InvalidFlagException {
        if (groupNames.contains(INVALID_TIME_FLAG)) {
            String invalidInput = parsedCommand.get(INVALID_TIME_FLAG);
            if (!Objects.isNull(invalidInput) && !invalidInput.isBlank()) {
                throw new InvalidFlagException(invalidInput);
            }
        }
    }

    private void checksForInvalidModDescriptionFlag() throws InvalidFlagException {
        if (groupNames.contains(INVALID_MOD_DES_FLAG)) {
            String invalidInput = parsedCommand.get(INVALID_MOD_DES_FLAG);
            if (!Objects.isNull(invalidInput) && !invalidInput.isBlank()) {
                throw new InvalidFlagException(invalidInput);
            }
        }
    }

    private void checksForInvalidTaskDescriptionFlag() throws InvalidFlagException {
        if (groupNames.contains(INVALID_TASK_DES_FLAG)) {
            String invalidInput = parsedCommand.get(INVALID_TASK_DES_FLAG);
            if (!Objects.isNull(invalidInput) && !invalidInput.isBlank()) {
                throw new InvalidFlagException(invalidInput);
            }
        }
    }

    private void checksForInvalidTaskName() throws InvalidFlagException {
        if (groupNames.contains(INVALID_TASK_NAME_FLAG)) {
            String invalidInput = parsedCommand.get(INVALID_TASK_NAME_FLAG);
            if (!Objects.isNull(invalidInput) && !invalidInput.isBlank()) {
                throw new InvalidFlagException(invalidInput);
            }
        }
    }

    private void checksForInvalidModFlag() throws InvalidFlagException {
        if (groupNames.contains(INVALID_MOD_FLAG)) {
            String invalidInput = parsedCommand.get(INVALID_MOD_FLAG);
            if (!Objects.isNull(invalidInput) && !invalidInput.isBlank()) {
                throw new InvalidFlagException(invalidInput);
            }
        }
    }


    protected void checksForExcessArg() throws ExcessArgumentException {
        if (groupNames.contains(INVALID)) {
            String invalidInput = parsedCommand.get(INVALID);
            if (!Objects.isNull(invalidInput) && !invalidInput.isBlank()) {
                throw new ExcessArgumentException(invalidInput);
            }
        }
    }

    /**
     * Parses the task index from a string to an integer form.
     * It will also check if the index is non-negative, throwing an exception if it is not.
     * @param taskNumberString the string representation of the task number
     * @return the zero-based index integer of the task number string
     * @throws InvalidNumberException if the task index is less than 0 or if the string cannot be parsed into an integer
     */
    protected int parseIndex(String taskNumberString) throws InvalidNumberException {
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
}