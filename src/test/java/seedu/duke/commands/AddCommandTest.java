package seedu.duke.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.stubs.ItemStubs;
import seedu.duke.stubs.UiStub;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {

    private static ItemList EXPECTED_ITEMLIST;

    @BeforeAll
    public static void generateExpectedList() {
        EXPECTED_ITEMLIST = new ItemList(new ArrayList<>());
        EXPECTED_ITEMLIST.addItem(ItemStubs.ITEM_MARKER);
        EXPECTED_ITEMLIST.addItem(ItemStubs.ITEM_WHITEBOARD);
        EXPECTED_ITEMLIST.addItem(ItemStubs.ITEM_HDMI_CABLE);
        EXPECTED_ITEMLIST.addItem(ItemStubs.ITEM_DVI_CABLE);
    }

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_validItemList_addSuccessful() {
        ItemList actualItemList = new ItemList(new ArrayList<>());
        // It's alright to use the ItemStubs directly here since AddCommand is a non-mutating command.
        Item item1 = ItemStubs.ITEM_MARKER;
        Item item2 = ItemStubs.ITEM_WHITEBOARD;
        Item item3 = ItemStubs.ITEM_HDMI_CABLE;
        actualItemList.addItem(item1);
        actualItemList.addItem(item2);
        actualItemList.addItem(item3);

        Ui ui = new UiStub();

        AddCommand testComd = new AddCommand(ItemStubs.ITEM_DVI_CABLE);
        testComd.execute(actualItemList, ui);

        assertEquals(EXPECTED_ITEMLIST, actualItemList);
    }

}
