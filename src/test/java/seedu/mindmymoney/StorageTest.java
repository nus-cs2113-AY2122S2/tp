package seedu.mindmymoney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.mindmymoney.data.CreditCardList;
import seedu.mindmymoney.data.ExpenditureList;
import seedu.mindmymoney.data.IncomeList;
import seedu.mindmymoney.userfinancial.Expenditure;
import seedu.mindmymoney.userfinancial.User;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @TempDir
    File storageDir;

    @Test
    void initialStartup_nonexistentFile_emptyList() {
        File storageFile = new File(storageDir, "list.txt");
        try {
            Storage storage = new Storage(storageFile);
        } catch (MindMyMoneyException e) {
            fail();
        }
    }

    @Test
    void normalUse_savedFile_listRetrieved() {
        File storageFile = new File(storageDir, "list.txt");
        try {
            storageFile.createNewFile();
        } catch (IOException e) {
            Assertions.fail("Creating storage file for test threw IOException");
        }
        User savedUser = new User();
        savedUser.setCreditCardListArray(new CreditCardList());
        savedUser.setIncomeListArray(new IncomeList());

        ExpenditureList expenditureList = new ExpenditureList();
        expenditureList.add(new Expenditure("cash", "Food",
                "test", 1, "Mar 2022"));
        expenditureList.add(new Expenditure("cash", "Food",
                "Make tests", 999, "Mar 2022"));

        savedUser.setExpenditureListArray(expenditureList);

        try {
            Storage storage = new Storage(storageFile);
            storage.save(savedUser);
            User loadedUser = storage.load();
            assertEquals(expenditureList.expenditureListArray,
                    loadedUser.getExpenditureListArray().expenditureListArray);
        } catch (MindMyMoneyException e) {
            fail();
        }
    }
}
