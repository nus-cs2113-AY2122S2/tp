package seedu.duke.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.duke.commands.Command;

public class ParserTest extends Parser {
    public ParserTest() {
        // This can be replaced to any regex you want to test
        commandFormat = "(\\s*(?<configurationGroupWord>[A-Z_]+)($|\\b=\\s*(?<newValue>.*)\\b))?";
        groupNames.add("configurationGroupWord");
        groupNames.add("newValue");
    }

    @Test
    public void checkRegex() {
        final String testString = "";
        try {
            parsedCommand = parseString(testString);
            assertEquals(null, parsedCommand.get("configurationGroupWord"));
            assertEquals(null, parsedCommand.get("newValue"));

        } catch (Exception e) {
            fail();
        }
    }


    @Override
    public Command parseCommand(String userInput) {
        return null;
    }
}
