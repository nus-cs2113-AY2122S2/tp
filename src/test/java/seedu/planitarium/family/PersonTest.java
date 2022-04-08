//@@author teanweijun

package seedu.planitarium.family;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PersonTest {
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final String VALID_NAME = "Alice";
    private static final String VALID_DESCRIPTION = "Testing";
    private static final double VALID_AMOUNT = 1000;
    private static final double NEW_AMOUNT = 500;
    private static final int INVALID_INDEX = -10;
    private static final int FIRST_ENTRY = 1;
    private static final boolean SILENT = true;
    private static final boolean NOT_SILENT = false;
    private static final boolean PERMANENT = true;

    private static final String ADD_INCOME = "A recurring income of $1000.00 from Testing has been added to Alice"
            + System.lineSeparator();
    private static final String ADD_EXPEND = "A recurring expenditure of $1000.00 for Testing has been added to Alice"
            + System.lineSeparator();

    private ByteArrayOutputStream redirectIO() {
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        return newOut;
    }

    @Test
    public void person_invalidName_assertionError() {
        try {
            new Person(null);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void person_validName_success() {
        Person person = new Person(VALID_NAME);
        assertEquals(VALID_NAME, person.getName());
    }

    @Test
    public void addIncome_validInputs_incomeCountOne() {
        Person person = new Person(VALID_NAME);
        person.addIncome(VALID_DESCRIPTION, VALID_AMOUNT, PERMANENT, SILENT);
        assertEquals(1, person.getNumberOfIncomes());
    }

    @Test
    public void addIncome_notSilent_correctPrinting() {
        ByteArrayOutputStream newOut = redirectIO();

        Person person = new Person(VALID_NAME);
        person.addIncome(VALID_DESCRIPTION, VALID_AMOUNT, PERMANENT, NOT_SILENT);
        assertEquals(ADD_INCOME, newOut.toString());

        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void addIncome_invalidDescription_assertionError() {
        Person person = new Person(VALID_NAME);
        try {
            person.addIncome(null, VALID_AMOUNT, PERMANENT, SILENT);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void deleteIncome_addThenDelete_incomeCountOneThenZero() {
        Person person = new Person(VALID_NAME);
        person.addIncome(VALID_DESCRIPTION, VALID_AMOUNT, PERMANENT, SILENT);
        assertEquals(1, person.getNumberOfIncomes());
        person.deleteIncome(FIRST_ENTRY);
        assertEquals(0, person.getNumberOfIncomes());
    }

    @Test
    public void deleteIncome_invalidIndex_assertionError() {
        Person person = new Person(VALID_NAME);
        try {
            person.deleteIncome(INVALID_INDEX);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void addExpend_validInputs_expendCountOne() {
        Person person = new Person(VALID_NAME);
        person.addExpend(VALID_DESCRIPTION, VALID_AMOUNT,FIRST_ENTRY, PERMANENT, SILENT);
        assertEquals(1, person.getNumberOfExpenditures());
    }

    @Test
    public void addExpend_notSilent_correctPrinting() {
        ByteArrayOutputStream newOut = redirectIO();

        Person person = new Person(VALID_NAME);
        person.addExpend(VALID_DESCRIPTION, VALID_AMOUNT, FIRST_ENTRY, PERMANENT, NOT_SILENT);
        assertEquals(ADD_EXPEND, newOut.toString());

        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void addExpend_invalidDescription_assertionError() {
        Person person = new Person(VALID_NAME);
        try {
            person.addExpend(null, VALID_AMOUNT, 1, PERMANENT, SILENT);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void deleteExpend_addThenDelete_expendCountOneThenZero() {
        Person person = new Person(VALID_NAME);
        person.addExpend(VALID_DESCRIPTION, VALID_AMOUNT, FIRST_ENTRY, PERMANENT, SILENT);
        assertEquals(1, person.getNumberOfExpenditures());
        person.deleteExpend(FIRST_ENTRY);
        assertEquals(0, person.getNumberOfExpenditures());
    }

    @Test
    public void deleteExpend_invalidIndex_assertionError() {
        Person person = new Person(VALID_NAME);
        try {
            person.deleteExpend(INVALID_INDEX);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void saveName_call_returnName() {
        Person person = new Person(VALID_NAME);
        assertEquals("u " + person.getName(), person.getSaveName());
    }

    @Test
    public void editIncome_addThenEdit_incomeChange() {
        Person person = new Person(VALID_NAME);
        person.addIncome(VALID_DESCRIPTION, VALID_AMOUNT, PERMANENT, SILENT);
        assertEquals(VALID_AMOUNT, person.getTotalIncome());
        person.editIncome(FIRST_ENTRY, null, NEW_AMOUNT, null);
        assertEquals(NEW_AMOUNT, person.getTotalIncome());
    }

    @Test
    public void editExpend_addThenEdit_expendChange() {
        Person person = new Person(VALID_NAME);
        person.addExpend(VALID_DESCRIPTION, VALID_AMOUNT, FIRST_ENTRY, PERMANENT, SILENT);
        assertEquals(VALID_AMOUNT, person.getTotalExpenditure());
        person.editExpend(FIRST_ENTRY, null, NEW_AMOUNT, null, null);
        assertEquals(NEW_AMOUNT, person.getTotalExpenditure());
    }
}
