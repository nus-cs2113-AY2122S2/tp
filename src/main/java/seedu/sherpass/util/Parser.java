package seedu.sherpass.util;

import org.json.JSONException;
import org.json.JSONObject;

import seedu.sherpass.command.AddCommand;
import seedu.sherpass.command.AddRecurringCommand;
import seedu.sherpass.command.ClearCommand;
import seedu.sherpass.command.Command;
import seedu.sherpass.command.DeleteCommand;
import seedu.sherpass.command.EditCommand;
import seedu.sherpass.command.EditRecurringCommand;
import seedu.sherpass.command.ExitCommand;
import seedu.sherpass.command.HelpCommand;
import seedu.sherpass.command.MarkCommand;
import seedu.sherpass.command.ShowCommand;
import seedu.sherpass.command.StudyCommand;
import seedu.sherpass.command.UnmarkCommand;

import seedu.sherpass.enums.Frequency;
import seedu.sherpass.exception.InputRepeatedException;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.exception.WrongEditInfoFormatException;
import seedu.sherpass.exception.InvalidTimeException;

import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static seedu.sherpass.constant.CommandParameters.BY_DATE_DELIMITER;
import static seedu.sherpass.constant.CommandParameters.DO_DATE_DELIMITER;
import static seedu.sherpass.constant.CommandParameters.FREQUENCY_DELIMITER;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.inputWithoutTimeFormat;
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
            boolean hasDoOnTime = taskData.getBoolean("has_dotime");
            boolean hasByTime = taskData.getBoolean("has_bytime");
            int identifier = taskData.getInt("identifier");
            String description = taskData.getString("description");
            String byDateString = taskData.getString("by_date");
            String doOnDateString = taskData.getString("do_date");
            LocalDateTime byDate = null;
            LocalDateTime doOnDate = null;
            if (!byDateString.equals("null")) {
                byDate = LocalDateTime.parse(byDateString, inputWithTimeFormat);
            }
            if (!doOnDateString.equals("null")) {
                doOnDate = LocalDateTime.parse(doOnDateString, inputWithTimeFormat);
            }
            parsedTask = new Task(description, byDate, doOnDate, hasByTime, hasDoOnTime);
            parsedTask.setIdentifier(identifier);
            String status = taskData.getString("status");
            if (status.equals("X")) {
                parsedTask.markAsDone();
            }
            return parsedTask;
        } catch (JSONException | DateTimeParseException exception) {
            throw new InvalidInputException(exception.getMessage());
        }
    }

    private static Command prepareMarkOrUnmark(String[] parsedInput, String commandWord, TaskList taskList) {
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
                return prepareTaskDate(input.trim(), false);
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

    private static LocalDateTime prepareTaskDate(String rawTaskDate, boolean hasTime) {
        if (rawTaskDate.isBlank()) {
            return null;
        }
        try {
            if (hasTime) {
                return LocalDateTime.parse(rawTaskDate, inputWithTimeFormat);
            }
            return LocalDate.parse(rawTaskDate, inputWithoutTimeFormat).atStartOfDay();
        } catch (DateTimeParseException e) {
            return confirmInvalidDateFormat();
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
            return "";
        }
        int afterParameter = argument.indexOf(parameter) + parameter.length() + 1;
        String rightSide = argument.substring(afterParameter);
        String[] splitArguments = rightSide.split(" ");
        StringBuilder result = new StringBuilder();
        for (String s : splitArguments) {
            if (s.charAt(0) == '/') {
                break;
            }
            result.append(s);
            result.append(" ");
        }
        return result.toString().trim();
    }

    public static String parseDescription(String fullArgument) {
        if (fullArgument.contains("/")) {
            if (fullArgument.indexOf("/") > 0) {
                return fullArgument.substring(0, fullArgument.indexOf('/') - 1);
            } else {
                return "";
            }
        }
        return fullArgument;
    }

    private static boolean hasTime(String input) {
        return (input.split(" ").length > 1);
    }

    private static Command prepareAddRecurring(String[] arguments) {
        if (arguments.length < 2) {
            return new HelpCommand(AddRecurringCommand.COMMAND_WORD);
        }
        String argument = arguments[1];
        AddRecurringCommand newCommand = new AddRecurringCommand();
        newCommand.setTaskDescription(parseDescription(argument));
        String doOnDateString = parseArgument(DO_DATE_DELIMITER, argument);
        try {
            newCommand.setFrequency(Frequency.valueOf(parseArgument(FREQUENCY_DELIMITER, argument).toUpperCase()));
        } catch (IllegalArgumentException exception) {
            return new HelpCommand(AddRecurringCommand.COMMAND_WORD);
        }
        if (newCommand.getTaskDescription().isBlank() || doOnDateString.isBlank()) {
            return new HelpCommand(AddRecurringCommand.COMMAND_WORD);
        }

        newCommand.setHasDoOnTime(hasTime(doOnDateString));
        newCommand.setDoOnDate(prepareTaskDate(doOnDateString, newCommand.getHasDoOnTime()));

        return newCommand;
    }

    private static Command prepareEditRecurring(String[] arguments) {
        if (arguments.length < 2) {
            return new HelpCommand(AddRecurringCommand.COMMAND_WORD);
        }
        String argument = arguments[1];
        EditRecurringCommand newCommand = new EditRecurringCommand();
        String[] splitArguments = argument.split(" ", 2);
        try {
            newCommand.setIndex(Integer.parseInt(splitArguments[0]));
            newCommand.setTaskDescription(parseDescription(splitArguments[1]));
            String doOnDateString = parseArgument(DO_DATE_DELIMITER, splitArguments[1]);
            if (doOnDateString.isBlank()) {
                newCommand.setDoOnDate(null);
                newCommand.setHasDoOnTime(false);
            } else {
                newCommand.setHasDoOnTime(hasTime(doOnDateString));
                newCommand.setDoOnDate(prepareTaskDate(doOnDateString, newCommand.getHasDoOnTime()));
            }
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            return new HelpCommand(EditRecurringCommand.COMMAND_WORD);
        }
        return newCommand;
    }

    private static Command prepareDeleteRecurring(String[] arguments) {
        if (arguments.length < 2) {
            return new HelpCommand(DeleteRecurringCommand.COMMAND_WORD);
        }
        DeleteRecurringCommand newCommand = new DeleteRecurringCommand();
        try {
            newCommand.setIndex(Integer.parseInt(arguments[1]));
        } catch (NumberFormatException exception) {
            return new HelpCommand(DeleteRecurringCommand.COMMAND_WORD);
        }
        return newCommand;
    }

    // Please add in constants to the magic literals
    private static Command prepareAdd(String[] splitInput, TaskList taskList) {
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
                byDate = prepareTaskDate(filteredTaskContent[1].trim(), false);
                return new AddCommand(filteredTaskContent[0].trim(), taskList, byDate, null);
            }

            String[] filteredDates = filteredTaskContent[1].split(DO_DATE_DELIMITER);
            byDate = prepareTaskDate(filteredDates[0].trim(), false);
            doOnDate = prepareTaskDate(filteredDates[1].trim(), false);
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

            return prepareTaskDate(dateToEdit, false);
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
            LocalDate dayInput = LocalDate.parse(selection, inputWithoutTimeFormat);
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
        case AddRecurringCommand.COMMAND_WORD:
            return prepareAddRecurring(splitInput);
        case EditCommand.COMMAND_WORD:
            return prepareEdit(splitInput);
        case EditRecurringCommand.COMMAND_WORD:
            return prepareEditRecurring(splitInput);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(splitInput, taskList);
        case DeleteRecurringCommand.COMMAND_WORD:
            return prepareDeleteRecurring(splitInput);
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
    public static void parseStudyMode(String rawUserInput, Ui ui, TimerLogic timerLogic) {
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
        default:
            ui.showToUser(ERROR_INVALID_STUDY_INPUT_MESSAGE);
        }
    }
}
