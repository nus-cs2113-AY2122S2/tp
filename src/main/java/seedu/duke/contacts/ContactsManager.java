package seedu.duke.contacts;


import seedu.duke.ui.TextUi;

import java.util.ArrayList;

public class ContactsManager {

    private static final String CONTACTS_WELCOME_MESSAGE = "Welcome to Contacts Manager";
    private static final int CONTACTS_LIST_MAX_SIZE = 100;
    private static final ArrayList<Contact> contactsList = new ArrayList<>(CONTACTS_LIST_MAX_SIZE);

    /**
     * Prints a message following a defined format.
     *
     * @param message Message to print.
     */
    public static void printFormat(String message) {
        System.out.println(message);
    }

    private static void contactsWelcome() {
        printFormat(CONTACTS_WELCOME_MESSAGE);
    }

    /**
     * Returns index of item in the list using number given by user.
     *
     * @param userInput String of original user input.
     * @return ind Index of item.
     */
    public static int parseNum(String userInput) {
        String stringOfNum = userInput.split(" ", 0)[1];
        int ind = Integer.parseInt(stringOfNum) - 1;
        return ind;
    }

    private static void listContacts() {
        if (contactsList.size() == 0) {
            printFormat("You haven't added any contacts to your list yet!");
            return;
        }

        String listAsString = "";
        for (int i = 0; i < contactsList.size(); i++) {
            Contact curr = contactsList.get(i);
            listAsString = listAsString.concat(String.format(" %d. %s\n", i + 1, curr));
        }
        printFormat("Here are the contacts in your list:\n" + listAsString);
    }

    private static void deleteContact(String userInput) {
        Contact curr;
        int taskInd = parseNum(userInput);
        curr = contactsList.get(taskInd);
        contactsList.remove(taskInd);
        printFormat("Noted. I've removed this contact:\n  "
                + curr
                + String.format("\nNow you have %d contacts in the list.", contactsList.size()));
    }

    private static void addContact(String userInput) {
        String[] commands = userInput.split(" ", 2);
        String name = "";
        if (commands.length > 1) {
            name = commands[1];
        }

        Contact contact = new Contact(name);
        contactsList.add(contact);
        printFormat("Got it. I've added this contact:\n  "
                + contact
                + String.format("\nNow you have %d contacts in the list.", contactsList.size()));
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
                deleteContact(userInput);
            } else {
                addContact(userInput);
            }
        }
    }


}
