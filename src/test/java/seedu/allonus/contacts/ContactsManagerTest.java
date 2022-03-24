package seedu.allonus.contacts;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.allonus.contacts.entry.Contact;
import seedu.allonus.contacts.entry.Description;
import seedu.allonus.contacts.entry.Email;
import seedu.allonus.contacts.entry.Faculty;
import seedu.allonus.contacts.entry.Name;

import java.util.ArrayList;
import java.util.Arrays;

public class ContactsManagerTest {
    private Contact aliceBetsy;
    private Contact bobChaplin;
    private Contact charlieDouglas;
    private Contact davidElliot;

    private static final int CONTACTS_LIST_MAX_SIZE = 100;
    private ArrayList<Contact> emptyContactsList;
    private ArrayList<Contact> defaultContactsList;

    @BeforeEach
    public void setUp() throws Exception {

        aliceBetsy = new Contact(new Name("Alice Betsy"),
                new Faculty("FOS"),
                new Email("e1@u.nus.edu"),
                new Description("AB"));
        bobChaplin = new Contact(new Name("Bob Chaplin"),
                new Faculty("FASS"),
                new Email("e2@u.nus.edu"),
                new Description("BC"));
        charlieDouglas = new Contact(new Name("Charlie Douglas"),
                new Faculty("SDE"),
                new Email("e3@u.nus.edu"),
                new Description("CD"));
        davidElliot = new Contact(new Name("David Elliot"),
                new Faculty("SoC"),
                new Email("e4@u.nus.edu"),
                new Description("DE"));

        emptyContactsList = new ArrayList<>(CONTACTS_LIST_MAX_SIZE);
        defaultContactsList = new ArrayList<>(CONTACTS_LIST_MAX_SIZE);
        defaultContactsList.add(aliceBetsy);
        defaultContactsList.add(bobChaplin);
    }

    @Test
    public void containsContact() throws Exception {
        ArrayList<Contact> personsWhoShouldBeIn = new ArrayList<>(Arrays.asList(aliceBetsy, bobChaplin));
        ArrayList<Contact> personsWhoShouldNotBeIn = new ArrayList<>(Arrays.asList(charlieDouglas, davidElliot));

        for (Contact personWhoShouldBeIn : personsWhoShouldBeIn) {
            assertTrue(defaultContactsList.contains(personWhoShouldBeIn));
        }
        for (Contact personWhoShouldNotBeIn : personsWhoShouldNotBeIn) {
            assertFalse(defaultContactsList.contains(personWhoShouldNotBeIn));
        }

        ArrayList<Contact> allPersons = new ArrayList<>(
                Arrays.asList(aliceBetsy, bobChaplin, charlieDouglas, davidElliot)
        );

        for (Contact person : allPersons) {
            assertFalse(emptyContactsList.contains(person));
        }
    }
}
