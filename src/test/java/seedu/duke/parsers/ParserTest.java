package seedu.duke.parsers;

import org.junit.jupiter.api.Test;

import seedu.duke.commands.Command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserTest extends Parser {
    public ParserTest() {
        // This can be replaced to any regex you want to test
        commandFormat = "((?<configurationGroupWord>[A-Z_]+)(=(?<newValue>\\w+))?)?(?<invalid>.*)";
        groupNames.add("configurationGroupWord");
        groupNames.add("newValue");
    }

    @Test
    public void checkRegex() {
        final String testString = "COMPLETED_TASK_SHOWN";
        try {
            parsedCommand = parseString(testString);
            assertEquals("COMPLETED_TASK_SHOWN", parsedCommand.get("configurationGroupWord"));
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
