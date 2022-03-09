package seedu.duke.ui.parsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.MarkCommand;
import seedu.duke.exceptions.ParseException;
import seedu.duke.exceptions.UnknownCommandException;
import seedu.duke.parsers.ModHappyParser;
import seedu.duke.tasks.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private ModHappyParser parser;

    @BeforeEach
    public void setUp() {
        parser = new ModHappyParser();
    }

    @Test
    public void parse_unrecognisedCommand_throwsException() {
        final String testString = "invalid command";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (UnknownCommandException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_invalidFlag() {
        final String testString = "add /a blahblah -d blahblahblah";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_noDescription_parsedCorrectly() {
        final String testString = "add /t /t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d   ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertNull(t.getTaskDescription());
            assertNull(t.getEstimatedWorkingTime());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_withDescription_parsedCorrectly() {
        final String testString = "add /t /t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d   "
                + "-d \"-d-d-d /t /m -d -d  \"";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertEquals("-d-d-d /t /m -d -d", t.getTaskDescription());
            assertNull(t.getEstimatedWorkingTime());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_withWorkingTime_parsedCorrectly() {
        final String testString = "add /t /t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d   "
                + "-t \"-d-d-d /t /m -d -d  \"";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertEquals("-d-d-d /t /m -d -d", t.getEstimatedWorkingTime());
            assertNull(t.getTaskDescription());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_withDescription_withWorkingTime_parsedCorrectly() {
        final String testString = "add /t /t/t/t/t-d -d \"-d-d-d /t /m -d -d  \" "
                + "-t \"-t-t-t t-t-t /t/t -d -d -d \"";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d", t.getTaskName());
            assertEquals("-d-d-d /t /m -d -d", t.getTaskDescription());
            assertEquals("-t-t-t t-t-t /t/t -d -d -d", t.getEstimatedWorkingTime());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_withDescription_withWorkingTime_wrongOrder_Incorrect() {
        final String testString = "add /t /t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d   "
                + "-t \"-t-t-t t-t-t /t/t -d -d -d \" "
                + "-d \"-d-d-d /t /m -d -d  \" ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertNotEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertEquals("-d-d-d /t /m -d -d", t.getTaskDescription());
            assertNotEquals("-t-t-t t-t-t /t/t -d -d -d", t.getEstimatedWorkingTime());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_invalidFlag() {
        final String testString = "mark /a 1234";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_noFlagProvided() {
        final String testString = "mark 123";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_noIndexProvided() {
        final String testString = "mark /c";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_notANumber() {
        final String testString = "mark /c iamnotanumber";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_parsedCorrectly() {
        final String testString = "mark /c 3";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof MarkCommand);
            assertEquals(2, ((MarkCommand) c).getTaskIndex()); // Remember, zero-indexed!
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_listCommand_unnecessaryArgs() {
        final String testString = "list blahblah";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_listCommand_parsedCorrectly() {
        final String testString = "list";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof ListCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_exitCommand_unnecessaryArgs() {
        final String testString = "exit blahblah";
        try {
            parser.parseCommand(testString);
            fail();
        } catch (ParseException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_exitCommand_parsedCorrectly() {
        final String testString = "exit";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof ExitCommand);
        } catch (Exception e) {
            fail();
        }
    }
}



