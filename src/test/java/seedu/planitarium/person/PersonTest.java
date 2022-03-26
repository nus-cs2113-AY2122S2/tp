//@@author teanweijun

package seedu.planitarium.person;

import org.junit.jupiter.api.Test;
import seedu.planitarium.global.Constants;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    private static final String VALID_NAME = "Alice";
    private static final String VALID_DESCRIPTION = "Test";
    private static final int VALID_AMOUNT = 1000;
    private static final int INVALID_INDEX = -10;

    @Test
    public void person_invalidName_assertionError() {
        try {
            Person person = new Person(null);
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
    public void addIncome_invalidDescription_assertionError() {
        Person person = new Person(VALID_NAME);
        try {
            person.addIncome(null, VALID_AMOUNT, false, Constants.FOR_USER);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
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
    public void addExpend_invalidDescription_assertionError() {
        Person person = new Person(VALID_NAME);
        try {
            person.addExpend(null, VALID_AMOUNT, false, Constants.FOR_USER);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
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
}
