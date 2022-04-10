package seedu.allonus.contacts;

import seedu.allonus.AllOnUs;
import seedu.allonus.contacts.entry.Contact;
import seedu.allonus.contacts.entry.Name;
import seedu.allonus.mode.Mode;
import seedu.allonus.storage.StorageFile;
import seedu.allonus.ui.TextUi;

import java.util.ArrayList;
import java.util.HashSet;
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

    public static final String CONTACTS_SET_NAME_CAPITALIZATION_MESSAGE =
            "*** Note: You didn't update this name / only updated casing! ***";
    private static final String CONTACTS_SET_DUPLICATE_NAME_MESSAGE =
            "A contact with the same name already exists!";

    private static final Logger logger = Logger.getLogger("");
    private static final int CONTACTS_LIST_MAX_SIZE = 100;
    private static final ArrayList<Contact> listOfContacts =
            new ArrayList<>(CONTACTS_LIST_MAX_SIZE);
    private static final HashSet<Integer> nameHashes =
            new HashSet<>(CONTACTS_LIST_MAX_SIZE);

    private static final String MENU_COMMAND_STRING = "menu";
    private static final String LIST_COMMAND_STRING = "list";
    private static final String DELETE_COMMAND_STRING = "rm";
    public static final String ADD_COMMAND_STRING = "add";
    private static final String FIND_COMMAND_STRING = "find";
    private static final String EDIT_COMMAND_STRING = "edit";
    private static final int LENGTH_COMMAND_ONLY = 1;
    private static final int LENGTH_ONE_KEYWORD = 2;
    private static final int INDEX_AFTER_COMMAND = 1;

    private static final StorageFile storageFile = new StorageFile();
    private static boolean isModified = false;

    //@@author OzairHasan
    /**
     * Prints a message to inform user they are already in the study manager.
     */
    private static void printAlreadyInContactsManagerMessage(TextUi ui) {
        ui.showToUser("You are already in Contacts Manager.");
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
     * Returns current contacts list.
     *
     * @return contacts list.
     */
    public ArrayList<Contact> getContactsList() {
        return listOfContacts;
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
     * Executes <code>addContact</code> method with saved contact entry from data file.
     *
     * @param savedContact the saved contact entry
     */
    public void loadAdd(String savedContact) {
        addContact(savedContact, false);
    }

    //@@author wli-linda
    private static void contactsWelcome() {
        showToUser(CONTACTS_WELCOME_MESSAGE);
        logger.log(Level.FINER, CONTACTS_ENTER_LOG_MESSAGE);
    }

    /**
     * Checks whether a duplicate of the new name already exists in the list.
     *
     * @param newName String of new name.
     * @param oldName String of old name.
     * @throws InvalidContactField If the new name is a duplicate.
     */
    public static void checkUniqueName(String newName, String oldName) throws InvalidContactField {
        String lowerOldName = oldName.toLowerCase();
        String lowerNewName = newName.toLowerCase();
        int oldNameHash = lowerOldName.hashCode();
        int newNameHash = lowerNewName.hashCode();

        boolean existsInList = nameHashes.contains(newNameHash);
        boolean isCaseChange = (oldNameHash == newNameHash);
        if (existsInList && !isCaseChange) {
            throw new InvalidContactField(CONTACTS_SET_DUPLICATE_NAME_MESSAGE);
        }
        if (isCaseChange) {
            System.out.println(CONTACTS_SET_NAME_CAPITALIZATION_MESSAGE);
        }

        nameHashes.remove(oldNameHash);
    }

    /**
     * Adds the hash code of a contact's name field to the set of names.
     *
     * @param updatedContact Contact to be added into the list of unique names.
     */
    public static void addHash(Contact updatedContact) {
        String contactName = updatedContact.getName().toString();
        int nameHash = contactName.toLowerCase().hashCode();
        nameHashes.add(nameHash);
    }

    /**
     * Removes the hash code of a contact's name from to the set of names.
     *
     * @param deletedContact Contact to be added into the list of unique names.
     */
    public static void deleteHash(Contact deletedContact) {
        String contactName = deletedContact.getName().toString();
        int nameHash = contactName.toLowerCase().hashCode();
        nameHashes.remove(nameHash);
    }

    /**
     * Lists all contacts.
     */
    private void listContacts() {
        if (listOfContacts.isEmpty()) {
            showToUser(CONTACTS_EMPTY_LIST_MESSAGE);
            return;
        }

        String listAsString = "";
        for (int i = 0; i < getContactsCount(); i++) {
            Contact curr = listOfContacts.get(i);
            String currEntry = String.format(CONTACTS_ENUMERATE_HEADER, i + 1, curr);
            listAsString = listAsString.concat(currEntry);
        }
        showToUser(CONTACTS_LIST_SUCCESS_MESSAGE + listAsString);
    }

    /**
     * Removes a contact from the list.
     *
     * @param userInput String of user input to parse.
     */
    private void deleteContact(String userInput) {
        Contact curr;
        try {
            int taskInd = ContactParser.parseNum(userInput);
            curr = listOfContacts.get(taskInd);
            assert taskInd < getContactsCount();
            listOfContacts.remove(taskInd);
            deleteHash(curr);
            assert nameHashes.size() == getContactsCount();
            assert taskInd >= 0;
            assert taskInd < CONTACTS_LIST_MAX_SIZE;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            showToUser(CONTACTS_REMOVE_INVALID_INDEX_MESSAGE);
            return;
        }
        showToUser(CONTACTS_REMOVE_SUCCESS_MESSAGE + curr
                + String.format(CONTACTS_UPDATED_LIST_SIZE_MESSAGE, getContactsCount()));
        isModified = true;
    }

    /**
     * Adds a contact to the list.
     *
     * @param userInput String of user input to parse.
     * @param fromCommandLine Boolean indicating whether the command is from the user in command line.
     */
    private void addContact(String userInput, boolean fromCommandLine) {
        Contact contact;
        try {
            contact = parseContact(userInput);
        } catch (InvalidContactField e) {
            showToUser(e.getMessage());
            return;
        }

        listOfContacts.add(contact);
        addHash(contact);
        assert nameHashes.size() == getContactsCount();
        if (fromCommandLine) {
            showToUser(CONTACTS_ADD_SUCCESS_MESSAGE + contact
                    + String.format(CONTACTS_UPDATED_LIST_SIZE_MESSAGE, getContactsCount()));
        }
        isModified = true;
    }

    /**
     * Prints contacts that contain a certain keyword.
     *
     * @param userInput String of user input to parse.
     */
    private void findContacts(String userInput) {
        String[] commands = userInput.split(" ");
        if (commands.length == LENGTH_COMMAND_ONLY) {
            showToUser(CONTACTS_FIND_EMPTY_KEYWORD_MESSAGE);
            return;
        }
        if (commands.length > LENGTH_ONE_KEYWORD) {
            showToUser(CONTACTS_FIND_MULTIPLE_KEYWORDS_MESSAGE);
            return;
        }
        assert commands.length == LENGTH_ONE_KEYWORD;
        String keyword = commands[INDEX_AFTER_COMMAND].toLowerCase();

        String listAsString = "";
        for (int i = 0; i < getContactsCount(); i++) {
            Contact curr = listOfContacts.get(i);
            Name currName = curr.getName();
            String contactName = currName.toString().toLowerCase();
            if (contactName.contains(keyword)) {
                String currEntry = String.format(CONTACTS_ENUMERATE_HEADER, i + 1, curr);
                listAsString = listAsString.concat(currEntry);
            }
        }
        if (!listAsString.equals("")) {
            showToUser(CONTACTS_FIND_SUCCESS_MESSAGE + listAsString);
        } else {
            showToUser(CONTACTS_FIND_NO_MATCHES_MESSAGE);
        }
    }

    /**
     * Edits an existing contact in the list.
     *
     * @param userInput String of user input to parse.
     */
    private void editContact(String userInput) {
        Contact curr;
        try {
            int taskInd = ContactParser.parseNum(userInput);
            curr = listOfContacts.get(taskInd);
            assert taskInd >= 0;
            assert taskInd <= getContactsCount();
            assert taskInd < CONTACTS_LIST_MAX_SIZE;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            showToUser(CONTACTS_EDIT_INVALID_INDEX_MESSAGE);
            return;
        }

        try {
            ArrayList<String> fieldStrings = getFieldStrings(userInput);
            if (fieldStrings.isEmpty()) {
                showToUser(CONTACTS_EDIT_NO_FIELDS_MESSAGE);
                return;
            }
            setContactFields(curr, fieldStrings);
            addHash(curr);
            assert nameHashes.size() == getContactsCount();
        } catch (InvalidContactField e) {
            showToUser(e.getMessage());
            return;
        }
        showToUser(CONTACTS_EDIT_SUCCESS_MESSAGE + curr);
        isModified = true;
    }

    /**
     * Handles user inputs and calls methods corresponding
     * to the relevant commands.
     *
     * @param ui An TextUi object for getting user input.
     * @return mode value pertaining to either menu, expense tracker or study manager.
     */
    public Mode contactsRunner(TextUi ui) {
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
                showToUser(CONTACTS_INVALID_COMMAND_MESSAGE);
                logger.log(Level.FINER, String.format(CONTACTS_INVALID_COMMAND_LOG_MESSAGE, userInput));
            }
            if (isModified) {
                storageFile.saveData();
            }
        }
    }


}
