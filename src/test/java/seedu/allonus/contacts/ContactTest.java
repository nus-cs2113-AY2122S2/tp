package seedu.allonus.contacts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import seedu.allonus.contacts.entry.Contact;
import seedu.allonus.contacts.entry.Description;
import seedu.allonus.contacts.entry.Email;
import seedu.allonus.contacts.entry.Faculty;
import seedu.allonus.contacts.entry.Name;

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
    public void contactsPrintCorrectly() {
        assertEquals("Name: Alice, Faculty: FOS, Email: e1@u.nus.edu, Description: AB", alice.toString());
        assertEquals("Name: Bob, Faculty: FASS, Email: e2@u.nus.edu, Description: BC", bob.toString());
        assertEquals("Name: Charlie, Faculty: SDE, Email: e3@u.nus.edu, Description: CD", charlie.toString());
        assertEquals("Name: David, Faculty: SoC, Email: e4@u.nus.edu, Description: DE", david.toString());

        assertNotEquals("Name: Alice, Faculty: FOS, Email: e1@u.nus.edu, Description: AB", bob.toString());
        assertNotEquals("Name: Bob, Faculty: FASS, Email: e2@u.nus.edu, Description: BC", charlie.toString());
    }
}
