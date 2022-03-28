package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.ExitCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ExitCommandParserTest {

    private static final String COMMAND_TYPE = ExitCommandParser.COMMAND_TEXT;

    /**
     * Checks if a ExitCommand object is correctly returned when the command is correctly entered.
     */
    @Test
    void getCommand_validUserInput_HelpCommand() {
        String validUserInput = "exit";
        String remainingArgs = Parser.getRemainingArgument(validUserInput);
        String errorMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, remainingArgs);
        if (!errorMessage.isEmpty()) {
            fail();
        }

        ExitCommandParser exitCommandParser = new ExitCommandParser();
        try {
            Command command = exitCommandParser.getCommand(remainingArgs);
            assertEquals(ExitCommand.class, command.getClass());
        } catch (InvalidFormatException exception) {
            fail();
        }
    }
}
