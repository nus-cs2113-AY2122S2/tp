package seedu.duke.utils;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Contains helper methods for testing command parsers.
 */
public class ParserTestUtils {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(Parser<? extends Command> parser, String userInput, String expectedMessage) {
        try {
            parser.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (InvMgrException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(Parser<? extends Command> parser, String userInput, Command expectedCommand) {
        try {
            Command command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (InvMgrException e) {
            throw new IllegalArgumentException("Invalid userInput.", e);
        }
    }
}
