package seedu.duke.ui.parsers;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.Command;
import seedu.duke.parsers.ModHappyParser;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

public class ModHappyParserTest {
    @Test
    public void testSample() {
        try {
            ModHappyParser modHappyParserTest = new ModHappyParser();
            Command command = modHappyParserTest.parseCommand("exit");
            assertEquals("exit",command.getCommandName());
            command = modHappyParserTest.parseCommand("add /t CS2113T Assignment");
            assertEquals("add",command.getCommandName());
            command = modHappyParserTest.parseCommand("mark 1");
            assertEquals("mark",command.getCommandName());

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
