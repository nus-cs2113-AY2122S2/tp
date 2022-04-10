package seedu.planitarium.command;

import org.junit.jupiter.api.Test;
import seedu.planitarium.commands.AddPersonCommand;
import seedu.planitarium.commands.AddRecordCommand;
import seedu.planitarium.commands.ListCommand;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.family.Family;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ListByGroupTest {
    protected static final String INVALID_GROUPINX_ERROR_MSG =
            "Unknown error is detected from 'Invalid group index `5`', please check again.";

    Family family;
    ListCommand list;

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
    void listByGroup_invalidGroupInx_fail() {
        try {
            initialize();
            list = new ListCommand(CommandsForTesting.LISTBYGROUP2, family);
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_GROUPINX_ERROR_MSG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void listByGroup_success() {
        try {
            initialize();
            list = new ListCommand(CommandsForTesting.LISTBYGROUP, family);
        } catch (Exception e) {
            fail();
        }
    }
}
