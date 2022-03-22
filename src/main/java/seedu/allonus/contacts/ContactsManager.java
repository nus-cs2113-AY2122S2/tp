package seedu.allonus.contacts;

import seedu.allonus.ui.TextUi;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.allonus.contacts.ContactParser.parseContact;

public class ContactsManager {

    private static final String CONTACTS_WELCOME_MESSAGE =
            "Welcome to Contacts Manager";
    private static final String CONTACTS_INVALID_COMMAND_MESSAGE =
            "Please enter a valid command for the Contacts Manager!\n"
                    + "You can try \"list\", \"add\", or \"rm\"";
    private static final String CONTACTS_UPDATED_LIST_SIZE_MESSAGE =
            "\nNow you have %d contacts in the list.";

    private static final String CONTACTS_EMPTY_LIST_MESSAGE =
            "You haven't added any contacts to your list yet!";
    private static final String CONTACTS_LIST_SUCCESS_MESSAGE =
            "Here are the contacts in your list:\n";

    private static final String CONTACTS_REMOVE_SUCCESS_MESSAGE =
            "Noted. I've removed this contact:\n  ";
    private static final String CONTACTS_REMOVE_INVALID_INDEX_MESSAGE =
            "You can only delete with a valid number that's in the list :')";

    private static final String CONTACTS_ADD_SUCCESS_MESSAGE =
            "Got it. I've added this contact:\n  ";

    private static Logger logger = Logger.getLogger("");
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
        logger.log(Level.FINER, "Entering Contacts Manager");
    }

    /**
     * Returns index of item in the list using number given by user.
     *
     * @param userInput String of original user input.
     * @return ind Index of item.
     */
    private static int parseNum(String userInput) {
        String stringOfNum = userInput.split(" ", 0)[1];
        return Integer.parseInt(stringOfNum) - 1;
    }

    private static void listContacts() {
        if (contactsList.size() == 0) {
            printFormat(CONTACTS_EMPTY_LIST_MESSAGE);
            return;
        }

        String listAsString = "";
        for (int i = 0; i < contactsList.size(); i++) {
            Contact curr = contactsList.get(i);
            listAsString = listAsString.concat(String.format(" %d. %s\n", i + 1, curr));
        }
        printFormat(CONTACTS_LIST_SUCCESS_MESSAGE + listAsString);
    }

    private static void deleteContact(String userInput) {
        Contact curr;
        try {
            int taskInd = parseNum(userInput);
            curr = contactsList.get(taskInd);
            assert taskInd < contactsList.size();
            contactsList.remove(taskInd);
            assert taskInd >= 0;
            assert taskInd < CONTACTS_LIST_MAX_SIZE;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printFormat(CONTACTS_REMOVE_INVALID_INDEX_MESSAGE);
            return;
        }
        printFormat(CONTACTS_REMOVE_SUCCESS_MESSAGE + curr
                + String.format(CONTACTS_UPDATED_LIST_SIZE_MESSAGE, contactsList.size()));
    }

    private static void addContact(String userInput) {
        Contact contact;
        try {
            contact = parseContact(userInput);
        } catch (InvalidContactField e) {
            printFormat(e.getMessage());
            return;
        }
        contactsList.add(contact);
        printFormat(CONTACTS_ADD_SUCCESS_MESSAGE + contact
                + String.format(CONTACTS_UPDATED_LIST_SIZE_MESSAGE, contactsList.size()));
    }

    /**
     * Prints contacts that contain a certain keyword.
     *
     * @param userInput String of user input to parse.
     */
    private static void findContacts(String userInput) {
        String[] commands = userInput.split(" ");
        if (commands.length > 2) {
            printFormat("Please only enter one keyword!");
            return;
        }
        String keyword = commands[1];

        String listAsString = "";
        for (int i = 0; i < contactsList.size(); i++) {
            Contact curr = contactsList.get(i);
            String contactName = curr.getName();
            if (contactName.contains(keyword)) {
                listAsString = listAsString.concat(String.format(" %d. %s\n", i + 1, curr));
            }
        }
        if (!listAsString.equals("")) {
            printFormat("Here are the matching contacts in your list:\n" + listAsString);
        } else {
            printFormat("There are no contacts matching this keyword!");
        }
    }

    public static void contactsRunner(TextUi ui) {
        contactsWelcome();
        String userInput;
        while (true) {
            userInput = ui.getUserInput();
            if (userInput.equals("menu")) {
                logger.log(Level.FINER, "Exiting Contacts Manager");
                return;
            } else if (userInput.equals("list")) {
                listContacts();
            } else if (userInput.startsWith("rm")) {
                deleteContact(userInput);
            } else if (userInput.startsWith("add")) {
                addContact(userInput);
            } else if (userInput.startsWith("find")) {
                findContacts(userInput);
            } else {
                printFormat(CONTACTS_INVALID_COMMAND_MESSAGE);
                logger.log(Level.FINER, String.format("Invalid command to Contacts Manager: %s", userInput));
            }
        }
    }


}
