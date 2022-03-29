package seedu.planitarium.command;

import org.junit.jupiter.api.Test;
import seedu.planitarium.commands.Command;
import seedu.planitarium.commands.CommandFactory;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.global.Constants;
import seedu.planitarium.person.Family;

import static org.junit.jupiter.api.Assertions.*;

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
        } catch (AssertionError e) {
            assertEquals(Constants.INPUT_NOT_NULL, e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getCommand_nullFamily_assertThrown() {
        try {
            newCommand = factory.getCommand(CommandsForTesting.ADDPERSON, family2);
        } catch (AssertionError e) {
            assertEquals(Constants.FAMILY_NOT_NULL, e.toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getCommand_invalidCommand_ExceptionThrown() {
        try {
            newCommand = factory.getCommand(CommandsForTesting.INVALIDCMD, family2);
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), "Unknown instruction is detected. Error is detected from "
                    + "'CommandFactory'. Please check your input again.");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getDeletePersonCommand_validDeletePersonCommand_success(){
        try {
            factory.getCommand(CommandsForTesting.ADDPERSON, family1).execute();
            newCommand = factory.getCommand(CommandsForTesting.DELETEPERSON, family1);
            assertEquals(newCommand.getType(), "DeletePersonCMD");
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void getAddRecordCommand_validAddRecordCommand_success(){
        try {
            newCommand = factory.getCommand(CommandsForTesting.DELETEPERSON, family1);
            assertEquals(newCommand.getType(), "DeletePersonCMD");
        } catch (Exception e) {
            fail();
        }
    }

}
