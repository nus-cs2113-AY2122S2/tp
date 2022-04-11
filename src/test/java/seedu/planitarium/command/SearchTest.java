//@@author hlwang56

package seedu.planitarium.command;

import org.junit.jupiter.api.Test;
import seedu.planitarium.commands.AddPersonCommand;
import seedu.planitarium.commands.AddRecordCommand;
import seedu.planitarium.commands.SearchCommand;
import seedu.planitarium.exceptions.PlanITariumException;
import seedu.planitarium.family.Family;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class SearchTest {
    protected static final String INVALID_DES_MSG =
            "Unknown error is detected from 'Empty string after `/d`', please check again.";
    protected static final String INVALID_CATINX_MSG =
            "Unknown error is detected from 'Invalid category index `-3`', please check again.";

    Family family;
    SearchCommand search;

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
    void find_invalidDes_fail() {
        try {
            initialize();
            search = new SearchCommand(CommandsForTesting.FIND2, family);
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_DES_MSG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void find_invalidCatInx_fail() {
        try {
            initialize();
            search = new SearchCommand(CommandsForTesting.FIND3, family);
            fail();
        } catch (PlanITariumException e) {
            assertEquals(e.toString(), INVALID_CATINX_MSG);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void  find_validInput_success() {
        try {
            initialize();
            search = new SearchCommand(CommandsForTesting.FIND, family);
        } catch (Exception e) {
            fail();
        }
    }

}
