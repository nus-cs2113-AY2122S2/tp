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

    private static int INVALID_INDEX = -1;
    private static int VALID_INDEX = 1;
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final String EXPECTED_LABELS = "Food: $20.00 - Recurring: false - "
            + "Category: Food and Drinks" + System.lineSeparator();
    private static final String EXPECTED_LABEL_NUMBERED = "1. Food: $20.00 - Recurring: false "
            + "- Category: Food and Drinks" + System.lineSeparator();
    private static final int NUM_OF_EXP = 2;

    private ExpenditureList personOne;

    @BeforeEach
    public void setUp() {
        personOne = new ExpenditureList();
        personOne.addExpenditure("Food", 20, 1, false);
        personOne.addExpenditure("Transport", 5, 1, false);
    }

    @Test
    public void addExpenditure_newExpenditure_existsInExpenditure() {
        ExpenditureList personTwo = new ExpenditureList();
        personTwo.addExpenditure("clothes", 30, 1, false);
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
            testList.addExpenditure(null, 24, 1, false);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    //@@author tjiarong

    @Test
    public void editExpenditure_validParam_Success() {
        personOne.editExpenditure(1, "Dabao", 1000.0, 2, true);
        assertEquals(personOne.getDescription(1), "Dabao");
        assertEquals(personOne.getExpenditureValue(1), 1000.0);
        assertEquals(personOne.getCategory(1), Category.getLabelForIndex(2));
        assertTrue(personOne.isPermanent(1));
    }

    @Test
    public void findExpenditure_validParam_Success() {
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        personOne.find("Food", 1);
        assertEquals(EXPECTED_LABELS, newOut.toString());
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void findExpenditure_allCat_Success() {
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        personOne.find("Food", 0);
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
        personTwo.addExpenditure("Food", 20, 1, false);
        personTwo.printExpenditureList();
        assertEquals(EXPECTED_LABEL_NUMBERED, newOut.toString());
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void getTotalExp_success() {
        double totalExp = personOne.getTotalExpenditure();
        double expectedExp = 25.0;
        assertEquals(expectedExp, totalExp);
    }

}