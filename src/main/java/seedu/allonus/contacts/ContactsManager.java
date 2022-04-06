package seedu.allonus.contacts;

import seedu.allonus.AllOnUs;
import seedu.allonus.contacts.entry.Contact;
import seedu.allonus.contacts.entry.Name;
import seedu.allonus.mode.Mode;
import seedu.allonus.storage.StorageFile;
import seedu.allonus.ui.TextUi;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.allonus.contacts.ContactParser.getFieldStrings;
import static seedu.allonus.contacts.ContactParser.parseContact;
import static seedu.allonus.contacts.ContactParser.setContactFields;
import static seedu.allonus.ui.TextUi.showToUser;

/**
 * The Contacts Manager and associated methods.
 */
public class ContactsManager {

    private static final String CONTACTS_ENTER_LOG_MESSAGE =
            "Entering Contacts Manager";
    private static final String CONTACTS_WELCOME_MESSAGE =
            "Welcome to Contacts Manager";
    private static final String CONTACTS_EXIT_LOG_MESSAGE =
            "Exiting Contacts Manager";

    private static final String CONTACTS_INVALID_COMMAND_LOG_MESSAGE =
            "Invalid command to Contacts Manager: %s";
    private static final String CONTACTS_INVALID_COMMAND_MESSAGE =
            "Please enter a valid command for the Contacts Manager!\n"
            + "You can try \"list\", \"add\", or \"rm\"";

    private static final String CONTACTS_ENUMERATE_HEADER = " %d. %s\n";
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
    private static final String CONTACTS_UPDATED_LIST_SIZE_MESSAGE =
            "\nNow you have %d contacts in the list.";

    private static final String CONTACTS_FIND_EMPTY_KEYWORD_MESSAGE =
            "You need to specify the keyword you want to find!";
    private static final String CONTACTS_FIND_MULTIPLE_KEYWORDS_MESSAGE =
            "Please only enter one keyword!";
    private static final String CONTACTS_FIND_NO_MATCHES_MESSAGE =
            "There are no contacts matching this keyword!";
    private static final String CONTACTS_FIND_SUCCESS_MESSAGE =
            "Here are the matching contacts in your list:\n";

    private static final String CONTACTS_EDIT_INVALID_INDEX_MESSAGE =
            "You can only edit with a valid number that's in the list :')";
    private static final String CONTACTS_EDIT_NO_FIELDS_MESSAGE =
            "You need to specify the contact field(s) you want to edit!";
    private static final String CONTACTS_EDIT_SUCCESS_MESSAGE =
            "Okay, I've updated the information of this contact:\n  ";

    private static final Logger logger = Logger.getLogger("");
    private static final int CONTACTS_LIST_MAX_SIZE = 100;
    private static final ArrayList<Contact> listOfContacts =
            new ArrayList<>(CONTACTS_LIST_MAX_SIZE);

    private static final String MENU_COMMAND_STRING = "menu";
    private static final String LIST_COMMAND_STRING = "list";
    private static final String DELETE_COMMAND_STRING = "rm";
    public static final String ADD_COMMAND_STRING = "add";
    private static final String FIND_COMMAND_STRING = "find";
    private static final String EDIT_COMMAND_STRING = "edit";
    private static final int LENGTH_COMMAND_ONLY = 1;
    private static final int LENGTH_ONE_KEYWORD = 2;
    private static final int INDEX_AFTER_COMMAND = 1;

    private static StorageFile storageFile = new StorageFile();
    private static boolean isModified = false;

    /**
     * Prints a message following a defined format.
     *
     * @param message Message to print.
     */
    public static void printFormat(String message) {
        showToUser(message);
    }

    private static void contactsWelcome() {
        printFormat(CONTACTS_WELCOME_MESSAGE);
        logger.log(Level.FINER, CONTACTS_ENTER_LOG_MESSAGE);
    }

    private static void listContacts() {
        if (listOfContacts.isEmpty()) {
            printFormat(CONTACTS_EMPTY_LIST_MESSAGE);
            return;
        }

        String listAsString = "";
        for (int i = 0; i < listOfContacts.size(); i++) {
            Contact curr = listOfContacts.get(i);
            String currEntry = String.format(CONTACTS_ENUMERATE_HEADER, i + 1, curr);
            listAsString = listAsString.concat(currEntry);
        }
        printFormat(CONTACTS_LIST_SUCCESS_MESSAGE + listAsString);
    }

    /**
     * Returns current number of items in contacts list.
     *
     * @return number of items in contacts list.
     */
    public int getContactsCount() {
        return listOfContacts.size();
    }

    /**
     * Returns logger attribute of this class ContactsManager.
     *
     * @return logger, an instance of class <code>Logger</code>, belonging to this <code>ContactsManager</code> class.
     */
    public Logger getLogger() {
        return logger;
    }


    /**
     * Returns current contacts list.
     *
     * @return contacts list.
     */
    public ArrayList<Contact> getContactsList() {
        return listOfContacts;
    }

    private static void deleteContact(String userInput) {
        Contact curr;
        try {
            int taskInd = ContactParser.parseNum(userInput);
            curr = listOfContacts.get(taskInd);
            assert taskInd < listOfContacts.size();
            listOfContacts.remove(taskInd);
            assert taskInd >= 0;
            assert taskInd < CONTACTS_LIST_MAX_SIZE;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printFormat(CONTACTS_REMOVE_INVALID_INDEX_MESSAGE);
            return;
        }
        printFormat(CONTACTS_REMOVE_SUCCESS_MESSAGE + curr
                + String.format(CONTACTS_UPDATED_LIST_SIZE_MESSAGE, listOfContacts.size()));
        isModified = true;
    }

    private static void addContact(String userInput, boolean fromCommandLine) {
        Contact contact;
        try {
            contact = parseContact(userInput);
        } catch (InvalidContactField e) {
            printFormat(e.getMessage());
            return;
        }

        listOfContacts.add(contact);
        if (fromCommandLine) {
            printFormat(CONTACTS_ADD_SUCCESS_MESSAGE + contact
                    + String.format(CONTACTS_UPDATED_LIST_SIZE_MESSAGE, listOfContacts.size()));
        }
        isModified = true;
    }

    /**
     * Executes <code>addContact</code> method with saved contact entry from data file.
     *
     * @param savedContact the saved contact entry
     */
    public void loadAdd(String savedContact) {
        addContact(savedContact, false);
    }

    /**
     * Prints contacts that contain a certain keyword.
     *
     * @param userInput String of user input to parse.
     */
    private static void findContacts(String userInput) {
        String[] commands = userInput.split(" ");
        if (commands.length == LENGTH_COMMAND_ONLY) {
            printFormat(CONTACTS_FIND_EMPTY_KEYWORD_MESSAGE);
            return;
        }
        if (commands.length > LENGTH_ONE_KEYWORD) {
            printFormat(CONTACTS_FIND_MULTIPLE_KEYWORDS_MESSAGE);
            return;
        }
        assert commands.length == LENGTH_ONE_KEYWORD;
        String keyword = commands[INDEX_AFTER_COMMAND];

        String listAsString = "";
        for (int i = 0; i < listOfContacts.size(); i++) {
            Contact curr = listOfContacts.get(i);
            Name currName = curr.getName();
            String contactName = currName.toString();
            if (contactName.contains(keyword)) {
                String currEntry = String.format(CONTACTS_ENUMERATE_HEADER, i + 1, curr);
                listAsString = listAsString.concat(currEntry);
            }
        }
        if (!listAsString.equals("")) {
            printFormat(CONTACTS_FIND_SUCCESS_MESSAGE + listAsString);
        } else {
            printFormat(CONTACTS_FIND_NO_MATCHES_MESSAGE);
        }
    }

    private static void editContact(String userInput) {
        Contact curr;
        try {
            int taskInd = ContactParser.parseNum(userInput);
            curr = listOfContacts.get(taskInd);
            assert taskInd >= 0;
            assert taskInd <= listOfContacts.size();
            assert taskInd < CONTACTS_LIST_MAX_SIZE;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            printFormat(CONTACTS_EDIT_INVALID_INDEX_MESSAGE);
            return;
        }

        ArrayList<String> fieldStrings = getFieldStrings(userInput);
        if (fieldStrings.isEmpty()) {
            printFormat(CONTACTS_EDIT_NO_FIELDS_MESSAGE);
            return;
        }
        try {
            setContactFields(curr, fieldStrings);
        } catch (InvalidContactField e) {
            printFormat(e.getMessage());
            return;
        }
        printFormat(CONTACTS_EDIT_SUCCESS_MESSAGE + curr);
        isModified = true;
    }

    /**
     * Prints a message to inform user they are already in the study manager.
     */
    private static void printAlreadyInContactsManagerMessage(TextUi ui) {
        ui.showToUser("You are already in Contacts Manager.");
    }

    /**
     * Handles user inputs and calls methods corresponding
     * to the relevant commands.
     *
     * @param ui An TextUi object for getting user input.
     * @return mode value pertaining to either menu, expense tracker or study manager.
     */
    public static Mode contactsRunner(TextUi ui) {
        contactsWelcome();
        String userInput;
        while (true) {
            isModified = false;
            userInput = ui.getUserInput();
            if (userInput.equals(MENU_COMMAND_STRING)) {
                logger.log(Level.FINER, CONTACTS_EXIT_LOG_MESSAGE);
                return Mode.MENU;
            } else if (AllOnUs.isExpenseTrackerCommand(userInput)) {
                return Mode.EXPENSE_TRACKER;
            } else if (AllOnUs.isStudyManagerCommand(userInput)) {
                return Mode.STUDY_MANAGER;
            } else if (AllOnUs.isContactsManagerCommand(userInput)) {
                printAlreadyInContactsManagerMessage(ui);
            } else if (userInput.equals(LIST_COMMAND_STRING)) {
                listContacts();
            } else if (userInput.startsWith(DELETE_COMMAND_STRING)) {
                deleteContact(userInput);
            } else if (userInput.startsWith(ADD_COMMAND_STRING)) {
                addContact(userInput, true);
            } else if (userInput.startsWith(FIND_COMMAND_STRING)) {
                findContacts(userInput);
            } else if (userInput.startsWith(EDIT_COMMAND_STRING)) {
                editContact(userInput);
            } else {
                printFormat(CONTACTS_INVALID_COMMAND_MESSAGE);
                logger.log(Level.FINER, String.format(CONTACTS_INVALID_COMMAND_LOG_MESSAGE, userInput));
            }
            if (isModified) {
                storageFile.saveData();
            }
        }
    }


}
