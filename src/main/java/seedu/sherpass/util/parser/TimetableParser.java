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

    /**
     * Parses the timetable command from the main Parser class.
     *
     * @param splitInput The string input given by user in array format.
     * @return A function call to decode the specific type of Show Command.
     */
    public static Command prepareShow(String[] splitInput) {
        try {
            String selection = splitInput[SHOW_OPTION_INDEX].trim();
            return parseShowCommandOptions(selection.toLowerCase());
        } catch (ArrayIndexOutOfBoundsException | InvalidInputException e) {
            System.out.println(ERROR_INVALID_INPUT_MESSAGE);
        }
        return null;
    }

    /**
     * Returns the specific type of Show Command Object.
     *
     * @param selection The specific type of Show Command given by the user.
     * @return The specific Show Command Object.
     * @throws InvalidInputException If the specific type of Show Command given by the user is empty.
     */
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
