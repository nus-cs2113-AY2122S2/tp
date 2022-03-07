package seedu.duke.ui.parsers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.commands.Command;
import seedu.duke.exceptions.ModHappyException;
import seedu.duke.parsers.Parser;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest extends Parser {

    public ParserTest() {
        super();
    }

    @Override
    public Command parseCommand(String userInput) throws ModHappyException {
        return null;
    }

    @Test
    public void sampleTest() throws ModHappyException {
        // Test parser for "add" parameter
        try {
            commandFormat = "\\s*(?<flag>(\\\\(m|t)))\\s+(?<argument1>[^\\-]*)\\s*"
                    + "((?<subFlag>\\-(\\bmod|d\\b))\\s+(?<argument2>.+))*";
            groupNames.clear();

            // add module with -d
            groupNames.clear();
            groupNames.add("flag");
            groupNames.add("argument1");
            groupNames.add("subFlag");
            groupNames.add("argument2");
            HashMap<String, String> parsedCommand = parseString("\\m CS2113T -d Software Engineer");
            assertEquals("\\m", parsedCommand.get("flag"));
            assertEquals("CS2113T", parsedCommand.get("argument1"));
            assertEquals("-d", parsedCommand.get("subFlag"));
            assertEquals("Software Engineer", parsedCommand.get("argument2"));

            // add module without -d
            groupNames.clear();
            groupNames.add("flag");
            groupNames.add("argument1");
            parsedCommand = parseString("\\m CS2113T");
            assertEquals("\\m", parsedCommand.get("flag"));
            assertEquals("CS2113T", parsedCommand.get("argument1"));

            // add task without -mod
            groupNames.clear();
            groupNames.add("flag");
            groupNames.add("argument1");
            groupNames.add("subFlag");
            groupNames.add("argument2");
            parsedCommand = parseString("\\t CS2113T Assignment -mod CS2113T");
            assertEquals("\\t", parsedCommand.get("flag"));
            assertEquals("CS2113T Assignment", parsedCommand.get("argument1"));
            assertEquals("-mod", parsedCommand.get("subFlag"));
            assertEquals("CS2113T", parsedCommand.get("argument2"));
        } catch (ModHappyException e) {
            throw e;
        }
    }


}



