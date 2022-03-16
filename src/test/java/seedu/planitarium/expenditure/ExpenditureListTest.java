package seedu.planitarium.expenditure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

class ExpenditureListTest {

    private static int INVALID_INDEX = -1;
    private static int VALID_INDEX = 1;
    private ExpenditureList personOne;

    @BeforeEach
    public void setUp() {
        personOne = new ExpenditureList();
        personOne.addExpenditure("Food", 20);
        personOne.addExpenditure("Transport", 5);
    }

    @Test
    public void addExpenditure_newExpenditure_existsInExpenditure() {
        ExpenditureList personTwo = new ExpenditureList();
        personTwo.addExpenditure("clothes", 30);
        String description = "clothes";
        double amount = 30;
        assertEquals(description, personTwo.getDescription(VALID_INDEX));
        assertEquals(amount, personTwo.getExpenditureValue(VALID_INDEX));

    }

    @Test
    public void getDescription_validIndex_expectSameDescription() {
        String inputString = "Food";
        String getDescriptionString = personOne.getDescription(VALID_INDEX);
        assertEquals(inputString, getDescriptionString);
    }

    @Test
    public void getExpenditureValue_validIndex_expectSameDescription() {
        double inputAmount = 20;
        double getAmount = personOne.getExpenditureValue(VALID_INDEX);
        assertEquals(inputAmount, getAmount);
    }

    @Test
    public void remove_expenditureExists_removesNormally() {
        int numberOfExpenditureBeforeRemoval = personOne.getNumberOfExpenditures();
        personOne.remove(VALID_INDEX);
        int numberOfExpenditureAfterRemoval = personOne.getNumberOfExpenditures();
        assertEquals(numberOfExpenditureBeforeRemoval - 1, numberOfExpenditureAfterRemoval);
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
    public void getExpenditureValue_invalidIndex_expectAssertionError() {
        try {
            personOne.getExpenditureValue(INVALID_INDEX);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void addExpenditure_nullDescription_expectAssertionError() {
        ExpenditureList testList = new ExpenditureList();
        try {
            testList.addExpenditure(null, 24);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

}