package seedu.duke.ui.parsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
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
    public void parse_addCommand_withDescription_withWorkingTime_differentOrder() {
        final String testString = "add /t /t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d   "
                + "-t \"-t-t-t t-t-t /t/t -d -d -d \" "
                + "-d \"-d-d-d /t /m -d -d  \" ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertEquals("-d-d-d /t /m -d -d", t.getTaskDescription());
            assertEquals("-t-t-t t-t-t /t/t -d -d -d", t.getEstimatedWorkingTime());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_invalidInput() {
        final String testString = "add /t 000 -d \"123\" -t \"456\" invalid";
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
    public void parse_addCommand_duplicateTaskDescription() {
        final String testString = "add /t 000 -d \"123\" -t \"456\" -d \"789\"";
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
    public void parse_addCommand_duplicateWorkingTime() {
        final String testString = "add /t 000 -t \"123\" -d \"456\" -t \"789\"";
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
    public void parse_addCommand_noFlagProvided() {
        final String testString = "add cs2113t";
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
    public void parse_addCommand_withModuleOnly_noModuleProvided() {
        final String testString = "add /m";
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
    public void parse_addCommand_withTaskOnly_noTaskProvided() {
        final String testString = "add /t";
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
    public void parse_deleteCommand_withTaskOnly_parsedCorrectly() {
        final String testString = "del /t 1";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof DeleteCommand);
            assertEquals(1, ((DeleteCommand) c).getTaskNumber());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_withModuleOnly_parsedCorrectly() {
        final String testString = "del /m CS2113T";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof DeleteCommand);
            assertEquals("CS2113T", ((DeleteCommand) c).getModuleCode());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_invalidFlag() {
        final String testString = "del /a 1";
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
    public void parse_deleteCommand_noFlagProvided() {
        final String testString = "del 1";
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
    public void parse_deleteCommand_withModuleOnly_noModuleProvided() {
        final String testString = "del /m";
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
    public void parse_deleteCommand_withTaskOnly_noIndexProvided() {
        final String testString = "del /t";
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
    public void parse_deleteCommand_withTaskOnly_notANumber() {
        final String testString = "del /t iamnotanumber";
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
    public void parse_deleteCommand_unnecessaryArgs() {
        final String testString = "del /t 1 blahblah";
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
    public void parse_markCommand_invalidFlag() {
        final String testString = "mark /a 1";
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
        final String testString = "mark 1";
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
    public void parse_markCommand_unnecessaryArgs() {
        final String testString = "mark /c 1 blahblah";
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
    public void parse_exitCommand_parsedCorrectly() {
        final String testString = "exit";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof ExitCommand);
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
}



