//@@author hlwang56

package seedu.planitarium.command;

import org.junit.jupiter.api.Test;
import seedu.planitarium.commands.AddPersonCommand;
import seedu.planitarium.commands.DeletePersonCommand;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.family.Family;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeletePersonTest {
    protected static final String INVALID_UID_MSG =
            "Unknown error is detected from 'Invalid user index `2`', please check again.";
    protected static final String EMPTY_GROUPINX_ERROR_MSG =
            "Unknown error is detected from 'Empty string after `/g`', please check again.";

    Family family;
    DeletePersonCommand delete;

    private void initialize() throws PlanITariumException {
        family = new Family();
        AddPersonCommand add = new AddPersonCommand(CommandsForTesting.ADDPERSON, family);
        add.execute();
    }

    @Test
    void deletePerson_invalidUid_fail() {
        try {
            initialize();
            delete = new DeletePersonCommand(CommandsForTesting.DELETEPERSON2, family);
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_UID_MSG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void addPerson_invalidGroupInx_fail() {
        try {
            initialize();
            delete = new DeletePersonCommand(CommandsForTesting.DELETEPERSON3, family);
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), EMPTY_GROUPINX_ERROR_MSG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void addPerson_validInput_sucess() {
        try {
            initialize();
            delete = new DeletePersonCommand(CommandsForTesting.DELETEPERSON, family);
        } catch (Exception e) {
            fail();
        }
    }
}
