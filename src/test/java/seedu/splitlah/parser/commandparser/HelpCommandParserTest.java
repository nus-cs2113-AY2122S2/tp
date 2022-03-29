package seedu.splitlah.parser.commandparser;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.command.HelpCommand;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.parser.ParserUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class HelpCommandParserTest {
    
    private static final String COMMAND_TYPE = HelpCommandParser.COMMAND_TEXT;
    
    /**
     * Checks if a HelpCommand object is correctly returned when the command is correctly entered.
     */
    @Test
    void getCommand_validUserInput_HelpCommand() {
        String validUserInput = "help";
        String remainingArgs = Parser.getRemainingArgument(validUserInput);
        String errorMessage = ParserUtils.checkIfCommandIsValid(COMMAND_TYPE, remainingArgs);
        if (!errorMessage.isEmpty()) {
            fail();
        }
        
        HelpCommandParser helpCommandParser = new HelpCommandParser();
        try {
            Command command = helpCommandParser.getCommand(remainingArgs);
            assertEquals(HelpCommand.class, command.getClass());
        } catch (InvalidFormatException exception) {
            fail();
        }
    }
}
