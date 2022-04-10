package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.itemcommands.UpdateItemPaxCommand;

import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.EmptyItemPaxException;
import seedu.duke.exceptions.InvalidItemPaxException;
import seedu.duke.exceptions.InvalidItemNameException;
import seedu.duke.exceptions.ItemNotFoundException;
import seedu.duke.exceptions.NewItemPaxSameAsCurrentPaxException;
import seedu.duke.exceptions.EmptyItemNameException;
import seedu.duke.exceptions.InvalidUpdateItemPaxCommandException;

import seedu.duke.itemlists.Item;
import seedu.duke.itemlists.ItemList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateItemPaxCommandTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();
    private static final int INDEX_OF_TOILET_PAPER = 0;

    @BeforeEach
    //Sets up the pretesting environment by creating an ItemList with 2 items and its corresponding pax.
    public void pretestingSetUp() throws HotelLiteManagerException, IOException {
        listContainer = new ListContainer();
        ui = new Ui();
        ItemList itemList = listContainer.getItemList();
        Item testItemToiletPaper = new Item("Toilet Paper", 15);
        itemList.addItemToList(testItemToiletPaper);
        Item testItemSofa = new Item("Sofa", 30);
        itemList.addItemToList(testItemSofa);
    }

    @Test
    public void execute_NonEmptyItemNameAndPax_success() throws HotelLiteManagerException, IOException {
        UpdateItemPaxCommand updateItemPaxCommand = new UpdateItemPaxCommand("Toilet Paper / 30");
        updateItemPaxCommand.execute(listContainer, ui);
        ItemList itemList = listContainer.getItemList();
        Item item = itemList.getItem(INDEX_OF_TOILET_PAPER);
        assertEquals(30, item.getPax());
    }

    @Test
    public void execute_EmptyItemPax_exceptionThrown() {
        assertThrows(EmptyItemPaxException.class, () -> new UpdateItemPaxCommand("Toilet Paper /"));
        assertThrows(EmptyItemPaxException.class, () -> new UpdateItemPaxCommand("Toilet Paper /   "));
    }

    @Test
    public void execute_itemPaxWithTheUpperBoundaryValue_success() throws HotelLiteManagerException, IOException {
        UpdateItemPaxCommand updateItemPaxCommand = new UpdateItemPaxCommand("Toilet Paper / 1000000");
        updateItemPaxCommand.execute(listContainer, ui);
        ItemList itemList = listContainer.getItemList();
        Item item = itemList.getItem(INDEX_OF_TOILET_PAPER);
        assertEquals(1000000, item.getPax());
    }

    @Test
    public void execute_itemPaxWithTheLowerBoundaryValue_success() throws HotelLiteManagerException {
        UpdateItemPaxCommand updateItemPaxCommand = new UpdateItemPaxCommand("Toilet Paper / 0");
        updateItemPaxCommand.execute(listContainer, ui);
        ItemList itemList = listContainer.getItemList();
        Item item = itemList.getItem(INDEX_OF_TOILET_PAPER);
        assertEquals(0, item.getPax());
    }

    @Test
    public void execute_itemPaxWithNegativeValue_exceptionThrown() {
        assertThrows(InvalidItemPaxException.class, () -> new UpdateItemPaxCommand("Toilet Paper / -1"));
    }

    @Test
    public void execute_itemPaxWithValueAboveUpperBoundary_exceptionThrown() {
        assertThrows(InvalidItemPaxException.class, () -> new UpdateItemPaxCommand("Toilet Paper / 1000001"));
    }

    @Test
    public void execute_NewItemPaxSameAsCurrentPax_exceptionThrown() throws HotelLiteManagerException {
        UpdateItemPaxCommand updateItemPaxCommand = new UpdateItemPaxCommand("Toilet Paper / 15");
        assertThrows(NewItemPaxSameAsCurrentPaxException.class, () -> updateItemPaxCommand.execute(listContainer, ui));
    }

    @Test
    public void execute_nonIntegerItemPax_exceptionThrown() {
        assertThrows(InvalidItemPaxException.class, () -> new UpdateItemPaxCommand("Towels / abc"));
    }

    @Test
    public void execute_EmptyItemName_exceptionThrown() {
        assertThrows(EmptyItemNameException.class, () -> new UpdateItemPaxCommand("/ 30"));
        assertThrows(EmptyItemNameException.class, () -> new UpdateItemPaxCommand("  / 30"));

    }

    @Test
    public void execute_EmptyItemNameAndPax_exceptionThrown() {
        assertThrows(InvalidUpdateItemPaxCommandException.class, () -> new UpdateItemPaxCommand(""));
    }

    @Test
    public void execute_ItemNameWithInvalidSymbols_exceptionThrown() {
        assertThrows(InvalidItemNameException.class, () -> new UpdateItemPaxCommand("{1,|0 / 15"));
    }

    @Test
    public void execute_ItemNameOfANonExistentItem_exceptionThrown() throws HotelLiteManagerException {
        UpdateItemPaxCommand itemNameCommand = new UpdateItemPaxCommand("Table / 15");
        assertThrows(ItemNotFoundException.class, () -> itemNameCommand.execute(listContainer, ui));
    }

}
