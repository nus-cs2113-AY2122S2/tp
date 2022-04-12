package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.common.Messages;
import seedu.duke.data.BorrowRecord;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ReturnCommandTest {
    private ArrayList<Item> itemArrayList = new ArrayList<>();
    ItemList itemList = new ItemList(itemArrayList);

    @BeforeEach
    public void setUp() throws InvMgrException {
        Item item1 = new Item("Markers", 3, "Drawing");
        itemList.addItem(item1);
        LocalDate startDate1 = LocalDate.parse("2022-03-21");
        LocalDate endDate1 = LocalDate.parse("2022-04-02");
        BorrowRecord borrowRecord1 = new BorrowRecord(3, startDate1, endDate1, "John Doe");
        borrowRecord1.setReturnStatus(true);
        item1.addBorrowRecord(borrowRecord1);

        Item item2 = new Item("Whiteboard", 1, "For writing and drawing");
        itemList.addItem(item2);
        LocalDate startDate2 = LocalDate.parse("2022-03-21");
        LocalDate endDate2 = LocalDate.parse("2022-04-02");
        BorrowRecord borrowRecord2 = new BorrowRecord(1, startDate2, endDate2, "John Doe");
        item2.addBorrowRecord(borrowRecord2);
    }

    /**
     * Checks that InvMgrException is thrown when no items are overdue nor currently on loan.
     * Checks that Messages.RETURN_ERROR_MESSAGE is displayed.
     * */
    @Test
    public void execute_noItemsToBeReturned_InvMgrExceptionThrown() {
        Ui ui = new Ui();
        ReturnCommand c = new ReturnCommand(0);
        try {
            c.execute(itemList, ui);
            fail();
        } catch (InvMgrException e) {
            assertEquals(Messages.RETURN_ERROR_MESSAGE, e.getMessage());
        }
    }

    /**
     * Asserts that isValidReturn is true when an overdue item is returned.
     * Asserts that the return status of the item is true
     * and that the end date of its borrow record is the date of return.
     * */
    @Test
    public void execute_overdueItem_success() throws InvMgrException {
        Ui ui = new Ui();
        ReturnCommand c = new ReturnCommand(1);
        c.execute(itemList, ui);
        assertAll("overdue item", () -> {
            boolean isValidReturn = c.getValidityOfReturn();
            assertTrue(isValidReturn);
            Item item2 = itemList.getItem(1);
            ArrayList<BorrowRecord> borrowRecords = item2.getBorrowRecords();
            BorrowRecord borrowRecord2 = borrowRecords.get(0);
            assertAll("second item",
                    () -> assertTrue(borrowRecord2.getReturnStatus()),
                    () -> assertEquals(LocalDate.now(), borrowRecord2.getEndDate())
            );
        }
        );
    }

    /**
     * Asserts that isValidReturn is true when an item that is currently on loan is returned.
     * Asserts that the return status of the item is true
     * and that the end date of its borrow record is the date of return.
     * */
    @Test
    public void execute_currentlyBorrowedItem_success() throws InvMgrException {
        itemList.removeItem(1);
        Item item2 = new Item("Whiteboard", 1, "For writing and drawing");
        itemList.addItem(item2);
        LocalDate startDate2 = LocalDate.now().plusDays(-1);
        LocalDate endDate2 = LocalDate.now().plusDays(2);
        BorrowRecord borrowRecord2 = new BorrowRecord(1, startDate2, endDate2, "John Doe");
        item2.addBorrowRecord(borrowRecord2);
        ReturnCommand c = new ReturnCommand(1);
        Ui ui = new Ui();
        c.execute(itemList, ui);
        assertAll("overdue item", () -> {
            boolean isValidReturn = c.getValidityOfReturn();
            assertTrue(isValidReturn);
            assertAll("second item",
                    () -> assertTrue(borrowRecord2.getReturnStatus()),
                    () -> assertEquals(LocalDate.now(), borrowRecord2.getEndDate())
            );
        }
        );
    }

    /**
     * Checks that InvMgrException is thrown when the inventory is empty.
     * Checks that Messages.EMPTY_ITEM_LIST_MESSAGE is displayed.
     * */
    @Test
    public void execute_emptyItemList_InvMgrExceptionThrown() {
        Ui ui = new Ui();
        ArrayList<Item> emptyItemArrayList = new ArrayList<>();
        ItemList emptyItemList = new ItemList(emptyItemArrayList);
        ReturnCommand c = new ReturnCommand(0);
        try {
            c.execute(emptyItemList, ui);
            fail();
        } catch (InvMgrException e) {
            assertEquals(Messages.EMPTY_ITEM_LIST_MESSAGE, e.getMessage());
        }
    }

    /**
     * Checks that InvMgrException is thrown when the item index is out of range.
     * Checks that Messages.ITEM_NUMBER_OUT_OF_RANGE_MESSAGE is displayed.
     * */
    @Test
    public void execute_indexOutOfRange_InvMgrExceptionThrown() {
        Ui ui = new Ui();
        ReturnCommand c = new ReturnCommand(10);
        try {
            c.execute(itemList, ui);
            fail();
        } catch (InvMgrException e) {
            assertEquals(Messages.ITEM_NUMBER_OUT_OF_RANGE_MESSAGE, e.getMessage());
        }
    }

}
