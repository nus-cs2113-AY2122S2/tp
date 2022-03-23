package seedu.duke.parsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import seedu.duke.exceptions.UnknownConfigurationGroupWord;
import seedu.duke.util.Configuration;



public class OptionParserTest {
    private OptionParser optionParser;
    private Configuration configuration;

    @BeforeEach
    public void setUp() {
        optionParser = new OptionParser();
        configuration = new Configuration();
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
    public void parse_configName() {
        final String testString = "COMPLETED_TASKS_SHOWN";
        try {
            optionParser.parseCommand(testString);
            assertEquals("COMPLETED_TASKS_SHOWN", optionParser.parsedCommand.get("configurationGroupWord"));
            assertNull(optionParser.parsedCommand.get("newValue"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_configNameAndValue() {
        final String testString = "COMPLETED_TASKS_SHOWN=true";
        try {
            optionParser.parseCommand(testString);
            assertEquals("COMPLETED_TASKS_SHOWN", optionParser.parsedCommand.get("configurationGroupWord"));
            assertEquals("true", optionParser.parsedCommand.get("newValue"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseIllegal_badConfigName() {
        final String testString = "ILLEGAL_TASK_SHOWN";
        try {
            optionParser.parseCommand(testString);
            fail();
        } catch (UnknownConfigurationGroupWord e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parseIllegal_configNameAndBadValue() {
        final String testString = "COMPLETED_TASKS_SHOWN=true1";
        try {
            optionParser.parseCommand(testString);
            fail();
        } catch (UnknownConfigurationGroupWord e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }
}
