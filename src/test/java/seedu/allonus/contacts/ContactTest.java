package seedu.allonus.contacts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ContactTest {
    Contact alice = new Contact("Alice");
    Contact bob = new Contact("Bob");
    Contact charlie = new Contact("Charlie");
    Contact david = new Contact("David");

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
        assertEquals("Alice", alice.toString());
        assertEquals("Bob", bob.toString());
        assertEquals("Charlie", charlie.toString());
        assertEquals("David", david.toString());

        assertNotEquals("Alice", bob.toString());
        assertNotEquals("Bob", charlie.toString());
    }
}
