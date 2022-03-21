package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.ResetCommand;
import seedu.duke.commands.SaveCommand;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;

/**
 * This Parser supports all commands which do not accept any additional arguments or parameters.
 */
public class NoArgumentParser extends Parser {
    private final String myCommandWord;

    public NoArgumentParser(String commandWord) {
        myCommandWord = commandWord;
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        // NoArgumentParser commands strictly take no input.
        if (userInput.length() != 0) {
            throw new ParseException();
        }
        switch (myCommandWord) {
        case (EXIT_COMMAND_WORD):
            return new ExitCommand();
        case (RESET_COMMAND_WORD):
            return new ResetCommand();
        case (SAVE_COMMAND_WORD):
            return new SaveCommand();
        default:
            throw new ParseException();
        }
    }
}
