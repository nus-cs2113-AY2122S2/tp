package seedu.planitarium.expenditure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpenditureTest {

    private Expenditure testItem;

    /**
     * Sets up a test expenditure input
     */
    @BeforeEach
    public void setUp() {
        testItem = new Expenditure("food", 24);
    }

    /**
     * Checks if the description of the expenditure added matches the
     * test input.
     */
    @Test
    public void getDescription_validExpenditure_expectedDescription() {
        String inputDescription = "food";
        String actualDescription = testItem.getDescription();
        assertEquals(inputDescription, actualDescription);
    }

    /**
     * Checks if the amount of the expenditure added matches the
     * test input.
     */
    @Test
    public void getAmount_validExpenditure_expectedAmount() {
        double inputAmount = 24;
        double actualAmount = testItem.getAmount();
        assertEquals(inputAmount, actualAmount);
    }

    /**
     * Tests if assertion will block a null description input
     * in initialising a new Expenditure object.
     */
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