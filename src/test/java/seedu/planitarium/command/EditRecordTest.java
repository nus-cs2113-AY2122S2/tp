package seedu.planitarium.command;

import org.junit.jupiter.api.Test;
import seedu.planitarium.commands.AddPersonCommand;
import seedu.planitarium.commands.AddRecordCommand;
import seedu.planitarium.commands.DeleteRecordCommand;
import seedu.planitarium.commands.EditRecordCommand;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.family.Family;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EditRecordTest {

    Family family;
    EditRecordCommand editIn;
    EditRecordCommand editOut;

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
        try{
            initialize();
            deleteIn = new DeleteRecordCommand(CommandsForTesting.DELETEINCOME4, family);
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
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_RECINX_MSG1);
        } catch (Exception e) {
            fail();
        }

        try {
            initialize();
            deleteOut = new DeleteRecordCommand(CommandsForTesting.DELETEEXPEND2, family);
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_RECINX_MSG2);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void addIncome_invalidMoney_fail() {
        try {
            initialize();
            addRec = new AddRecordCommand(CommandsForTesting.ADDINCOME3, family);
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_IMONEY_MSG2);
        } catch (Exception e) {
            fail();
        }

        try {
            initialize();
            addRec = new AddRecordCommand(CommandsForTesting.ADDINCOME4, family);
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_IMONEY_MSG);
        } catch (Exception e) {
            fail();
        }

        try {
            initialize();
            addRec = new AddRecordCommand(CommandsForTesting.ADDEXPEND2, family);
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_EMONEY_MSG);
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    void addExpend_invalidCat_fail() {
        try {
            initialize();
            addRec = new AddRecordCommand(CommandsForTesting.ADDEXPEND3, family);
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_CAT_MSG);
        } catch (Exception e) {
            fail();
        }
    }
}
