package seedu.allonus.storage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import seedu.allonus.contacts.ContactsManager;
import seedu.allonus.expense.ExpenseTracker;
import seedu.allonus.modules.StudyManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests the functionality of StorageFile class.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StorageFileTest {

    public static final String TEST_DATA_FILE = "allonusTestData.txt";

    StudyManager studyManager;
    ContactsManager contactsManager;
    ExpenseTracker expenseTracker;

    StorageFile storageFile;

    String module1 = "add m/CS2113 c/lec d/Friday t/4pm-6pm";
    String module2 = "add m/CS3244 c/tut d/Monday t/2pm-3pm";
    String module3 = "add m/EE4204 c/exam d/Monday t/10am-12pm";
    String[] modules = new String[]{module1, module2, module3};

    String expense1 = "add d/2022-03-15 a/9.50 c/Movie r/Spiderman: No Way Home";
    String expense2 = "add d/2022-02-17 a/2.40 c/Food r/Popcorn";
    String expense3 = "add d/2022-03-18 a/9.50 c/Movie r/Matrix";
    String[] expenses = new String[]{expense1, expense2, expense3};

    String contact1 = "add n/Jane Doe f/SoC e/e0334987@u.nus.edu d/group mate for CS4215";
    String contact2 = "add n/Ryan Briggs f/FoE e/e1122334@u.nus.edu d/friend";
    String contact3 = "add n/Benjamin f/SoC e/e0000127@u.nus.edu d/best friend";
    String[] contacts = new String[]{contact1, contact2, contact3};

    /**
     * Creates manager, tracker and StorageFile objects.
     */
    @BeforeAll
    public void setUp() {
        studyManager = new StudyManager();
        contactsManager = new ContactsManager();
        expenseTracker = new ExpenseTracker();
        StorageFile.setFields(contactsManager, expenseTracker, studyManager, TEST_DATA_FILE);
        storageFile = new StorageFile();
        expenseTracker.getLogger().setLevel(Level.SEVERE);
        studyManager.getLogger().setLevel(Level.WARNING);
        contactsManager.getLogger().setLevel(Level.SEVERE);
        storageFile.getLogger().setLevel(Level.WARNING);

        storageFile.createFile();
        storageFile.saveData();
    }

    /**
     * Tests module related methods of StorageFile including loadModule and getModuleInFileFormat.
     * Ensures that the contents of the module list is equal to the content loaded.
     */
    @BeforeEach
    public void testModuleRelatedMethods() {
        storageFile.loadModule(module1);
        storageFile.loadModule(module2);
        storageFile.loadModule(module3);

        for (int i = 0; i < studyManager.getModulesList().size(); i++) {
            assertEquals(storageFile.getModuleInFileFormat(i), "S," + modules[i]);
        }
    }

    /**
     * Tests expense related methods of StorageFile including loadExpense and getExpenseInFileFormat.
     * Ensures that the contents of the expense list is equal to the content loaded.
     */
    @BeforeEach
    public void testExpenseRelatedMethods() {
        storageFile.loadExpense(expense1);
        storageFile.loadExpense(expense2);
        storageFile.loadExpense(expense3);

        for (int i = 0; i < expenseTracker.getExpenseList().size(); i++) {
            assertEquals(storageFile.getExpenseInFileFormat(i), "E," + expenses[i]);
        }
    }

    /**
     * Tests contacts related methods of StorageFile including loadContact and getContactInFileFormat.
     * Ensures that the contents of the contacts list is equal to the content loaded.
     */
    @BeforeEach
    public void testContactRelatedMethods() {
        storageFile.loadContact(contact1);
        storageFile.loadContact(contact2);
        storageFile.loadContact(contact3);

        for (int i = 0; i < contactsManager.getContactsList().size(); i++) {
            assertEquals(storageFile.getContactInFileFormat(i), "C," + contacts[i]);
        }
    }

    /**
     * Tests saveData and loadData methods of StorageFile class
     * by adding data to managers and tracker, saving and ensuring the
     * same content is on the saved file, then loading data from the saved
     * file and comparing to loaded data in managers and tracker to ensure
     * data integrity.
     */
    @Test
    public void testSaveAndLoad() {
        // test save
        storageFile.saveData();
        try {
            File f = new File(storageFile.getDatafileRelativePath());
            Scanner fileReader = new Scanner(f);

            for (int i = 0; i < expenseTracker.getExpenseList().size(); i++) {
                if (fileReader.hasNext()) {
                    assertEquals("E," + expenses[i], fileReader.nextLine());
                } else {
                    assertEquals(1, 0);
                }
            }

            for (int i = 0; i < studyManager.getModulesList().size(); i++) {
                if (fileReader.hasNext()) {
                    assertEquals("S," + modules[i], fileReader.nextLine());
                } else {
                    assertEquals(1, 0);
                }
            }

            for (int i = 0; i < contactsManager.getContactsList().size(); i++) {
                if (fileReader.hasNext()) {
                    assertEquals("C," + contacts[i], fileReader.nextLine());
                } else {
                    assertEquals(1, 0);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // test load

        storageFile.loadData();

        // Instead of deleting the entries per manager/tracker (private delete methods),
        // load on top of them to test.
        for (int i = 3; i < studyManager.getModulesList().size(); i++) {
            assertEquals(storageFile.getModuleInFileFormat(i), "S," + modules[i - 3]);
        }
        for (int i = 3; i < expenseTracker.getExpenseList().size(); i++) {
            assertEquals(storageFile.getExpenseInFileFormat(i), "E," + expenses[i - 3]);
        }
        for (int i = 3; i < contactsManager.getContactsList().size(); i++) {
            assertEquals(storageFile.getContactInFileFormat(i), "C," + contacts[i - 3]);
        }
    }
}
