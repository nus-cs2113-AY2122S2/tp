//@@author HansHengGit

package seedu.planitarium.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.planitarium.category.Category;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpenditureListTest {

    private static final int INVALID_INDEX = -1;
    private static final int VALID_INDEX = 1;
    private static final int NUM_OF_EXP = 2;
    private static final int VALID_CAT = 2;
    private static final double VALID_AMOUNT = 30.00;
    private static final double VALID_AMOUNT_2 = 5.00;
    private static final double EXPECTED_AMOUNT = 35.00;
    private static final String VALID_DESC = "clothes";
    private static final String VALID_DESC_2 = "Transport";
    private static final String INDENTATION = "   ";
    private static final String EXPECTED_LABELS = VALID_DESC + ": $30.00 - Recurring: false - "
            + "Category: Food and Drinks" + System.lineSeparator();
    private static final String EXPECTED_LABEL_NUMBERED = INDENTATION + "1. " + VALID_DESC
            + ": $30.00 - Recurring: false - Category: Food and Drinks" + System.lineSeparator();
    private static final PrintStream ORIGINAL_OUT = System.out;


    private ExpenditureList personOne;

    @BeforeEach
    public void setUp() {
        personOne = new ExpenditureList();
        personOne.addExpenditure(VALID_DESC, VALID_AMOUNT, VALID_CAT, false);
        personOne.addExpenditure(VALID_DESC_2, VALID_AMOUNT_2, VALID_CAT, false);
    }

    @Test
    public void addExpenditure_newExpenditure_existsInExpenditure() {
        ExpenditureList personTwo = new ExpenditureList();
        personTwo.addExpenditure(VALID_DESC, VALID_AMOUNT, VALID_CAT, false);
        assertEquals(VALID_DESC, personTwo.getDescription(VALID_INDEX));
        assertEquals(VALID_AMOUNT, personTwo.getExpenditureValue(VALID_INDEX));

    }

    @Test
    public void getDescription_validIndex_expectSameDescription() {
        String getDescriptionString = personOne.getDescription(VALID_INDEX);
        assertEquals(VALID_DESC, getDescriptionString);
    }

    @Test
    public void getExpenditureValue_validIndex_expectSameDescription() {
        double getAmount = personOne.getExpenditureValue(VALID_INDEX);
        assertEquals(VALID_AMOUNT, getAmount);
    }

    @Test
    public void getPermanent_validIndex_expectFalse() {
        boolean isPermanent = personOne.isPermanent(VALID_INDEX);
        assertFalse(isPermanent);
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
            testList.addExpenditure(null, VALID_AMOUNT, VALID_CAT, false);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    //@@author tjiarong

    @Test
    public void editExpenditure_validParam_Success() {
        personOne.editExpenditure(VALID_INDEX, VALID_DESC_2, VALID_AMOUNT_2, VALID_CAT, true);
        assertEquals(personOne.getDescription(VALID_INDEX), VALID_DESC_2);
        assertEquals(personOne.getExpenditureValue(VALID_INDEX), VALID_AMOUNT_2);
        assertEquals(personOne.getCategory(VALID_INDEX), Category.getLabelForIndex(VALID_CAT));
        assertTrue(personOne.isPermanent(VALID_INDEX));
    }

    @Test
    public void findExpenditure_validParam_Success() {
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        personOne.find(VALID_DESC, VALID_CAT);
        assertEquals(EXPECTED_LABELS, newOut.toString());
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void findExpenditure_allCat_Success() {
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        personOne.find(VALID_DESC, VALID_CAT);
        assertEquals(EXPECTED_LABELS, newOut.toString());
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void getNumOfExp_success() {
        int numOfExp = personOne.getNumberOfExpenditures();
        assertEquals(NUM_OF_EXP, numOfExp);
    }

    @Test
    public void updateList_noChange_success() {
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        ExpenditureList personTwo = new ExpenditureList();
        personTwo.addExpenditure(VALID_DESC, VALID_AMOUNT, VALID_CAT, false);
        personTwo.printExpenditureList();
        assertEquals(EXPECTED_LABEL_NUMBERED, newOut.toString());
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void getTotalExp_success() {
        double totalExp = personOne.getTotalExpenditure();
        assertEquals(EXPECTED_AMOUNT, totalExp);
    }
}
