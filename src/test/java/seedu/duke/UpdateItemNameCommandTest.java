package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.itemcommands.UpdateItemNameCommand;

import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidUpdateItemNameCommandException;
import seedu.duke.exceptions.DuplicateItemNameException;
import seedu.duke.exceptions.InvalidItemNameException;
import seedu.duke.exceptions.ItemNotFoundException;
import seedu.duke.exceptions.ItemNameAlreadyInListException;
import seedu.duke.exceptions.DuplicateCommandException;

import seedu.duke.itemlists.Item;
import seedu.duke.itemlists.ItemList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UpdateItemNameCommandTest {
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
    public void execute_NonEmptyItemNames_success() throws HotelLiteManagerException {
        UpdateItemNameCommand itemNameCommand = new UpdateItemNameCommand("Toilet Paper / Premium Toilet Paper"
        );
        itemNameCommand.execute(listContainer, ui);
        ItemList itemList = listContainer.getItemList();
        Item item = itemList.getItem(INDEX_OF_TOILET_PAPER);
        assertEquals("Premium Toilet Paper", item.getName());
    }

    @Test
    public void execute_EmptyCurrentItemName_exceptionThrown() {
        assertThrows(InvalidUpdateItemNameCommandException.class, () -> new UpdateItemNameCommand("/ Premium "
                + "Toilet Paper"));
        assertThrows(InvalidUpdateItemNameCommandException.class, () -> new UpdateItemNameCommand("   / Premium "
                + "Toilet Paper"));
    }

    @Test
    public void execute_EmptyUpdatedItemName_exceptionThrown() {
        assertThrows(InvalidUpdateItemNameCommandException.class, () -> new UpdateItemNameCommand("Toilet "
                + "Paper /"));
        assertThrows(InvalidUpdateItemNameCommandException.class, () -> new UpdateItemNameCommand("Toilet "
                + "Paper /   "));
    }

    @Test
    public void execute_EmptyCurrentAndUpdatedItemNames_exceptionThrown() {
        assertThrows(InvalidUpdateItemNameCommandException.class, () -> new UpdateItemNameCommand(""));
    }

    @Test
    public void execute_NewItemNameSameAsCurrentName_exceptionThrown() throws HotelLiteManagerException {
        UpdateItemNameCommand updateItemNameCommand = new UpdateItemNameCommand("Toilet Paper / Toilet Paper");
        assertThrows(DuplicateItemNameException.class, () -> updateItemNameCommand.execute(listContainer, ui));
    }

    @Test
    public void execute_ItemNameWithInvalidSymbols_exceptionThrown() {
        assertThrows(InvalidItemNameException.class, () -> new UpdateItemNameCommand("{1,/0"));
    }

    @Test
    public void execute_ItemNameOfANonExistentItem_success() throws HotelLiteManagerException {
        UpdateItemNameCommand itemNameCommand = new UpdateItemNameCommand("Table / New Table");
        assertThrows(ItemNotFoundException.class, () -> itemNameCommand.execute(listContainer, ui));
    }

    @Test
    public void execute_NewItemNameAlreadyFoundInItemList_success() throws HotelLiteManagerException {
        UpdateItemNameCommand itemNameCommand = new UpdateItemNameCommand("Sofa / Toilet Paper");
        assertThrows(ItemNameAlreadyInListException.class, () -> itemNameCommand.execute(listContainer, ui));
    }

    @Test
    public void execute_UpdateItemNameCommandWithinItemNameOrPax_exceptionThrown() {
        assertThrows(DuplicateCommandException.class, () -> new UpdateItemNameCommand("update item name / "
                + "Premium Toilet Roll"));
        assertThrows(DuplicateCommandException.class, () -> new UpdateItemNameCommand("Toilet Roll / update "
                + "item name"));
    }

}
