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
        Expenditure expenditure = new Expenditure("Cash", "Food", "chicken"
                + "expenditure", 20, "05/03/2022");
        try {
            String serialized = expenditure.serialize();
            Expenditure deserialized = Expenditure.deserialize(serialized);
            assertEquals(expenditure, deserialized);
        } catch (MindMyMoneyException e) {
            fail();
        }
    }
}
