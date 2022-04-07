package seedu.sherpass.util.parser;

import seedu.sherpass.command.AddCommand;
import seedu.sherpass.command.Command;
import seedu.sherpass.command.DeleteCommand;
import seedu.sherpass.command.EditCommand;
import seedu.sherpass.command.HelpCommand;
import seedu.sherpass.command.MarkCommand;
import seedu.sherpass.command.UnmarkCommand;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;

import seedu.sherpass.util.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import static seedu.sherpass.constant.CommandParameter.BY_DATE_DELIMITER;
import static seedu.sherpass.constant.CommandParameter.BY_TIME_DELIMITER;
import static seedu.sherpass.constant.CommandParameter.DO_DATE_DELIMITER;
import static seedu.sherpass.constant.CommandParameter.END_TIME_DELIMITER;
import static seedu.sherpass.constant.CommandParameter.FREQUENCY_DELIMITER;
import static seedu.sherpass.constant.CommandParameter.START_TIME_DELIMITER;
import static seedu.sherpass.constant.DateAndTimeFormat.inputDateOnlyFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.timeOnlyFormat;
import static seedu.sherpass.constant.Index.EDIT_INDEX;
import static seedu.sherpass.constant.Index.EDIT_TASK_CONTENT;
import static seedu.sherpass.constant.Index.INDEX_OFFSET;
import static seedu.sherpass.constant.Index.SPLIT_FIRST_PART_INDEX;
import static seedu.sherpass.constant.Index.SPLIT_TWO_PART_LIMIT;
import static seedu.sherpass.constant.Index.START_OF_STRING;
import static seedu.sherpass.constant.Index.WHITESPACE_OFFSET;
import static seedu.sherpass.constant.Index.ZERO_INDEX_OFFSET;
import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.ERROR_BY_DATE_TIME_MISSING_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_EMPTY_ADD_COMMANDS_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_EMPTY_DESCRIPTION_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_DATETIME_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_DELETE_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_FREQUENCY_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_MARKING_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_NO_VALUE_FOR_PARAMETER_MESSAGE;
import static seedu.sherpass.constant.Message.WHITESPACE;

public class TaskParser {

    /**
     * Returns the value for a specific parameter.
     *
     * @param parameter The parameter to retrieve values from (e.g /start, /do)
     * @param argument  The full argument given by the user
     * @return The value given by the user for a particular parameter
     */
    public static String parseArgument(String parameter, String argument) throws InvalidInputException {
        if (!argument.contains(parameter)) {
            return EMPTY_STRING;
        }
        try {
            int indexAfterParameter = argument.indexOf(parameter) + parameter.length() + WHITESPACE_OFFSET;
            String stringAfterParameter = argument.substring(indexAfterParameter);
            String[] splitArguments = stringAfterParameter.split(WHITESPACE, SPLIT_TWO_PART_LIMIT);
            return splitArguments[SPLIT_FIRST_PART_INDEX];
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidInputException(ERROR_NO_VALUE_FOR_PARAMETER_MESSAGE);
        }
    }

    /**
     * Returns the index of a Command nearest to the task description.
     * Command is in the format of "/by" "/do" "/start" "/end".
     * Checks if any of these commands are present.
     *
     * @param fullArgument Full argument from user input.
     * @return Index > 0 if command exists. -1 otherwise.
     */
    private static int findCommandDelimiterIndex(String fullArgument) {
        int byDelimiterIndex = fullArgument.indexOf(BY_DATE_DELIMITER);
        int doDelimiterIndex = fullArgument.indexOf(DO_DATE_DELIMITER);
        int startDelimiterIndex = fullArgument.indexOf(START_TIME_DELIMITER);
        int endDelimiterIndex = fullArgument.indexOf(END_TIME_DELIMITER);
        int freqDelimiterIndex = fullArgument.indexOf(FREQUENCY_DELIMITER);
        int[] delimiterIndexes = {byDelimiterIndex, doDelimiterIndex, startDelimiterIndex,
            endDelimiterIndex, freqDelimiterIndex};
        int min = Integer.MAX_VALUE;
        for (int delimiterIndex : delimiterIndexes) {
            if (delimiterIndex < min && delimiterIndex >= 0) {
                min = delimiterIndex;
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    /**
     * Returns the parsed task description.
     *
     * @param fullArgument The entire user input, excluding the command.
     */
    public static String parseDescription(String fullArgument) {
        String taskDescription;
        int commandDelimiterIndex = findCommandDelimiterIndex(fullArgument);
        try {
            taskDescription = fullArgument.substring(START_OF_STRING, commandDelimiterIndex);
        } catch (IndexOutOfBoundsException e) {
            taskDescription = (commandDelimiterIndex == START_OF_STRING) ? EMPTY_STRING : fullArgument;
        }
        return taskDescription.trim();
    }

    public static LocalDateTime prepareTaskDateTime(String taskDate, String time, DateTimeFormatter dateTimeFormat)
            throws InvalidInputException {
        try {
            return LocalDateTime.parse(taskDate + time, dateTimeFormat);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(ERROR_INVALID_DATETIME_MESSAGE);
        }
    }

    private static LocalDate prepareTaskDate(String taskDate) throws InvalidInputException {
        try {
            return LocalDate.parse(taskDate,
                    inputDateOnlyFormat.withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(ERROR_INVALID_DATETIME_MESSAGE);
        }
    }

    private static LocalTime prepareTaskTime(String taskTime) throws InvalidInputException {
        try {
            return LocalTime.parse(taskTime, timeOnlyFormat);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(ERROR_INVALID_DATETIME_MESSAGE);
        }
    }

    /**
     * Returns an AddCommand object with the task description and do datetime given in the user input.
     *
     * @param argument The argument given by the user
     * @return AddCommand object
     * @throws InvalidInputException          If the argument is blank or the task description is empty
     * @throws ArrayIndexOutOfBoundsException If the parameter was specified but no value was given
     */
    private static AddCommand prepareAddTaskContent(String argument) throws InvalidInputException,
            ArrayIndexOutOfBoundsException {
        if (argument.isBlank()) {
            throw new InvalidInputException(ERROR_EMPTY_ADD_COMMANDS_MESSAGE);
        }
        String taskDescription = parseDescription(argument);
        if (taskDescription.isBlank()) {
            throw new InvalidInputException(ERROR_EMPTY_DESCRIPTION_MESSAGE);
        }
        String doOnDateString = parseArgument(DO_DATE_DELIMITER, argument);
        String startTimeString = parseArgument(START_TIME_DELIMITER, argument);
        String endTimeString = parseArgument(END_TIME_DELIMITER, argument);
        return new AddCommand(taskDescription,
                prepareTaskDateTime(doOnDateString + WHITESPACE, startTimeString, inputWithTimeFormat),
                prepareTaskDateTime(doOnDateString + WHITESPACE, endTimeString, inputWithTimeFormat));
    }

    /**
     * Parses the by date and time of the task given in the user input.
     *
     * @param newCommand The newCommand object that is being prepared
     * @param argument   The user input
     * @throws InvalidInputException If the date and time in the user input is invalid
     */
    private static void prepareAddByDate(AddCommand newCommand, String argument) throws InvalidInputException {
        if (argument.contains(BY_DATE_DELIMITER) || argument.contains(BY_TIME_DELIMITER)) {
            String byDateString = parseArgument(BY_DATE_DELIMITER, argument);
            String byTimeString = parseArgument(BY_TIME_DELIMITER, argument);
            if (byTimeString.isBlank() || byDateString.isBlank()) {
                throw new InvalidInputException(ERROR_BY_DATE_TIME_MISSING_MESSAGE);
            }
            newCommand.setTaskByDate(prepareTaskDateTime(byDateString + WHITESPACE,
                    byTimeString, inputWithTimeFormat));
        } else {
            newCommand.setTaskByDate(null);
        }
    }

    /**
     * Parses the frequency of recurrence of the task given in the user input.
     *
     * @param newCommand The newCommand object that is being prepared
     * @param argument   The user input
     * @throws IllegalArgumentException If the frequency specified is invalid
     */
    private static void prepareAddFrequency(AddCommand newCommand, String argument) throws IllegalArgumentException,
            InvalidInputException {
        newCommand.setFrequency(Frequency.valueOf(parseArgument(FREQUENCY_DELIMITER, argument).toUpperCase()));
    }

    /**
     * Returns an AddCommand containing parsed user inputs ready for adding a task.
     * Parse and prepare user inputs for adding of a task.
     *
     * @param argument The argument from the user input, excluding the input command "add".
     * @param ui       User interface.
     * @return AddCommand containing parsed user inputs in proper format.
     */
    public static Command prepareAdd(String argument, Ui ui) {
        try {
            AddCommand newCommand = prepareAddTaskContent(argument);
            if (argument.contains(FREQUENCY_DELIMITER)) {
                prepareAddFrequency(newCommand, argument);
            } else {
                newCommand.setFrequency(Frequency.SINGLE);
            }
            prepareAddByDate(newCommand, argument);
            return newCommand;
        } catch (IllegalArgumentException exception) {
            ui.showError(ERROR_INVALID_FREQUENCY_MESSAGE);
            ui.showLine();
            return new HelpCommand(AddCommand.COMMAND_WORD);
        } catch (InvalidInputException e) {
            ui.showError(e.getMessage());
            ui.showLine();
            return new HelpCommand(AddCommand.COMMAND_WORD);
        }
    }


    /**
     * Returns a MarkCommand/UnmarkCommand containing parsed user inputs in proper format.
     *
     * @param argument    Argument parsed from user input. Excludes the input command "mark" / "unmark".
     * @param commandWord The input command, i.e. "mark" or "unmark".
     * @return A MarkCommand/UnmarkCommand depending on user input.
     */
    public static Command prepareMarkOrUnmark(String argument, String commandWord, Ui ui) {
        try {
            int markIndex = Integer.parseInt(argument) - 1;
            if (commandWord.equals(MarkCommand.COMMAND_WORD)) {
                return new MarkCommand(markIndex);
            }
            return new UnmarkCommand(markIndex);
        } catch (NumberFormatException e) {
            ui.showError(ERROR_INVALID_MARKING_INDEX_MESSAGE);
            ui.showLine();
            if (commandWord.equals(MarkCommand.COMMAND_WORD)) {
                return new HelpCommand(MarkCommand.COMMAND_WORD);
            }
            return new HelpCommand(UnmarkCommand.COMMAND_WORD);
        }
    }

    private static EditCommand prepareEditTaskContent(String argument) throws InvalidInputException {
        String[] splitInput = argument.split(WHITESPACE, SPLIT_TWO_PART_LIMIT);
        int editIndex = Integer.parseInt(splitInput[EDIT_INDEX]) - INDEX_OFFSET;
        String editTaskContent = EMPTY_STRING;
        if (splitInput.length > 1) {
            editTaskContent = splitInput[EDIT_TASK_CONTENT];
        }
        String taskDescription = parseDescription(editTaskContent);
        String doOnDateString = parseArgument(DO_DATE_DELIMITER, editTaskContent);
        String startTimeString = parseArgument(START_TIME_DELIMITER, editTaskContent);
        String endTimeString = parseArgument(END_TIME_DELIMITER, editTaskContent);
        LocalDate doOnDate = (doOnDateString.isBlank()) ? null : prepareTaskDate(doOnDateString);
        LocalTime startTime = (startTimeString.isBlank()) ? null : prepareTaskTime(startTimeString);
        LocalTime endTime = (endTimeString.isBlank()) ? null : prepareTaskTime(endTimeString);
        return new EditCommand(editIndex, taskDescription, doOnDate, startTime, endTime);
    }

    private static void prepareEditByDate(EditCommand newCommand, String argument) throws InvalidInputException {
        if (argument.contains(BY_DATE_DELIMITER) || argument.contains(BY_TIME_DELIMITER)) {
            String byDateString = parseArgument(BY_DATE_DELIMITER, argument);
            String byTimeString = parseArgument(BY_TIME_DELIMITER, argument);
            if (byTimeString.isBlank() || byDateString.isBlank()) {
                throw new InvalidInputException(ERROR_BY_DATE_TIME_MISSING_MESSAGE);
            }
            newCommand.setByDate(prepareTaskDateTime(byDateString + WHITESPACE,
                    byTimeString, inputWithTimeFormat));
        } else {
            newCommand.setByDate(null);
        }
    }

    /**
     * Returns a EditCommand containing parsed user inputs ready for
     * editing the content of a task.
     *
     * @param argument Argument from the user input. Excludes the input command "edit".
     * @param ui       User Interface.
     * @return EditCommand with parsed inputs in proper format
     */
    public static Command prepareEdit(String argument, Ui ui) {
        if (argument.isBlank()) {
            return new HelpCommand(EditCommand.COMMAND_WORD);
        }
        try {
            EditCommand newCommand = prepareEditTaskContent(argument);
            boolean isRepeating = argument.contains(FREQUENCY_DELIMITER);
            newCommand.setRepeating(isRepeating);
            prepareEditByDate(newCommand, argument);
            newCommand.checkValidCommand();
            return newCommand;
        } catch (InvalidInputException e) {
            ui.showError(e.getMessage());
            ui.showLine();
            return new HelpCommand(EditCommand.COMMAND_WORD);
        } catch (NumberFormatException exception) {
            ui.showError(ERROR_INVALID_INDEX_MESSAGE);
            ui.showLine();
            return new HelpCommand(EditCommand.COMMAND_WORD);
        }
    }

    protected static Command prepareDelete(String argument, Ui ui) {
        try {
            boolean isRepeat = false;
            if (argument.contains(FREQUENCY_DELIMITER)) {
                isRepeat = true;
                argument = argument.replace(FREQUENCY_DELIMITER, EMPTY_STRING).trim();
            }
            int deleteIndex = Integer.parseInt(argument) - ZERO_INDEX_OFFSET;
            return new DeleteCommand(deleteIndex, isRepeat);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.showError(ERROR_INVALID_DELETE_INDEX_MESSAGE);
            ui.showLine();
            return new HelpCommand(DeleteCommand.COMMAND_WORD);
        }
    }
}
