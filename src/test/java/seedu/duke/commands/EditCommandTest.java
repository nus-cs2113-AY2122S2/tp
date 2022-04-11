package seedu.duke.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.stubs.ItemStubs;
import seedu.duke.stubs.UiStub;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.stubs.ItemStubs.ITEM_DVI_CABLE;
import static seedu.duke.stubs.ItemStubs.ITEM_HDMI_CABLE;
import static seedu.duke.stubs.ItemStubs.ITEM_MARKER;
import static seedu.duke.stubs.ItemStubs.ITEM_PAPER_A4_10;
import static seedu.duke.stubs.ItemStubs.ITEM_PAPER_A4_15;
import static seedu.duke.stubs.ItemStubs.ITEM_PAPER_A5_10;
import static seedu.duke.stubs.ItemStubs.ITEM_PENCIL;
import static seedu.duke.stubs.ItemStubs.ITEM_WHITEBOARD;

public class EditCommandTest {
    private static ItemList EDIT_NEGATIVE_QUANTITY_LIST_BEFORE;
    private static ItemList EDIT_NAME_LIST_BEFORE;
    private static ItemList EDIT_NAME_EXPECTED_LIST_AFTER;
    private static ItemList EDIT_INCREASEQUANTITY_LIST_BEFORE;
    private static ItemList EDIT_INCREASEQUANTITY_EXPECTED_LIST_AFTER;
    private static ItemList EDIT_DESC_LIST_BEFORE;
    private static ItemList EDIT_DESC_EXPECTED_LIST_AFTER;
    private static ItemList EDIT_ALL_LIST_BEFORE;
    private static ItemList EDIT_ALL_EXPECTED_LIST_AFTER;
    private static ItemList EDIT_NAME_LIST_WITH_BORROWRECORDS_BEFORE;
    private static ItemList EDIT_NAME_LIST_WITH_BORROWRECORDS_AFTER;

    @BeforeAll
    public static void generateStubs() {
        // EditCommand modifies Items.
        // The ItemStubs must be cloned for consistent behaviour.
        EDIT_NEGATIVE_QUANTITY_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_10);
        EDIT_NAME_LIST_BEFORE = generateItemList(ITEM_PENCIL);
        EDIT_NAME_EXPECTED_LIST_AFTER = generateItemList(ITEM_MARKER);
        EDIT_INCREASEQUANTITY_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_10);
        EDIT_INCREASEQUANTITY_EXPECTED_LIST_AFTER = generateItemList(ITEM_PAPER_A4_15);
        EDIT_DESC_EXPECTED_LIST_AFTER = generateItemList(ITEM_PAPER_A5_10);
        EDIT_DESC_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_10);
        EDIT_ALL_LIST_BEFORE = generateItemList(ITEM_WHITEBOARD);
        EDIT_ALL_EXPECTED_LIST_AFTER = generateItemList(ITEM_MARKER);
        EDIT_NAME_LIST_WITH_BORROWRECORDS_BEFORE = generateItemList(ITEM_DVI_CABLE);
        EDIT_NAME_LIST_WITH_BORROWRECORDS_AFTER = generateItemList(ITEM_HDMI_CABLE);
    }

    @Test
    public void execute_givenNegativeQuantity_failsToEdit() {
        UiStub uiStub = new UiStub();
        EditCommand testRelQuantityEditCommand = new EditCommand(0,
                Optional.empty(),
                Optional.of(Integer.MAX_VALUE),
                Optional.empty());
        assertThrows(InvMgrException.class, () ->
                testRelQuantityEditCommand.execute(EDIT_NEGATIVE_QUANTITY_LIST_BEFORE, uiStub));
    }

    @Test
    public void execute_givenSomeNewFields_correctlyEdits() throws InvMgrException {
        // Edit Name, "Pencil" -> "Marker"
        UiStub uiStub = new UiStub();
        EditCommand testNameEditCommand = new EditCommand(0,
                Optional.of(ItemStubs.MARKER_NAME),
                Optional.empty(),
                Optional.empty());
        testNameEditCommand.execute(EDIT_NAME_LIST_BEFORE, uiStub);
        assertEquals(EDIT_NAME_EXPECTED_LIST_AFTER, EDIT_NAME_LIST_BEFORE);

        // Edit Absolute Quantity, 10 -> 15 (of Paper)
        uiStub = new UiStub();
        EditCommand testAbsQuantityEditCommand = new EditCommand(0,
                Optional.empty(),
                Optional.of(ItemStubs.PAPER_QUANTITY_15 - ItemStubs.PAPER_QUANTITY_10),
                Optional.empty());
        testAbsQuantityEditCommand.execute(EDIT_INCREASEQUANTITY_LIST_BEFORE, uiStub);
        assertEquals(EDIT_INCREASEQUANTITY_EXPECTED_LIST_AFTER, EDIT_INCREASEQUANTITY_LIST_BEFORE);

        // Edit Description, "A4 stacks of 30" -> "A5 stacks of 30"
        uiStub = new UiStub();
        EditCommand testDescEditCommand = new EditCommand(0,
                Optional.empty(),
                Optional.empty(),
                Optional.of(ItemStubs.A5_PAPER_DESCRIPTION));
        testDescEditCommand.execute(EDIT_DESC_LIST_BEFORE, uiStub);
        assertEquals(EDIT_DESC_EXPECTED_LIST_AFTER, EDIT_DESC_LIST_BEFORE);

        // Edit all, "Whiteboard" ->  "Marker", 1 -> 3,"To draw on" -> "Drawing"
        uiStub = new UiStub();
        EditCommand testAllEditCommand = new EditCommand(0,
                Optional.of(ItemStubs.MARKER_NAME),
                Optional.of(ItemStubs.MARKER_QUANTITY - ItemStubs.WHITEBOARD_QUANTITY),
                Optional.of(ItemStubs.MARKER_DESCRIPTION));
        testAllEditCommand.execute(EDIT_ALL_LIST_BEFORE, uiStub);
        assertEquals(EDIT_ALL_EXPECTED_LIST_AFTER, EDIT_ALL_LIST_BEFORE);


        // Edit name, "DVI" -> "HDMI" while maintaining BorrowRecords
        uiStub = new UiStub();
        EditCommand testNameWithBorrowRecordsEditCommand = new EditCommand(0,
                Optional.of(ItemStubs.HDMI_CABLE_NAME),
                Optional.empty(),
                Optional.empty());
        testNameWithBorrowRecordsEditCommand.execute(EDIT_NAME_LIST_WITH_BORROWRECORDS_BEFORE, uiStub);
        assertEquals(EDIT_NAME_LIST_WITH_BORROWRECORDS_AFTER,
                EDIT_NAME_LIST_WITH_BORROWRECORDS_BEFORE);
    }

    private static ItemList generateItemList(Item... items) {
        ArrayList<Item> list = new ArrayList<>();
        for (Item item: items) {
            list.add(Item.copyItem(item));
        }
        return new ItemList(list);
    }
}
