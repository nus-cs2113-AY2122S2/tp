package seedu.duke.contacts;


import seedu.duke.ui.TextUi;

import java.util.ArrayList;

public class ContactsManager {

    private static final String CONTACTS_WELCOME_MESSAGE = "Welcome to Contacts Manager";
    private static final int CONTACTS_LIST_MAX_SIZE = 100;
    private static final ArrayList<Contact> contactsList = new ArrayList<>(CONTACTS_LIST_MAX_SIZE);

    private static void contactsWelcome() {
        System.out.println(CONTACTS_WELCOME_MESSAGE);
    }

    private static void listContacts() {
        if (contactsList.size() == 0) {
            System.out.println("You haven't added any contacts to your list yet!");
            return;
        }

        String listAsString = "";
        for (int i = 0; i < contactsList.size(); i++) {
            Contact curr = contactsList.get(i);
            listAsString = listAsString.concat(String.format(" %d. %s\n", i + 1, curr));
        }
        System.out.println("Here are the contacts in your list:\n" + listAsString);
    }

    private static void deleteContact() {
        System.out.println("delete");
    }

    private static void addContact(String userInput) {
        System.out.println("add");
    }

    public static void contactsRunner(TextUi ui) {
        contactsWelcome();
        String userInput;
        while (true) {
            userInput = ui.getUserInput();
            if (userInput.equals("menu")) {
                return;
            } else if (userInput.equals("list")) {
                listContacts();
            } else if (userInput.startsWith("rm")) {
                deleteContact();
            } else {
                addContact(userInput);
            }
        }
    }


}
