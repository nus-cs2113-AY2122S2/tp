package seedu.mindmymoney.userfinancial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.mindmymoney.MindMyMoneyException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Performs tests for Expenditures.
 */
public class ExpenditureTest {
    /**
     * Tests that an Expenditure can correctly serialize and deserialize itself.
     * Sample description is intentionally populated with special characters
     * to try and trip errors in parsing.
     */
    @Test
    void serialization_normalInput_correctDeserialization() {
        Expenditure expenditure = new Expenditure("Test%!$+:/=-<%<%<of amount>%>9()&)* \t\n"
                + "expenditure", 20);
        String serialized = expenditure.serialize();
        try {
            Expenditure deserialized = Expenditure.deserialize(serialized);
            assertEquals(expenditure, deserialized);
        } catch (MindMyMoneyException e) {
            Assertions.fail("MindMyMoney exception was thrown");
        }
    }

    /**
     * Tests that an incorrectly formatted string will not be deserialized by Expenditure.
     */
    @Test
    void deserialization_improperInput_throwsException() {
        String incorrectSerialization1 = "food of $10";
        assertThrows(MindMyMoneyException.class, () -> Expenditure.deserialize(incorrectSerialization1));
    }
}
