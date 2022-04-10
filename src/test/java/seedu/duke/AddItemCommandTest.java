package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.itemcommands.AddItemCommand;

import seedu.duke.exceptions.EmptyItemNameException;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.EmptyItemPaxException;
import seedu.duke.exceptions.InvalidItemPaxException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.ItemAlreadyInListException;

import seedu.duke.itemlists.Item;
import seedu.duke.itemlists.ItemList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddItemCommandTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();
    private static final int INDEX_OF_SHAMPOO = 2;
    private static final int INDEX_OF_TOOTH_BRUSH = 2;
    private static final int INDEX_OF_COMB = 2;


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
        AddItemCommand addItemPaxCommand = new AddItemCommand("Shampoo / 30");
        addItemPaxCommand.execute(listContainer, ui);
        ItemList itemList = listContainer.getItemList();
        Item item = itemList.getItem(INDEX_OF_SHAMPOO);
        assertEquals("Shampoo", item.getName());
        assertEquals(30, item.getPax());
    }

    @Test
    public void execute_EmptyItemPax_exceptionThrown() {
        assertThrows(EmptyItemPaxException.class, () -> new AddItemCommand("Towels /"));
        assertThrows(EmptyItemPaxException.class, () -> new AddItemCommand("Towels /    "));
    }

    @Test
    public void execute_itemPaxWithTheUpperBoundaryValue_success() throws HotelLiteManagerException, IOException {
        AddItemCommand addItemPaxCommand = new AddItemCommand("Tooth Brush / 1000000");
        addItemPaxCommand.execute(listContainer, ui);
        ItemList itemList = listContainer.getItemList();
        Item item = itemList.getItem(INDEX_OF_TOOTH_BRUSH);
        assertEquals("Tooth Brush", item.getName());
        assertEquals(1000000, item.getPax());
    }

    @Test
    public void execute_itemPaxWithTheLowerBoundaryValue_success() throws HotelLiteManagerException, IOException {
        AddItemCommand addItemPaxCommand = new AddItemCommand("Comb / 1");
        addItemPaxCommand.execute(listContainer, ui);
        ItemList itemList = listContainer.getItemList();
        Item item = itemList.getItem(INDEX_OF_COMB);
        assertEquals("Comb", item.getName());
        assertEquals(1, item.getPax());
    }

    @Test
    public void execute_itemPaxWithNegativeValue_exceptionThrown() {
        assertThrows(InvalidItemPaxException.class, () -> new AddItemCommand("Towels / -1"));
    }

    @Test
    public void execute_itemPaxWithValueAboveUpperBoundary_exceptionThrown() {
        assertThrows(InvalidItemPaxException.class, () -> new AddItemCommand("Towels / 1000001"));
    }

    @Test
    public void execute_itemPaxWithValueBelowLowerBoundary_exceptionThrown() {
        assertThrows(InvalidItemPaxException.class, () -> new AddItemCommand("Towels / 0"));
    }

    @Test
    public void execute_nonIntegerItemPax_exceptionThrown() {
        assertThrows(InvalidItemPaxException.class, () -> new AddItemCommand("Towels / abc"));
    }

    @Test
    public void execute_EmptyItemName_exceptionThrown() {
        assertThrows(EmptyItemNameException.class, () -> new AddItemCommand("/ 30"));
        assertThrows(EmptyItemNameException.class, () -> new AddItemCommand("  / 30"));
    }

    @Test
    public void execute_EmptyItemNameAndPax_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> new AddItemCommand(""));
        assertThrows(InvalidCommandException.class, () -> new AddItemCommand(""));
    }

    @Test
    public void execute_DuplicateItem_exceptionThrown() throws HotelLiteManagerException {
        AddItemCommand addItemPaxCommand = new AddItemCommand("Toilet Paper / 50");
        assertThrows(ItemAlreadyInListException.class, () -> addItemPaxCommand.execute(listContainer, ui));
    }
}
