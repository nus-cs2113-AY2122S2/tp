package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.Timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void prepareDeleteCommand_InvalidIndex_throwException() {
        Timetable timetable = new Timetable();
        String inputString = "delete hello";
        Parser parser = new Parser(inputString);
        Command command = parser.parseCommand();
        assertEquals(HelpCommand.class,command.getClass());
    }
}
