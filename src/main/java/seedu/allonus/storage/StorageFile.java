package seedu.allonus.storage;

import seedu.allonus.contacts.ContactsManager;
import seedu.allonus.contacts.entry.Contact;
import seedu.allonus.expense.Expense;
import seedu.allonus.expense.ExpenseTracker;
import seedu.allonus.modules.Module;
import seedu.allonus.modules.StudyManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class StorageFile {
    private static ContactsManager contactsManager;
    private static StudyManager studyManager;
    private static ExpenseTracker expenseTracker;

    public static final String DIRECTORY_NAME = "data";
    public static final String DATAFILE_RELATIVE_PATH = DIRECTORY_NAME + File.separator + "allonusData.txt";
    public static final String PROJECT_PATH = System.getProperty("user.dir");

    public StorageFile() {
    }

    public static void setFields(ContactsManager contactsManagerParam, ExpenseTracker expenseTrackerParam,
                                 StudyManager studyManagerParam) {
        contactsManager = contactsManagerParam;
        expenseTracker = expenseTrackerParam;
        studyManager = studyManagerParam;
    }

    public void createDirectory() {
        String directory = DIRECTORY_NAME;
        String directoryPath = PROJECT_PATH + File.separator + directory;
        File dir = new File(directoryPath);
        boolean isMade = dir.mkdir();
        if (!isMade) {
            System.out.println("Error creating the new directory.");
        }
    }

    /**
     * This method creates a file at path specified in <code>Duke.DATAFILE_RELATIVE_PATH</code>. A directory is
     * created first if it doesn't exist.
     *
     * @see IOException
     * @see File
     */
    public void createFile() {
        File dir = new File(DIRECTORY_NAME);
        // attempt to create directory if it does not exist.
        while (!dir.exists()) {
            createDirectory();
        }
        File f = new File(PROJECT_PATH + File.separator + DATAFILE_RELATIVE_PATH);

        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating the new file.");
        }
    }

    public void loadExpense(String expense) {
        expenseTracker.loadAdd(expense);
    }

    public void loadModule(String module) {
        studyManager.addModule(module);
    }

    public void loadContact(String contact) {
        contactsManager.loadAdd(contact);
    }

    public void transferDataFromFileToList() throws FileNotFoundException {
        File f = new File(DATAFILE_RELATIVE_PATH);
        Scanner fileReader = new Scanner(f);
        while (fileReader.hasNext()) {
            String fileRow = fileReader.nextLine();
            String[] dataEntry = fileRow.split(",", 2);
            if (dataEntry[0].equals("E")) {
                loadExpense(dataEntry[1]);
            } else if (dataEntry[0].equals("S")) {
                System.out.println(dataEntry[1]);
                loadModule(dataEntry[1]);
            } else if (dataEntry[0].equals("C")) {
                loadContact(dataEntry[1]);
            } else {
                System.out.println("There seems to be a corrupted entry in the data file.");
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
        Expense expense = (expenseTracker.getExpenseList()).get(listIndex);
        return "E,add d/" + expense.getDate() + " a/" + expense.getAmount() + " c/"
                + expense.getCategory() + " r/" + expense.getRemark();
    }

    /**
     * Returns string encoding of <code>Module</code> object at specified <code>listIndex</code> in
     * <code>moduleList</code>.
     *
     * @param listIndex Integer depicting index of interest in <code>moduleList</code>.
     * @return string encoding of a <code>Module</code> object at specified index in <code>moduleList</code>.
     */
    public String getModuleInFileFormat(int listIndex) {
        Module module = (studyManager.getModulesList()).get(listIndex);
        String category = "";
        switch (module.getCategory()) {
        case "Lecture":
            category = "lec";
            break;
        case "Exam":
            category = "exam";
            break;
        case "Tutorial":
            category = "tut";
            break;
        default:
            System.out.println("Should not reach here.");
        }
        return "S,add m/" + module.getModuleCode() + " c/" + category + " d/"
                + module.getDay() + " t/" + module.getTimeSlot();
    }

    /**
     * Returns string encoding of <code>Contact</code> object at specified <code>listIndex</code> in
     * <code>contactsList</code>.
     *
     * @param listIndex Integer depicting index of interest in <code>contactsList</code>.
     * @return string encoding of a <code>Contact</code> object at specified index in <code>contactsList</code>.
     */
    public String getContactInFileFormat(int listIndex) {
        Contact contact = (contactsManager.getContactsList()).get(listIndex);
        return "C,add n/" + contact.getName() + " f/" + contact.getFaculty() + " e/"
                + contact.getEmail() + " d/" + contact.getDescription();
    }

    /**
     * Writes the content of current <code>expenseList</code> to file with path specified
     * by <code>DATAFILE_RELATIVE_PATH</code>.
     *
     * @throws IOException when there is some error writing to the file.
     * @see IOException
     */
    public void writeDataToFile() throws IOException {
        // save expenses to data file
        FileWriter fileWrite = new FileWriter(DATAFILE_RELATIVE_PATH);
        if (expenseTracker.getExpenseCount() == 0) {
            fileWrite.write("");
            fileWrite.close();
        } else {
            fileWrite.write(getExpenseInFileFormat(0) + "\n");
            fileWrite.close();
        }
        fileWrite = new FileWriter(DATAFILE_RELATIVE_PATH, true);
        for (int i = 1; i < expenseTracker.getExpenseCount(); i++) {
            fileWrite.write(getExpenseInFileFormat(i) + "\n");
        }

        // save modules to data file
        for (int i = 0; i < studyManager.getModuleCount(); i++) {
            fileWrite.write(getModuleInFileFormat(i) + "\n");
        }

        // save contacts to data file
        for (int i = 0; i < contactsManager.getContactsCount(); i++) {
            fileWrite.write(getContactInFileFormat(i) + "\n");
        }
        fileWrite.close();
    }

    /**
     * This method loads data into <code>taskList</code>if data file exists, else create file.
     */
    public void loadData() {
        File f = new File(DATAFILE_RELATIVE_PATH);
        try {
            transferDataFromFileToList();
        } catch (FileNotFoundException e) {
            // attempt to create file till it is created.
            while (!f.exists()) {
                createFile();
            }
        }
    }

    public void saveData() {
        File f = new File(DATAFILE_RELATIVE_PATH);
        // attempt to create file if it does not exist.
        while (!f.exists()) {
            createFile();
        }
        try {
            writeDataToFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}