package seedu.sherpass;


import seedu.sherpass.command.Command;
import seedu.sherpass.command.DeadlineCommand;
import seedu.sherpass.command.DeleteCommand;
import seedu.sherpass.command.EventCommand;
import seedu.sherpass.command.HelpCommand;
import seedu.sherpass.command.MarkCommand;
import seedu.sherpass.command.TodoCommand;
import seedu.sherpass.command.UnmarkCommand;
import seedu.sherpass.command.UpdateCommand;
import seedu.sherpass.command.FindCommand;
import seedu.sherpass.command.ListCommand;
import seedu.sherpass.command.ExitCommand;
import seedu.sherpass.command.ClearCommand;
import seedu.sherpass.exception.InputRepeatedException;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.task.Deadline;
import seedu.sherpass.task.Event;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static seedu.sherpass.constant.DateAndTimeFormat.noTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.savedTaskNoTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.savedTaskWithTimeFormat;
import static seedu.sherpass.constant.DateAndTimeFormat.withTimeFormat;
import static seedu.sherpass.constant.Indexes.FIND_BY_TASK_CONTENT_INDEX;
import static seedu.sherpass.constant.Indexes.FIND_BY_TASK_DATE_INDEX;
import static seedu.sherpass.constant.Indexes.FIND_BY_TASK_DESCRIPTION_NO_DATE_INDEX;
import static seedu.sherpass.constant.Indexes.FIND_BY_TASK_DESCRIPTION_WITH_DATE_INDEX;
import static seedu.sherpass.constant.Indexes.HELP_OPTIONS_INDEX;
import static seedu.sherpass.constant.Indexes.MARK_INDEX;
import static seedu.sherpass.constant.Indexes.OPTIONS_INDEX;
import static seedu.sherpass.constant.Indexes.SAVE_TASK_DATE_INDEX;
import static seedu.sherpass.constant.Indexes.SAVE_TASK_DESCRIPTION_INDEX;
import static seedu.sherpass.constant.Indexes.SAVE_TASK_MARK_STATUS;
import static seedu.sherpass.constant.Indexes.SAVE_TASK_TYPE_INDEX;
import static seedu.sherpass.constant.Indexes.TASK_CONTENT_INDEX;
import static seedu.sherpass.constant.Indexes.TASK_DATE_INDEX;
import static seedu.sherpass.constant.Indexes.TASK_DESCRIPTION_INDEX;
import static seedu.sherpass.constant.Messages.DATE_FORMAT_WITHOUT_TIME;
import static seedu.sherpass.constant.Messages.DATE_FORMAT_WITH_TIME;
import static seedu.sherpass.constant.Messages.ERROR_DEADLINE_MISSING_COMMAND_MESSAGE;
import static seedu.sherpass.constant.Messages.ERROR_EVENT_MISSING_COMMAND_MESSAGE;
import static seedu.sherpass.constant.Messages.ERROR_INVALID_DELETE_INDEX_MESSAGE;
import static seedu.sherpass.constant.Messages.ERROR_INVALID_INPUT_MESSAGE;
import static seedu.sherpass.constant.Messages.ERROR_INVALID_MARKING_INDEX_MESSAGE;
import static seedu.sherpass.constant.Messages.ERROR_TODO_REPEATED_INPUT_MESSAGE;
import static seedu.sherpass.constant.Messages.HELP_MESSAGE_SPECIFIC_COMMAND;

public class Parser {

    private static boolean isReadTaskEmpty(Task parsedData) {
        switch (parsedData.getType()) {
        case "T":
            return parsedData.getDescription().isBlank();
        case "D":
            // fallthrough since Deadline and Event have the same getters
        case "E":
            return parsedData.getDescription().isBlank() || parsedData.getDate().isBlank();
        default:
            return false;
        }
    }


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
        Task parsedData;
        if (!rawData[SAVE_TASK_MARK_STATUS].equals("0") && !rawData[SAVE_TASK_MARK_STATUS].equals("1")) {
            throw new InvalidInputException();
        }
        String parsedDate = null;
        if (!rawData[SAVE_TASK_TYPE_INDEX].isBlank()) {
            parsedDate = prepareTaskDate(rawData[SAVE_TASK_DATE_INDEX].trim(), true);
        }
        switch (rawData[SAVE_TASK_TYPE_INDEX].trim()) {
        case "T":
            parsedData = new Todo(rawData[SAVE_TASK_DESCRIPTION_INDEX].trim());
            break;
        case "D":
            parsedData = new Deadline(rawData[SAVE_TASK_DESCRIPTION_INDEX].trim(),
                    parsedDate);
            break;
        case "E":
            parsedData = new Event(rawData[SAVE_TASK_DESCRIPTION_INDEX].trim(),
                    parsedDate);
            break;
        default:
            throw new InvalidInputException();
        }
        if (isReadTaskEmpty(parsedData)) {
            throw new InvalidInputException();
        }
        if (rawData[SAVE_TASK_MARK_STATUS].equals("1")) {
            parsedData.markAsDone();
        }
        return parsedData;
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
     * @param taskList Array of tasks
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
        case HelpCommand.COMMAND_WORD:
            return prepareHelp(userInput);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            System.out.println(ERROR_INVALID_INPUT_MESSAGE);
            return null;
        }
    }

}
