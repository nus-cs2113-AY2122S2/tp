package seedu.allonus.storage;

import seedu.allonus.contacts.ContactsManager;
import seedu.allonus.contacts.entry.Contact;
import seedu.allonus.contacts.entry.Email;
import seedu.allonus.contacts.entry.Faculty;
import seedu.allonus.contacts.entry.Name;
import seedu.allonus.contacts.entry.Description;

import java.util.ArrayList;

/**
 * Stub class for ContactsManager for unit testing.
 */
public class ContactsManagerStub extends ContactsManager {
    private ArrayList<Contact> list = new ArrayList<Contact>();
    private static int number;

    public ContactsManagerStub() {
        this.number = 0;
    }

    @Override
    public int getContactsCount() {
        return number;
    }

    @Override
    public ArrayList<Contact> getContactsList() {
        return list;
    }

    @Override
    public void loadAdd(String savedContact) {
        String[] components = savedContact.split(" ",5);
        Contact contact = new Contact(new Name(components[1].substring(2)), new Faculty(components[2].substring(2)),
                new Email(components[3].substring(2)),new Description(components[4].substring(2)));
        list.add(contact);
        number += 1;
    }
}
