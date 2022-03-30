//@@author tjiarong

package seedu.planitarium.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

class IncomeListTest {

    private static final PrintStream ORIGINAL_OUT = System.out;
    private static int INVALID_INDEX = -1;
    private static int VALID_INDEX = 1;
    private IncomeList personOne;
    private static final String EXPECTED_LABEL_NUMBERED = "1. Food: $20.00 - Recurring: false"
            + System.lineSeparator();
    private static final String EXPECTED_LABEL = "Food: $20.00 - Recurring: false"
            + System.lineSeparator();
    private static final int NUM_OF_INC = 2;

    @BeforeEach
    public void setUp() {
        personOne = new IncomeList();
        personOne.addIncome("Food", 20.0, false);
        personOne.addIncome("Transport", 5.0, false);
    }

    @Test
    public void addIncome_newIncome_existsInIncome() {
        IncomeList personTwo = new IncomeList();
        personTwo.addIncome("clothes", 30.0, false);
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
            testList.addIncome(null, 24.0, false);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void editIncome_validParam_Success() {
        personOne.editIncome(1, "Dabao", 1000.0, true);
        assertEquals(personOne.getDescription(1), "Dabao");
        assertEquals(personOne.getIncomeValue(1), 1000.0);
        assertEquals(personOne.isPermanent(1), true);
    }

    @Test
    public void findIncome_validParam_Success() {
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        personOne.find("Food");
        assertEquals(EXPECTED_LABEL, newOut.toString());
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void getNumOfInc_success() {
        int numOfInc = personOne.getNumberOfIncomes();
        assertEquals(NUM_OF_INC, numOfInc);
    }

    @Test
    public void updateList_noChange_success() {
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        IncomeList personTwo = new IncomeList();
        personTwo.addIncome("Food", 20.0, false);
        personTwo.printIncomeList();
        assertEquals(EXPECTED_LABEL_NUMBERED, newOut.toString());
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void getTotalInc_success() {
        double totalIncome = personOne.getTotalIncome();
        double expectedIncome = 25.0;
        assertEquals(expectedIncome, totalIncome);
    }
}