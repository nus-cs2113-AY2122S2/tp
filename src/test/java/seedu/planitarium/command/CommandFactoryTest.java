package seedu.planitarium.command;

import org.junit.jupiter.api.Test;
import seedu.planitarium.commands.Command;
import seedu.planitarium.commands.CommandFactory;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.family.Family;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandFactoryTest {

    public static Family family1 = new Family();
    public static Family family2 = null;
    protected CommandFactory factory = new CommandFactory();
    protected Command newCommand;

    @Test
    void getAddPersonCommand_validAddPersonCommand_success() {
        try {
            newCommand = factory.getCommand(CommandsForTesting.ADDPERSON, family1);
            assertEquals(newCommand.getType(), "AddPersonCMD");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getCommand_nullUserInput_assertThrown() {
        try {
            newCommand = factory.getCommand(null, family1);
            fail();
        } catch (AssertionError e) {
            assertEquals(Constants.INPUT_NOT_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getCommand_nullFamily_assertThrown() {
        try {
            newCommand = factory.getCommand(CommandsForTesting.ADDPERSON, family2);
            fail();
        } catch (AssertionError e) {
            assertEquals(Constants.FAMILY_NOT_NULL, e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getCommand_invalidCommand_ExceptionThrown() {
        try {
            newCommand = factory.getCommand(CommandsForTesting.INVALIDCMD, family1);
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), "Unknown instruction is detected. Error is detected from "
                    + "'CommandFactory'. Please check your input again.");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getDeletePersonCommand_validDeletePersonCommand_success() {
        try {
            factory.getCommand(CommandsForTesting.ADDPERSON, family1).execute();
            newCommand = factory.getCommand(CommandsForTesting.DELETEPERSON, family1);
            assertEquals(newCommand.getType(), "DeletePersonCMD");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void getAddRecordCommand_validAddRecordCommand_success() {
        try {
            newCommand = factory.getCommand(CommandsForTesting.ADDINCOME, family1);
            assertEquals(newCommand.getType(), "AddRecordCMD");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void getDeleteRecordCommand_validDeleteRecordCommand_success() {
        try {
            factory.getCommand(CommandsForTesting.ADDINCOME, family1).execute();
            newCommand = factory.getCommand(CommandsForTesting.DELETEINCOME, family1);
            assertEquals(newCommand.getType(), "DeleteRecordCMD");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void getEditRecordCommand_validEditRecordCommand_success() {
        try {
            factory.getCommand(CommandsForTesting.ADDPERSON, family1).execute();
            factory.getCommand(CommandsForTesting.ADDINCOME, family1).execute();
            newCommand = factory.getCommand(CommandsForTesting.EDITINCOME, family1);
            assertEquals(newCommand.getType(), "EditRecordCMD");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void getOverviewCommand_validOverviewRecordCommand_success() {
        try {
            newCommand = factory.getCommand(CommandsForTesting.OVERVIEW, family1);
            assertEquals(newCommand.getType(), "OverviewCMD");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getListCommand_validListRecordCommand_success() {
        try {
            newCommand = factory.getCommand(CommandsForTesting.LISTBYGROUP, family1);
            assertEquals(newCommand.getType(), "ListCMD");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getHelpCommand_validHelpCommand_success() {
        try {
            newCommand = factory.getCommand(CommandsForTesting.HELP, family1);
            assertEquals(newCommand.getType(), "HelpCMD");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getListcatCommand_validListcatCommand_success() {
        try {
            newCommand = factory.getCommand(CommandsForTesting.LISTCAT, family1);
            assertEquals(newCommand.getType(), "ListcatCMD");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getExitCommand_validExitCommand_success() {
        try {
            newCommand = factory.getCommand(CommandsForTesting.EXIT, family1);
            assertEquals(newCommand.getType(), "ExitCMD");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getSearchCommand_validSearchCommand_success() {
        try {
            newCommand = factory.getCommand(CommandsForTesting.FIND, family1);
            assertEquals(newCommand.getType(), "SearchCMD");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void executionTest_validInput() {
        try {
            factory.getCommand(CommandsForTesting.LISTCAT, family1).execute();
            factory.getCommand(CommandsForTesting.ADDPERSON, family1).execute();
            factory.getCommand(CommandsForTesting.ADDINCOME, family1).execute();
            factory.getCommand(CommandsForTesting.ADDEXPEND, family1).execute();
            factory.getCommand(CommandsForTesting.LISTBYGROUP, family1).execute();
            factory.getCommand(CommandsForTesting.EDITINCOME, family1).execute();
            factory.getCommand(CommandsForTesting.LISTBYGROUP, family1).execute();
            factory.getCommand(CommandsForTesting.EDITEXPEND, family1).execute();
            factory.getCommand(CommandsForTesting.DELETEINCOME, family1).execute();
            factory.getCommand(CommandsForTesting.DELETEEXPEND, family1).execute();
            factory.getCommand(CommandsForTesting.OVERVIEW, family1).execute();
            factory.getCommand(CommandsForTesting.FIND, family1).execute();
            //factory.getCommand(CommandsForTesting.HELP, family1).execute();
            factory.getCommand(CommandsForTesting.DELETEPERSON, family1).execute();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
