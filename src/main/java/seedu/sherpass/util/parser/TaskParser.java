package seedu.sherpass.util.parser;

import seedu.sherpass.command.AddCommand;
import seedu.sherpass.command.Command;
import seedu.sherpass.command.DeleteCommand;
import seedu.sherpass.command.DeleteRecurringCommand;
import seedu.sherpass.command.EditCommand;
import seedu.sherpass.command.EditRecurringCommand;
import seedu.sherpass.command.HelpCommand;
import seedu.sherpass.command.MarkCommand;
import seedu.sherpass.command.ShowCommand;
import seedu.sherpass.command.UnmarkCommand;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.WrongEditInfoFormatException;

import seedu.sherpass.task.TaskList;

import seedu.sherpass.util.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static seedu.sherpass.constant.CommandParameter.BY_DATE_DELIMITER;
import static seedu.sherpass.constant.CommandParameter.DO_DATE_DELIMITER;
import static seedu.sherpass.constant.CommandParameter.END_TIME_DELIMITER;
import static seedu.sherpass.constant.CommandParameter.FREQUENCY_DELIMITER;
import static seedu.sherpass.constant.CommandParameter.START_TIME_DELIMITER;

import static seedu.sherpass.constant.DateAndTimeFormat.inputTimeIndependentFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithoutTimeFormat;

import static seedu.sherpass.constant.Index.SHOW_OPTION_INDEX;


import static seedu.sherpass.constant.Index.EXPECTED_EDITRECURRING_ARG_LENGTH;
import static seedu.sherpass.constant.Index.INVALID_INDEX;
import static seedu.sherpass.constant.Index.SLASH_OFFSET;
import static seedu.sherpass.constant.Index.SPLIT_FIRST_PART_INDEX;
import static seedu.sherpass.constant.Index.SPLIT_SECOND_PART_INDEX;
import static seedu.sherpass.constant.Index.SPLIT_TWO_PART_LIMIT;
import static seedu.sherpass.constant.Index.START_OF_STRING;
import static seedu.sherpass.constant.Index.WHITESPACE_OFFSET;
import static seedu.sherpass.constant.Index.ZERO_INDEX_OFFSET;

import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.ERROR_EMPTY_ADD_COMMANDS_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_EMPTY_DESCRIPTION_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_DATETIME_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_DELETE_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_FREQUENCY_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_MARKING_INDEX_MESSAGE;
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
        if (!argument.contains(parameter) && !parameter.equals(FREQUENCY_DELIMITER)
                && !parameter.equals(BY_DATE_DELIMITER)) {
            throw new InvalidInputException(ERROR_EMPTY_ADD_COMMANDS_MESSAGE);
        }
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

    public static String parseDescription(String fullArgument) throws InvalidInputException {
        String taskDescription = " ";
        if (fullArgument.contains("/") && fullArgument.indexOf("/") > START_OF_STRING) {
            taskDescription = fullArgument.substring(START_OF_STRING, fullArgument.indexOf('/') - SLASH_OFFSET);
        }
        if (!fullArgument.contains("/")) {
            throw new InvalidInputException(ERROR_EMPTY_ADD_COMMANDS_MESSAGE);
        }
        if (taskDescription.isBlank()) {
            throw new InvalidInputException(ERROR_EMPTY_DESCRIPTION_MESSAGE);
        }
        return taskDescription;
    }

    public static LocalDateTime prepareTaskDate(String taskDate, String time,
                                                DateTimeFormatter dateTimeFormat) throws InvalidInputException {
        try {
            return LocalDateTime.parse(taskDate + time, dateTimeFormat);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(ERROR_INVALID_DATETIME_MESSAGE);
        }
    }


    public static boolean isValidFreq(Frequency frequency) {
        return frequency != null;
    }

    private static void prepareTaskContent(AddCommand newCommand, String argument)
            throws IllegalArgumentException, InvalidInputException, ArrayIndexOutOfBoundsException {
        String taskDescription = parseDescription(argument);
        String doOnDateString = parseArgument(DO_DATE_DELIMITER, argument);
        String startTimeString = parseArgument(START_TIME_DELIMITER, argument);
        String endTimeString = parseArgument(END_TIME_DELIMITER, argument);
        newCommand.setTaskContent(taskDescription,
                prepareTaskDate(doOnDateString + WHITESPACE, startTimeString, inputWithTimeFormat),
                prepareTaskDate(doOnDateString + WHITESPACE, endTimeString, inputWithTimeFormat));
        Frequency repeats = Frequency.valueOf(parseArgument(FREQUENCY_DELIMITER, argument).toUpperCase());
        newCommand.setFrequency(repeats);
    }

    private static void prepareByDate(AddCommand newCommand, String argument, Ui ui) {
        try {
            if (argument.contains(BY_DATE_DELIMITER)) {
                newCommand.setTaskByDate(prepareTaskDate(
                        parseArgument(BY_DATE_DELIMITER, argument), EMPTY_STRING, inputTimeIndependentFormat));
                return;
            }
            newCommand.setTaskByDate(null);
        } catch (InvalidInputException e) {
            ui.showToUser(e.getMessage());
        }
        newCommand.setFrequency(null);
    }

    public static Command prepareAdd(String argument, Ui ui) {
        if (argument.isBlank()) {
            return new HelpCommand(AddCommand.COMMAND_WORD);
        }
        AddCommand newCommand = new AddCommand();
        try {
            prepareTaskContent(newCommand, argument);
        } catch (IllegalArgumentException exception) {
            if (argument.contains(FREQUENCY_DELIMITER)) {
                ui.showToUser(ERROR_INVALID_FREQUENCY_MESSAGE);
                ui.showLine();
                return new HelpCommand(AddCommand.COMMAND_WORD);
            }
            prepareByDate(newCommand, argument, ui);
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

        if (splitIndexAndOthers.length < EXPECTED_EDITRECURRING_ARG_LENGTH) {
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
                    prepareTaskDate(doOnDateString + WHITESPACE, startTimeString, inputWithTimeFormat));
            newCommand.setDoOnEndDateTime(
                    prepareTaskDate(doOnDateString + WHITESPACE, endTimeString, inputWithTimeFormat));
        } catch (NumberFormatException notNumberException) {
            newCommand.setIndex(INVALID_INDEX);
        } catch (InvalidInputException e) {
            return new HelpCommand(EditCommand.COMMAND_WORD);
        }

        return newCommand;
    }

    protected static Command prepareDeleteRecurring(String argument) {
        if (argument.isBlank()) {
            return new HelpCommand(DeleteCommand.COMMAND_WORD);
        }
        String argumentWithoutRepeat = removeRecurringDelimiter(argument);
        DeleteRecurringCommand newCommand = new DeleteRecurringCommand();
        try {
            newCommand.setIndex(Integer.parseInt(argumentWithoutRepeat) - ZERO_INDEX_OFFSET);
        } catch (NumberFormatException exception) {
            newCommand.setIndex(INVALID_INDEX);
        }
        return newCommand;
    }


    public static Command prepareEdit(String[] splitInput) {

        String[] fullEditInfo = splitInput[1].trim().split(" ", 2);

        // 7 possibilities of editing, incorrect format of inputs are rejected
        try {

            int taskNumberToEdit = Integer.parseInt(fullEditInfo[0]);
            checkCorrectEditInfoFormat(fullEditInfo[1]);
            return handleEdit(taskNumberToEdit, fullEditInfo[1]);

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Please key in a valid task number");
        } catch (InvalidInputException e) {
            System.out.println("Invalid date");
        } catch (WrongEditInfoFormatException e) {
            System.out.println("Please use the correct order of keywords:\n"
                    + "<task_description> /by <task_due_date> /do_on <date_to_work_on_task>\n\n"
                    + "You only need to input the parts you want to edit.\n"
                    + "e.g. edit 1 /do_on 2022/02/12\n"
                    + "(The task_description and task_due_date is left out here)");
        }

        return null;
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

    public static Command prepareShow(String[] splitInput) {
        try {
            String selection = splitInput[SHOW_OPTION_INDEX].trim();
            return parseShowCommandOptions(selection.toLowerCase());
        } catch (ArrayIndexOutOfBoundsException | InvalidInputException e) {
            System.out.println(ERROR_INVALID_INPUT_MESSAGE);
        }
        return null;
    }

    private static Command parseShowCommandOptions(String selection) throws InvalidInputException {
        if (selection.isBlank()) {
            throw new InvalidInputException();
        }
        try {
            LocalDate dayInput = LocalDate.parse(selection, inputWithoutTimeFormat);
            return new ShowCommand(dayInput, null);
        } catch (DateTimeParseException e) {
            return new ShowCommand(null, selection);
        }
    }

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
        /*
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

 */
        return null;
    }

    private static LocalDateTime getParsedDateToEdit(String fullEditInfo, String keyword) throws InvalidInputException {
        /*
        if (fullEditInfo.contains(keyword)) {

            int offsetForKeyword = keyword.length() + 1;
            int offsetForSubstring = fullEditInfo.indexOf(keyword) + offsetForKeyword;

            // gets the substring (of fullEditInfo) after the keyword (, which is either "/by" or "/do_on")
            // splits the substring and obtains the first word (which should be the date of format yyyy/MM/dd)
            String dateToEdit = fullEditInfo.substring(offsetForSubstring).split(" ")[0].trim();

            return prepareTaskDate(dateToEdit, "00:00");
        }

         */
        return null;
    }


}
