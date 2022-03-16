package seedu.planitarium.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonListTest {
    @Test
    public void getNumberOfMembers_newPersonList_returnZero() {
        PersonList list = new PersonList();
        assertEquals(0, list.getNumberOfMembers());
    }

    @Test
    public void getNumberOfMembers_addPerson_returnOne() {
        PersonList list = new PersonList();
        list.addPerson("Alice");
        assertEquals(1, list.getNumberOfMembers());
    }
    
    @Test
    public void getNumberOfMembers_addAndDeletePerson_returnZero() {
        PersonList list = new PersonList();
        list.addPerson("Alice");
        list.removePerson(1);
        assertEquals(0, list.getNumberOfMembers());
    }
}
