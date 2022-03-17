package seedu.allonus.contacts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ContactTest {
    Contact alice = new Contact("Alice", "FOS", "e1@u.nus.edu", "AB");
    Contact bob = new Contact("Bob", "FASS", "e2@u.nus.edu", "BC");
    Contact charlie = new Contact("Charlie", "SDE", "e3@u.nus.edu", "CD");
    Contact david = new Contact("David", "SoC", "e4@u.nus.edu", "DE");

    @Test
    public void getNameWorksForContacts() {
        assertEquals("Alice", alice.getName());
        assertEquals("Bob", bob.getName());
        assertEquals("Charlie", charlie.getName());
        assertEquals("David", david.getName());

        assertNotEquals("Alice", bob.getName());
        assertNotEquals("Bob", charlie.getName());
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
