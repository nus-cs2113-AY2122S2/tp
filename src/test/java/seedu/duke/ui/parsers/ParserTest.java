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
import seedu.duke.tasks.Module;
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
    public void parse_addCommand_task_noDescription_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d \"  ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertNull(t.getTaskDescription());
            assertNull(t.getEstimatedWorkingTime());
            assertNull(((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d \"  "
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
            assertNull(((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withWorkingTime_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d \"  "
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
            assertNull(((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withTargetModule_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d \"  "
                + "-m cs2113t";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertNull(t.getTaskDescription());
            assertNull(t.getEstimatedWorkingTime());
            assertEquals("cs2113t", ((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withTargetModule_invalidModuleCode() {
        final String testString = "add /t \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d \"  "
                + "-m cs 2113 t";
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
    public void parse_addCommand_task_withDescription_withWorkingTime_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d\" -d \"-d-d-d /t /m -d -d  \" "
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
            assertNull(((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_withWorkingTime_wrongOrder() {
        final String testString = "add /t \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d\"   "
                + "-t \"-t-t-t t-t-t /t/t -d -d -d \" "
                + "-d \"-d-d-d /t /m -d -d  \" ";
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
    public void parse_addCommand_task_withDescription_withTargetModule_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d\" -d \"-d-d-d /t /m -d -d  \" "
                + "-m cs2113t";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d", t.getTaskName());
            assertEquals("-d-d-d /t /m -d -d", t.getTaskDescription());
            assertNull(t.getEstimatedWorkingTime());
            assertEquals("cs2113t", ((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_withTargetModule_wrongOrder() {
        final String testString = "add /t \"/t/t/t/t-d\" -m cs2113t "
                + "-d \"-d-d-d /t /m -d -d  \"";
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
    public void parse_addCommand_task_withWorkingTime_withTargetModule_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d\" -t \"-d-d-d /t /m -d -d  \" "
                + "-m cs2113t    ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d", t.getTaskName());
            assertNull(t.getTaskDescription());
            assertEquals("-d-d-d /t /m -d -d", t.getEstimatedWorkingTime());
            assertEquals("cs2113t", ((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withWorkingTime_withTargetModule_wrongOrder() {
        final String testString = "add /t \"/t/t/t/t-d\" -m cs2113t "
                + "-t \"-d-d-d /t /m -d -d  \"";
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
    public void parse_addCommand_task_withDescription_withWorkingTime_withTargetModule_parsedCorrectly() {
        final String testString = "add /t \"/t/t/t/t-d\" -d \"-d-d-t-m /m -m -d -t  \" -t \"-d-d-d /t /m -d -d  \" "
                + "-m cs2113t";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d", t.getTaskName());
            assertEquals("-d-d-t-m /m -m -d -t", t.getTaskDescription());
            assertEquals("-d-d-d /t /m -d -d", t.getEstimatedWorkingTime());
            assertEquals("cs2113t", ((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_withWorkingTime_withTargetModule_wrongOrder1() {
        final String testString = "add /t \"/t/t/t/t-d\" -t \"-d-d-t-m /m -m -d -t  \" -d \"-d-d-d /t /m -d -d  \" "
                + "-m cs2113t";
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
    public void parse_addCommand_task_withDescription_withWorkingTime_withTargetModule_wrongOrder2() {
        final String testString = "add /t \"/t/t/t/t-d\" -t \"-d-d-t-m /m -m -d -t  \" -m cs2113t"
                + "-d \"-d-d-d /t /m -d -d  \" ";
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
    public void parse_addCommand_task_withDescription_withWorkingTime_withTargetModule_wrongOrder3() {
        final String testString = "add /t \"/t/t/t/t-d\" -m cs2113t   -d \"-d -d-t-t -t -m -m -m /m/m\""
                + "  -t \" -d-d -t /m -m -m-d -t -m\"";
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
    public void parse_addCommand_module_noDescription_parsedCorrectly() {
        final String testString = "add  \t /m modulecode \t\t    ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Module m = ((AddCommand) c).getNewModule();
            assertNotEquals(null, m);
            assertNull(((AddCommand) c).getNewTask());
            assertEquals("modulecode", m.getModuleCode());
            assertNull(m.getModuleDescription());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_module_noDescription_invalidModuleCode() {
        final String testString = "add  \t /m module code \t\t    ";
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
    public void parse_addCommand_module_withDescription_parsedCorrectly() {
        final String testString = "add  \t /m modu__lec_ode \t\t    -d \t\t  \t \"i am a descrip\t -d-d tion\t \"\t  ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Module m = ((AddCommand) c).getNewModule();
            assertNotEquals(null, m);
            assertNull(((AddCommand) c).getNewTask());
            assertEquals("modu__lec_ode", m.getModuleCode());
            assertEquals("i am a descrip\t -d-d tion", m.getModuleDescription());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_module_withDescription_invalidModuleCode() {
        final String testString = "add  \t /m module code \t\t    -d \t\t  \t \"i am a descrip\t -d-d tion\t \"\t  ";
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
    public void parse_addCommand_module_withDescription_invalidInput() {
        final String testString = "add /m cs2113t -d \"11111\"123";
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
        final String testString = "add /a \"blahblah\" -d \"blahblahblah\"";
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
    public void parse_deleteCommand_withTaskOnly_integerOverflow() {
        final String testString = "del /t 2147483648";
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
    public void parse_deleteCommand_withTask_withTargetModule_parsedCorrectly() {
        final String testString = "del /t 1 -m cs2113t";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof DeleteCommand);
            assertEquals(1, ((DeleteCommand) c).getTaskNumber());
            assertEquals("cs2113t", ((DeleteCommand) c).getTaskModule());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_withTask_withTargetModule_invalidModuleCode() {
        final String testString = "del /t 1 -m cs 2113 t";
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
    public void parse_markCommand_noModule_parsedCorrectly() {
        final String testString = "mark /c 3";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof MarkCommand);
            assertEquals(2, ((MarkCommand) c).getTaskIndex()); // Remember, zero-indexed!
            assertNull(((MarkCommand) c).getTaskModuleString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_markCommand_withModule_parsedCorrectly() {
        final String testString = "mark /c 3 -m cs2113t";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof MarkCommand);
            assertEquals(2, ((MarkCommand) c).getTaskIndex()); // Remember, zero-indexed!
            assertEquals("cs2113t", ((MarkCommand) c).getTaskModuleString());
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
    public void parse_markCommand_invalidInput() {
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



