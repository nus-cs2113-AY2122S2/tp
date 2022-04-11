package seedu.allonus.storage;

import seedu.allonus.contacts.ContactParser;
import seedu.allonus.contacts.ContactsManager;
import seedu.allonus.contacts.entry.Contact;
import seedu.allonus.expense.Expense;
import seedu.allonus.expense.ExpenseParser;
import seedu.allonus.expense.ExpenseTracker;
import seedu.allonus.modules.Module;
import seedu.allonus.modules.StudyManager;
import seedu.allonus.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class encapsulating methods to save application data from respective managers/trackers to
 * a file and load data from this file to update application context for respective managers/trackers.
 */
public class StorageFile {
    public static final String LOG_ATTEMPT_TO_CREATE_DIRECTORY = "Attempt to create directory.";
    public static final String LOG_UNABLE_TO_CREATE_DIRECTORY = "Unable to create directory.";
    public static final String LOG_DIRECTORY_NOT_FOUND = "Directory not found and will now attempt to create.";
    public static final String ASSERT_DIRECTORY_EXISTS = "Directory dir should exist.";
    public static final String LOG_UNABLE_TO_CREATE_NEW_FILE = "Unable to create new file to write to. "
            + "IOException occurred.";
    public static final String LOG_ADD_EXPENSE_ENTRY = "Expense entry found in saved file. "
            + "Adding it to list in expense manager.";
    public static final String LOG_ADD_MODULE_ENTRY = "Module entry found in saved file. "
            + "Adding it to list in study manager.";
    public static final String LOG_ADD_CONTACT_ENTRY = "Contact entry found in saved file. "
            + "Adding it to list in contacts manager.";
    public static final String LOG_CORRUPTED_ENTRY = "There seems to be a corrupted entry in the data file.";
    public static final String LOG_CONVERT_EXPENSE_TO_FILE_FORMAT = "Converting an expense in expense tracker "
            + "into file (save) format.";
    public static final String LOG_CONVERT_MODULE_TO_FILE_FORMAT = "Converting a module in study manager into "
            + "file (save) format.";
    public static final String ASSERT_CATEGORY_IS_ACCOUNTED_FOR = "Should not reach default since a module "
            + "has either 'Lecture', 'Exam' or 'Tutorial' as category";
    public static final String LOG_CONVERT_CONTACT_TO_FILE_FORMAT = "Converting a contact in contacts manager "
            + "into file (save) format.";
    public static final String LOG_SAVING_EXPENSE_ENTRIES = "Saving entries from Expense Tracker into file.";
    public static final String LOG_SAVING_MODULE_ENTRIES = "Saving entries from Study Manager into file.";
    public static final String LOG_SAVING_CONTACT_ENTRIES = "Saving entries from Contacts Manager into file.";
    public static final String LOG_FILE_NOT_FOUND = "File not found and will now attempt to create.";
    public static final String LOG_UNABLE_TO_WRITE_TO_FILE = "Unable to write data to file. IOException occurred.";
    public static final String ASSERT_FILE_EXISTS = "File f should exist.";
    public static final String FILE_ENTRY_DELIMITER = ",";
    public static final String EXPENSE_ENTRY_IDENTIFIER = "E";
    public static final String MODULE_ENTRY_IDENTIFIER = "S";
    public static final String CONTACT_ENTRY_IDENTIFIER = "C";
    public static final int NUMBER_OF_SEGMENTS_TO_SPLIT_INTO = 2;
    public static final int INDEX_OF_IDENTIFIER_SEGMENT = 0;
    public static final int INDEX_OF_DATA_SEGMENT = 1;
    public static final String EMPTY_STRING = "";
    public static final int INDEX_OF_FIRST_ELEMENT = 0;
    public static final String WHITESPACE = " ";
    public static final String FORWARD_SLASH = "/";
    public static final String MESSAGE_CORRUPTED_ENTRY = "Corrupted entry in file. It will be overwritten. Please go "
            + "through the managers and trackers to observe non-corrupted entries.";

    private static ContactsManager contactsManager;
    private static StudyManager studyManager;
    private static ExpenseTracker expenseTracker;
    private static Logger logger = Logger.getLogger("StorageFileLog");
    private static String fileName;
    private static String datafileRelativePath;

    public static final String DIRECTORY_NAME = "data";
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public StorageFile() {
    }

    /**
     * Returns relative path of the file used for save and load operations.
     * @return String containing relative path of file.
     */
    public String getDatafileRelativePath() {
        return datafileRelativePath;
    }

    /**
     * Sets <code>contactsManager</code>, <code>studyManager</code> and <code>expenseTracker</code>
     * fields of this class <code>StorageFile</code> with instances of the respective classes.
     * @param contactsManagerParam instance of class <code>ContactsManager</code>.
     * @param expenseTrackerParam instance of class <code>ExpenseTracker</code>.
     * @param studyManagerParam instance of class <code>StudyManager</code>.
     */
    public static void setFields(ContactsManager contactsManagerParam, ExpenseTracker expenseTrackerParam,
            StudyManager studyManagerParam, String fileNameParam) {
        contactsManager = contactsManagerParam;
        expenseTracker = expenseTrackerParam;
        studyManager = studyManagerParam;
        fileName = fileNameParam;
        datafileRelativePath = DIRECTORY_NAME + File.separator + fileName;
    }

    /**
     * Creates a directory with name <code>DIRECTORY_NAME</code> at path specified in
     * <code>directoryPath</code>.
     */
    public void createDirectory() {
        logger.log(Level.INFO, LOG_ATTEMPT_TO_CREATE_DIRECTORY);
        String directory = DIRECTORY_NAME;
        String directoryPath = PROJECT_PATH + File.separator + directory;
        File dir = new File(directoryPath);
        boolean isMade = dir.mkdir();
        if (!isMade) {
            logger.log(Level.WARNING, LOG_UNABLE_TO_CREATE_DIRECTORY);
        }
    }

    /**
     * Creates a file at path specified in <code>DATAFILE_RELATIVE_PATH</code>. The directory
     * in which the file resides is created first if it doesn't exist.
     *
     * @see IOException
     * @see File
     */
    public void createFile() {
        File dir = new File(DIRECTORY_NAME);
        while (!dir.exists()) {
            logger.log(Level.INFO, LOG_DIRECTORY_NOT_FOUND);
            createDirectory();
        }
        assert dir.exists() : ASSERT_DIRECTORY_EXISTS;
        File f = new File(PROJECT_PATH + File.separator + datafileRelativePath);

        try {
            f.createNewFile();
        } catch (IOException e) {
            logger.log(Level.SEVERE, LOG_UNABLE_TO_CREATE_NEW_FILE);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Calls <code>loadAdd</code> method of <code>expenseTracker</code> to update context of
     * <code>expenseTracker</code>.
     *
     * @param expense is a string containing the expense entry to be used for updating
     *                context of <code>expenseTracker</code>.
     */
    public void loadExpense(String expense) {
        expenseTracker.loadAdd(expense);
    }

    /**
     * Calls <code>loadAdd</code> method of <code>studyManager</code> to update context of
     * <code>studyManager</code>.
     *
     * @param module is a string containing the module entry to be used for updating
     *                context of <code>studyManager</code>.
     */
    public void loadModule(String module) {
        studyManager.loadAdd(module);
    }

    /**
     * Calls <code>loadAdd</code> method of <code>contactsManager</code> to update context of
     * <code>contactsManager</code>.
     *
     * @param contact is a string containing the contact entry to be used for updating
     *                context of <code>contactManager</code>.
     */
    public void loadContact(String contact) {
        contactsManager.loadAdd(contact);
    }

    /**
     * Identifies expense, module and contact entries in saved file and updates the context of
     * the respective trackers/managers.
     *
     * @throws FileNotFoundException when File f being read from does not exist.
     * @see FileNotFoundException
     */
    public void transferDataFromFileToList() throws FileNotFoundException {
        TextUi ui = new TextUi();
        File f = new File(datafileRelativePath);
        Scanner fileReader = new Scanner(f);
        while (fileReader.hasNext()) {
            String fileRow = fileReader.nextLine();
            String[] dataEntry = fileRow.split(FILE_ENTRY_DELIMITER, NUMBER_OF_SEGMENTS_TO_SPLIT_INTO);
            if (dataEntry[INDEX_OF_IDENTIFIER_SEGMENT].equals(EXPENSE_ENTRY_IDENTIFIER)) {
                logger.log(Level.INFO, LOG_ADD_EXPENSE_ENTRY);
                loadExpense(dataEntry[INDEX_OF_DATA_SEGMENT]);
            } else if (dataEntry[INDEX_OF_IDENTIFIER_SEGMENT].equals(MODULE_ENTRY_IDENTIFIER)) {
                logger.log(Level.INFO, LOG_ADD_MODULE_ENTRY);
                loadModule(dataEntry[INDEX_OF_DATA_SEGMENT]);
            } else if (dataEntry[INDEX_OF_IDENTIFIER_SEGMENT].equals(CONTACT_ENTRY_IDENTIFIER)) {
                logger.log(Level.INFO, LOG_ADD_CONTACT_ENTRY);
                loadContact(dataEntry[INDEX_OF_DATA_SEGMENT]);
            } else {
                logger.log(Level.WARNING, LOG_CORRUPTED_ENTRY);
                ui.showToUser(MESSAGE_CORRUPTED_ENTRY);
                continue;
            }
        }
    }

    /**
     * Returns string encoding of <code>Expense</code> object at specified <code>listIndex</code> in
     * <code>expenseList</code>.
     *
     * @param listIndex Integer depicting index of interest in <code>expenseList</code>.
     * @return string encoding of a <code>Expense</code> object at specified index in <code>expenseList</code>.
     */
    public String getExpenseInFileFormat(int listIndex) {
        logger.log(Level.INFO, LOG_CONVERT_EXPENSE_TO_FILE_FORMAT);
        Expense expense = (expenseTracker.getExpenseList()).get(listIndex);
        return EXPENSE_ENTRY_IDENTIFIER + FILE_ENTRY_DELIMITER + ExpenseTracker.KEYWORD_ADD
                + ExpenseParser.DATE_DELIMITER + expense.getDate()
                + ExpenseParser.AMOUNT_DELIMITER + expense.getAmount()
                + ExpenseParser.CATEGORY_DELIMITER + expense.getCategory()
                + ExpenseParser.REMARKS_DELIMITER + expense.getRemark();
    }

    /**
     * Returns string encoding of <code>Module</code> object at specified <code>listIndex</code> in
     * <code>moduleList</code>.
     *
     * @param listIndex Integer depicting index of interest in <code>moduleList</code>.
     * @return string encoding of a <code>Module</code> object at specified index in <code>moduleList</code>.
     */
    public String getModuleInFileFormat(int listIndex) {
        logger.log(Level.INFO, LOG_CONVERT_MODULE_TO_FILE_FORMAT);
        Module module = (studyManager.getModulesList()).get(listIndex);
        String category = EMPTY_STRING;
        switch (module.getCategory()) {
        case StudyManager.MODULE_CATEGORY_LEC:
            category = StudyManager.CATEGORY_LECTURE_SHORTHAND;
            break;
        case StudyManager.MODULE_CATEGORY_EXAM:
            category = StudyManager.CATEGORY_EXAM_SHORTHAND;
            break;
        case StudyManager.MODULE_CATEGORY_TUT:
            category = StudyManager.CATEGORY_TUTORIAL_SHORTHAND;
            break;
        case StudyManager.MODULE_CATEGORY_LAB:
            category = StudyManager.CATEGORY_LAB_SHORTHAND;
            break;
        default:
            assert false : ASSERT_CATEGORY_IS_ACCOUNTED_FOR;
        }
        return MODULE_ENTRY_IDENTIFIER + FILE_ENTRY_DELIMITER + StudyManager.ADD_COMMAND
                + WHITESPACE + StudyManager.MODULE_CODE_DELIMITER + module.getModuleCode()
                + WHITESPACE + StudyManager.MODULE_CATEGORY_DELIMITER + category
                + WHITESPACE + StudyManager.MODULE_DAY_DELIMITER + module.getDay()
                + WHITESPACE + StudyManager.MODULE_TIME_DELIMITER + module.getTimeSlot();
    }

    /**
     * Returns string encoding of <code>Contact</code> object at specified <code>listIndex</code> in
     * <code>contactsList</code>.
     *
     * @param listIndex Integer depicting index of interest in <code>contactsList</code>.
     * @return string encoding of a <code>Contact</code> object at specified index in <code>contactsList</code>.
     */
    public String getContactInFileFormat(int listIndex) {
        logger.log(Level.INFO, LOG_CONVERT_CONTACT_TO_FILE_FORMAT);
        Contact contact = (contactsManager.getContactsList()).get(listIndex);
        return CONTACT_ENTRY_IDENTIFIER + FILE_ENTRY_DELIMITER + ContactsManager.ADD_COMMAND_STRING
                + WHITESPACE + ContactParser.NAME_DELIMITER + FORWARD_SLASH + contact.getName()
                + WHITESPACE + ContactParser.FACULTY_DELIMITER + FORWARD_SLASH + contact.getFaculty()
                + WHITESPACE + ContactParser.EMAIL_DELIMITER + FORWARD_SLASH + contact.getEmail()
                + WHITESPACE + ContactParser.DESCRIPTION_DELIMITER + FORWARD_SLASH + contact.getDescription();
    }

    /**
     * Writes the content of current <code>expenseList</code> to file with path specified
     * by <code>DATAFILE_RELATIVE_PATH</code>.
     *
     * @throws IOException when there is some error writing to the file.
     * @see IOException
     */
    public void writeDataToFile() throws IOException {
        FileWriter fileWrite = new FileWriter(datafileRelativePath);

        logger.log(Level.INFO, LOG_SAVING_EXPENSE_ENTRIES);
        if (expenseTracker.getExpenseCount() == 0) {
            fileWrite.write(EMPTY_STRING);
            fileWrite.close();
        } else {
            fileWrite.write(getExpenseInFileFormat(INDEX_OF_FIRST_ELEMENT) + "\n");
            fileWrite.close();
        }
        fileWrite = new FileWriter(datafileRelativePath, true);
        for (int i = INDEX_OF_FIRST_ELEMENT + 1; i < expenseTracker.getExpenseCount(); i++) {
            fileWrite.write(getExpenseInFileFormat(i) + "\n");
        }

        logger.log(Level.INFO, LOG_SAVING_MODULE_ENTRIES);
        for (int i = INDEX_OF_FIRST_ELEMENT; i < studyManager.getModuleCount(); i++) {
            fileWrite.write(getModuleInFileFormat(i) + "\n");
        }

        logger.log(Level.INFO, LOG_SAVING_CONTACT_ENTRIES);
        for (int i = INDEX_OF_FIRST_ELEMENT; i < contactsManager.getContactsCount(); i++) {
            fileWrite.write(getContactInFileFormat(i) + "\n");
        }
        fileWrite.close();
    }

    /**
     * Loads data into <code>taskList</code> if data file specified by <code>DATAFILE_RELATIVE_PATH</code>
     * exists, else this file is created.
     *
     * @see FileNotFoundException
     */
    public void loadData() {
        logger.setLevel(Level.SEVERE);
        expenseTracker.getLogger().setLevel(Level.SEVERE);
        studyManager.getLogger().setLevel(Level.WARNING);
        contactsManager.getLogger().setLevel(Level.SEVERE);

        File f = new File(datafileRelativePath);
        try {
            transferDataFromFileToList();
        } catch (FileNotFoundException e) {
            while (!f.exists()) {
                logger.log(Level.INFO, LOG_FILE_NOT_FOUND);
                createFile();
            }
        }
    }

    /**
     * Saves app data into file specified by <code>DATAFILE_RELATIVE_PATH</code>
     * if it exists. If the file does not exist, it attempts to create it first before saving data on it.
     *
     * @see IOException
     */
    public void saveData() {
        logger.setLevel(Level.SEVERE);
        File f = new File(datafileRelativePath);
        while (!f.exists()) {
            logger.log(Level.INFO, LOG_FILE_NOT_FOUND);
            createFile();
        }
        try {
            assert f.exists() : ASSERT_FILE_EXISTS;
            writeDataToFile();
        } catch (IOException e) {
            logger.log(Level.SEVERE, LOG_UNABLE_TO_WRITE_TO_FILE);
            System.out.println(e.getMessage());
        }
    }

    public Logger getLogger() {
        return logger;
    }
}