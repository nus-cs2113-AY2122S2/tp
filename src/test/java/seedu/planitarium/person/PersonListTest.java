package seedu.planitarium.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class PersonListTest {
    private static final String VALID_NAME = "Alice";
    private static final int INVALID_INDEX = -10;

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
        list.removePerson(1);
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
    public void removePerson_validIndex_success() {
        PersonList list = new PersonList();
        list.addPerson(VALID_NAME);
        list.removePerson(1);
        assertTrue(list.getPersonList().isEmpty());
    }

    @Test
    public void removePerson_invalidIndex_assertionError() {
        PersonList list = new PersonList();
        try {
            list.removePerson(INVALID_INDEX);
            fail();
        } catch (AssertionError e) {
            assertNull(e.getMessage());
        }
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
}
