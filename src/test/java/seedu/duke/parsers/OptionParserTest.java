package seedu.duke.parsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import seedu.duke.exceptions.UnknownConfigurationGroupWordException;
import seedu.duke.exceptions.UnsupportedResultTypeException;

import seedu.duke.util.StringConstants;

//@@author Ch40gRv1-Mu
public class OptionParserTest {
    private OptionParser optionParser;

    private void testParseCommand_expectUnknownConfigGroupWordException(String testString) {
        assertThrows(UnknownConfigurationGroupWordException.class, () -> {
            optionParser.parseCommand(testString);
        });
    }

    private void testParseCommand_expectUnsupportedResultTypeException(String testString) {
        assertThrows(UnsupportedResultTypeException.class, () -> {
            optionParser.parseCommand(testString);
        });
    }

    @BeforeEach
    public void setUp() {
        optionParser = new OptionParser();
    }

    @Test
    public void parse_emptyArgument() {
        final String testString = "";
        try {
            optionParser.parseCommand(testString);
            assertNull(optionParser.parsedCommand.get("configurationGroupWord"));
            assertNull(optionParser.parsedCommand.get("newValue"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_legalConfigName() {
        final String testString = StringConstants.SHOW_COMPLETED_TASKS_NAME;
        try {
            optionParser.parseCommand(testString);
            assertEquals(StringConstants.SHOW_COMPLETED_TASKS_NAME,
                    optionParser.parsedCommand.get("configurationGroupWord"));
            assertNull(optionParser.parsedCommand.get("newValue"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_legalConfigNameAndLegalValue() {
        final String testString = StringConstants.SHOW_COMPLETED_TASKS_NAME + "=" + StringConstants.TRUE;
        try {
            optionParser.parseCommand(testString);
            assertEquals(StringConstants.SHOW_COMPLETED_TASKS_NAME,
                    optionParser.parsedCommand.get("configurationGroupWord"));
            assertEquals(StringConstants.TRUE, optionParser.parsedCommand.get("newValue"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_badConfigName() {
        final String testString = "ILLEGAL_TASK_SHOWN";
        testParseCommand_expectUnknownConfigGroupWordException(testString);
    }

    @Test
    public void parse_legalConfigNameAndBadValue() {
        final String testString = StringConstants.SHOW_COMPLETED_TASKS_NAME + "=true1";
        testParseCommand_expectUnsupportedResultTypeException(testString);
    }

    @Test
    public void parse_legalConfigNameAndLegalValue_withExtraWhitespace() {
        final String testString = StringConstants.SHOW_COMPLETED_TASKS_NAME + "=" + StringConstants.TRUE + " ";
        try {
            optionParser.parseCommand(testString);
            assertEquals(StringConstants.SHOW_COMPLETED_TASKS_NAME,
                    optionParser.parsedCommand.get("configurationGroupWord"));
            assertEquals(StringConstants.TRUE, optionParser.parsedCommand.get("newValue"));
        } catch (Exception e) {
            fail();
        }
    }
}
