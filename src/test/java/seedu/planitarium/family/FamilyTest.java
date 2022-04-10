//@@ author teanweijun

package seedu.planitarium.family;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class FamilyTest {
    private static final PrintStream ORIGINAL_OUT = System.out;
    private static final int INVALID_INDEX = -1;
    private static final int PARENTS_INDEX = 1;
    private static final int MY_GEN_INDEX = 2;
    private static final int CHILDREN_INDEX = 3;
    private static final String VALID_NAME = "Alice";
    private static final boolean SILENT = true;
    private static final boolean NOT_SILENT = false;
    private static final int FIRST_ENTRY = 1;
    private static final Double VALID_AMOUNT = 1000.0;
    private static final Double NEW_AMOUNT = 500.0;
    private static final String VALID_DESCRIPTION = "Testing";
    private static final boolean PERMANENT = true;

    private static final String INDENTATION = "   ";
    private static final String ADD_TO_PARENTS = "Alice has been successfully added to Parents"
            + System.lineSeparator();
    private static final String ADD_TO_MY_GEN = "Alice has been successfully added to My generation"
            + System.lineSeparator();
    private static final String ADD_TO_CHILDREN = "Alice has been successfully added to Children"
            + System.lineSeparator();
    private static final String EMPTY_STRING = "";
    private static final String BASE_OVERVIEW = "Here is the overview for your family:" + System.lineSeparator()
            + "1. Parents:" + System.lineSeparator()
            + INDENTATION + "Income: $0.00" + System.lineSeparator()
            + INDENTATION + "Expenditure: $0.00" + System.lineSeparator()
            + INDENTATION + "Disposable: $0.00" + System.lineSeparator()
            + System.lineSeparator()
            + "2. My generation:" + System.lineSeparator()
            + INDENTATION + "Income: $0.00" + System.lineSeparator()
            + INDENTATION + "Expenditure: $0.00" + System.lineSeparator()
            + INDENTATION + "Disposable: $0.00" + System.lineSeparator()
            + System.lineSeparator()
            + "3. Children:" + System.lineSeparator()
            + INDENTATION + "Income: $0.00" + System.lineSeparator()
            + INDENTATION + "Expenditure: $0.00" + System.lineSeparator()
            + INDENTATION + "Disposable: $0.00" + System.lineSeparator()
            + System.lineSeparator()
            + "Total disposable income in family: $0.00" + System.lineSeparator();
    private static final String PARENTS_LIST = "For Parents:" + System.lineSeparator();
    private static final String MY_GEN_LIST = "For My generation:" + System.lineSeparator();
    private static final String CHILDREN_LIST = "For Children:" + System.lineSeparator();

    private ByteArrayOutputStream redirectIO() {
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newOut));
        return newOut;
    }

    @Test
    public void getList_getParents_returnParents() {
        Family family = new Family();
        family.getList(PARENTS_INDEX);
    }

    @Test
    public void getList_invalidIndex_assertionError() {
        Family family = new Family();
        try {
            family.getList(INVALID_INDEX);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void addPerson_toParents_printString() {
        ByteArrayOutputStream newOut = redirectIO();

        Family family = new Family();
        family.addPerson(PARENTS_INDEX, VALID_NAME, NOT_SILENT);
        assertEquals(ADD_TO_PARENTS, newOut.toString());

        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void addPerson_toMyGen_printString() {
        ByteArrayOutputStream newOut = redirectIO();

        Family family = new Family();
        family.addPerson(MY_GEN_INDEX, VALID_NAME, NOT_SILENT);
        assertEquals(ADD_TO_MY_GEN, newOut.toString());

        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void addPerson_toChildren_printString() {
        ByteArrayOutputStream newOut = redirectIO();

        Family family = new Family();
        family.addPerson(CHILDREN_INDEX, VALID_NAME, NOT_SILENT);
        assertEquals(ADD_TO_CHILDREN, newOut.toString());

        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void addPerson_silent_noPrinting() {
        ByteArrayOutputStream newOut = redirectIO();

        Family family = new Family();
        family.addPerson(PARENTS_INDEX, VALID_NAME, SILENT);
        assertEquals(EMPTY_STRING, newOut.toString());

        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void deletePerson_addThenDelete_memberCountOneThenZero() {
        Family family = new Family();
        family.addPerson(PARENTS_INDEX, VALID_NAME, SILENT);
        assertEquals(1, family.getNumberOfMembers(PARENTS_INDEX));
        family.deletePerson(PARENTS_INDEX, FIRST_ENTRY);
        assertEquals(0, family.getNumberOfMembers(PARENTS_INDEX));
    }

    @Test
    public void addIncome_validInputs_incomeCountOne() {
        Family family = new Family();
        family.addPerson(PARENTS_INDEX, VALID_NAME, SILENT);
        family.addIncome(PARENTS_INDEX, FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT, PERMANENT, SILENT);
        assertEquals(1, family.getNumberOfIncomes(PARENTS_INDEX, FIRST_ENTRY));
    }

    @Test
    public void deleteIncome_addThenDelete_incomeCountOneThenZero() {
        Family family = new Family();
        family.addPerson(PARENTS_INDEX, VALID_NAME, SILENT);
        family.addIncome(PARENTS_INDEX, FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT, PERMANENT, SILENT);
        assertEquals(1, family.getNumberOfIncomes(PARENTS_INDEX, FIRST_ENTRY));
        family.deleteIncome(PARENTS_INDEX, FIRST_ENTRY, FIRST_ENTRY);
        assertEquals(0, family.getNumberOfIncomes(PARENTS_INDEX, FIRST_ENTRY));
    }

    @Test
    public void addExpend_validInputs_expendCountOne() {
        Family family = new Family();
        family.addPerson(PARENTS_INDEX, VALID_NAME, SILENT);
        family.addExpend(PARENTS_INDEX, FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT, FIRST_ENTRY, PERMANENT, SILENT);
        assertEquals(1, family.getNumberOfExpenditures(PARENTS_INDEX, FIRST_ENTRY));
    }

    @Test
    public void deleteExpend_addThenDelete_expendCountOneThenZero() {
        Family family = new Family();
        family.addPerson(PARENTS_INDEX, VALID_NAME, SILENT);
        family.addExpend(PARENTS_INDEX, FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT, FIRST_ENTRY, PERMANENT, SILENT);
        assertEquals(1, family.getNumberOfExpenditures(PARENTS_INDEX, FIRST_ENTRY));
        family.deleteExpend(PARENTS_INDEX, FIRST_ENTRY, FIRST_ENTRY);
        assertEquals(0, family.getNumberOfExpenditures(PARENTS_INDEX, FIRST_ENTRY));
    }

    @Test
    public void overview_noEntries_printAllZero() {
        ByteArrayOutputStream newOut = redirectIO();

        Family family = new Family();
        family.overview();
        assertEquals(BASE_OVERVIEW, newOut.toString());

        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void list_parentsNoEntries_printOnlyHeader() {
        ByteArrayOutputStream newOut = redirectIO();

        Family family = new Family();
        family.list(PARENTS_INDEX);
        assertEquals(PARENTS_LIST, newOut.toString());

        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void list_myGenNoEntries_printOnlyHeader() {
        ByteArrayOutputStream newOut = redirectIO();

        Family family = new Family();
        family.list(MY_GEN_INDEX);
        assertEquals(MY_GEN_LIST, newOut.toString());

        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void list_childrenNoEntries_printOnlyHeader() {
        ByteArrayOutputStream newOut = redirectIO();

        Family family = new Family();
        family.list(CHILDREN_INDEX);
        assertEquals(CHILDREN_LIST, newOut.toString());

        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void editIncome_addThenEdit_incomeChange() {
        Family family = new Family();
        family.addPerson(PARENTS_INDEX, VALID_NAME, SILENT);
        family.addIncome(PARENTS_INDEX, FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT, PERMANENT, SILENT);
        assertEquals(VALID_AMOUNT, family.getList(PARENTS_INDEX).getTotalIncome());
        family.editIncome(PARENTS_INDEX, FIRST_ENTRY, FIRST_ENTRY, null, NEW_AMOUNT, null);
        assertEquals(NEW_AMOUNT, family.getList(PARENTS_INDEX).getTotalIncome());
    }

    @Test
    public void editExpend_addThenEdit_expendChange() {
        Family family = new Family();
        family.addPerson(PARENTS_INDEX, VALID_NAME, SILENT);
        family.addExpend(PARENTS_INDEX, FIRST_ENTRY, VALID_DESCRIPTION, VALID_AMOUNT, FIRST_ENTRY, PERMANENT,
                SILENT);
        assertEquals(VALID_AMOUNT, family.getList(PARENTS_INDEX).getTotalExpenditure());
        family.editExpend(PARENTS_INDEX, FIRST_ENTRY, FIRST_ENTRY, null, NEW_AMOUNT, null,
                null);
        assertEquals(NEW_AMOUNT, family.getList(PARENTS_INDEX).getTotalExpenditure());
    }
}
