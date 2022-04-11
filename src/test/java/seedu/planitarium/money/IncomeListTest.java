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

    private static int INVALID_INDEX = -1;
    private static int VALID_INDEX = 1;
    private static final int NUM_OF_INC = 2;
    private static final double VALID_AMOUNT = 30.00;
    private static final double VALID_AMOUNT_2 = 5.00;
    private static final double EXPECTED_AMOUNT = 35.00;
    private static final String VALID_DESC = "clothes";
    private static final String VALID_DESC_2 = "Transport";
    private static final String INDENTATION = "   ";
    private static final String EXPECTED_LABEL_NUMBERED = INDENTATION + "1. " + VALID_DESC
            + ": $30.00 - Recurring: false" + System.lineSeparator();
    private static final String EXPECTED_LABEL = VALID_DESC + ": $30.00 - Recurring: false"
            + System.lineSeparator();
    private static final PrintStream ORIGINAL_OUT = System.out;

    private IncomeList personOne;

    @BeforeEach
    public void setUp() {
        personOne = new IncomeList();
        personOne.addIncome(VALID_DESC, VALID_AMOUNT, false);
        personOne.addIncome(VALID_DESC_2, VALID_AMOUNT_2, false);
    }

    @Test
    public void addIncome_newIncome_existsInIncome() {
        IncomeList personTwo = new IncomeList();
        personTwo.addIncome(VALID_DESC, VALID_AMOUNT_2, false);
        assertEquals(VALID_DESC, personTwo.getDescription(VALID_INDEX));
        assertEquals(VALID_AMOUNT_2, personTwo.getIncomeValue(VALID_INDEX));

    }

    @Test
    public void getDescription_validIndex_expectSameDescription() {
        String getDescriptionString = personOne.getDescription(VALID_INDEX);
        assertEquals(VALID_DESC, getDescriptionString);
    }

    @Test
    public void getIncomeValue_validIndex_expectSameDescription() {
        double getAmount = personOne.getIncomeValue(VALID_INDEX);
        assertEquals(VALID_AMOUNT, getAmount);
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
            testList.addIncome(null, VALID_AMOUNT_2, false);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void editIncome_validParam_Success() {
        personOne.editIncome(VALID_INDEX, VALID_DESC_2, VALID_AMOUNT_2, true);
        assertEquals(personOne.getDescription(VALID_INDEX), VALID_DESC_2);
        assertEquals(personOne.getIncomeValue(VALID_INDEX), VALID_AMOUNT_2);
        assertEquals(personOne.isPermanent(VALID_INDEX), true);
    }

    @Test
    public void findIncome_validParam_Success() {
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        personOne.find(VALID_DESC);
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
        personTwo.addIncome(VALID_DESC, VALID_AMOUNT, false);
        personTwo.printIncomeList();
        assertEquals(EXPECTED_LABEL_NUMBERED, newOut.toString());
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void getTotalInc_success() {
        double totalIncome = personOne.getTotalIncome();
        assertEquals(EXPECTED_AMOUNT, totalIncome);
    }
}
