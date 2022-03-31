package commands;

import manager.ExpenseManager;
import manager.LimitManager;
import manager.RecordManager;

import manager.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import records.Product;
import records.Subscription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddCommandTest {
    @Test
    void addProductCommand_correctInputs_success() throws Exception {
        AddCommand addCommandTest = new AddCommand();
        addCommandTest.AddProductCommand("Hotdog", 5.50, "24/12/2020", "Food");
        assertEquals("Hotdog", addCommandTest.getToAdd().getName());
        assertEquals(5.5, addCommandTest.getToAdd().getPrice());
        assertEquals("24/12/2020", addCommandTest.getToAdd().getDate());
        assertEquals("Food", ((Product)addCommandTest.getToAdd()).getProductType());
    }

    @Test
    void addSubscriptionCommand_correctInputs_success() throws Exception {
        AddCommand addCommandTest = new AddCommand();
        addCommandTest.AddSubscriptionCommand("Music player", 5.50, "24/12/2020", "Monthly");
        assertEquals("Music player", addCommandTest.getToAdd().getName());
        assertEquals(5.5, addCommandTest.getToAdd().getPrice());
        assertEquals("24/12/2020", addCommandTest.getToAdd().getDate());
        assertEquals("Monthly", ((Subscription)addCommandTest.getToAdd()).getRenewal());
    }

    @Test
    void execute_productTypeRecord_success() throws Exception {
        RecordManager recordManager = new RecordManager(new Storage("data/records.txt","data/totalExpense.txt","data/limit.txt"));
        LimitManager limitManager = LimitManager.getLimitManagerInstance();

        AddCommand addCommandTest = new AddCommand();
        addCommandTest.AddProductCommand("Hotdog", 5.50, "24/12/2020", "Food");

        addCommandTest.setData(recordManager);
        addCommandTest.setLimitManager(limitManager);

        addCommandTest.execute();

        assertTrue(recordManager.getRecordByIndex(0) instanceof Product);
        assertEquals(5.5, ExpenseManager.getTotalExpense());
    }

    @Test
    void execute_subscriptionTypeRecord_success() throws Exception {
        RecordManager recordManager = new RecordManager(new Storage("data/records.txt","data/totalExpense.txt","data/limit.txt"));
        LimitManager limitManager = LimitManager.getLimitManagerInstance();

        AddCommand addCommandTest = new AddCommand();
        addCommandTest.AddSubscriptionCommand("Music player", 5.50, "24/12/2020", "Monthly");

        addCommandTest.setData(recordManager);
        addCommandTest.setLimitManager(limitManager);

        addCommandTest.execute();

        assertTrue(recordManager.getRecordByIndex(0) instanceof Subscription);
        assertEquals(5.5, ExpenseManager.getTotalExpense());
    }

    @Test
    void execute_newRecordExceedsLimit_warningMessageReturned() throws Exception {
        RecordManager recordManager = new RecordManager(new Storage("data/records.txt","data/totalExpense.txt","data/limit.txt"));
        LimitManager limitManager = LimitManager.getLimitManagerInstance();

        AddCommand addCommandTest = new AddCommand();
        addCommandTest.AddProductCommand("Hotdog", 5.50, "24/12/2020", "Food");

        addCommandTest.setData(recordManager);
        addCommandTest.setLimitManager(limitManager);

        CommandResult result = addCommandTest.execute();

        assertEquals("New record added: " + addCommandTest.getToAdd().toString()
        + "\nTotal expense: " + 5.5 + "\nWARNING: You have exceeded the spending limit of 0.0!!",
                result.getFeedbackToUser());
    }

    @Test
    void execute_newRecordDoesNotExceedLimit_noWarningMessageReturned() throws Exception {
        RecordManager recordManager = new RecordManager(new Storage("data/records.txt","data/totalExpense.txt","data/limit.txt"));
        LimitManager limitManager = LimitManager.getLimitManagerInstance();

        limitManager.setLimit(50);

        AddCommand addCommandTest = new AddCommand();
        addCommandTest.AddProductCommand("Hotdog", 5.50, "24/12/2020", "Food");

        addCommandTest.setData(recordManager);
        addCommandTest.setLimitManager(limitManager);

        CommandResult result = addCommandTest.execute();

        assertEquals("New record added: " + addCommandTest.getToAdd().toString()
                        + "\nTotal expense: " + 5.5, result.getFeedbackToUser());
    }

    @AfterEach
    void resetExpenseManager() {
        ExpenseManager.resetExpenses();
    }
}