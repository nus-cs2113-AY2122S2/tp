//@@author tjiarong

package seedu.planitarium.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class IncomeTest {

    public static final double VALID_AMOUNT = 1000.00;
    public static final String VALID_DESCRIPTION = "food";
    public static final String EXPECTED_SAVE_STRING = "i food /d 1000.0 /d false /d " + LocalDate.now();
    public static final String EXPECTED_TO_STRING = "food: $1000.00 - Recurring: false";

    private Income testItem;

    @BeforeEach
    public void setUp() {
        testItem = new Income(VALID_DESCRIPTION, VALID_AMOUNT, false);
    }

    @Test
    public void getDescription_validIncome_expectedDescription() {
        String actualDescription = testItem.getDescription();
        assertEquals(VALID_DESCRIPTION, actualDescription);
    }

    @Test
    public void setDescription_validIncome_success() {
        testItem.setDescription(VALID_DESCRIPTION);
        String actualDescription = testItem.getDescription();
        assertEquals(VALID_DESCRIPTION, actualDescription);
    }

    @Test
    public void getAmount_validIncome_expectedAmount() {
        double actualAmount = testItem.getAmount();
        assertEquals(VALID_AMOUNT, actualAmount);
    }

    @Test
    public void getPermanent_validIncome_expectedPermanent() {
        boolean isPermanent = testItem.isPermanent();
        assertFalse(isPermanent);
    }

    @Test
    public void addIncome_nullDescription_expectAssertionError() {
        try {
            Income testNullDescription = new Income(null, VALID_AMOUNT, false);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void getInitDate_validDate_success() {
        LocalDate today = LocalDate.now();
        LocalDate itemDate = testItem.getInitDate();
        assertEquals(today, itemDate);
    }

    @Test
    public void saveString_getString_success() {
        String actualString = testItem.getSaveString();
        assertEquals(EXPECTED_SAVE_STRING, actualString);
    }

    @Test
    public void toString_getString_success() {
        String actualString = testItem.toString();
        assertEquals(EXPECTED_TO_STRING, actualString);
    }
}
