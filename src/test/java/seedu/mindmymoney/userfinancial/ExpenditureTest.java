package seedu.mindmymoney.userfinancial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Performs tests for Expenditures.
 */
public class ExpenditureTest {
    /**
     * Tests that an Expenditure can correctly serialize itself.
     */
    @Test
    void serialization_normalInput_correctSerialization() {
        Expenditure expenditure = new Expenditure("cash", "Food", "chicken"
                + "expenditure", 20, "March 2022");
        try {
            String serialized = expenditure.getAddCommand();
            assertEquals(serialized, "add /pm cash /c Food /d chickenexpenditure /a 20.000000 /t 2022-03\n");
        } catch (MindMyMoneyException e) {
            fail();
        }
    }
}
