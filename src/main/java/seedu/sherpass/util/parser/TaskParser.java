package seedu.sherpass.util.parser;

import seedu.sherpass.command.AddCommand;
import seedu.sherpass.command.Command;
import seedu.sherpass.command.DeleteCommand;
import seedu.sherpass.command.EditCommand;
import seedu.sherpass.command.HelpCommand;
import seedu.sherpass.command.MarkCommand;
import seedu.sherpass.command.ShowCommand;
import seedu.sherpass.command.UnmarkCommand;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;

import seedu.sherpass.task.TaskList;

import seedu.sherpass.util.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static seedu.sherpass.constant.CommandParameter.BY_DATE_DELIMITER;
import static seedu.sherpass.constant.CommandParameter.DO_DATE_DELIMITER;
import static seedu.sherpass.constant.CommandParameter.END_TIME_DELIMITER;
import static seedu.sherpass.constant.CommandParameter.FREQUENCY_DELIMITER;
import static seedu.sherpass.constant.CommandParameter.START_TIME_DELIMITER;

import static seedu.sherpass.constant.DateAndTimeFormat.inputDateOnlyFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.inputTimeIndependentFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithoutTimeFormat;

import static seedu.sherpass.constant.DateAndTimeFormat.timeOnlyFormat;
import static seedu.sherpass.constant.Index.EDIT_INDEX;
import static seedu.sherpass.constant.Index.EDIT_TASK_CONTENT;
import static seedu.sherpass.constant.Index.INDEX_OFFSET;
import static seedu.sherpass.constant.Index.SHOW_OPTION_INDEX;


import static seedu.sherpass.constant.Index.SLASH_OFFSET;
import static seedu.sherpass.constant.Index.SPLIT_FIRST_PART_INDEX;
import static seedu.sherpass.constant.Index.SPLIT_TWO_PART_LIMIT;
import static seedu.sherpass.constant.Index.START_OF_STRING;
import static seedu.sherpass.constant.Index.WHITESPACE_OFFSET;

import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.ERROR_EMPTY_ADD_COMMANDS_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_EMPTY_DESCRIPTION_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_DATETIME_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_DELETE_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_FREQUENCY_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_MARKING_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_MULTIPLE_ARGS_MESSAGE;
import static seedu.sherpass.constant.Message.HELP_MESSAGE_SPECIFIC_COMMAND;
import static seedu.sherpass.constant.Message.WHITESPACE;


public class TaskParser {


    /**
     * Returns the value for a specific parameter.
     *
     * @param parameter The parameter to retrieve values from (e.g /by, /do)
     * @param argument  The full argument given by the user
     * @return The value given by the user for a particular parameter
     */
    public static String parseArgument(String parameter, String argument)
            throws InvalidInputException {
        if (!argument.contains(parameter)) {
            return EMPTY_STRING;
        }
        int indexAfterParameter = argument.indexOf(parameter) + parameter.length() + WHITESPACE_OFFSET;
        String stringAfterParameter = argument.substring(indexAfterParameter);
        String[] splitArguments = stringAfterParameter.split(WHITESPACE, SPLIT_TWO_PART_LIMIT);
        if (parameter.equals(FREQUENCY_DELIMITER) && argument.contains(parameter)
                && splitArguments[SPLIT_FIRST_PART_INDEX].isBlank()) {
            throw new InvalidInputException(ERROR_INVALID_FREQUENCY_MESSAGE);
        }
        return splitArguments[SPLIT_FIRST_PART_INDEX];
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

    public static LocalDateTime prepareTaskDateTime(String taskDate, String time,
                                                    DateTimeFormatter dateTimeFormat) throws InvalidInputException {
        try {
            return LocalDateTime.parse(taskDate + time, dateTimeFormat);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(ERROR_INVALID_DATETIME_MESSAGE);
        }
    }


    /**
     * Returns a boolean value checking if the frequency attribute
     * of a task is valid or not. Verifies if a task is recurring or
     * non-recurring.
     *
     * @param frequency The frequency of a task, i.e. daily, weekly, monthly
     * @return Returns true if frequency is of type daily, weekly, or monthly,
     *         and is also not null. False otherwise.
     */
    public static boolean isValidFreq(Frequency frequency) {
        return (frequency != null)
                && (frequency.equals(Frequency.DAILY)
                        || frequency.equals(Frequency.WEEKLY)
                        || frequency.equals(Frequency.MONTHLY));
    }

    private static void prepareAddTaskContent(AddCommand newCommand, String argument)
            throws IllegalArgumentException, InvalidInputException, ArrayIndexOutOfBoundsException {
        if (argument.isBlank()) {
            throw new InvalidInputException(ERROR_EMPTY_ADD_COMMANDS_MESSAGE);
        }
        if (argument.contains(FREQUENCY_DELIMITER) && argument.contains(BY_DATE_DELIMITER)) {
            throw new InvalidInputException(ERROR_MULTIPLE_ARGS_MESSAGE);
        }
        String taskDescription = parseDescription(argument);
        if (taskDescription.isBlank()) {
            throw new InvalidInputException(ERROR_EMPTY_DESCRIPTION_MESSAGE);
        }
        String doOnDateString = parseArgument(DO_DATE_DELIMITER, argument);
        String startTimeString = parseArgument(START_TIME_DELIMITER, argument);
        String endTimeString = parseArgument(END_TIME_DELIMITER, argument);
        if (doOnDateString.isBlank() || startTimeString.isBlank() || endTimeString.isBlank()) {
            throw new InvalidInputException(ERROR_EMPTY_ADD_COMMANDS_MESSAGE);
        }
        newCommand.setTaskContent(taskDescription,
                prepareTaskDateTime(doOnDateString + WHITESPACE, startTimeString, inputWithTimeFormat),
                prepareTaskDateTime(doOnDateString + WHITESPACE, endTimeString, inputWithTimeFormat));
        Frequency repeats = Frequency.valueOf(parseArgument(FREQUENCY_DELIMITER, argument).toUpperCase());
        newCommand.setFrequency(repeats);
    }

    private static void prepareAddCommandByDate(AddCommand newCommand, String argument, Ui ui) {
        try {
            if (argument.contains(BY_DATE_DELIMITER)) {
                newCommand.setTaskByDate(prepareTaskDateTime(
                        parseArgument(BY_DATE_DELIMITER, argument), EMPTY_STRING, inputTimeIndependentFormat));
                return;
            }
            newCommand.setTaskByDate(null);
        } catch (InvalidInputException e) {
            ui.showToUser(e.getMessage());
        }
        newCommand.setFrequency(null);
    }

    /**
     * Returns an AddCommand containing parsed user inputs ready for adding a task.
     * Parse and prepare user inputs for adding of a task.
     *
     *
     * @param argument The argument from the user input, excluding the input command "add".
     * @param ui User interface.
     * @return AddCommand containing parsed user inputs in proper format.
     */
    public static Command prepareAdd(String argument, Ui ui) {
        AddCommand newCommand = new AddCommand();
        try {
            prepareAddTaskContent(newCommand, argument);
        } catch (IllegalArgumentException exception) {
            if (argument.contains(FREQUENCY_DELIMITER)) {
                ui.showToUser(ERROR_INVALID_FREQUENCY_MESSAGE);
                ui.showLine();
                return new HelpCommand(AddCommand.COMMAND_WORD);
            }
            prepareAddCommandByDate(newCommand, argument, ui);
        } catch (InvalidInputException e) {
            ui.showToUser(e.getMessage());
            ui.showLine();
            return new HelpCommand(AddCommand.COMMAND_WORD);
        } catch (IndexOutOfBoundsException e) {
            ui.showToUser(ERROR_EMPTY_ADD_COMMANDS_MESSAGE);
            ui.showLine();
            return new HelpCommand(AddCommand.COMMAND_WORD);
        }
        return newCommand;
    }


    /**
     * Returns a MarkCommand/UnmarkCommand containing parsed user inputs in proper format.
     *
     * @param argument Argument parsed from user input. Excludes the input command "mark" / "unmark".
     * @param commandWord The input command, i.e. "mark" or "unmark".
     * @param taskList Array representation of the tasks.
     * @return A MarkCommand/UnmarkCommand depending on user input.
     */
    public static Command prepareMarkOrUnmark(String argument, String commandWord, TaskList taskList) {
        try {
            int markIndex = Integer.parseInt(argument) - 1;
            if (commandWord.equals(MarkCommand.COMMAND_WORD)) {
                return new MarkCommand(markIndex, taskList);
            }
            return new UnmarkCommand(markIndex, taskList);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(ERROR_INVALID_MARKING_INDEX_MESSAGE);
        }
        return null;
    }

    /*
    public static String removeRecurringDelimiter(String argument) {
        if (!argument.contains(FREQUENCY_DELIMITER)) {
            return argument;
        }
        String[] splitStrings = argument.split(FREQUENCY_DELIMITER);
        return String.join(WHITESPACE, splitStrings).trim();
    }


    public static Command prepareEditRecurring(String argument) {
        if (argument.isBlank()) {
            return new HelpCommand(AddCommand.COMMAND_WORD);
        }
        String argumentWithoutRepeat = removeRecurringDelimiter(argument);
        String[] splitIndexAndOthers = argumentWithoutRepeat.split(WHITESPACE, SPLIT_TWO_PART_LIMIT);

        if (splitIndexAndOthers.length < EXPECTED_EDIT_RECURRING_ARG_LENGTH) {
            return new HelpCommand(EditCommand.COMMAND_WORD);
        }

        String indexString = splitIndexAndOthers[SPLIT_FIRST_PART_INDEX];
        String descAndDateString = splitIndexAndOthers[SPLIT_SECOND_PART_INDEX];

        EditRecurringCommand newCommand = new EditRecurringCommand();
        try {
            newCommand.setTaskDescription(parseDescription(descAndDateString));
            String doOnDateString = parseArgument(DO_DATE_DELIMITER, descAndDateString);
            String startTimeString = parseArgument(START_TIME_DELIMITER, descAndDateString);
            String endTimeString = parseArgument(END_TIME_DELIMITER, descAndDateString);
            newCommand.setIndex(Integer.parseInt(indexString) - ZERO_INDEX_OFFSET);
            newCommand.setDoOnStartDateTime(
                    prepareTaskDateTime(doOnDateString + WHITESPACE, startTimeString, inputWithTimeFormat));
            newCommand.setDoOnEndDateTime(
                    prepareTaskDateTime(doOnDateString + WHITESPACE, endTimeString, inputWithTimeFormat));
        } catch (NumberFormatException notNumberException) {
            newCommand.setIndex(INVALID_INDEX);
        } catch (InvalidInputException e) {
            return new HelpCommand(EditCommand.COMMAND_WORD);
        }

        return newCommand;
    }

  */

    private static LocalDate prepareTaskDate(String taskDate) throws InvalidInputException {
        try {
            return LocalDate.parse(taskDate, inputDateOnlyFormat);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException();
        }
    }

    private static LocalTime prepareTaskTime(String taskTime) throws InvalidInputException {
        try {
            return LocalTime.parse(taskTime, timeOnlyFormat);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException();
        }
    }

    private static void prepareEditTaskContent(EditCommand newCommand, TaskList taskList, String fullArgument)
            throws InvalidInputException {
        String[] splitInput = fullArgument.split(WHITESPACE, SPLIT_TWO_PART_LIMIT);
        newCommand.setEditIndex(Integer.parseInt(splitInput[EDIT_INDEX]) - INDEX_OFFSET, taskList);
        String taskDescription = parseDescription(splitInput[EDIT_TASK_CONTENT]);
        String doOnDateString = parseArgument(DO_DATE_DELIMITER, splitInput[EDIT_TASK_CONTENT]);
        String startTimeString = parseArgument(START_TIME_DELIMITER, splitInput[EDIT_TASK_CONTENT]);
        String endTimeString = parseArgument(END_TIME_DELIMITER, splitInput[EDIT_TASK_CONTENT]);
        String byDateString = parseArgument(BY_DATE_DELIMITER, splitInput[EDIT_TASK_CONTENT]);
        LocalDateTime byDate = (byDateString.isBlank()) ? null
                : prepareTaskDateTime(byDateString, EMPTY_STRING, inputTimeIndependentFormat);
        LocalDate doOnDate = (doOnDateString.isBlank()) ? null : prepareTaskDate(doOnDateString);
        LocalTime startTime = (startTimeString.isBlank()) ? null : prepareTaskTime(startTimeString);
        LocalTime endTime = (endTimeString.isBlank()) ? null : prepareTaskTime(endTimeString);
        newCommand.setTaskContent(taskDescription, doOnDate, startTime, endTime, byDate);
    }

    /**
     * Returns a EditCommand containing parsed user inputs ready for
     * editing the content of a task.
     *
     * @param argument Argument from the user input. Excludes the input command "edit".
     * @param taskList Array representation of tasks.
     * @param ui User Interface.
     * @return EditCommand with parsed inputs in proper format
     */
    public static Command prepareEdit(String argument, TaskList taskList, Ui ui) {
        if (argument.isBlank()) {
            return new HelpCommand(EditCommand.COMMAND_WORD);
        }
        EditCommand newCommand = new EditCommand();
        try {
            prepareEditTaskContent(newCommand, taskList, argument);
        } catch (NumberFormatException | InvalidInputException | IndexOutOfBoundsException e) {
            ui.showToUser(e.getMessage() + "\nPlease follow the command format as shown below:");
            ui.showLine();
            return new HelpCommand(EditCommand.COMMAND_WORD);
        }
        return newCommand;
    }

    protected static Command prepareDelete(String argument, TaskList taskList, Ui ui) {
        try {
            int deleteIndex = Integer.parseInt(argument) - 1;
            return new DeleteCommand(deleteIndex, taskList);
        } catch (IndexOutOfBoundsException | InvalidInputException | NumberFormatException e) {
            ui.showToUser(ERROR_INVALID_DELETE_INDEX_MESSAGE + HELP_MESSAGE_SPECIFIC_COMMAND);
        }
        return null;
    }


    /*
    private static void checkCorrectEditInfoFormat(String fullEditInfo) throws WrongEditInfoFormatException {
        // tests to make sure the byDate is before the doOnDate
        if (fullEditInfo.contains(BY_DATE_DELIMITER) && fullEditInfo.contains(DO_DATE_DELIMITER)) {
            if (fullEditInfo.indexOf(BY_DATE_DELIMITER) > fullEditInfo.indexOf(DO_DATE_DELIMITER)) {
                throw new WrongEditInfoFormatException();
            }
        }
        // tests to make sure the task description is the first input if it is present
        String[] splitEditInfo = fullEditInfo.split(
                "/by \\d{4}/\\d{2}/\\d{2}|/do_on \\d{4}/\\d{2}/\\d{2}");
        if (splitEditInfo.length > 1) {
            throw new WrongEditInfoFormatException();
        }
    }

    private static Command handleEdit(int taskNumberToEdit, String fullEditInfo) throws InvalidInputException {

        String[] splitEditInfo = fullEditInfo.split(" ");
        String descriptionToEdit;
        LocalDate parsedByDateToEdit;
        LocalDateTime parsedDoOnDateToEdit;

        if (!splitEditInfo[0].trim().equals(BY_DATE_DELIMITER)
                && !(splitEditInfo[0].trim().equals(DO_DATE_DELIMITER))) {
            descriptionToEdit = splitEditInfo[0];
        } else {
            descriptionToEdit = EMPTY_STRING;
        }

        parsedByDateToEdit = getParsedDateToEdit(fullEditInfo, BY_DATE_DELIMITER);
        parsedDoOnDateToEdit = getParsedDateToEdit(fullEditInfo, DO_DATE_DELIMITER);

        return new EditCommand(taskNumberToEdit, descriptionToEdit, parsedByDateToEdit, parsedDoOnDateToEdit);


        return null;
    }

    private static LocalDateTime getParsedDateToEdit(String fullEditInfo, String keyword) throws InvalidInputException {

        if (fullEditInfo.contains(keyword)) {

            int offsetForKeyword = keyword.length() + 1;
            int offsetForSubstring = fullEditInfo.indexOf(keyword) + offsetForKeyword;

            // gets the substring (of fullEditInfo) after the keyword (, which is either "/by" or "/do_on")
            // splits the substring and obtains the first word (which should be the date of format yyyy/MM/dd)
            String dateToEdit = fullEditInfo.substring(offsetForSubstring).split(" ")[0].trim();

            return prepareTaskDate(dateToEdit, "00:00");
        }


        return null;
    }*/
}
