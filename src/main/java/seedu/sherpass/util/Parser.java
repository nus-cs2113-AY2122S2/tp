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
import seedu.sherpass.command.MarkCommand;
import seedu.sherpass.command.ShowCommand;
import seedu.sherpass.command.StudyCommand;
import seedu.sherpass.command.UnmarkCommand;

import seedu.sherpass.exception.InputRepeatedException;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.WrongEditInfoFormatException;
import seedu.sherpass.exception.InvalidTimeException;

import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static seedu.sherpass.constant.DateAndTimeFormat.inputFormat;

import static seedu.sherpass.constant.Index.MARK_INDEX;
import static seedu.sherpass.constant.Index.CUSTOM_COMMAND_INDEX;
import static seedu.sherpass.constant.Index.SHOW_OPTION_INDEX;
import static seedu.sherpass.constant.Index.TASK_CONTENT_INDEX;
import static seedu.sherpass.constant.Index.TIMER_FORMAT_INDEX;
import static seedu.sherpass.constant.Index.HELP_OPTIONS_INDEX;
import static seedu.sherpass.constant.Index.OPTIONS_INDEX;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_ZERO;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_ONE;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_TWO;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_THREE;
import static seedu.sherpass.constant.Index.CUSTOM_TIMER_INDEX;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_INDEX;
import static seedu.sherpass.constant.Index.STUDY_COMMAND_INDEX;
import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_DELETE_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_MARKING_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.HELP_MESSAGE_SPECIFIC_COMMAND;
import static seedu.sherpass.constant.Message.ERROR_INVALID_STUDY_INPUT_MESSAGE;

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
                byDate = LocalDate.parse(byDateString, inputFormat);
            }
            if (!doOnDateString.equals("null")) {
                doOnDate = LocalDate.parse(doOnDateString, inputFormat);
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

    private static LocalDate confirmInvalidDateFormat() throws InvalidInputException {
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
                return prepareTaskDate(input.trim());
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

    private static LocalDate prepareTaskDate(String rawTaskDate) throws InvalidInputException {
        if (rawTaskDate.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(rawTaskDate, inputFormat);
        } catch (DateTimeParseException e) {
            return confirmInvalidDateFormat();
        }
    }


    // Please add in constants to the magic literals
    private static Command prepareAdd(String[] splitInput, TaskList taskList) {
        String[] filteredTaskContent;
        LocalDate byDate;
        LocalDate doOnDate;
        try {
            if (!splitInput[TASK_CONTENT_INDEX].contains("/by")
                    && !splitInput[TASK_CONTENT_INDEX].contains("/do_on")) {
                return new AddCommand(splitInput[TASK_CONTENT_INDEX], taskList, null, null);
            }

            filteredTaskContent = splitInput[TASK_CONTENT_INDEX].split("/by", 2);
            if (!splitInput[1].contains("/do_on")) {
                byDate = prepareTaskDate(filteredTaskContent[1].trim());
                return new AddCommand(filteredTaskContent[0].trim(), taskList, byDate, null);
            }

            String[] filteredDates = filteredTaskContent[1].split("/do_on");
            byDate = prepareTaskDate(filteredDates[0].trim());
            doOnDate = prepareTaskDate(filteredDates[1].trim());
            return new AddCommand(filteredTaskContent[0], taskList, byDate, doOnDate);

        } catch (ArrayIndexOutOfBoundsException | InvalidInputException e) {
            printMissingInputMessage();
        } catch (InputRepeatedException e) {
            System.out.println("Oops! It seems that you've entered a duplicate task.\n"
                    + "Please re-enter a new task if you wish to add one.");
        }
        return null;
    }

    private static Command prepareEdit(String[] splitInput) {

        String[] fullEditInfo = splitInput[1].trim().split(" ", 2);

        //7 possibilities of editing, incorrect format of inputs are rejected
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

    private static void checkCorrectEditInfoFormat(String fullEditInfo) throws WrongEditInfoFormatException {
        // tests to make sure the byDate is before the doOnDate
        if (fullEditInfo.contains("/by") && fullEditInfo.contains("/do_on")) {
            if (fullEditInfo.indexOf("/by") > fullEditInfo.indexOf("/do_on")) {
                throw new WrongEditInfoFormatException();
            }
        }
        // tests to make sure the task description is the first input if it is present
        String[] splitEditInfo = fullEditInfo.split("/by \\d{4}/\\d{2}/\\d{2}|/do_on \\d{4}/\\d{2}/\\d{2}");
        if (splitEditInfo.length > 1) {
            throw new WrongEditInfoFormatException();
        }
    }

    private static Command handleEdit(int taskNumberToEdit, String fullEditInfo) throws InvalidInputException {

        String[] splitEditInfo = fullEditInfo.split(" ");
        String descriptionToEdit;
        LocalDate parsedByDateToEdit;
        LocalDate parsedDoOnDateToEdit;

        if (!splitEditInfo[0].trim().equals("/by") && !(splitEditInfo[0].trim().equals("/do_on"))) {
            descriptionToEdit = splitEditInfo[0];
        } else {
            descriptionToEdit = EMPTY_STRING;
        }

        parsedByDateToEdit = getParsedDateToEdit(fullEditInfo, "/by");
        parsedDoOnDateToEdit = getParsedDateToEdit(fullEditInfo, "/do_on");

        return new EditCommand(taskNumberToEdit, descriptionToEdit, parsedByDateToEdit, parsedDoOnDateToEdit);
    }

    private static LocalDate getParsedDateToEdit(String fullEditInfo, String keyword) throws InvalidInputException {

        if (fullEditInfo.contains(keyword)) {

            int offsetForKeyword = keyword.length() + 1;
            int offsetForSubstring = fullEditInfo.indexOf(keyword) + offsetForKeyword;

            // gets the substring (of fullEditInfo) after the keyword (, which is either "/by" or "/do_on")
            // splits the substring and obtains the first word (which should be the date of format yyyy/MM/dd)
            String dateToEdit = fullEditInfo.substring(offsetForSubstring).split(" ")[0].trim();

            return prepareTaskDate(dateToEdit);
        }

        return null;
    }

    private static Command prepareDelete(String[] parsedInput, TaskList taskList) {
        try {
            return new DeleteCommand(parsedInput, taskList);
        } catch (IndexOutOfBoundsException | InvalidInputException | NumberFormatException e) {
            System.out.println(ERROR_INVALID_DELETE_INDEX_MESSAGE + HELP_MESSAGE_SPECIFIC_COMMAND);
        }
        return null;
    }

    private static Command prepareHelp(String userInput) {
        try {
            String[] parsedInput = userInput.split(" ", 2);
            return new HelpCommand(parsedInput[HELP_OPTIONS_INDEX]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return new HelpCommand("show help list");
        }
    }

    private static Command parseShowCommandOptions(String selection) throws InvalidInputException {
        if (selection.isBlank()) {
            throw new InvalidInputException();
        }
        try {
            LocalDate dayInput = LocalDate.parse(selection, inputFormat);
            return new ShowCommand(dayInput, null);
        } catch (DateTimeParseException e) {
            return new ShowCommand(null, selection);
        }
    }

    private static Command prepareShow(String[] splitInput) {
        try {
            String selection = splitInput[SHOW_OPTION_INDEX].trim();
            return parseShowCommandOptions(selection.toLowerCase());
        } catch (ArrayIndexOutOfBoundsException | InvalidInputException e) {
            System.out.println(ERROR_INVALID_INPUT_MESSAGE);
        }
        return null;
    }

    /**
     * Parses the user command input.
     *
     * @param userInput User command.
     * @param taskList  Array of tasks.
     * @return Command type matching the user command.
     */
    public static Command parseCommand(String userInput, TaskList taskList, Ui ui) {
        String[] splitInput = userInput.split(" ", 2);
        String commandWord = splitInput[OPTIONS_INDEX].toLowerCase().trim();
        switch (commandWord) {
        case MarkCommand.COMMAND_WORD:
            return prepareMarkOrUnmark(splitInput, MarkCommand.COMMAND_WORD, taskList);
        case UnmarkCommand.COMMAND_WORD:
            return prepareMarkOrUnmark(splitInput, UnmarkCommand.COMMAND_WORD, taskList);
        case AddCommand.COMMAND_WORD:
            return prepareAdd(splitInput, taskList);
        case EditCommand.COMMAND_WORD:
            return prepareEdit(splitInput);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(splitInput, taskList);
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case StudyCommand.COMMAND_WORD:
            return new StudyCommand();
        case ShowCommand.COMMAND_WORD:
            return prepareShow(splitInput);
        case HelpCommand.COMMAND_WORD:
            return prepareHelp(userInput);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            ui.showToUser(ERROR_INVALID_INPUT_MESSAGE);
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
    public static void parseStudyMode(Ui ui, Storage storage, String rawUserInput,TimerLogic timerLogic) throws IOException {
        String[] parsedInput = rawUserInput.trim().split(" ", 2);
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
        case "mark":
            timerLogic.markTask(storage, parsedInput);
            break;
        default:
            ui.showToUser(ERROR_INVALID_STUDY_INPUT_MESSAGE);
        }
    }
}
