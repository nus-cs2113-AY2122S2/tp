package seedu.sherpass.util;

import seedu.sherpass.command.Command;
import seedu.sherpass.command.DeleteCommand;
import seedu.sherpass.command.HelpCommand;
import seedu.sherpass.command.MarkCommand;
import seedu.sherpass.command.StudyCommand;
import seedu.sherpass.command.AddCommand;
import seedu.sherpass.command.UnmarkCommand;
import seedu.sherpass.command.FindCommand;
import seedu.sherpass.command.ListCommand;
import seedu.sherpass.command.ExitCommand;
import seedu.sherpass.command.ClearCommand;
import seedu.sherpass.exception.InputRepeatedException;
import seedu.sherpass.exception.InvalidInputException;
import seedu.sherpass.task.Task;
import seedu.sherpass.task.TaskList;
import seedu.sherpass.task.Add;

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
import static seedu.sherpass.constant.Index.SAVE_TASK_REMIND_DATE_INDEX;
import static seedu.sherpass.constant.Index.SAVE_TASK_MARK_STATUS;
import static seedu.sherpass.constant.Index.STUDY_COMMAND_INDEX;
import static seedu.sherpass.constant.Index.TASK_CONTENT_INDEX;
import static seedu.sherpass.constant.Index.TASK_DATE_INDEX;
import static seedu.sherpass.constant.Index.TIMER_FORMAT_INDEX;
import static seedu.sherpass.constant.Message.*;


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
        String parsedByDate = "";
        String parsedRemindDate = "";

        if (rawData.length >= 3) {
            try {
                parsedByDate = prepareTaskDate(rawData[SAVE_TASK_BY_DATE_INDEX], true);

                if (rawData.length == 4) {
                    parsedRemindDate = prepareTaskDate(rawData[SAVE_TASK_REMIND_DATE_INDEX], true);
                }

            } catch (DateTimeParseException invalidDate) {
                throw new InvalidInputException();
            }
        }

        parsedData = new Add(rawData[SAVE_TASK_DESCRIPTION_INDEX].trim(), parsedByDate, parsedRemindDate);

        if (rawData[SAVE_TASK_MARK_STATUS].equals("X")) {
            parsedData.markAsDone();
        }

        return parsedData;
    }

    private static Boolean isValidData(String[] rawData) {
        if (!rawData[SAVE_TASK_MARK_STATUS].equals(" ") && !rawData[SAVE_TASK_MARK_STATUS].equals("X")) {
            return false;
        }
        return !rawData[SAVE_TASK_DESCRIPTION_INDEX].isBlank();
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

    private static Command prepareAdd(String[] splitInput, TaskList taskList) {
        String[] filteredTaskContent = null;
        String byDate = null;
        String remindDate = null;
        try {
            if (!splitInput[TASK_CONTENT_INDEX].contains("/by") && !splitInput[TASK_CONTENT_INDEX].contains("/remind")) {
                return new AddCommand(splitInput[TASK_CONTENT_INDEX], taskList, EMPTY_STRING, EMPTY_STRING);
            } else {
                filteredTaskContent = splitInput[TASK_CONTENT_INDEX].split("/by", 2);
                if (!splitInput[1].contains("/remind")) {
                    byDate = prepareTaskDate(filteredTaskContent[1].trim(), false);
                    return new AddCommand(filteredTaskContent[0].trim(), taskList, byDate, EMPTY_STRING);
                } else {
                    String[] filteredDates = filteredTaskContent[1].split("/remind");
                    byDate = prepareTaskDate(filteredDates[0].trim(), false);
                    remindDate = prepareTaskDate(filteredDates[1].trim(), false);
                    return new AddCommand(filteredTaskContent[0], taskList, byDate, remindDate);
                }
            }
        } catch (ArrayIndexOutOfBoundsException | InvalidInputException e) {
            printMissingInputMessage();
        } catch (InputRepeatedException e) {
            //return new UpdateCommand(filteredTaskContent[TASK_DESCRIPTION_INDEX].trim(), byDate);
            System.out.println("repeated description. delete the previous iteration and type out your command again");
        }
        return null;
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
            // converts readable date String to localDate (yyyy-MM-dd) and back to readable date String
            // basically just to check whether the input date is valid or will throw any exception
            if (isParseSaveFile) {
                LocalDate localDate = LocalDate.parse(rawTaskDate, savedTaskNoTimeFormat);
                String readableDate = localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT_WITHOUT_TIME));
                return readableDate;
            }

            // converts localDate of format (yyyy/MM/dd) to String of format (dd MMM yyyy, EEE)
            LocalDate localDate = LocalDate.parse(rawTaskDate, noTimeFormat);
            String readableDate = localDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT_WITHOUT_TIME));
            return readableDate;

        } catch (DateTimeParseException e) {
            return checkCorrectDateFormat(rawTaskDate, isParseSaveFile);
        }
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
        String[] splitInput = userInput.split(" ", 2);
        String commandWord = splitInput[OPTIONS_INDEX].toLowerCase().trim();
        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return prepareMarkOrUnmark(splitInput, MarkCommand.COMMAND_WORD, taskList);
        case UnmarkCommand.COMMAND_WORD:
            return prepareMarkOrUnmark(splitInput, UnmarkCommand.COMMAND_WORD, taskList);
        case AddCommand.COMMAND_WORD:
            return prepareAdd(splitInput, taskList);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(splitInput, taskList);
        case FindCommand.COMMAND_WORD:
            return prepareFind(splitInput);
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
