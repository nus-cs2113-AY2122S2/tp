//@@author tjiarong

package seedu.planitarium.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

class IncomeListTest {

    private static int INVALID_INDEX = -1;
    private static int VALID_INDEX = 1;
    private IncomeList personOne;

    @BeforeEach
    public void setUp() {
        personOne = new IncomeList();
        personOne.addIncome("Food", 20, false);
        personOne.addIncome("Transport", 5, false);
    }

    @Test
    public void addIncome_newIncome_existsInIncome() {
        IncomeList personTwo = new IncomeList();
        personTwo.addIncome("clothes", 30, false);
        String description = "clothes";
        double amount = 30;
        assertEquals(description, personTwo.getDescription(VALID_INDEX));
        assertEquals(amount, personTwo.getIncomeValue(VALID_INDEX));

    }

    @Test
    public void getDescription_validIndex_expectSameDescription() {
        String inputString = "Food";
        String getDescriptionString = personOne.getDescription(VALID_INDEX);
        assertEquals(inputString, getDescriptionString);
    }

    @Test
    public void getIncomeValue_validIndex_expectSameDescription() {
        double inputAmount = 20;
        double getAmount = personOne.getIncomeValue(VALID_INDEX);
        assertEquals(inputAmount, getAmount);
    }

    @Test
    public void getPermanent_validIndex_expectFalse() {
        boolean isPermanent = personOne.isPermanent(VALID_INDEX);
        assertFalse(isPermanent);
    }


    @Test
    public void remove_incomeExists_removesNormally() {
        int numberOfIncomeBeforeRemoval = personOne.getNumberOfIncomes();
        personOne.remove(VALID_INDEX);
        int numberOfIncomeAfterRemoval = personOne.getNumberOfIncomes();
        assertEquals(numberOfIncomeBeforeRemoval - 1, numberOfIncomeAfterRemoval);
    }

    @Test
    public void remove_invalidIndex_expectAssertionError() {
        try {
            personOne.remove(INVALID_INDEX);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void getDescription_invalidIndex_expectAssertionError() {
        try {
            personOne.getDescription(INVALID_INDEX);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void getIncomeValue_invalidIndex_expectAssertionError() {
        try {
            personOne.getIncomeValue(INVALID_INDEX);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void addIncome_nullDescription_expectAssertionError() {
        IncomeList testList = new IncomeList();
        try {
            testList.addIncome(null, 24, false);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void editIncome_ValidParam_Success() {
        personOne.editIncome(1, "Dabao", 1000.0, true);
        assertEquals(personOne.getDescription(1), "Dabao");
        assertEquals(personOne.getIncomeValue(1), 1000.0);
        assertEquals(personOne.isPermanent(1), true);
    }

}