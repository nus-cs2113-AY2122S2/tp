package seedu.planitarium.command;

import org.junit.jupiter.api.Test;
import seedu.planitarium.commands.AddPersonCommand;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.family.Family;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddPersonTest {
    Family family = new Family();
    AddPersonCommand add;
    protected static final String NAME_ERROR_MSG =
            "Unknown error is detected from 'Empty string after `/n`', please check again.";
    protected static final String EMPTY_GROUPINX_ERROR_MSG =
            "Unknown error is detected from 'Empty string after `/g`', please check again.";
    protected static final String INVALID_GRPINX_ERROR_MSG =
            "Unknown error is detected from 'Invalid group index `4`', please check again.";

    @Test
    void addPerson_invalidName_fail() {
        try {
            add = new AddPersonCommand(CommandsForTesting.ADDPERSON4, family);
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), NAME_ERROR_MSG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void addPerson_invalidGroupInx_fail() {
        try {
            add = new AddPersonCommand(CommandsForTesting.ADDPERSON6, family);
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), EMPTY_GROUPINX_ERROR_MSG);
        } catch (Exception e) {
            fail();
        }

        try {
            add = new AddPersonCommand(CommandsForTesting.ADDPERSON5, family);
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_GRPINX_ERROR_MSG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void addPerson_sucess() {
        try {
            add = new AddPersonCommand(CommandsForTesting.ADDPERSON, family);
        } catch (Exception e) {
            fail();
        }
    }
}
