package seedu.duke.contacts;


import seedu.duke.ui.TextUi;

public class ContactsManager {

    private static final String CONTACTS_WELCOME_MESSAGE = "Welcome to Contacts Manager";

    private static void contactsWelcome() {
        System.out.println(CONTACTS_WELCOME_MESSAGE);
    }

    private static void listContacts() {
        System.out.println("list");
    }

    private static void deleteContact() {
        System.out.println("delete");
    }

    private static void addContact(String userInput) {
        System.out.println("add");
    }

    public static void contactsRunner (TextUi ui) {
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
