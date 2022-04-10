package seedu.duke.parsers;

import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.GpaCommand;
import seedu.duke.commands.ResetCommand;
import seedu.duke.commands.SaveCommand;
import seedu.duke.exceptions.AdditionalParameterException;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.GeneralParseException;

//@@author chooyikai
/**
 * This Parser supports all commands which do not accept any additional arguments or parameters.
 */
public class NoArgumentParser extends Parser {
    private final String myCommandWord;

    public NoArgumentParser(String commandWord) {
        myCommandWord = commandWord;
    }

    @Override
    public void determineError() throws GeneralParseException {
        throw new GeneralParseException();
    }

    /**
     * Determines the command based on the command word.
     * @param userInput An empty string
     * @return A new {@code Command} object
     * @throws ModHappyException If there is an error parsing the command
     */
    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        // NoArgumentParser commands strictly take no input.
        if (userInput.length() != 0) {
            throw new AdditionalParameterException();
        }
        switch (myCommandWord) {
        case (EXIT_COMMAND_WORD):
            return new ExitCommand();
        case (GPA_COMMAND_WORD):
            return new GpaCommand();
        case (RESET_COMMAND_WORD):
            return new ResetCommand();
        case (SAVE_COMMAND_WORD):
            return new SaveCommand();
        default:
            throw new GeneralParseException();
        }
    }
}
