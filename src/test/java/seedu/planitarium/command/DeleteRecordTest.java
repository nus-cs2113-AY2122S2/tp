package seedu.planitarium.command;

import org.junit.jupiter.api.Test;
import seedu.planitarium.commands.AddPersonCommand;
import seedu.planitarium.commands.AddRecordCommand;
import seedu.planitarium.commands.DeleteRecordCommand;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.family.Family;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteRecordTest {
    protected static final String INVALID_UID_MSG =
            "Unknown error is detected from 'Empty string after `/u`', please check again.";
    protected static final String INVALID_GROUPINX_ERROR_MSG =
            "Unknown error is detected from 'Empty string after `/g`', please check again.";
    protected static final String INVALID_RECINX_MSG1 =
            "Unknown error is detected from 'Invalid income index `0`', please check again.";
    protected static final String INVALID_RECINX_MSG2 =
            "Unknown error is detected from 'Invalid expenditure index `0`', please check again.";

    Family family;
    DeleteRecordCommand deleteIn;
    DeleteRecordCommand deleteOut;

    private void initialize() throws PlanITariumException {
        family = new Family();
        AddPersonCommand add = new AddPersonCommand(CommandsForTesting.ADDPERSON, family);
        add.execute();
        AddRecordCommand addIn = new AddRecordCommand(CommandsForTesting.ADDEXPEND, family);
        AddRecordCommand addOut = new AddRecordCommand(CommandsForTesting.ADDINCOME, family);
        addIn.execute();
        addOut.execute();
    }

    @Test
    void deleteRec_invalidUid_fail() {
        try {
            initialize();
            deleteIn = new DeleteRecordCommand(CommandsForTesting.DELETEINCOME4, family);
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_UID_MSG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void deleteRec_invalidGroupInx_fail() {
        try {
            initialize();
            deleteIn = new DeleteRecordCommand(CommandsForTesting.DELETEINCOME3, family);
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_GROUPINX_ERROR_MSG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void deleteRec_invalidRecInx_fail() {
        try {
            initialize();
            deleteIn = new DeleteRecordCommand(CommandsForTesting.DELETEINCOME2, family);
            deleteIn.execute();
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_RECINX_MSG1);
        } catch (Exception e) {
            fail();
        }

        try {
            initialize();
            deleteOut = new DeleteRecordCommand(CommandsForTesting.DELETEEXPEND2, family);
            deleteOut.execute();
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_RECINX_MSG2);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void deleteRec_validInput_success() {
        try {
            initialize();
            deleteIn = new DeleteRecordCommand(CommandsForTesting.DELETEINCOME, family);
            deleteOut = new DeleteRecordCommand(CommandsForTesting.DELETEEXPEND, family);
            deleteIn.execute();
            deleteOut.execute();
        } catch (Exception e) {
            fail();
        }
    }


}
