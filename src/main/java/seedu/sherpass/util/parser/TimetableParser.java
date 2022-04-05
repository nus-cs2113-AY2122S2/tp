package seedu.sherpass.util.parser;

import seedu.sherpass.command.Command;
import seedu.sherpass.command.ShowCommand;
import seedu.sherpass.exception.InvalidInputException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import static seedu.sherpass.constant.DateAndTimeFormat.inputWithoutTimeFormat;
import static seedu.sherpass.constant.Index.SHOW_OPTION_INDEX;
import static seedu.sherpass.constant.Message.ERROR_INVALID_INPUT_MESSAGE;

public class TimetableParser {

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
            LocalDate dayInput = LocalDate.parse(selection,
                    inputWithoutTimeFormat.withResolverStyle(ResolverStyle.STRICT));
            return new ShowCommand(dayInput, null);
        } catch (DateTimeParseException e) {
            return new ShowCommand(null, selection);
        }
    }
}
