package seedu.duke.parsers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.GradeCommand;
import seedu.duke.commands.GpaCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.MarkCommand;
import seedu.duke.commands.OptionCommand;
import seedu.duke.commands.ResetCommand;
import seedu.duke.commands.SaveCommand;
import seedu.duke.commands.TagCommand;
import seedu.duke.exceptions.AdditionalParameterException;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.InvalidFlagException;
import seedu.duke.exceptions.ExcessArgumentException;
import seedu.duke.exceptions.InvalidCompulsoryParameterException;
import seedu.duke.exceptions.MissingCompulsoryParameterException;
import seedu.duke.exceptions.MissingNumberException;
import seedu.duke.exceptions.InvalidTagOperationException;
import seedu.duke.exceptions.UnknownCommandException;
import seedu.duke.data.Module;
import seedu.duke.data.Task;

public class ModHappyParserTest {
    private ModHappyParser parser;


    private void testParseCommand_expectAdditionalParameterException(String testString) {
        assertThrows(AdditionalParameterException.class, () -> parser.parseCommand(testString));
    }

    private void testParseCommand_expectInvalidNumberException(String testString) {
        assertThrows(InvalidNumberException.class, () -> parser.parseCommand(testString));
    }

    private void testParseCommand_expectInvalidCompulsoryParameterException(String testString) {
        assertThrows(InvalidCompulsoryParameterException.class, () -> parser.parseCommand(testString));
    }

    private void testParseCommand_expectMissingCompulsoryParameterException(String testString) {
        assertThrows(MissingCompulsoryParameterException.class, () -> parser.parseCommand(testString));
    }

    private void testParseCommand_expectMissingNumberException(String testString) {
        assertThrows(MissingNumberException.class, () -> parser.parseCommand(testString));
    }

    private void testParseCommand_expectUnknownCommandException(String testString) {
        assertThrows(UnknownCommandException.class, () -> parser.parseCommand(testString));
    }


    private void testParseCommand_expectInvalidExcessArgumentException(String testString) {
        assertThrows(ExcessArgumentException.class, () -> parser.parseCommand(testString));

    }

    private void testParseCommand_expectInvalidTagOperationException(String testString) {
        assertThrows(InvalidTagOperationException.class, () -> parser.parseCommand(testString));
    }


    private void testParseCommand_expectInvalidFlagException(String testString) {
        assertThrows(InvalidFlagException.class, () -> parser.parseCommand(testString));
    }

    //@@author chooyikai
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
        final String testString = "add task \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d \"  ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertNull(t.getTaskDescription());
            assertNull(t.getWorkingTimeString());
            assertNull(((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_parsedCorrectly() {
        final String testString = "add task \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d \"  "
                + "-d \"-d-d-d /t /m -d -d  \"";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertEquals("-d-d-d /t /m -d -d", t.getTaskDescription());
            assertNull(t.getWorkingTimeString());
            assertNull(((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    //@@author
    @Test
    public void parse_addCommand_mod_unknownCommand() {
        final String testString = "add . mod 1 4";
        testParseCommand_expectUnknownCommandException(testString);
    }

    @Test
    public void parse_addCommand_mod_invalidModuleCode() {
        final String testString = "add mod . 1 4";
        testParseCommand_expectInvalidCompulsoryParameterException(testString);
    }

    @Test
    public void parse_addCommand_mod_invalidModularCredits() {
        final String testString = "add mod 1  .4";
        testParseCommand_expectInvalidNumberException(testString);
    }

    @Test
    public void parse_addCommand_mod_excessArguments() {
        final String testString = "add mod 1 4 .";
        testParseCommand_expectInvalidExcessArgumentException(testString);
    }

    @Test
    public void parse_addCommand_task_invalidCommand() {
        final String testString = "add . task \"test\" -m cs2113t -d \"desc\" -t \"2 hours\"";
        testParseCommand_expectUnknownCommandException(testString);
    }

    @Test
    public void parse_addCommand_task_invalidTaskName() {
        final String testString = "add task . \"test\" -m cs2113t -d \"desc\" -t \"2 hours\"";
        testParseCommand_expectInvalidCompulsoryParameterException(testString);
    }

    @Test
    public void parse_addCommand_task_invalidModuleCode() {
        final String testString = "add task \"test\" -m . cs2113t -d \"desc\" -t \"2 hours\"";
        testParseCommand_expectInvalidCompulsoryParameterException(testString);
    }

    @Test
    public void parse_addCommand_task_invalidDescription() {
        final String testString = "add task \"test\" -m cs2113t -d .\"desc\" -t \"2 hours\"";
        testParseCommand_expectInvalidExcessArgumentException(testString);
    }

    @Test
    public void parse_addCommand_task_invalidTime() {
        final String testString = "add task \"test\" -m cs2113t -d \"desc\" -t .\"2 hours\"";
        testParseCommand_expectInvalidExcessArgumentException(testString);
    }

    @Test
    public void parse_addCommand_task_wrongOrder() {
        final String testString = "add task \"test\" -m cs2113t -t .\"2 hours\" -d .\"desc\" ";
        testParseCommand_expectInvalidExcessArgumentException(testString);
    }

    @Test
    public void parse_editCommand_mod_invalidCommand() {
        final String testString = "edit . mod cs2113t  -d \"changed\"";
        testParseCommand_expectUnknownCommandException(testString);
    }

    @Test
    public void parse_editCommand_mod_invalidModule() {
        final String testString = "edit mod . cs2113t  -d \"changed\"";
        testParseCommand_expectInvalidCompulsoryParameterException(testString);
    }

    @Test
    public void parse_editCommand_mod_invalidDescription() {
        final String testString = "edit mod  cs2113t  -d .\"changed\"";
        testParseCommand_expectInvalidCompulsoryParameterException(testString);
    }

    @Test
    public void parse_editCommand_task_invalidCommand() {
        final String testString = "edit .task 1 -m CS2113T  -d \"changed\"";
        testParseCommand_expectUnknownCommandException(testString);
    }

    @Test
    public void parse_editCommand_task_invalidTaskNumber() {
        final String testString = "edit task .1 -m CS2113T  -d \"changed\"";
        testParseCommand_expectInvalidNumberException(testString);
    }

    @Test
    public void parse_editCommand_task_invalidModuleCode() {
        final String testString = "edit task 1 -m .CS2113T  -d \"changed\"";
        testParseCommand_expectInvalidCompulsoryParameterException(testString);
    }

    @Test
    public void parse_editCommand_task_invalidDescription() {
        final String testString = "edit task 1 -m CS2113T  -d .\"changed\"";
        testParseCommand_expectInvalidCompulsoryParameterException(testString);
    }

    //@@author chooyikai
    @Test
    public void parse_addCommand_task_withTargetModule_parsedCorrectly() {
        final String testString = "add task \"/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d \"  "
                + "-m cs2113t";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d-d-d-d-d -d/t/t-d-d-d-d -d-d-d", t.getTaskName());
            assertNull(t.getTaskDescription());
            assertNull(t.getWorkingTimeString());
            assertEquals("cs2113t", ((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_task_withDescription_withTargetModule_parsedCorrectly() {
        final String testString = "add task \"/t/t/t/t-d\" -m cs2113t -d \"-d-d-d /t /m -d -d  \" ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d", t.getTaskName());
            assertEquals("-d-d-d /t /m -d -d", t.getTaskDescription());
            assertNull(t.getWorkingTimeString());
            assertEquals("cs2113t", ((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void parse_addCommand_task_withDescription_withWorkingTime_withTargetModule_parsedCorrectly() {
        final String testString = "add task \"/t/t/t/t-d\" -m cs2113t -d \"-d-d-t-m /m -m -d -t  \" "
                + "-t \"75 minutes\" ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Task t = ((AddCommand) c).getNewTask();
            assertNotEquals(null, t);
            assertNull(((AddCommand) c).getNewModule());
            assertEquals("/t/t/t/t-d", t.getTaskName());
            assertEquals("-d-d-t-m /m -m -d -t", t.getTaskDescription());
            assertEquals("1 hour(s) 15 minute(s)", t.getWorkingTimeString());
            assertEquals("cs2113t", ((AddCommand) c).getTargetModuleName());
        } catch (Exception e) {
            fail();
        }
    }

    //@@author
    @Test
    public void parse_addCommand_duplicateTaskDescription() {
        final String testString = "add task \"000\" -d \"123\" -t \"456\" -d \"789\"";
        testParseCommand_expectInvalidExcessArgumentException(testString);
    }



    @Test
    public void parse_addCommand_module_noDescription_parsedCorrectly() {
        final String testString = "add  \t mod modulecode 4 \t\t    ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof AddCommand);
            Module m = ((AddCommand) c).getNewModule();
            assertNotEquals(null, m);
            assertNull(((AddCommand) c).getNewTask());
            assertEquals("modulecode", m.getModuleCode());
            assertEquals(4, m.getModularCredit());
            assertNull(m.getModuleDescription());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_addCommand_module_invalidModularCredit() {
        final String testString = "add  \t mod modulecode four \t\t    ";
        testParseCommand_expectInvalidNumberException(testString);
    }

    @Test
    public void parse_addCommand_module_noDescription_invalidModuleCode() {
        final String testString = "add  \t mod module code /c 4 \t\t    ";
        testParseCommand_expectInvalidNumberException(testString);
    }


    @Test
    public void parse_addCommand_module_withDescription_invalidModuleCode() {
        final String testString = "add  \t mod module code \t\t  4  -d \t\t  \t \"i am a descrip\t -d-d tion\t \"\t  ";
        testParseCommand_expectInvalidNumberException(testString);
    }

    @Test
    public void parse_addCommand_module_withDescription_invalidInput() {
        final String testString = "add mod cs2113t 4 -d \"11111\"123";
        testParseCommand_expectInvalidExcessArgumentException(testString);
    }

    @Test
    public void parse_addCommand_invalidFlag() {
        final String testString = "add /a \"blahblah\" -d \"blahblahblah\"";
        testParseCommand_expectUnknownCommandException(testString);
    }

    @Test
    public void parse_addCommand_noFlagProvided() {
        final String testString = "add cs2113t";
        testParseCommand_expectUnknownCommandException(testString);
    }

    @Test
    public void parse_addCommand_withModuleOnly_noModuleProvided() {
        final String testString = "add mod";
        testParseCommand_expectMissingCompulsoryParameterException(testString);
    }

    @Test
    public void parse_addCommand_withTaskOnly_noTaskProvided() {
        final String testString = "add task";
        testParseCommand_expectMissingCompulsoryParameterException(testString);
    }

    @Test
    public void parse_deleteCommand_withTaskOnly_parsedCorrectly() {
        final String testString = "del task 1";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof DeleteCommand);
            assertEquals(0, ((DeleteCommand) c).getTaskIndex()); // zero-indexed
        } catch (Exception e) {
            fail();
        }
    }



    @Test
    public void parse_deleteCommand_withModuleOnly_parsedCorrectly() {
        final String testString = "del mod CS2113T";
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
        final String testString = "del task 1 -m cs2113t";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof DeleteCommand);
            assertEquals(0, ((DeleteCommand) c).getTaskIndex()); // zero-indexed
            assertEquals("cs2113t", ((DeleteCommand) c).getTaskModule());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommand_withTask_withTargetModule_invalidModuleCode() {
        final String testString = "del task 1 -m cs 2113 t";
        testParseCommand_expectInvalidExcessArgumentException(testString);
    }

    @Test
    public void parse_deleteCommand_invalidFlag() {
        final String testString = "del a 1";
        testParseCommand_expectUnknownCommandException(testString);
    }

    @Test
    public void parse_deleteCommand_noFlagProvided() {
        final String testString = "del 1";
        testParseCommand_expectUnknownCommandException(testString);
    }

    @Test
    public void parse_deleteCommand_withModuleOnly_noModuleProvided() {
        final String testString = "del mod";
        testParseCommand_expectMissingCompulsoryParameterException(testString);
    }

    @Test
    public void parse_deleteCommand_withTaskOnly_noIndexProvided() {
        final String testString = "del task";
        testParseCommand_expectMissingNumberException(testString);
    }


    @Test
    public void parse_editCommand_task_parsedCorrectly() {
        final String testString = "edit task 1 -m cs2113t -n \"changed\" ";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof EditCommand);
            assertEquals(0, ((EditCommand) c).getTaskIndex()); // zero-indexed
            assertNull(((EditCommand) c).getModuleCode());
            assertEquals("cs2113t", ((EditCommand) c).getTaskModule());
        } catch (Exception e) {
            fail();
        }
    }



    @Test
    public void parse_editCommand_task_noOptionalFlags() {
        final String testString = "edit task 1";
        testParseCommand_expectMissingCompulsoryParameterException(testString);
    }

    @Test
    public void parse_editCommand_module_wrongFlag() {
        final String testString = "edit mod cs2113t -t \"111\"";
        testParseCommand_expectInvalidFlagException(testString);
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
        testParseCommand_expectAdditionalParameterException(testString);
    }

    @Test
    public void parse_gradeCommand_parsedCorrectly() {
        final String testString = "grade CS2113T a+";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof GradeCommand);
            assertEquals("CS2113T", ((GradeCommand) c).getModuleCode()); // Remember, zero-indexed!
            assertEquals("A+", ((GradeCommand) c).getModuleGrade());
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void parse_gradeCommand_task_tooManyGrades() {
        final String testString = "grade CS2113T A+ B+ B-";
        testParseCommand_expectInvalidExcessArgumentException(testString);
    }

    @Test
    public void parse_gradeCommand_wrongOrder() {
        final String testString = "grade A- CS2113T";
        testParseCommand_expectInvalidCompulsoryParameterException(testString);
    }

    @Test
    public void parse_gpaCommand_parsedCorrectly() {
        final String testString = "gpa";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof GpaCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_gpaCommand_unnecessaryArgs() {
        final String testString = "gpa blahblah";
        testParseCommand_expectAdditionalParameterException(testString);
    }

    @Test
    public void parse_helpCommand_parsedCorrectly() {
        final String testString = "help";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof HelpCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_helpCommand_withCommandWord_parsedCorrectly() {
        final String testString = "help add";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof HelpCommand);
            assertEquals("add", ((HelpCommand) c).getCommand());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_helpCommand_unnecessaryArgs() {
        final String testString = "help add blahblah";
        testParseCommand_expectInvalidExcessArgumentException(testString);
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
    public void parse_listCommandwithArgument_noExeceptionThrown() {
        final String testString = "list test";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof ListCommand);
            assertEquals("test", ((ListCommand) c).getArgument());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_listCommand_unnecessaryArgs() {
        final String testString = "list test blahblah";
        testParseCommand_expectInvalidExcessArgumentException(testString);
    }

    @Test
    public void parse_markCommand_noModule_parsedCorrectly() {
        final String testString = "mark c 3";
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
        final String testString = "mark c 3 -m cs2113t";
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
    public void parse_markCommand_noFlagProvided() {
        final String testString = "mark 1";
        testParseCommand_expectInvalidFlagException(testString);
    }

    @Test
    public void parse_markCommand_noIndexProvided() {
        final String testString = "mark c";
        testParseCommand_expectMissingNumberException(testString);
    }


    @Test
    public void parse_markCommand_unnecessaryArgs() {
        final String testString = "mark c 1 blahblah";
        testParseCommand_expectInvalidExcessArgumentException(testString);
    }

    @Test
    public void parse_optionCommand_parsedCorrectly() {
        final String testString = "option";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof OptionCommand);
        } catch (Exception e) {
            fail();
        }
    }


    @Test
    public void parse_resetCommand_parsedCorrectly() {
        final String testString = "reset";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof ResetCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_resetCommand_unnecessaryArgs() {
        final String testString = "reset blahblah";
        testParseCommand_expectAdditionalParameterException(testString);
    }

    @Test
    public void parse_saveCommand_parsedCorrectly() {
        final String testString = "save";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof SaveCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_saveCommand_unnecessaryArgs() {
        final String testString = "save blahblah";
        testParseCommand_expectAdditionalParameterException(testString);
    }

    @Test
    public void parse_tagCommand_addTag_withoutTargetModule_parsedCorrectly() {
        final String testString = "tag add 1 tag";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof TagCommand);
            assertEquals("add", ((TagCommand) c).getTagOperation());
            assertEquals(0, ((TagCommand) c).getTaskIndex());
            assertEquals("tag", ((TagCommand) c).getTagDescription());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_tagCommand_delTag_withoutTargetModule_parsedCorrectly() {
        final String testString = "tag del 1 tag";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof TagCommand);
            assertEquals("del", ((TagCommand) c).getTagOperation());
            assertEquals(0, ((TagCommand) c).getTaskIndex());
            assertEquals("tag", ((TagCommand) c).getTagDescription());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_tagCommand_addTag_withTargetModule_parsedCorrectly() {
        final String testString = "tag add 1 -m cs2113t tag";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof TagCommand);
            assertEquals("add", ((TagCommand) c).getTagOperation());
            assertEquals(0, ((TagCommand) c).getTaskIndex());
            assertEquals("cs2113t", ((TagCommand) c).getTaskModule());
            assertEquals("tag", ((TagCommand) c).getTagDescription());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_tagCommand_delTag_withTargetModule_parsedCorrectly() {
        final String testString = "tag del 1 -m cs2113t tag";
        try {
            Command c = parser.parseCommand(testString);
            assertTrue(c instanceof TagCommand);
            assertEquals("del", ((TagCommand) c).getTagOperation());
            assertEquals(0, ((TagCommand) c).getTaskIndex());
            assertEquals("cs2113t", ((TagCommand) c).getTaskModule());
            assertEquals("tag", ((TagCommand) c).getTagDescription());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void parse_tagCommand_invalidFlag_throwsException() {
        final String testString = "tag add 1 -f cs2113t tag";
        testParseCommand_expectInvalidFlagException(testString);
    }

    @Test
    public void parse_tagCommand_invalidTagOperation_throwsException() {
        final String testString = "tag invalidOp 1 tagDescription";
        testParseCommand_expectInvalidTagOperationException(testString);
    }
}
