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
import seedu.sherpass.exception.WrongEditInfoFormatException;
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

    private static void printExceptionMessage(String exceptionMessage, String commandWord) {
        System.out.println(exceptionMessage + HELP_MESSAGE_SPECIFIC_COMMAND + commandWord + CLOSED_APOSTROPHE);
    }

    private static Command prepareMarkOrUnmark(String taskContent, String commandWord, TaskList taskList) {
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

    private static LocalDate prepareTaskDate(String rawTaskDate)
            throws InvalidInputException, DateTimeParseException {
        if (rawTaskDate.isBlank()) {
            return null;
        }
        return LocalDate.parse(rawTaskDate, parseFormat);
    }

    private static Command prepareAdd(String taskContent, TaskList taskList) {
        String[] splitTaskContent;
        LocalDate byDate;
        LocalDate doOnDate;
        try {
            if (!taskContent.contains(BY_KEYWORD) && !taskContent.contains(DO_ON_KEYWORD)) {
                return new AddCommand(taskContent, taskList, null, null);
            }

            splitTaskContent = taskContent.split(BY_KEYWORD, 2);

            if (!taskContent.contains(DO_ON_KEYWORD)) {
                byDate = prepareTaskDate(splitTaskContent[TASK_CONTENT_INDEX].trim());
                return new AddCommand(splitTaskContent[TASK_DESCRIPTION_INDEX].trim(), taskList, byDate, null);
            }

            String[] splitDates = splitTaskContent[TASK_CONTENT_INDEX].split(DO_ON_KEYWORD);
            byDate = prepareTaskDate(splitDates[BY_DATE_INDEX].trim());
            doOnDate = prepareTaskDate(splitDates[DO_ON_DATE_INDEX].trim());
            return new AddCommand(splitTaskContent[TASK_DESCRIPTION_INDEX], taskList, byDate, doOnDate);

        } catch (ArrayIndexOutOfBoundsException | InvalidInputException e) {
            printExceptionMessage(ERROR_EMPTY_ADD_MESSAGE, AddCommand.COMMAND_WORD);
        } catch (DateTimeParseException e) {
            printExceptionMessage(ERROR_INVALID_DATE_FORMAT_MESSAGE, AddCommand.COMMAND_WORD);
        } catch (InputRepeatedException e) {
            printExceptionMessage(ERROR_DUPLICATE_ADD_TASK_MESSAGE, AddCommand.COMMAND_WORD);
        }
        return null;
    }

    private static Command prepareEdit(String taskContent) {

        String[] fullEditInfo = taskContent.trim().split(SINGLE_SPACE, 2);

        //7 possibilities of editing. Incorrect format of inputs are rejected
        try {

            int taskNumberToEdit = Integer.parseInt(fullEditInfo[TASK_NUMBER_INDEX]);
            checkCorrectEditInfoFormat(fullEditInfo[TASK_CONTENT_INDEX]);
            return handleEdit(taskNumberToEdit, fullEditInfo[TASK_CONTENT_INDEX]);

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printExceptionMessage(ERROR_INVALID_TASK_NUMBER_MESSAGE, EditCommand.COMMAND_WORD);
        } catch (InvalidInputException e) {
            printExceptionMessage(ERROR_INVALID_DATE_FORMAT_MESSAGE, EditCommand.COMMAND_WORD);
        } catch (WrongEditInfoFormatException e) {
            printExceptionMessage(ERROR_INVALID_EDIT_FORMAT_MESSAGE, EditCommand.COMMAND_WORD);
        }

        return null;
    }

    private static void checkCorrectEditInfoFormat(String fullEditInfo) throws WrongEditInfoFormatException {
        // tests to make sure the byDate is before the doOnDate
        if (fullEditInfo.contains(BY_KEYWORD) && fullEditInfo.contains(DO_ON_KEYWORD)
                && (fullEditInfo.indexOf(BY_KEYWORD) > fullEditInfo.indexOf(DO_ON_KEYWORD))) {
            throw new WrongEditInfoFormatException();
        }

        // tests to make sure the task description is the first input if it is present
        String[] splitEditInfo = fullEditInfo.split(REGEX_TO_SPLIT_STRING_USING_BY_OR_DO_ON_DATES);
        if (splitEditInfo.length > 1) {
            throw new WrongEditInfoFormatException();
        }
    }

    private static Command handleEdit(int taskNumberToEdit, String fullEditInfo) throws InvalidInputException {

        assert(!fullEditInfo.isBlank());

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

        return new EditCommand(taskNumberToEdit, descriptionToEdit, parsedByDateToEdit, parsedDoOnDateToEdit);
    }

    private static LocalDate getParsedDateToEdit(String fullEditInfo, String keyword) throws InvalidInputException {

        if (fullEditInfo.contains(keyword)) {

            // gets the substring (of fullEditInfo) after the keyword (, which is either "/by" or "/do_on")
            int offsetForKeyword = keyword.length() + 1;
            int offsetForSubstring = fullEditInfo.indexOf(keyword) + offsetForKeyword;
            String substringAfterKeyword = fullEditInfo.substring(offsetForSubstring);

            // splits the substring and obtains the first word (which should be the date of format d/M/yyyy)
            String dateToEdit = substringAfterKeyword.split(SINGLE_SPACE)[FIRST_SUBSTRING_INDEX].trim();

            try {
                return LocalDate.parse(dateToEdit, parseFormat);
            } catch (DateTimeParseException e){
                throw new InvalidInputException();
            }
        }
        return null;
    }

    private static Command prepareDelete(String taskContent, TaskList taskList) {
        try {
            return new DeleteCommand(taskContent, taskList);
        } catch (IndexOutOfBoundsException | InvalidInputException | NumberFormatException e) {
            printExceptionMessage(ERROR_INVALID_DELETE_INDEX_MESSAGE, DeleteCommand.COMMAND_WORD);
        }
        return null;
    }

    private static Command prepareHelp(String userInput) {
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
            return prepareEdit(taskContent);
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
