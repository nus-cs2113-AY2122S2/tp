package seedu.duke.parsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import seedu.duke.exceptions.UnkownConfigurationGroupWord;
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
    public void parse_empty_argument() {
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
    public void parse_legal_configuration_group_word_only_argument() {
        final String testString = "COMPLETED_TASK_SHOWN";
        try {
            optionParser.parseCommand(testString);
            assertEquals("COMPLETED_TASK_SHOWN", optionParser.parsedCommand.get("configurationGroupWord"));
            assertNull(optionParser.parsedCommand.get("newValue"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_legal_configuration_group_word_and_new_value_argument() {
        final String testString = "COMPLETED_TASK_SHOWN=true";
        try {
            optionParser.parseCommand(testString);
            assertEquals("COMPLETED_TASK_SHOWN", optionParser.parsedCommand.get("configurationGroupWord"));
            assertEquals("true", optionParser.parsedCommand.get("newValue"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_illegal_configuration_group_word_only_argument() {
        final String testString = "ILLEGAL_TASK_SHOWN";
        try {
            optionParser.parseCommand(testString);
            fail();
        } catch (UnkownConfigurationGroupWord e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_legal_configuration_group_word_with_illegal_new_value_argument() {
        final String testString = "COMPLETED_TASK_SHOWN=true1";
        try {
            optionParser.parseCommand(testString);
            fail();
        } catch (UnkownConfigurationGroupWord e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }


}
