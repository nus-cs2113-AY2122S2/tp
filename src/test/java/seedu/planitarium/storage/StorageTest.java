//@@author HansHengGit

package seedu.planitarium.storage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.planitarium.global.Constants;
import seedu.planitarium.family.Family;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.planitarium.storage.Storage.logger;


class StorageTest {

    private static final String VALID_FILE_PATH = "Data/PlanITarium.txt";
    private static Storage storageSave;
    private static Family familyTest;

    @BeforeEach
    void setUp() {
        File file = new File(VALID_FILE_PATH);
        try {
            Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            String loggerString = "Error in deleting file";
            logger.log(Level.SEVERE, loggerString);
        }
    }

    @Test
    void saveData_ValidInput_expectedRecord() {
        storageSave = new Storage();
        familyTest = new Family();
        familyTest.addPerson(1, "Alice", Constants.FOR_STORAGE);
        familyTest.addExpend(1, 1, "Food", 24.00, 1, true, Constants.FOR_STORAGE);
        familyTest.addIncome(1, 1, "Salary", 3000.0, false, Constants.FOR_STORAGE);
        storageSave.saveData(familyTest);
        Storage storageLoad = new Storage();
        Family familyResult;
        familyResult = storageLoad.loadData();
        String resultName = familyResult.getList(1).getPerson(1).getName();
        assertEquals("Alice", resultName);
        int resultNumberOfExpenditures = familyResult.getList(1).getPerson(1).getNumberOfExpenditures();
        assertEquals(1, resultNumberOfExpenditures);
        int resultNumberOfIncomes = familyResult.getList(1).getPerson(1).getNumberOfIncomes();
        assertEquals(1, resultNumberOfIncomes);
    }

    @Test
    void loadData_validFileInput_expectedRecordLoaded() {
        familyTest = new Family();
        storageSave = new Storage();
        familyTest.addPerson(2, "Bob", Constants.FOR_STORAGE);
        familyTest.addIncome(2, 1, "Salary", 4000.0, true, Constants.FOR_STORAGE);
        familyTest.addPerson(3, "David", Constants.FOR_STORAGE);
        familyTest.addExpend(3, 1, "Transport", 50.00, 3, false, Constants.FOR_STORAGE);
        storageSave.saveData(familyTest);
        Storage loadStorage = new Storage();
        Family loadResult = loadStorage.loadData();
        String resultName = loadResult.getList(3).getPerson(1).getName();
        assertEquals("David", resultName);
        int resultNumberOfExpenditure = loadResult.getList(3).getPerson(1).getNumberOfExpenditures();
        assertEquals(1, resultNumberOfExpenditure);
    }

    @Test
    void loadData_invalidQuery_expectAssertionError() {
        familyTest = new Family();
        storageSave = new Storage();
        familyTest.addPerson(2, "", Constants.FOR_STORAGE);
        familyTest.addIncome(2, 1, "Salary", 4000.0, true, Constants.FOR_STORAGE);
        familyTest.addPerson(3, "David", Constants.FOR_STORAGE);
        familyTest.addExpend(3, 1, "Transport", 50.00, 3, false, Constants.FOR_STORAGE);
        storageSave.saveData(familyTest);
        Storage loadStorage = new Storage();
        Family loadResult = loadStorage.loadData();
        try {
            String resultName = loadResult.getList(2).getPerson(1).getName();
            fail();
        } catch (AssertionError e) {
            e.getMessage();
        }
    }

    @AfterAll
    static void wipeData() {
        familyTest = new Family();
        storageSave = new Storage();
        storageSave.saveData(familyTest);
    }

}
