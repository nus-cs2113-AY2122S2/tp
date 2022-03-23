package seedu.allonus.contacts;

import org.junit.jupiter.api.Test;
import seedu.allonus.contacts.entry.Contact;
import seedu.allonus.contacts.entry.Description;
import seedu.allonus.contacts.entry.Email;
import seedu.allonus.contacts.entry.Faculty;
import seedu.allonus.contacts.entry.Name;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContactParserTest {

    private ContactParser defaultContactParser = new ContactParser();
    private String aliceString = "n/Alice f/FOS e/e1@u.nus.edu d/AB";
    private String bobString = "n/Bob f/FASS e/e2@u.nus.edu d/BC";
    private String charlieString = "f/SDE d/CD n/Charlie e/e3@u.nus.edu";
    private String davidString = "e/e4@u.nus.edu f/SoC d/DE n/David";

    private String invalidNameString = "n/ f/valid e/valid d/valid";
    private String invalidFacultyString = "n/valid e/valid d/valid f/";

    Contact charlie = new Contact(new Name("Charlie"),
            new Faculty("SDE"),
            new Email("e3@u.nus.edu"),
            new Description("CD"));
    Contact david = new Contact(new Name("David"),
            new Faculty("SoC"),
            new Email("e4@u.nus.edu"),
            new Description("DE"));

    private ArrayList<String> aliceFields =
            new ArrayList<>(Arrays.asList("n/Alice", "f/FOS", "e/e1@u.nus.edu", "d/AB"));
    private ArrayList<String> bobFields =
            new ArrayList<>(Arrays.asList("n/Bob", "f/FASS", "e/e2@u.nus.edu", "d/BC"));

    @Test
    void getFieldStringsWorks() {
        ArrayList<String> parsedFieldsOfAlice = defaultContactParser.getFieldStrings(aliceString);
        ArrayList<String> parsedFieldsOfBob = defaultContactParser.getFieldStrings(bobString);

        for (String field : parsedFieldsOfAlice) {
            assertTrue(aliceFields.contains(field));
        }
        for (String field : parsedFieldsOfBob) {
            assertFalse(aliceFields.contains(field));
        }
    }

    @Test
    void setContactFieldsWorks() throws InvalidContactField {
        Contact testContact = new Contact(new Name(""), new Faculty(""),
                new Email(""), new Description(""));

        defaultContactParser.setContactFields(testContact, bobFields);
        assertTrue(bobFields.contains("n/" + testContact.getName().toString()));
        assertTrue(bobFields.contains("f/" + testContact.getFaculty().toString()));
        assertTrue(bobFields.contains("e/" + testContact.getEmail().toString()));
        assertTrue(bobFields.contains("d/" + testContact.getDescription().toString()));
    }

    @Test
    void parseContact_validString_parsesNormally() throws InvalidContactField {
        Contact parsedCharlie = defaultContactParser.parseContact(charlieString);
        Contact parsedDavid = defaultContactParser.parseContact(davidString);

        assertEquals(parsedCharlie.toString(), charlie.toString());
        assertEquals(parsedDavid.toString(), david.toString());

        assertNotEquals(parsedCharlie.toString(), david.toString());
        assertNotEquals(parsedDavid.toString(), charlie.toString());
    }

    @Test
    void parseContact_invalidString_throwsInvalidContactField() {
        assertThrows(InvalidContactField.class, () -> defaultContactParser.parseContact(invalidNameString));
        assertThrows(InvalidContactField.class, () -> defaultContactParser.parseContact(invalidFacultyString));
    }
}