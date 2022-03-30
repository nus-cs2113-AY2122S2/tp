package seedu.duke.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.duke.commands.Command;

public class ParserTest extends Parser {
    public ParserTest() {
        // This can be replaced to any regex you want to test
        commandFormat = "(?<duration>[1-9]\\d*\\.?\\d*|0\\.\\d*[1-9])\\s*(?<durationUnit>\\bm\\b|\\bmin\\b|\\bMin\\b|\\bminutes\\b|\\bMinutes\\b|\\bminute\\b|\\bMinute\\b|\\bh\\b|\\bH\\b|\\bhours\\b|\\bHours\\b|\\bhour\\b|\\bHour\\b|\\Bm\\b|\\Bmin\\b|\\BMin\\b|\\Bminutes\\b|\\BMinutes\\b|\\Bminute\\b|\\BMinute\\b|\\Bh\\b|\\BH\\b|\\Bhours\\b|\\BHours\\b|\\Bhour\\b|\\BHour\\b)";
        groupNames.add("duration");
        groupNames.add("durationUnit");
    }

    @Test
    public void checkRegex() {
        final String testString = "1.5h";
        try {
            parsedCommand = parseString(testString);
            assertEquals("1.5", parsedCommand.get("duration"));
            assertEquals("h", parsedCommand.get("durationUnit"));

        } catch (Exception e) {
            fail();
        }
    }


    @Override
    public Command parseCommand(String userInput) {
        return null;
    }
}
