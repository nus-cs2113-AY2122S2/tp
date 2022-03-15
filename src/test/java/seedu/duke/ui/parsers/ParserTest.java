package seedu.duke.ui.parsers;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.exceptions.ParseException;
import seedu.duke.parsers.Parser;


public class ParserTest extends Parser {
    public ParserTest() {
        // This can be replaced to any regex you want to test
        commandFormat = "(?<commandWord>\\S+)\\s*(?<arguments>.*)";
        groupNames.add("commandWord");
        groupNames.add("arguments");
    }

    @Test
    public void checkRegex() {
        final String testString = "add /m cs2113t -d \"11111\"123";
        try {
            parsedCommand = parseString(testString);
            assertEquals("add", parsedCommand.get("commandWord"));
            assertEquals("/m cs2113t -d \"11111\"123", parsedCommand.get("arguments"));
        } catch (Exception e) {
            fail();
        }

    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        return null;
    }
}
