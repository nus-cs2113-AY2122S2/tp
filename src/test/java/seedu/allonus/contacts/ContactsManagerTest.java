package seedu.allonus.contacts;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        aliceBetsy = new Contact("Alice Betsy", "FOS", "e1@u.nus.edu", "AB");
        bobChaplin = new Contact("Bob Chaplin", "FASS", "e2@u.nus.edu", "BC");
        charlieDouglas = new Contact("Charlie Douglas", "SDE", "e3@u.nus.edu", "CD");
        davidElliot = new Contact("David Elliot", "SoC", "e4@u.nus.edu", "DE");

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
