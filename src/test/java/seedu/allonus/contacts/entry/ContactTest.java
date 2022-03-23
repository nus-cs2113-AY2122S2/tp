package seedu.allonus.contacts.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ContactTest {
    Contact alice = new Contact(new Name("Alice"),
            new Faculty("FOS"),
            new Email("e1@u.nus.edu"),
            new Description("AB"));
    Contact bob = new Contact(new Name("Bob"),
            new Faculty("FASS"),
            new Email("e2@u.nus.edu"),
            new Description("BC"));
    Contact charlie = new Contact(new Name("Charlie"),
            new Faculty("SDE"),
            new Email("e3@u.nus.edu"),
            new Description("CD"));
    Contact david = new Contact(new Name("David"),
            new Faculty("SoC"),
            new Email("e4@u.nus.edu"),
            new Description("DE"));

    @Test
    public void getNameWorksForContacts() {
        assertEquals("Alice", alice.getName().toString());
        assertEquals("Bob", bob.getName().toString());
        assertEquals("Charlie", charlie.getName().toString());
        assertEquals("David", david.getName().toString());

        assertNotEquals("Alice", bob.getName().toString());
        assertNotEquals("Bob", charlie.getName().toString());
    }

    @Test
    public void getFacultyWorksForContacts() {
        assertEquals("FOS", alice.getFaculty().toString());
        assertEquals("FASS", bob.getFaculty().toString());
        assertEquals("SDE", charlie.getFaculty().toString());
        assertEquals("SoC", david.getFaculty().toString());

        assertNotEquals("FOS", bob.getFaculty().toString());
        assertNotEquals("FASS", charlie.getFaculty().toString());
    }

    @Test
    public void getEmailWorksForContacts() {
        assertEquals("e1@u.nus.edu", alice.getEmail().toString());
        assertEquals("e2@u.nus.edu", bob.getEmail().toString());
        assertEquals("e3@u.nus.edu", charlie.getEmail().toString());
        assertEquals("e4@u.nus.edu", david.getEmail().toString());

        assertNotEquals("e1@u.nus.edu", bob.getEmail().toString());
        assertNotEquals("e2@u.nus.edu", charlie.getEmail().toString());
    }

    @Test
    public void getDescriptionWorksForContacts() {
        assertEquals("AB", alice.getDescription().toString());
        assertEquals("BC", bob.getDescription().toString());
        assertEquals("CD", charlie.getDescription().toString());
        assertEquals("DE", david.getDescription().toString());

        assertNotEquals("AB", bob.getDescription().toString());
        assertNotEquals("BC", charlie.getDescription().toString());
    }

    @Test
    public void contactsPrintCorrectly() {
        assertEquals("Name: Alice, Faculty: FOS, Email: e1@u.nus.edu, Description: AB", alice.toString());
        assertEquals("Name: Bob, Faculty: FASS, Email: e2@u.nus.edu, Description: BC", bob.toString());
        assertEquals("Name: Charlie, Faculty: SDE, Email: e3@u.nus.edu, Description: CD", charlie.toString());
        assertEquals("Name: David, Faculty: SoC, Email: e4@u.nus.edu, Description: DE", david.toString());

        assertNotEquals("Name: Alice, Faculty: FOS, Email: e1@u.nus.edu, Description: AB", bob.toString());
        assertNotEquals("Name: Bob, Faculty: FASS, Email: e2@u.nus.edu, Description: BC", charlie.toString());
    }
}
