package seedu.duke.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.stubs.UiStub;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static seedu.duke.stubs.ItemStubs.ITEM_DVI_CABLE;
import static seedu.duke.stubs.ItemStubs.ITEM_HDMI_CABLE;
import static seedu.duke.stubs.ItemStubs.ITEM_MARKER;
import static seedu.duke.stubs.ItemStubs.ITEM_WHITEBOARD;

public class AddCommandTest {

    private static ItemList LIST_BEFORE;
    private static ItemList LIST_AFTER;
    private static ItemList LIST_MATCHING_BEFORE;

    /**
     * Done before every test, to generate the following item lists to test matching and desired outcomes.
     */
    @BeforeAll
    public static void generateExpectedList() {
        LIST_BEFORE = generateItemList(ITEM_DVI_CABLE, ITEM_HDMI_CABLE, ITEM_MARKER);
        LIST_AFTER = generateItemList(ITEM_DVI_CABLE, ITEM_HDMI_CABLE, ITEM_MARKER, ITEM_WHITEBOARD);
        LIST_MATCHING_BEFORE = generateItemList(ITEM_DVI_CABLE, ITEM_HDMI_CABLE, ITEM_MARKER);
    }

    /**
     * Asserts that AddCommand will throw a NullPointerException if it is constructed without any item.
     */
    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    /**
     * Asserts that the item list will be updated successful when AddCommand is executed with a valid item.
     */
    @Test
    public void execute_validItemList_addSuccessful() {
        UiStub uiStub = new UiStub();

        AddCommand testCmd = new AddCommand(ITEM_WHITEBOARD);
        testCmd.execute(LIST_BEFORE, uiStub);

        assertEquals(LIST_AFTER, LIST_BEFORE);
    }

    /**
     * Asserts that item list will not be updated when AddCommand is executed with a duplicate item in the list.
     */
    @Test
    public void execute_existingItem_addUnsuccessful() {
        UiStub uiStub = new UiStub();

        // Test adding ITEM_DVI_CABLE which already exists in the list
        AddCommand testCmd = new AddCommand(ITEM_DVI_CABLE);
        testCmd.execute(LIST_BEFORE, uiStub);

        // LIST_BEFORE should not have changed
        assertEquals(LIST_MATCHING_BEFORE, LIST_BEFORE);
    }

    /**
     * Generates an item list with a list of items.
     * @param items list of items.
     * @return item list.
     */
    private static ItemList generateItemList(Item... items) {
        ArrayList<Item> list = new ArrayList<>();
        for (Item item : items) {
            list.add(Item.copyItem(item));
        }
        return new ItemList(list);
    }

}
