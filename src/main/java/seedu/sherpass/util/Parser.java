package seedu.sherpass.util;

import seedu.sherpass.command.Command;
import seedu.sherpass.command.DeadlineCommand;
import seedu.sherpass.command.DeleteCommand;
import seedu.sherpass.command.EventCommand;
import seedu.sherpass.command.HelpCommand;
import seedu.sherpass.command.MarkCommand;
import seedu.sherpass.command.StudyCommand;
import seedu.sherpass.command.TodoCommand;
import seedu.sherpass.command.UnmarkCommand;
import seedu.sherpass.command.UpdateCommand;
import seedu.sherpass.command.FindCommand;
import seedu.sherpass.command.ListCommand;
import seedu.sherpass.command.ExitCommand;
import seedu.sherpass.command.ClearCommand;
import seedu.sherpass.exception.InputRepeatedException;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


import static seedu.sherpass.constant.DateAndTimeFormat.noTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.savedTaskNoTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.savedTaskWithTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.withTimeFormat;

import static seedu.sherpass.constant.Index.CUSTOM_TIMER_INDEX;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_INDEX;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_ONE;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_THREE;
import static seedu.sherpass.constant.Index.DEFAULT_TIMER_TWO;
import static seedu.sherpass.constant.Index.FIND_BY_TASK_CONTENT_INDEX;
import static seedu.sherpass.constant.Index.FIND_BY_TASK_DATE_INDEX;
import static seedu.sherpass.constant.Index.FIND_BY_TASK_DESCRIPTION_NO_DATE_INDEX;
import static seedu.sherpass.constant.Index.FIND_BY_TASK_DESCRIPTION_WITH_DATE_INDEX;
import static seedu.sherpass.constant.Index.HELP_OPTIONS_INDEX;
import static seedu.sherpass.constant.Index.MARK_INDEX;
import static seedu.sherpass.constant.Index.OPTIONS_INDEX;
import static seedu.sherpass.constant.Index.SAVE_TASK_BY_DATE_INDEX;
import static seedu.sherpass.constant.Index.SAVE_TASK_DESCRIPTION_INDEX;
import static seedu.sherpass.constant.Index.SAVE_TASK_DO_DATE_INDEX;
import static seedu.sherpass.constant.Index.SAVE_TASK_MARK_STATUS;
import static seedu.sherpass.constant.Index.STUDY_COMMAND_INDEX;
import static seedu.sherpass.constant.Index.TASK_CONTENT_INDEX;
import static seedu.sherpass.constant.Index.TASK_DATE_INDEX;
import static seedu.sherpass.constant.Index.TASK_DESCRIPTION_INDEX;
import static seedu.sherpass.constant.Index.TIMER_FORMAT_INDEX;
import static seedu.sherpass.constant.Message.DATE_FORMAT_WITHOUT_TIME;
import static seedu.sherpass.constant.Message.DATE_FORMAT_WITH_TIME;
import static seedu.sherpass.constant.Message.ERROR_DEADLINE_MISSING_COMMAND_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_EVENT_MISSING_COMMAND_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_DELETE_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_INVALID_MARKING_INDEX_MESSAGE;
import static seedu.sherpass.constant.Message.ERROR_TODO_REPEATED_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.HELP_MESSAGE_SPECIFIC_COMMAND;


public class Parser {
    /**
     * Parses the saved data of the tasks in the save file.
     * Arranges the parsed data in a manner that can be initialised
     * as a 'Task'.
     *
     * @param rawData Array of strings containing saved data of task.
     * @return Task containing the saved data for adding into program's task array.
     * @throws InvalidInputException If saved data is missing content, i.e. task description or date.
     */
    public static Task parseSavedData(String[] rawData) throws InvalidInputException {
        if (!isValidData(rawData)) {
            throw new InvalidInputException();
        }
        Task parsedData;
        try {
            LocalDate parsedByDate = LocalDate.parse(rawData[SAVE_TASK_DO_DATE_INDEX]);
            LocalDate parsedDoDate = LocalDate.parse(rawData[SAVE_TASK_BY_DATE_INDEX]);

        } catch (DateTimeParseException invalidDate) {
            throw new InvalidInputException();
        }

        // Using Todo for now, will be updated in the future
        parsedData = new Todo(rawData[SAVE_TASK_DESCRIPTION_INDEX].trim());

        if (rawData[SAVE_TASK_MARK_STATUS].equals("X")) {
            parsedData.markAsDone();
        }

        return parsedData;
    }

    private static Boolean isValidData(String[] rawData) {
        if (rawData.length != 4) {
            return false;
        } else if (!rawData[SAVE_TASK_MARK_STATUS].equals(" ") && !rawData[SAVE_TASK_MARK_STATUS].equals("X")) {
            return false;
        }
        return !rawData[SAVE_TASK_DESCRIPTION_INDEX].isBlank() && !rawData[SAVE_TASK_BY_DATE_INDEX].isBlank()
                && !rawData[SAVE_TASK_DO_DATE_INDEX].isBlank();
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

    private static void printMissingInputMessage(String input, String taskType) {
        System.out.println("Oops! The " + input + " of a(n) " + taskType + " cannot be empty."
                + HELP_MESSAGE_SPECIFIC_COMMAND);
    }

    private static Command prepareAddTodo(String[] parsedInput, TaskList taskList) {
        try {
            return new TodoCommand(parsedInput, taskList);
        } catch (ArrayIndexOutOfBoundsException | InvalidInputException e) {
            printMissingInputMessage("description", "todo");
        } catch (InputRepeatedException e) {
            System.out.println(ERROR_TODO_REPEATED_INPUT_MESSAGE);
        }
        return null;
    }

    private static void printMissingCommandMessage(String taskType) {
        if (taskType.equals("event")) {
            System.out.println(ERROR_EVENT_MISSING_COMMAND_MESSAGE + HELP_MESSAGE_SPECIFIC_COMMAND);
        } else {
            System.out.println(ERROR_DEADLINE_MISSING_COMMAND_MESSAGE + HELP_MESSAGE_SPECIFIC_COMMAND);
        }
    }


    private static String confirmInvalidDateFormat() throws InvalidInputException {
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

    private static String checkCorrectDateFormat(String rawTaskDate, boolean isParseSaveFile)
            throws InvalidInputException {
        try {
            if (isParseSaveFile) {
                return LocalDate.parse(rawTaskDate, savedTaskNoTimeFormat)
                        .format(DateTimeFormatter.ofPattern(DATE_FORMAT_WITHOUT_TIME));
            }
            return LocalDate.parse(rawTaskDate, noTimeFormat)
                    .format(DateTimeFormatter.ofPattern(DATE_FORMAT_WITHOUT_TIME));
        } catch (DateTimeParseException e) {
            if (isParseSaveFile) {
                throw new InvalidInputException();
            }
            return confirmInvalidDateFormat();
        }
    }

    private static String prepareTaskDate(String rawTaskDate, boolean isParseSaveFile)
            throws InvalidInputException {
        if (rawTaskDate.isBlank()) {
            throw new InvalidInputException();
        }
        try {
            if (isParseSaveFile) {
                return LocalDateTime.parse(rawTaskDate, savedTaskWithTimeFormat)
                        .format(DateTimeFormatter.ofPattern(DATE_FORMAT_WITH_TIME));
            }
            return LocalDateTime.parse(rawTaskDate, withTimeFormat)
                    .format(DateTimeFormatter.ofPattern(DATE_FORMAT_WITH_TIME));
        } catch (DateTimeParseException e) {
            return checkCorrectDateFormat(rawTaskDate, isParseSaveFile);
        }
    }

    private static Command prepareAddEventOrDeadline(String[] parsedInput, String inputKeyword,
                                                     TaskList taskList, String taskType) {
        String[] filteredTaskContent = null;
        String dueDate = null;
        try {
            if (!parsedInput[TASK_CONTENT_INDEX].contains(inputKeyword)) {
                printMissingCommandMessage(taskType);
                return null;
            }
            filteredTaskContent = parsedInput[TASK_CONTENT_INDEX].split(inputKeyword, 2);
            dueDate = prepareTaskDate(filteredTaskContent[TASK_DATE_INDEX].trim(), false);
            if (dueDate == null) {
                return null;
            }
            if (taskType.equals(EventCommand.COMMAND_WORD)) {
                return new EventCommand(filteredTaskContent[TASK_DESCRIPTION_INDEX].trim(), taskList, dueDate);
            }
            return new DeadlineCommand(filteredTaskContent[TASK_DESCRIPTION_INDEX].trim(), taskList, dueDate);
        } catch (ArrayIndexOutOfBoundsException | InvalidInputException e) {
            printMissingInputMessage("description and/or date\n", taskType);
        } catch (InputRepeatedException e) {
            return new UpdateCommand(filteredTaskContent[TASK_DESCRIPTION_INDEX].trim(), dueDate);
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

    private static Command prepareFind(String[] userInput) {
        String[] parsedInputToSearchByDate;
        try {
            if (userInput[FIND_BY_TASK_CONTENT_INDEX].contains("/date")) {
                parsedInputToSearchByDate = userInput[FIND_BY_TASK_CONTENT_INDEX].split("/date");
                String taskDateToSearch = prepareTaskDate(parsedInputToSearchByDate[FIND_BY_TASK_DATE_INDEX].trim(),
                        false);
                if (taskDateToSearch == null) {
                    return null;
                }
                return new FindCommand(parsedInputToSearchByDate[FIND_BY_TASK_DESCRIPTION_WITH_DATE_INDEX].trim(),
                        taskDateToSearch);
            }
            return new FindCommand(userInput[FIND_BY_TASK_DESCRIPTION_NO_DATE_INDEX].trim(), null);
        } catch (IndexOutOfBoundsException | InvalidInputException e) {
            System.out.println("Your search input seems to be missing.\n"
                    + "Please enter your input again." + HELP_MESSAGE_SPECIFIC_COMMAND);
        }
        return null;
    }

    /**
     * Parses the user command input.
     *
     * @param userInput User command.
     * @param taskList  Array of tasks
     * @return Command type matching the user command.
     */
    public static Command parseCommand(String userInput, TaskList taskList) {
        String[] parsedInput = userInput.split(" ", 2);
        String commandWord = parsedInput[OPTIONS_INDEX].toLowerCase().trim();
        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return prepareMarkOrUnmark(parsedInput, MarkCommand.COMMAND_WORD, taskList);
        case UnmarkCommand.COMMAND_WORD:
            return prepareMarkOrUnmark(parsedInput, UnmarkCommand.COMMAND_WORD, taskList);
        case TodoCommand.COMMAND_WORD:
            return prepareAddTodo(parsedInput, taskList);
        case EventCommand.COMMAND_WORD:
            return prepareAddEventOrDeadline(parsedInput, "/at",
                    taskList, EventCommand.COMMAND_WORD);
        case DeadlineCommand.COMMAND_WORD:
            return prepareAddEventOrDeadline(parsedInput, "/by",
                    taskList, DeadlineCommand.COMMAND_WORD);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(parsedInput, taskList);
        case FindCommand.COMMAND_WORD:
            return prepareFind(parsedInput);
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

    private static int selectDefaultTimer(String defaultTimerChoice, Ui ui) {
        switch (defaultTimerChoice) {
        case "1":
            return DEFAULT_TIMER_ONE;
        case "2":
            return DEFAULT_TIMER_TWO;
        case "3":
            return DEFAULT_TIMER_THREE;
        default:
            ui.showToUser("Sorry! I can't recognise the choice you've entered.\n"
                    + "Please re-enter a valid default timer input");
        }
        return -1;
    }

    private static int parseTimerInput(String[] parsedInput, Ui ui) {
        try {
            if (parsedInput[TIMER_FORMAT_INDEX].trim().contains("/custom")) {
                String[] customTimerInput = parsedInput[TIMER_FORMAT_INDEX].split("/custom", 2);
                return Integer.parseInt(customTimerInput[CUSTOM_TIMER_INDEX].trim());
            }
            return selectDefaultTimer(parsedInput[DEFAULT_TIMER_INDEX].trim(), ui);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showToUser("Oops! Your input seems to be missing some commands.\n"
                    + "Please re-enter a valid input.");
        } catch (NumberFormatException e) {
            ui.showToUser("Oops! Your timer input does not seem to be correct.\n"
                    + "Please re-enter a valid input.");
        }
        return -1;
    }

    public static void parseStudyMode(String rawUserInput, Ui ui) {
        String[] parsedInput = rawUserInput.trim().split(" ", 2);
        switch (parsedInput[STUDY_COMMAND_INDEX].trim().toLowerCase()) {
        case "start":
            int duration = parseTimerInput(parsedInput, ui);
            if (duration >= 0) {
                Timer.start(duration, ui);
            }
            break;
        case "pause":
            Timer.pause(ui);
            break;
        case "resume":
            Timer.resume(ui);
            break;
        case "stop":
            Timer.stop(ui);
            break;
        default:
            ui.showToUser(ERROR_INVALID_INPUT_MESSAGE);
        }
    }
}
