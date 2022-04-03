//@@author teanweijun

package seedu.planitarium.family;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class PersonListTest {
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final String VALID_NAME = "Alice";
    private static final int INVALID_INDEX = -10;
    private static final int FIRST_ENTRY = 1;
    private static final boolean SILENT = true;
    private static final Double VALID_AMOUNT = 1000.0;
    private static final Double NEW_AMOUNT = 500.0;
    private static final String VALID_DESCRIPTION = "Testing";
    private static final boolean PERMANENT = true;
    private static final Double FIRST_AMOUNT = 10.0;
    private static final Double SECOND_AMOUNT = 5.0;

    private static final String INDENTATION = "   ";
    private static final String EMPTY_STRING = "";
    private static final String SAMPLE_LIST = "1. Alice" + System.lineSeparator()
            + INDENTATION + "List of incomes:" + System.lineSeparator()
            + INDENTATION + "List of expenditures:" + System.lineSeparator();

    private ByteArrayOutputStream redirectIO() {
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        return newOut;
    }

    @Test
    public void getListIndex_validIndex_success() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        assertEquals(0, list.getListIndex(FIRST_ENTRY));
    }

    @Test
    public void getListIndex_invalidIndex_assertionError() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        try {
            assertEquals(0, list.getListIndex(INVALID_INDEX));
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void getPerson_validIndex_success() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        assertEquals(VALID_NAME, list.getPerson(FIRST_ENTRY).getName());
    }

    @Test
    public void getPerson_invalidIndex_assertionError() {
        PersonList list = new PersonList();
        try {
            list.getPerson(INVALID_INDEX);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void getNumberOfMembers_newPersonList_returnZero() {
        PersonList list = new PersonList();
        assertEquals(0, list.getNumberOfMembers());
    }

    @Test
    public void getNumberOfMembers_addPerson_returnOne() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        assertEquals(1, list.getNumberOfMembers());
    }
    
    @Test
    public void getNumberOfMembers_addAndDeletePerson_returnZero() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        list.deletePerson(1);
        assertEquals(0, list.getNumberOfMembers());
    }

    @Test
    public void addPerson_nullName_assertionError() {
        PersonList list = new PersonList();
        try {
            list.addPerson(null);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void deletePerson_validIndex_success() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        list.deletePerson(FIRST_ENTRY);
        assertTrue(list.getPersonList().isEmpty());
    }

    @Test
    public void deletePerson_invalidIndex_assertionError() {
        PersonList list = new PersonList();
        try {
            list.deletePerson(INVALID_INDEX);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void getRemain_addIncomeAndExpenditure_returnDiff() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        list.addIncome(FIRST_ENTRY, VALID_DESCRIPTION, FIRST_AMOUNT, PERMANENT, SILENT);
        list.addExpend(FIRST_ENTRY, VALID_DESCRIPTION, SECOND_AMOUNT, FIRST_ENTRY, PERMANENT, SILENT);
        assertEquals(FIRST_AMOUNT - SECOND_AMOUNT, list.getRemain());
    }

    @Test
    public void getTotalIncome_addIncome_returnAmount() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        list.addIncome(FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT, PERMANENT, SILENT);
        assertEquals(VALID_AMOUNT, list.getTotalIncome());
    }

    @Test
    public void getTotalExpenditure_addExpend_returnAmount() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        list.addExpend(FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT, FIRST_ENTRY, PERMANENT, SILENT);
        assertEquals(VALID_AMOUNT, list.getTotalExpenditure());
    }

    @Test
    public void list_noEntries_printNothing() {
        ByteArrayOutputStream newOut = redirectIO();

        PersonList list = new PersonList();
        list.list();
        assertEquals(EMPTY_STRING, newOut.toString());

        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void list_validEntry_printOnlyHeader() {
        ByteArrayOutputStream newOut = redirectIO();

        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        list.list();
        assertEquals(SAMPLE_LIST, newOut.toString());

        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void addIncome_validInputs_incomeCountOne() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        list.addIncome(FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT, PERMANENT, SILENT);
        assertEquals(1, list.getNumberOfIncomes(FIRST_ENTRY));
    }

    @Test
    public void deleteIncome_addThenDelete_incomeCountOneThenZero() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        list.addIncome(FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT, PERMANENT, SILENT);
        assertEquals(1, list.getNumberOfIncomes(FIRST_ENTRY));
        list.deleteIncome(FIRST_ENTRY, FIRST_ENTRY);
        assertEquals(0, list.getNumberOfIncomes(FIRST_ENTRY));
    }

    @Test
    public void addExpend_validInputs_expendCountOne() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        list.addExpend(FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT,FIRST_ENTRY, PERMANENT, SILENT);
        assertEquals(1, list.getNumberOfExpenditures(FIRST_ENTRY));
    }

    @Test
    public void deleteExpend_addThenDelete_expendCountOneThenZero() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        list.addExpend(FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT, FIRST_ENTRY, PERMANENT, SILENT);
        assertEquals(1, list.getNumberOfExpenditures(FIRST_ENTRY));
        list.deleteExpend(FIRST_ENTRY, FIRST_ENTRY);
        assertEquals(0, list.getNumberOfExpenditures(FIRST_ENTRY));
    }

    @Test
    public void editIncome_addThenEdit_incomeChange() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        list.addIncome(FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT, PERMANENT, SILENT);
        assertEquals(VALID_AMOUNT, list.getTotalIncome());
        list.editIncome(FIRST_ENTRY, FIRST_ENTRY, null, NEW_AMOUNT, null);
        assertEquals(NEW_AMOUNT, list.getTotalIncome());
    }

    @Test
    public void editExpend_addThenEdit_expendChange() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        list.addExpend(FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT, FIRST_ENTRY, PERMANENT, SILENT);
        assertEquals(VALID_AMOUNT, list.getTotalExpenditure());
        list.editExpend(FIRST_ENTRY, FIRST_ENTRY, null, NEW_AMOUNT, null, null);
        assertEquals(NEW_AMOUNT, list.getTotalExpenditure());
    }
}
