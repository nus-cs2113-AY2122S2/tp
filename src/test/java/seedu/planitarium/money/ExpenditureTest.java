//@@author HansHengGit

package seedu.planitarium.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.planitarium.category.Category;
import seedu.planitarium.global.Constants;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class ExpenditureTest {

    public static final double VALID_AMOUNT = 1000.00;
    public static final String VALID_DESCRIPTION = "food";
    public static final String EXPECTED_SAVE_STRING = "e food /d 1000.0 /d false /d 1 /d " + LocalDate.now();
    public static final String EXPECTED_TO_STRING = "food: $1000.00 - Recurring: false - Category: Others";

    private Expenditure testItem;

    @BeforeEach
    public void setUp() {
        testItem = new Expenditure(VALID_DESCRIPTION, VALID_AMOUNT, 1, false);
    }

    @Test
    public void getDescription_validExpenditure_success() {
        String actualDescription = testItem.getDescription();
        assertEquals(VALID_DESCRIPTION, actualDescription);
    }

    @Test
    public void getAmount_validExpenditure_success() {
        double actualAmount = testItem.getAmount();
        assertEquals(VALID_AMOUNT, actualAmount);
    }

    @Test
    public void addExpenditure_nullDescription_expectAssertionError() {
        try {
            Expenditure testNullDescription = new Expenditure(null, 24.0, 1, false);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    //@@author tjiarong

    @Test
    public void getStatus_validExpenditure_success() {
        boolean isPermanent = testItem.isPermanent();
        assertFalse(isPermanent);
    }

    @Test
    public void setStatus_validStatus_success() {
        testItem.setPermanent(true);
        boolean actualStatus = testItem.isPermanent();
        assertTrue(actualStatus);
    }

    @Test
    public void getCategory_validCategory_success() {
        String expectedCat = Category.getLabelForIndex(Constants.MIN_CATEGORY_INDEX);
        String actualCat = testItem.getCategory();
        assertEquals(expectedCat, actualCat);
    }

    @Test
    public void setCategory_validCategory_success() {
        testItem.setCategory(Constants.LIMIT_TWO_TOKENS);
        String expectedCat = Category.getLabelForIndex(Constants.LIMIT_TWO_TOKENS);
        String actualCat = testItem.getCategory();
        assertEquals(expectedCat, actualCat);
    }

    @Test
    public void setAmount_validAmount_success() {
        testItem.setAmount(VALID_AMOUNT);
        double actualAmount = testItem.getAmount();
        assertEquals(VALID_AMOUNT, actualAmount);
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
