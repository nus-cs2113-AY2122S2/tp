//@@author tjiarong

package seedu.planitarium.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

class IncomeTest {

    private Income testItem;

    @BeforeEach
    public void setUp() {
        testItem = new Income("food", 24, false);
    }

    @Test
    public void getDescription_validIncome_expectedDescription() {
        String inputDescription = "food";
        String actualDescription = testItem.getDescription();
        assertEquals(inputDescription, actualDescription);
    }

    @Test
    public void getAmount_validIncome_expectedAmount() {
        double inputAmount = 24;
        double actualAmount = testItem.getAmount();
        assertEquals(inputAmount, actualAmount);
    }

    @Test
    public void getPermanent_validIncome_expectedPermanent() {
        boolean isPermanent = testItem.isPermanent();
        assertFalse(isPermanent);
    }

    @Test
    public void addIncome_nullDescription_expectAssertionError() {
        try {
            Income testNullDescription = new Income(null, 24, false);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void saveString_getString_success() {
        String result = "i food - 24.0 - false";
        String actual = testItem.saveString();
        assertEquals(result, actual);
    }
}