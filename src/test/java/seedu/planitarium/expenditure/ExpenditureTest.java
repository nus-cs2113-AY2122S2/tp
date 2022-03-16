package seedu.planitarium.expenditure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class ExpenditureTest {

    private Expenditure testItem;

    @BeforeEach
    public void setUp() {
        testItem = new Expenditure("food", 24);
    }

    @Test
    public void getDescription_validExpenditure_expectedDescription() {
        String inputDescription = "food";
        String actualDescription = testItem.getDescription();
        assertEquals(inputDescription, actualDescription);
    }

    @Test
    public void getAmount_validExpenditure_expectedAmount() {
        double inputAmount = 24;
        double actualAmount = testItem.getAmount();
        assertEquals(inputAmount, actualAmount);
    }

    @Test
    public void addExpenditure_nullDescription_expectAssertionError() {
        try {
            Expenditure testNullDescription = new Expenditure(null, 24);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }
}