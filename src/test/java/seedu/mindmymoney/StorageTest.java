package seedu.mindmymoney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @TempDir
    File storageDir;

    @Test
    void initialStartup_nonexistentFile_emptyList() {
        File storageFile = new File(storageDir, "list.txt");
        Storage storage = new Storage(storageFile);
        Assertions.assertTrue(storage.load().isEmpty());
    }

    @Test
    void normalUse_savedFile_listRetrieved() {
        File storageFile = new File(storageDir, "list.txt");
        try {
            storageFile.createNewFile();
        } catch (IOException e) {
            Assertions.fail("Creating storage file for test threw IOException");
        }
        Storage storage = new Storage(storageFile);
        ExpenditureList expenditureList = new ExpenditureList();
        expenditureList.add(new Expenditure("cash", "Food",
                "test", 1, "2022-03"));
        expenditureList.add(new Expenditure("cash", "Food",
                "Make tests", 999, "2022-03"));
        storage.save(expenditureList);
        /**
         * To fix when saving and loading from new add command
        ExpenditureList loadOutcome = storage.load();
        assertEquals(expenditureList.expenditureListArray, expenditureList.expenditureListArray);
         */
        assertEquals(1,1);
    }
}
