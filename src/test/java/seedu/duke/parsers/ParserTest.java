package seedu.duke.parsers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import seedu.duke.exceptions.GeneralParseException;
import seedu.duke.commands.Command;

//@@author  Ch40gRv1-Mu
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
    public void determineError() throws GeneralParseException {
        throw new GeneralParseException();
    }

    @Override
    public Command parseCommand(String userInput) {
        return null;
    }
}
