package seedu.sherpass.util.parser;

import seedu.sherpass.command.Command;
import seedu.sherpass.command.ClearCommand;
import seedu.sherpass.command.ExitCommand;
import seedu.sherpass.command.HelpCommand;
import seedu.sherpass.command.StudyCommand;

import seedu.sherpass.util.Ui;

import static seedu.sherpass.constant.Index.INDEX_COMMAND_CONTENT;
import static seedu.sherpass.constant.Index.INDEX_HELP_OPTIONS;
import static seedu.sherpass.constant.Index.INDEX_OPTIONS;
import static seedu.sherpass.constant.Index.SPLIT_TWO_PART_LIMIT;
import static seedu.sherpass.constant.Message.EMPTY_STRING;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INPUT_MESSAGE;
import static seedu.sherpass.constant.Message.WHITESPACE;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_ADD;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_CLEAR;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_DELETE;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_EDIT;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_EXIT;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_HELP;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_SHOW;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_MARK;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_UNMARK;
import static seedu.sherpass.constant.CommandMessage.COMMAND_WORD_STUDY;

public class Parser {

    private static Command prepareHelp(String userInput) {
        try {
            String[] parsedInput = userInput.split(" ", 2);
            return new HelpCommand(parsedInput[INDEX_HELP_OPTIONS]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return new HelpCommand("show help list");
        }
    }

    /**
     * Parses the user command input.
     *
     * @param userInput User command.
     * @return Command type matching the user command.
     */
    public static Command parseCommand(String userInput, Ui ui) {
        String[] splitInput = userInput.split(WHITESPACE, SPLIT_TWO_PART_LIMIT);
        String commandWord = splitInput[INDEX_OPTIONS].toLowerCase().trim();
        String argument = (splitInput.length > 1)
                ? splitInput[INDEX_COMMAND_CONTENT].trim() : EMPTY_STRING;
        switch (commandWord) {
        case COMMAND_WORD_MARK:
            // Fallthrough
        case COMMAND_WORD_UNMARK:
            return TaskParser.prepareMarkOrUnmark(argument, commandWord, ui);
        case COMMAND_WORD_ADD:
            return TaskParser.prepareAdd(argument, ui);
        case COMMAND_WORD_EDIT:
            return TaskParser.prepareEdit(argument, ui);
        case COMMAND_WORD_DELETE:
            return TaskParser.prepareDelete(argument, ui);
        case COMMAND_WORD_CLEAR:
            return new ClearCommand(argument);
        case COMMAND_WORD_STUDY:
            return new StudyCommand();
        case COMMAND_WORD_SHOW:
            return TimetableParser.prepareShow(splitInput);
        case COMMAND_WORD_HELP:
            return prepareHelp(userInput);
        case COMMAND_WORD_EXIT:
            return new ExitCommand();
        default:
            ui.showToUser(ERROR_INVALID_INPUT_MESSAGE);
            return null;
        }
    }
}
