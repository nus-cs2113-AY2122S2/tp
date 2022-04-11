package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.itemcommands.SearchItemCommand;

import seedu.duke.exceptions.DuplicateCommandException;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.EmptyKeywordException;

import seedu.duke.itemlists.Item;
import seedu.duke.itemlists.ItemList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SearchItemCommandTest {
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
    public void execute_NonEmptyKeyword_success() {
        ItemList itemList = listContainer.getItemList();
        ItemList listOfMatchingItem = itemList.findItemsInList("Toilet Paper");
        Item item = listOfMatchingItem.getItem(INDEX_OF_TOILET_PAPER);
        assertEquals("Toilet Paper", item.getName());
    }

    @Test
    public void execute_EmptyKeyword_exceptionThrown() {
        assertThrows(EmptyKeywordException.class, () -> new SearchItemCommand(" "));
    }

    @Test
    public void execute_ItemNameOfANonExistentItem_success() throws HotelLiteManagerException {
        SearchItemCommand itemNameCommand = new SearchItemCommand(" Table");
        ItemList itemList = listContainer.getItemList();
        String keyword = itemNameCommand.getKeyword();
        ItemList listOfMatchingItem = itemList.findItemsInList(keyword);
        assertEquals(0, listOfMatchingItem.getSize());
    }

    @Test
    public void execute_SearchItemCommandWithinItemNameOrPax_exceptionThrown()  {
        assertThrows(DuplicateCommandException.class, () -> new SearchItemCommand(" search item"));
    }

}
