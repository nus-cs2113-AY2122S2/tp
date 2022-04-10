package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.itemcommands.DeleteItemCommand;

import seedu.duke.exceptions.EmptyItemNameException;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidItemNameException;
import seedu.duke.exceptions.ItemNotFoundException;
import seedu.duke.exceptions.DuplicateCommandException;

import seedu.duke.itemlists.Item;
import seedu.duke.itemlists.ItemList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteItemCommandTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();

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
    public void execute_NonEmptyItemName_success() throws HotelLiteManagerException {
        DeleteItemCommand itemNameCommand = new DeleteItemCommand("Toilet Paper");
        itemNameCommand.execute(listContainer, ui);
        ItemList itemList = listContainer.getItemList();
        ItemList listOfMatchingItem = itemList.findItemsInList("Toilet Paper");
        assertEquals(0, listOfMatchingItem.getSize());
    }

    @Test
    public void execute_EmptyItemName_exceptionThrown() {
        assertThrows(EmptyItemNameException.class, () -> new DeleteItemCommand(""));
    }

    @Test
    public void execute_ItemNameOfANonExistentItem_exceptionThrown() throws HotelLiteManagerException {
        DeleteItemCommand itemNameCommand = new DeleteItemCommand("Table");
        assertThrows(ItemNotFoundException.class, () -> itemNameCommand.execute(listContainer, ui));
    }

    @Test
    public void execute_ItemNameWithInvalidSymbols_exceptionThrown() {
        assertThrows(InvalidItemNameException.class, () -> new DeleteItemCommand("{1,/0"));
    }

    @Test
    public void execute_DeleteItemCommandWithinItemNameOrPax_exceptionThrown()  {
        assertThrows(DuplicateCommandException.class, () -> new DeleteItemCommand("delete item"));
    }
}
