package seedu.planitarium.command;

import org.junit.jupiter.api.Test;
import seedu.planitarium.commands.AddPersonCommand;
import seedu.planitarium.commands.AddRecordCommand;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.family.Family;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddRecordTest {
    Family family;
    AddRecordCommand addRec;
    protected static final String INVALID_UID_MSG =
            "Unknown error is detected from 'Invalid category index `-1`', please check again.";
    protected static final String INVALID_IMONEY_MSG =
            "Unknown error is detected from 'Empty string after `/i`', please check again.";
    protected static final String INVALID_IMONEY_MSG2 =
            "Unknown error is detected from 'Invalid money value `-999.99`', please check again.";

    private void initialize() throws PlanITariumException {
        family = new Family();
        AddPersonCommand add = new AddPersonCommand(CommandsForTesting.ADDPERSON, family);
        add.execute();
    }

    @Test
    void addRec_invalidUid_fail() {
        try{
            initialize();
            addRec = new AddRecordCommand(CommandsForTesting.ADDINCOME2, family);
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_UID_MSG);
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
    }
}
