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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class LostCommandTest {
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
    }

    /**
     * Asserts that itemList size is 0 after full quantity of markers is lost.
     * */
    @Test
    public void execute_fullQuantityLost_itemDeleted() throws InvMgrException {
        Ui ui = new Ui();
        LostCommand c = new LostCommand(0, 3);
        c.execute(itemList, ui);
        assertEquals(0, itemList.getSize());
    }

    /**
     * Asserts that itemList size is 1 after 2 of 3 markers are reported lost.
     * */
    @Test
    public void execute_partialQuantityLost_itemQuantityReduced() throws InvMgrException {
        Ui ui = new Ui();
        LostCommand c = new LostCommand(0, 2);
        c.execute(itemList, ui);
        Item lostItem = itemList.getItem(0);
        assertEquals(1, lostItem.getQuantity());
    }

    /**
     * Checks that InvMgrException is thrown when lost quantity exceeds item quantity.
     * Checks that the RETURN_ERROR_MESSAGE is displayed.
     * */
    @Test
    public void execute_excessiveQuantityLost_InvMgrExceptionThrown() {
        Ui ui = new Ui();
        LostCommand c = new LostCommand(0, 5);
        try {
            c.execute(itemList, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(Messages.LOST_ERROR_MESSAGE, e.getMessage());
        }
    }

    /**
     * Checks that InvMgrException is thrown when inventory is empty.
     * Checks that the EMPTY_ITEM_LIST_MESSAGE is displayed.
     * */
    @Test
    public void execute_emptyInventory_InvMgrExceptionThrown() {
        Ui ui = new Ui();
        ArrayList<Item> emptyItemArrayList = new ArrayList<>();
        ItemList emptyItemList = new ItemList(emptyItemArrayList);
        LostCommand c = new LostCommand(0, 1);
        try {
            c.execute(emptyItemList, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(Messages.EMPTY_ITEM_LIST_MESSAGE, e.getMessage());
        }
    }

    /**
     * Checks that InvMgrException is thrown when inventory is empty.
     * Checks that the EMPTY_ITEM_LIST_MESSAGE is displayed.
     * */
    @Test
    public void execute_indexOutOfRange_InvMgrExceptionThrown() {
        Ui ui = new Ui();
        LostCommand c = new LostCommand(10, 1);
        try {
            c.execute(itemList, ui);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals(Messages.ITEM_NUMBER_OUT_OF_RANGE_MESSAGE, e.getMessage());
        }
    }

}
