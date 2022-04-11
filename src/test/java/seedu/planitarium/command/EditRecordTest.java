//@@author hlwang56

package seedu.planitarium.command;

import org.junit.jupiter.api.Test;
import seedu.planitarium.commands.AddPersonCommand;
import seedu.planitarium.commands.AddRecordCommand;
import seedu.planitarium.commands.EditRecordCommand;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.family.Family;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EditRecordTest {
    protected static final String INVALID_DES_MSG =
            "Unknown error is detected from 'Empty string after `/d`', please check again.";
    protected static final String INVALID_UID_MSG =
            "Unknown error is detected from 'Invalid user index `0`', please check again.";
    protected static final String INVALID_IMONEY_MSG =
            "Unknown error is detected from 'Invalid money value `-2000`', please check again.";
    protected static final String INVALID_EMONEY_MSG =
            "Unknown error is detected from 'Invalid money value `-999.99`', please check again.";
    protected static final String INVALID_CATINX_MSG =
            "Unknown error is detected from 'Invalid category index `7`', please check again.";
    protected static final String INVALID_GROUPINX_ERROR_MSG =
            "Unknown error is detected from 'Invalid group index `4`', please check again.";
    protected static final String INVALID_RECINX_MSG1 =
            "Unknown error is detected from 'Invalid income index `0`', please check again.";
    protected static final String INVALID_RECINX_MSG2 =
            "Unknown error is detected from 'Invalid expenditure index `0`', please check again.";

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
    void editRec_invalidUid_fail() {
        try {
            initialize();
            editIn = new EditRecordCommand(CommandsForTesting.EDITINCOME6, family);
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_UID_MSG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void editRec_invalidGroupInx_fail() {
        try {
            initialize();
            editIn = new EditRecordCommand(CommandsForTesting.EDITINCOME2, family);
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_GROUPINX_ERROR_MSG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void editRec_invalidDes_fail() {
        try {
            initialize();
            editIn = new EditRecordCommand(CommandsForTesting.EDITINCOME5, family);
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_DES_MSG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void editRec_invalidRecInx_fail() {
        try {
            initialize();
            editIn = new EditRecordCommand(CommandsForTesting.EDITINCOME3, family);
            editIn.execute();
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_RECINX_MSG1);
        } catch (Exception e) {
            fail();
        }

        try {
            initialize();
            editOut = new EditRecordCommand(CommandsForTesting.EDITEXPEND2, family);
            editOut.execute();
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_RECINX_MSG2);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void editIncome_invalidMoney_fail() {
        try {
            initialize();
            editIn = new EditRecordCommand(CommandsForTesting.EDITINCOME4, family);
            editIn.execute();
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_IMONEY_MSG);
        } catch (Exception e) {
            fail();
        }

        try {
            initialize();
            editOut = new EditRecordCommand(CommandsForTesting.EDITEXPEND3, family);
            editOut.execute();
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_EMONEY_MSG);
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    void editExpend_invalidCat_fail() {
        try {
            initialize();
            editOut = new EditRecordCommand(CommandsForTesting.EDITEXPEND4, family);
            editOut.execute();
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_CATINX_MSG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void editRec_validInput_success() {
        try {
            initialize();
            editIn = new EditRecordCommand(CommandsForTesting.EDITINCOME, family);
            editOut = new EditRecordCommand(CommandsForTesting.EDITEXPEND, family);
            editIn.execute();
            editOut.execute();
        } catch (Exception e) {
            fail();
        }
    }
}
