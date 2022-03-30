package seedu.duke.parsers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import seedu.duke.commands.Command;

public class ParserTest extends Parser {
    public ParserTest() {
        // This can be replaced to any regex you want to test
        commandFormat = "(\\s*(?<configurationGroupWord>[A-Z_]+)($|=\\\"\\b(?<newValue>.*)\\b\\\"))?";
        groupNames.add("configurationGroupWord");
        groupNames.add("newValue");
    }

    @Test
    public void checkRegex() {
        final String testString = "COMPLETED_TASKS_SHOWN";
        try {
            parsedCommand = parseString(testString);
            assertEquals("COMPLETED_TASKS_SHOWN", parsedCommand.get("configurationGroupWord"));
            assertEquals(null, parsedCommand.get("newValue"));
            assertNull(parsedCommand.get("newValue"));

        } catch (Exception e) {
            fail();
        }
    }


    @Override
    public Command parseCommand(String userInput) {
        return null;
    }
}
