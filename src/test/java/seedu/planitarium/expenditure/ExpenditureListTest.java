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

    /**
     * Creates a test ExpenditureList object with two expenditure added
     */
    @BeforeEach
    public void setUp() {
        personOne = new ExpenditureList();
        personOne.addExpenditure("Food", 20);
        personOne.addExpenditure("Transport", 5);
    }

    /**
     * Creates a new ExpenditureList object and adds an expenditure.
     * Checks if both description and amount matches the test input
     * through getDescription and getExpenditureValue methods.
     */
    @Test
    public void addExpenditure_newExpenditure_existsInExpenditure() {
        ExpenditureList personTwo = new ExpenditureList();
        personTwo.addExpenditure("clothes", 30);
        String description = "clothes";
        double amount = 30;
        assertEquals(description, personTwo.getDescription(VALID_INDEX));
        assertEquals(amount, personTwo.getExpenditureValue(VALID_INDEX));

    }

    /**
     * Checks if the description of the expenditure added matches
     * the test input.
     */
    @Test
    public void getDescription_validIndex_expectSameDescription() {
        String inputString = "Food";
        String getDescriptionString = personOne.getDescription(VALID_INDEX);
        assertEquals(inputString, getDescriptionString);
    }

    /**
     * Checks if the amount of the expenditure matches the
     * the test input.
     */
    @Test
    public void getExpenditureValue_validIndex_expectSameDescription() {
        double inputAmount = 20;
        double getAmount = personOne.getExpenditureValue(VALID_INDEX);
        assertEquals(inputAmount, getAmount);
    }

    /**
     * Checks if the method successfully removes an expenditure
     * by comparing the number of expenditure before and after removal.
     */
    @Test
    public void remove_expenditureExists_removesNormally() {
        int numberOfExpenditureBeforeRemoval = personOne.getNumberOfExpenditures();
        personOne.remove(VALID_INDEX);
        int numberOfExpenditureAfterRemoval = personOne.getNumberOfExpenditures();
        assertEquals(numberOfExpenditureBeforeRemoval - 1, numberOfExpenditureAfterRemoval);
    }

    /**
     * Tests if assertion blocks an invalid index in
     * the remove method.
     */
    @Test
    public void remove_invalidIndex_expectAssertionError() {
        try {
            personOne.remove(INVALID_INDEX);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    /**
     * Tests if assertion blocks an invalid index in
     * the getDescription method.
     */
    @Test
    public void getDescription_invalidIndex_expectAssertionError() {
        try {
            personOne.getDescription(INVALID_INDEX);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    /**
     * Tests if assertion blocks an invalid index in
     * the getExpenditureValue method.
     */
    @Test
    public void getExpenditureValue_invalidIndex_expectAssertionError() {
        try {
            personOne.getExpenditureValue(INVALID_INDEX);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    /**
     * Tests if assertion will block a null description input
     * in the addExpenditure method.
     */
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