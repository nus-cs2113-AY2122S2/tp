//@@author HansHengGit

package seedu.planitarium.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.planitarium.category.Category;
import seedu.planitarium.global.Constants;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class ExpenditureTest {

    public static final double VALID_AMOUNT = 1000.00;

    private Expenditure testItem;

    @BeforeEach
    public void setUp() {
        testItem = new Expenditure("food", 24, 2, false);
    }

    @Test
    public void getDescription_validExpenditure_success() {
        String expectedDescription = "food";
        String actualDescription = testItem.getDescription();
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void getAmount_validExpenditure_success() {
        double expectedAmount = 24;
        double actualAmount = testItem.getAmount();
        assertEquals(expectedAmount, actualAmount);
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
        String expectedString = "e food - 24.0 - false - 1"
                + " - 2022-03-30";;
        String actualString = testItem.saveString();
        assertEquals(expectedString, actualString);
    }

    @Test
    public void toString_getString_success() {
        String expectedResult = "food: $24.00 - Recurring: false - Category: Food and Drinks";
        assertEquals(expectedResult, testItem.toString());
    }
}