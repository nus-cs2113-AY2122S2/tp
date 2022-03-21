package seedu.sherpass.util;

import org.json.JSONException;
import org.json.JSONObject;
import seedu.sherpass.command.Command;
import seedu.sherpass.command.AddCommand;
import seedu.sherpass.command.ClearCommand;
import seedu.sherpass.command.DeleteCommand;
import seedu.sherpass.command.EditCommand;
import seedu.sherpass.command.ExitCommand;
import seedu.sherpass.command.HelpCommand;
import seedu.sherpass.command.ListCommand;
import seedu.sherpass.command.MarkCommand;
import seedu.sherpass.command.StudyCommand;
import seedu.sherpass.command.UnmarkCommand;

import seedu.sherpass.exception.InputRepeatedException;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.WrongAttributeOrderException;
import seedu.sherpass.exception.InvalidTimeException;

import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import static seedu.sherpass.constant.DateAndTimeFormat.parseFormat;

import static seedu.sherpass.constant.Index.OPTIONS_INDEX;
import static seedu.sherpass.constant.Index.TASK_NUMBER_INDEX;
import static seedu.sherpass.constant.Index.TASK_CONTENT_INDEX;
import static seedu.sherpass.constant.Index.TASK_DESCRIPTION_INDEX;
import static seedu.sherpass.constant.Index.BY_DATE_INDEX;
import static seedu.sherpass.constant.Index.DO_ON_DATE_INDEX;
import static seedu.sherpass.constant.Index.FIRST_SUBSTRING_INDEX;
import static seedu.sherpass.constant.Index.HELP_OPTIONS_INDEX;
import static seedu.sherpass.constant.Index.STUDY_COMMAND_INDEX;
import static seedu.sherpass.constant.Index.TIMER_FORMAT_INDEX;
import static seedu.sherpass.constant.Index.CUSTOM_TIMER_INDEX;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_INDEX;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_ONE;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_TWO;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_THREE;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_ZERO;
import static seedu.sherpass.constant.Index.CUSTOM_COMMAND_INDEX;
import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_DELETE_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_MARKING_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.HELP_MESSAGE_SPECIFIC_COMMAND;
import static seedu.sherpass.constant.Message.ERROR_INVALID_STUDY_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_EMPTY_ADD_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_DATE_FORMAT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_DUPLICATE_ADD_TASK_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_TASK_NUMBER_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_ADD_FORMAT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_EDIT_FORMAT_MESSAGE;

import static seedu.sherpass.constant.StringConstant.DO_ON_KEYWORD;
import static seedu.sherpass.constant.StringConstant.BY_KEYWORD;
import static seedu.sherpass.constant.StringConstant.SINGLE_SPACE;
import static seedu.sherpass.constant.StringConstant.CLOSED_APOSTROPHE;
import static seedu.sherpass.constant.StringConstant.REGEX_TO_SPLIT_STRING_USING_BY_OR_DO_ON_DATES;

public class Parser {

    /**
     * Returns a task object parsed from the data file.
     *
     * @param taskData The data of a task in JSON.
     * @return Task containing the saved data for adding into program's task array.
     * @throws InvalidInputException If saved data is missing content, i.e. task description or date.
     */
    public static Task parseSavedData(JSONObject taskData) throws InvalidInputException {
        Task parsedTask;
        try {
            String description = taskData.getString("description");
            String byDateString = taskData.getString("by_date");
            String doOnDateString = taskData.getString("do_date");
            LocalDate byDate = null;
            LocalDate doOnDate = null;
            if (!byDateString.equals("null")) {
                byDate = LocalDate.parse(byDateString, parseFormat);
            }
            if (!doOnDateString.equals("null")) {
                doOnDate = LocalDate.parse(doOnDateString, parseFormat);
            }
            parsedTask = new Task(description, byDate, doOnDate);
            String status = taskData.getString("status");
            if (status.equals("X")) {
                parsedTask.markAsDone();
            }
            return parsedTask;
        } catch (JSONException | DateTimeParseException exception) {
            throw new InvalidInputException(exception.getMessage());
        }
    }

    /**
     * Prints out exception messages for commands.
     * Includes a suggestion to refer to help command.
     *
     * @param exceptionMessage  Specific exception message of each task
     * @param commandWord       Command word of the command
     */
    public static void printExceptionMessage(String exceptionMessage, String commandWord) {
        System.out.println(exceptionMessage + HELP_MESSAGE_SPECIFIC_COMMAND + commandWord + CLOSED_APOSTROPHE);
    }

    /**
     * Checks if the user input for the Mark or Unmark command is valid.
     * Then it creates and returns a new Mark or Unmark command to the parseCommand method.
     *
     * @param taskContent   User's input. Should be of type int denoting task number to mark or unmark.
     * @param commandWord   Command word of command (either 'mark' or 'unmark'.
     * @param taskList      Task array.
     * @return A new Mark or Unmark command. If user input is invalid, return null.
     */
    public static Command prepareMarkOrUnmark(String taskContent, String commandWord, TaskList taskList) {
        try {
            int markIndex = Integer.parseInt(taskContent) - 1;
            if (commandWord.equals(MarkCommand.COMMAND_WORD)) {
                return new MarkCommand(markIndex, taskList);
            }
            return new UnmarkCommand(markIndex, taskList);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            if (commandWord.equals(MarkCommand.COMMAND_WORD)) {
                printExceptionMessage(ERROR_INVALID_MARKING_INDEX_MESSAGE, MarkCommand.COMMAND_WORD);
            } else {
                printExceptionMessage(ERROR_INVALID_MARKING_INDEX_MESSAGE, UnmarkCommand.COMMAND_WORD);
            }
        }
        return null;
    }

    /**
     * Converts String input to a LocalDate date.
     * If input has invalid format, throw exceptions.
     *
     * @param rawTaskDate   String of format d/M/yyyy
     * @return LocalDate of format d/M/yyyy
     * @throws InvalidInputException If rawTaskDate is an empty string.
     * @throws DateTimeParseException If rawTaskDate cannot be parsed by LocalDate.
     */
    public static LocalDate prepareTaskDate(String rawTaskDate)
            throws InvalidInputException, DateTimeParseException {
        if (rawTaskDate.isBlank()) {
            return null;
        }
        return LocalDate.parse(rawTaskDate, parseFormat);
    }

    /**
     * Checks if the user's input is valid for the Add command.
     * User's input is invalid if user input is empty, the task date is not of format d/M/yyyy,
     * or if the task description is a repeated one from a current task in the task list.
     * If user's input is valid, it creates and returns a new Add command to the parseCommand method.
     *
     * @param taskContent   User's input. Should be of the format:
     *                      [task_description] /by [task_due_date] /do_on [date to work on task]
     *                      (the 2 dates are optional)
     * @param taskList      Task array.
     * @return A new Add command. If user's input is invalid, return null.
     */
    public static Command prepareAdd(String taskContent, TaskList taskList) {
        String[] splitTaskContent;
        LocalDate byDate;
        LocalDate doOnDate;
        try {
            checkCorrectAttributeInfoFormat(taskContent);

            if (!taskContent.contains(BY_KEYWORD) && !taskContent.contains(DO_ON_KEYWORD)) {
                return new AddCommand(taskContent, taskList, null, null);
            }

            if (taskContent.contains(BY_KEYWORD) && !taskContent.contains(DO_ON_KEYWORD)) {
                splitTaskContent = taskContent.split(BY_KEYWORD, 2);
                byDate = prepareTaskDate(splitTaskContent[TASK_CONTENT_INDEX].trim());
                return new AddCommand(splitTaskContent[TASK_DESCRIPTION_INDEX].trim(), taskList, byDate, null);
            }

            if (taskContent.contains(DO_ON_KEYWORD) && !taskContent.contains(BY_KEYWORD)) {
                splitTaskContent = taskContent.split(DO_ON_KEYWORD, 2);
                doOnDate = prepareTaskDate(splitTaskContent[TASK_CONTENT_INDEX].trim());
                return new AddCommand(splitTaskContent[TASK_DESCRIPTION_INDEX].trim(), taskList, null, doOnDate);
            }

            splitTaskContent = taskContent.split(REGEX_TO_SPLIT_STRING_USING_BY_OR_DO_ON_DATES, 3);
            byDate = prepareTaskDate(splitTaskContent[BY_DATE_INDEX].trim());
            doOnDate = prepareTaskDate(splitTaskContent[DO_ON_DATE_INDEX].trim());
            return new AddCommand(splitTaskContent[TASK_DESCRIPTION_INDEX], taskList, byDate, doOnDate);

        } catch (ArrayIndexOutOfBoundsException | InvalidInputException e) {
            printExceptionMessage(ERROR_EMPTY_ADD_MESSAGE, AddCommand.COMMAND_WORD);
        } catch (DateTimeParseException e) {
            printExceptionMessage(ERROR_INVALID_DATE_FORMAT_MESSAGE, AddCommand.COMMAND_WORD);
        } catch (InputRepeatedException e) {
            printExceptionMessage(ERROR_DUPLICATE_ADD_TASK_MESSAGE, AddCommand.COMMAND_WORD);
        } catch (WrongAttributeOrderException e) {
            printExceptionMessage(ERROR_INVALID_ADD_FORMAT_MESSAGE, AddCommand.COMMAND_WORD);
        }
        return null;
    }

    /**
     * Checks if the user input is of the correct order of attributes.
     * Correct order should be: [task_description] /by [task_due_date] /do_on [date to work on task]
     * (The 3 attributes are individually optional.)
     *
     * @param fullInfoString  User's input.
     * @throws WrongAttributeOrderException If fullEditInfo is of the wrong format.
     */
    public static void checkCorrectAttributeInfoFormat(String fullInfoString) throws WrongAttributeOrderException {
        // tests to make sure the byDate is before the doOnDate
        if (fullInfoString.contains(BY_KEYWORD) && fullInfoString.contains(DO_ON_KEYWORD)
                && (fullInfoString.indexOf(BY_KEYWORD) > fullInfoString.indexOf(DO_ON_KEYWORD))) {
            throw new WrongAttributeOrderException();
        }

        // tests to make sure the task description is the first input if it is present
        String[] splitEditInfo = fullInfoString.split(REGEX_TO_SPLIT_STRING_USING_BY_OR_DO_ON_DATES);
        if (splitEditInfo.length > 1) {
            throw new WrongAttributeOrderException();
        }
    }

    /**
     * Checks if the user's input is valid for the Edit command.
     * User's input is invalid if the first word is not a valid task number, the user input is empty,
     * the task date(s) is/are not of format d/M/yyyy, or if the attributes are not inputted in the correct format.
     * If user's input is valid, it creates and returns a new Edit command to the parseCommand method.
     *
     * @param taskContent   User's input. Should be of the format:
     *                      [task_number] [task_description] /by [task_due_date] /do_on [date to work on task]
     *                      (the 3 attributes after task_number are optional)
     * @param taskList      Task array.
     * @return A new Edit command. If user's input is invalid, return null.
     */
    public static Command prepareEdit(String taskContent, TaskList taskList) {

        String[] fullEditInfo = taskContent.trim().split(SINGLE_SPACE, 2);

        //7 possibilities of editing. Incorrect format of inputs are rejected
        try {

            int taskNumberToEdit = Integer.parseInt(fullEditInfo[TASK_NUMBER_INDEX]);
            checkCorrectAttributeInfoFormat(fullEditInfo[TASK_CONTENT_INDEX]);
            return handleEdit(taskList, taskNumberToEdit, fullEditInfo[TASK_CONTENT_INDEX]);

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printExceptionMessage(ERROR_INVALID_TASK_NUMBER_MESSAGE, EditCommand.COMMAND_WORD);
        } catch (InvalidInputException e) {
            printExceptionMessage(ERROR_INVALID_DATE_FORMAT_MESSAGE, EditCommand.COMMAND_WORD);
        } catch (WrongAttributeOrderException e) {
            printExceptionMessage(ERROR_INVALID_EDIT_FORMAT_MESSAGE, EditCommand.COMMAND_WORD);
        }

        return null;
    }

    /**
     * Parses the user's input (to determine the task description, task due date, and date to work on task)
     * for the Edit command. Returns parsed command to the prepareEdit command.
     *
     * @param taskList          Task array.
     * @param taskNumberToEdit  Index of task to edit.
     * @param fullEditInfo      User's input.
     * @return A new Edit command containing the parsed attributes.
     * @throws InvalidInputException If dates cannot be parsed by LocalDate.
     * @throws IndexOutOfBoundsException If the task index is out of bounds
     */
    public static Command handleEdit(TaskList taskList, int taskNumberToEdit, String fullEditInfo)
            throws InvalidInputException, IndexOutOfBoundsException {

        assert (!fullEditInfo.isBlank());

        String descriptionToEdit;
        String[] splitEditInfo = fullEditInfo.split(REGEX_TO_SPLIT_STRING_USING_BY_OR_DO_ON_DATES);

        if (Arrays.stream(splitEditInfo).findAny().isPresent()) {   //if the splitEditInfo array is not empty
            if (!splitEditInfo[FIRST_SUBSTRING_INDEX].isBlank()) {
                descriptionToEdit = splitEditInfo[FIRST_SUBSTRING_INDEX];
            } else {
                descriptionToEdit = EMPTY_STRING;
            }
        } else {
            descriptionToEdit = EMPTY_STRING;
        }

        LocalDate parsedByDateToEdit = getParsedDateToEdit(fullEditInfo, BY_KEYWORD);
        LocalDate parsedDoOnDateToEdit = getParsedDateToEdit(fullEditInfo, DO_ON_KEYWORD);

        return new EditCommand(taskList, taskNumberToEdit, descriptionToEdit, parsedByDateToEdit, parsedDoOnDateToEdit);
    }

    /**
     * Extracts either the task_due_date or the date_to_work_on_task from the user's input,
     * then converts it to LocalDate format, and returns it to the handleEdit command.     *
     *
     * @param fullEditInfo  User's input.
     * @param keyword       Either "/by" or "/do_on".
     * @return Task date of type LocalDate.
     * @throws InvalidInputException If date cannot be parsed by LocalDate.
     */
    public static LocalDate getParsedDateToEdit(String fullEditInfo, String keyword) throws InvalidInputException {

        if (fullEditInfo.contains(keyword)) {

            // gets the substring (of fullEditInfo) after the keyword (which is either "/by" or "/do_on")
            int offsetForKeyword = keyword.length() + 1;
            int offsetForSubstring = fullEditInfo.indexOf(keyword) + offsetForKeyword;
            String substringAfterKeyword = fullEditInfo.substring(offsetForSubstring);

            // splits the substring and obtains the first word (which should be the date of format d/M/yyyy)
            String dateToEdit = substringAfterKeyword.split(SINGLE_SPACE)[FIRST_SUBSTRING_INDEX].trim();

            try {
                return LocalDate.parse(dateToEdit, parseFormat);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException();
            }
        }
        return null;
    }

    /**
     * Checks if the user's input is a valid task number to delete the task.
     * If user's input is valid, return a new Delete command to the parseCommand method.
     *
     * @param taskContent   User's input.
     * @param taskList      Task array.
     * @return A new Delete command. If user's input is invalid, return null.
     */
    public static Command prepareDelete(String taskContent, TaskList taskList) {
        try {
            return new DeleteCommand(taskContent, taskList);
        } catch (IndexOutOfBoundsException | InvalidInputException | NumberFormatException e) {
            printExceptionMessage(ERROR_INVALID_DELETE_INDEX_MESSAGE, DeleteCommand.COMMAND_WORD);
        }
        return null;
    }

    /**
     * Checks if user's input is a valid commandWord.
     * If it is, returns a new Help command to the parseCommand method.
     *
     * @param userInput Denotes the commandWord (e.g. "add")
     * @return A new Help command. If user's input is invalid, return a help command to show a short command summary.
     */
    public static Command prepareHelp(String userInput) {
        try {
            String[] splitInput = userInput.split(SINGLE_SPACE, 2);
            return new HelpCommand(splitInput[HELP_OPTIONS_INDEX]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return new HelpCommand("show help list");
        }
    }

    /**
     * Parses the user command input.
     *
     * @param userInput User command.
     * @param taskList  Array of tasks.
     * @return Command type matching the user command.
     */
    public static Command parseCommand(String userInput, TaskList taskList) {
        String[] splitInput = userInput.split(SINGLE_SPACE, 2);
        String commandWord = splitInput[OPTIONS_INDEX].toLowerCase().trim();
        String taskContent = EMPTY_STRING; 
        if (splitInput.length > 1) {
            taskContent = splitInput[TASK_CONTENT_INDEX].trim();
        }
        
        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return prepareMarkOrUnmark(taskContent, MarkCommand.COMMAND_WORD, taskList);
        case UnmarkCommand.COMMAND_WORD:
            return prepareMarkOrUnmark(taskContent, UnmarkCommand.COMMAND_WORD, taskList);
        case AddCommand.COMMAND_WORD:
            return prepareAdd(taskContent, taskList);
        case EditCommand.COMMAND_WORD:
            return prepareEdit(taskContent, taskList);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(taskContent, taskList);
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case StudyCommand.COMMAND_WORD:
            return new StudyCommand();
        case HelpCommand.COMMAND_WORD:
            return prepareHelp(userInput);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            System.out.println(ERROR_INVALID_INPUT_MESSAGE);
            return null;
        }
    }

    /**
     * Parses the default timer modes.
     *
     * @param defaultTimerChoice Mode number.
     * @return Returns the duration of the timer mode selected in seconds.
     * @throws InvalidTimeException If defaultTimerChoice does not match
     *                              with the given choices.
     */
    private static int selectDefaultTimer(String defaultTimerChoice) throws InvalidTimeException {
        switch (defaultTimerChoice) {
        case "0":
            return DEFAULT_TIMER_ZERO;
        case "1":
            return DEFAULT_TIMER_ONE;
        case "2":
            return DEFAULT_TIMER_TWO;
        case "3":
            return DEFAULT_TIMER_THREE;
        default:
            throw new InvalidTimeException();
        }
    }

    private static boolean isValidDuration(int duration) {
        return duration > 0;
    }

    /**
     * Parses input to the timer.
     *
     * @param parsedInput Parsed input.
     * @return Returns duration of custom timer input, or the duration of
     *         selected default timer mode in seconds.
     * @throws InvalidTimeException If timer input less than or equals to 0 or there is
     *                              multiple timer inputs.
     */
    public static int parseTimerInput(String[] parsedInput) throws InvalidTimeException {
        if (parsedInput[TIMER_FORMAT_INDEX].trim().contains("/custom")) {
            if (parsedInput[TIMER_FORMAT_INDEX].trim().indexOf("/custom") != CUSTOM_COMMAND_INDEX) {
                throw new InvalidTimeException();
            }
            String[] customTimerInput = parsedInput[TIMER_FORMAT_INDEX].split("/custom", 2);
            int customDuration = Integer.parseInt(customTimerInput[CUSTOM_TIMER_INDEX].trim());
            if (!isValidDuration(customDuration)) {
                throw new InvalidTimeException();
            }
            return customDuration;
        }
        return selectDefaultTimer(parsedInput[DEFAULT_TIMER_INDEX].trim());
    }

    /**
     * Parses commands for study mode.
     *
     * @param rawUserInput Raw user input.
     * @param ui           UI.
     */
    public static void parseStudyMode(String rawUserInput, Ui ui, TimerLogic timerLogic) {
        String[] parsedInput = rawUserInput.trim().split(SINGLE_SPACE, 2);
        switch (parsedInput[STUDY_COMMAND_INDEX].trim().toLowerCase()) {
        case "start":
            timerLogic.callStartTimer(parsedInput);
            break;
        case "pause":
            timerLogic.callPauseTimer();
            break;
        case "resume":
            timerLogic.callResumeTimer();
            break;
        case "stop":
            timerLogic.callStopTimer();
            break;
        default:
            printExceptionMessage(ERROR_INVALID_STUDY_INPUT_MESSAGE, StudyCommand.COMMAND_WORD);
        }
    }
}
