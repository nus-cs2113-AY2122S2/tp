package seedu.mindmymoney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.userfinancial.Expenditure;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        expenditureList.add(new Expenditure("test", null, 1));
        expenditureList.add(new Expenditure("Make tests", null, 999));
        storage.save(expenditureList);
        ExpenditureList loadOutcome = storage.load();
        assertEquals(expenditureList.expenditureListArray, loadOutcome.expenditureListArray);
    }
}
