package seedu.mindmymoney.command;

import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.User;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateCommandTest {
    /** Assert that the command can update the expenditure list. */
    @Test
    void updateCommand_updateExpenditure_listUpdated() {
        Expenditure testExpenditure = new Expenditure("cash", "food",
                "porridge", 5, "01/04/2022");
        Expenditure newExpenditure = new Expenditure("Cash", "Others",
                "chicken rice", (float)4.50, "01/05/2021");
        User testUser = new User();
        testUser.setExpenditureListArray(new ExpenditureList());
        testUser.getExpenditureListArray().add(testExpenditure);
        String input = "1 /pm cash /c Others /d chicken rice /a 4.50 /t 01/05/2021";
        UpdateCommand updateCommand = new UpdateCommand(input, testUser);
        try {
            updateCommand.executeCommand();
            assertEquals(testUser.getExpenditureListArray().get(0), newExpenditure);
        } catch (MindMyMoneyException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    /** Assert that an invalid command will throw an exception. */
    @Test
    void updateCommand_invalidInput_exceptionThrown() {
        Expenditure testExpenditure = new Expenditure("cash", "food",
                "porridge", 5, "01/03/2022");
        User testUser = new User();
        testUser.setExpenditureListArray(new ExpenditureList());
        testUser.getExpenditureListArray().add(testExpenditure);
        String input = "invalid input";
        UpdateCommand updateCommand = new UpdateCommand(input, testUser);
        assertThrows(MindMyMoneyException.class, updateCommand::executeCommand);
    }
}
