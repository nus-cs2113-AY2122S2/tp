package seedu.sherpass.util.parser;

import seedu.sherpass.command.AddCommand;
import seedu.sherpass.command.AddRecurringCommand;
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
import seedu.sherpass.exception.InputRepeatedException;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.WrongEditInfoFormatException;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.util.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static seedu.sherpass.constant.CommandParameters.BY_DATE_DELIMITER;
import static seedu.sherpass.constant.CommandParameters.DO_DATE_DELIMITER;
import static seedu.sherpass.constant.CommandParameters.END_TIME_DELIMITER;
import static seedu.sherpass.constant.CommandParameters.FREQUENCY_DELIMITER;
import static seedu.sherpass.constant.CommandParameters.START_TIME_DELIMITER;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithoutTimeFormat;

import static seedu.sherpass.constant.Index.MARK_INDEX;
import static seedu.sherpass.constant.Index.SHOW_OPTION_INDEX;
import static seedu.sherpass.constant.Index.TASK_CONTENT_INDEX;
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
import static seedu.sherpass.constant.Message.ERROR_INVALID_DELETE_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_MARKING_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.HELP_MESSAGE_SPECIFIC_COMMAND;
import static seedu.sherpass.constant.Message.WHITESPACE;

public class TaskParser {

    public static Command prepareMarkOrUnmark(String[] parsedInput, String commandWord, TaskList taskList) {
        try {
            int markIndex = Integer.parseInt(parsedInput[MARK_INDEX]) - 1;
            if (commandWord.equals(MarkCommand.COMMAND_WORD)) {
                return new MarkCommand(markIndex, taskList);
            }
            return new UnmarkCommand(markIndex, taskList);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(ERROR_INVALID_MARKING_INDEX_MESSAGE);
        }
        return null;
    }

    private static void printMissingInputMessage() {
        System.out.println("Oops! The description of an 'add' command cannot be empty."
                + HELP_MESSAGE_SPECIFIC_COMMAND);
    }

    private static LocalDateTime confirmInvalidDateFormat() {
        Ui anotherUi = new Ui();
        anotherUi.showToUser("It seems that the date and time\nyou gave is not in the correct format.\n"
                + "Would you like to re-enter a valid date and time? (Y/N)\n"
                + "*Enter 'No' to skip the adding of this task*");
        anotherUi.showLine();
        while (true) {
            String input = anotherUi.readCommand();
            anotherUi.showLine();
            if (input.trim().equalsIgnoreCase("Y")
                    || input.trim().equalsIgnoreCase("Yes")) {
                anotherUi.showToUser("Understood. Please key in the date and time you wish to save.");
                anotherUi.showLine();
                anotherUi.showToUser("Enter valid date input:");
                input = anotherUi.readCommand();
                anotherUi.showLine();
                return prepareTaskDate(input.trim(), "00:00");
            }
            if (input.trim().equalsIgnoreCase("N")
                    || input.trim().equalsIgnoreCase("No")) {
                anotherUi.showToUser("Okay, proceeding to stop current task process...");
                return null;
            }
            anotherUi.showToUser("Please confirm your choice with either Y (Yes) or N (No).");
            anotherUi.showLine();
        }
    }

    public static LocalDateTime prepareTaskDate(String taskDate, String time) {
        try {
            return LocalDateTime.parse(taskDate + WHITESPACE + time, inputWithTimeFormat);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Returns the value for a specific parameter.
     *
     * @param parameter The parameter to retrieve values from (e.g /by, /do)
     * @param argument The full argument given by the user
     * @return The value given by the user for a particular parameter
     */
    public static String parseArgument(String parameter, String argument) {
        if (!argument.contains(parameter)) {
            return EMPTY_STRING;
        }
        int indexAfterParameter = argument.indexOf(parameter) + parameter.length() + WHITESPACE_OFFSET;
        String stringAfterParameter = argument.substring(indexAfterParameter);
        String[] splitArguments = stringAfterParameter.split(WHITESPACE, SPLIT_TWO_PART_LIMIT);
        return splitArguments[SPLIT_FIRST_PART_INDEX];
    }

    public static String parseDescription(String fullArgument) {
        if (fullArgument.contains("/")) {
            if (fullArgument.indexOf("/") > START_OF_STRING) {
                return fullArgument.substring(START_OF_STRING, fullArgument.indexOf('/') - SLASH_OFFSET);
            } else {
                return EMPTY_STRING;
            }
        }
        return fullArgument.trim();
    }

    public static String removeRecurringDelimiter(String argument) {
        if (!argument.contains(FREQUENCY_DELIMITER)) {
            return argument;
        }
        String[] splitStrings = argument.split(FREQUENCY_DELIMITER);
        return String.join(WHITESPACE, splitStrings).trim();
    }

    protected static Command prepareAddRecurring(String argument) {
        if (argument.isBlank()) {
            return new HelpCommand(AddCommand.COMMAND_WORD);
        }
        AddRecurringCommand newCommand = new AddRecurringCommand();
        newCommand.setTaskDescription(parseDescription(argument));
        String doOnDateString = parseArgument(DO_DATE_DELIMITER, argument);
        String startTimeString = parseArgument(START_TIME_DELIMITER, argument);
        String endTimeString = parseArgument(END_TIME_DELIMITER, argument);
        newCommand.setDoOnStartDateTime(prepareTaskDate(doOnDateString, startTimeString));
        newCommand.setDoOnEndDateTime(prepareTaskDate(doOnDateString, endTimeString));
        try {
            newCommand.setFrequency(Frequency.valueOf(parseArgument(FREQUENCY_DELIMITER, argument).toUpperCase()));
        } catch (IllegalArgumentException exception) {
            newCommand.setFrequency(null);
        }

        return newCommand;
    }

    protected static Command prepareEditRecurring(String argument) {
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
        newCommand.setTaskDescription(parseDescription(descAndDateString));
        String doOnDateString = parseArgument(DO_DATE_DELIMITER, descAndDateString);
        String startTimeString = parseArgument(START_TIME_DELIMITER, descAndDateString);
        String endTimeString = parseArgument(END_TIME_DELIMITER, descAndDateString);
        try {
            newCommand.setIndex(Integer.parseInt(indexString) - ZERO_INDEX_OFFSET);
        } catch (NumberFormatException notNumberException) {
            newCommand.setIndex(INVALID_INDEX);
        }
        newCommand.setDoOnStartDateTime(prepareTaskDate(doOnDateString, startTimeString));
        newCommand.setDoOnEndDateTime(prepareTaskDate(doOnDateString, endTimeString));
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

    // Please add in constants to the magic literals
    protected static Command prepareAdd(String[] splitInput, TaskList taskList) {
        String[] filteredTaskContent;
        LocalDateTime byDate;
        LocalDateTime doOnDate;
        try {
            if (!splitInput[TASK_CONTENT_INDEX].contains(BY_DATE_DELIMITER)
                    && !splitInput[TASK_CONTENT_INDEX].contains(DO_DATE_DELIMITER)) {
                return new AddCommand(splitInput[TASK_CONTENT_INDEX], taskList, null, null);
            }

            filteredTaskContent = splitInput[TASK_CONTENT_INDEX].split(BY_DATE_DELIMITER, 2);
            if (!splitInput[1].contains(DO_DATE_DELIMITER)) {
                byDate = prepareTaskDate(filteredTaskContent[1].trim(), "00:00");
                return new AddCommand(filteredTaskContent[0].trim(), taskList, byDate, null);
            }

            String[] filteredDates = filteredTaskContent[1].split(DO_DATE_DELIMITER);
            byDate = prepareTaskDate(filteredDates[0].trim(), "00:00");
            doOnDate = prepareTaskDate(filteredDates[1].trim(), "00:00");
            return new AddCommand(filteredTaskContent[0], taskList, byDate, doOnDate);

        } catch (ArrayIndexOutOfBoundsException | InvalidInputException e) {
            printMissingInputMessage();
        } catch (InputRepeatedException e) {
            System.out.println("Oops! It seems that you've entered a duplicate task.\n"
                    + "Please re-enter a new task if you wish to add one.");
        }
        return null;
    }

    protected static Command prepareEdit(String[] splitInput) {
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

    protected static Command prepareDelete(String[] parsedInput, TaskList taskList) {
        try {
            return new DeleteCommand(parsedInput, taskList);
        } catch (IndexOutOfBoundsException | InvalidInputException | NumberFormatException e) {
            System.out.println(ERROR_INVALID_DELETE_INDEX_MESSAGE + HELP_MESSAGE_SPECIFIC_COMMAND);
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
        String[] splitEditInfo = fullEditInfo.split(" ");
        String descriptionToEdit;
        LocalDateTime parsedByDateToEdit;
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
    }
}
