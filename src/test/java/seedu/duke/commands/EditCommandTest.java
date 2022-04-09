package seedu.duke.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.stubs.ItemListStubs;
import seedu.duke.stubs.ItemStubs;
import seedu.duke.stubs.UiStub;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.duke.stubs.ItemStubs.*;

public class EditCommandTest {
    private static ItemList EDIT_NEGATIVE_QUANTITY_LIST_BEFORE;
    private static ItemList EDIT_NAME_LIST_BEFORE;
    private static ItemList EDIT_NAME_EXPECTED_LIST_AFTER;
    private static ItemList EDIT_ABSQUANTITY_LIST_BEFORE;
    private static ItemList EDIT_ABSQUANTITY_EXPECTED_LIST_AFTER;
    private static ItemList EDIT_RELQUANTITY_LIST_BEFORE;
    private static ItemList EDIT_RELQUANTITY_EXPECTED_LIST_AFTER;
    private static ItemList EDIT_DESC_LIST_BEFORE;
    private static ItemList EDIT_DESC_EXPECTED_LIST_AFTER;
    private static ItemList EDIT_ALL_LIST_BEFORE;
    private static ItemList EDIT_ALL_EXPECTED_LIST_AFTER;
    private static ItemList EDIT_NAME_LIST_WITH_BORROWRECORDS_BEFORE;
    private static ItemList EDIT_NAME_LIST_WITH_BORROWRECORDS_AFTER;

    @BeforeAll
    public static void generateStubs() {
        EDIT_NEGATIVE_QUANTITY_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_10);
        EDIT_NAME_LIST_BEFORE = generateItemList(ITEM_PENCIL);
        EDIT_NAME_EXPECTED_LIST_AFTER = generateItemList(ITEM_MARKER);
        EDIT_ABSQUANTITY_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_10);
        EDIT_ABSQUANTITY_EXPECTED_LIST_AFTER = generateItemList(ITEM_PAPER_A4_15);
        EDIT_RELQUANTITY_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_15);
        EDIT_RELQUANTITY_EXPECTED_LIST_AFTER = generateItemList(ITEM_PAPER_A4_10);
        EDIT_DESC_EXPECTED_LIST_AFTER = generateItemList(ITEM_PAPER_A5_10);
        EDIT_DESC_LIST_BEFORE = generateItemList(ITEM_PAPER_A4_10);
        EDIT_ALL_LIST_BEFORE = generateItemList(ITEM_MARKER);
        EDIT_ALL_EXPECTED_LIST_AFTER = generateItemList(ITEM_WHITEBOARD);
        EDIT_NAME_LIST_WITH_BORROWRECORDS_BEFORE = generateItemList(ITEM_DVI_CABLE);
        EDIT_NAME_LIST_WITH_BORROWRECORDS_AFTER = generateItemList(ITEM_HDMI_CABLE);
    }

    @Test
    public void execute_givenNegativeQuantity_failsToEdit() {
        UiStub uiStub = new UiStub();
        EditCommand testRelQuantityEditCommand = new EditCommand(0,
                Optional.empty(),
                Optional.of(Integer.MAX_VALUE),
                Optional.empty(),
                Optional.of(false));
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
                Optional.empty(),
                Optional.empty());
        testNameEditCommand.execute(EDIT_NAME_LIST_BEFORE, uiStub);
        assertEquals(EDIT_NAME_EXPECTED_LIST_AFTER, EDIT_NAME_LIST_BEFORE);

        // Edit Absolute Quantity, 10 -> 15 (of Paper)
        uiStub = new UiStub();
        EditCommand testAbsQuantityEditCommand = new EditCommand(0,
                Optional.empty(),
                Optional.of(ItemStubs.PAPER_QUANTITY_15),
                Optional.empty(),
                Optional.empty());
        testAbsQuantityEditCommand.execute(EDIT_ABSQUANTITY_LIST_BEFORE, uiStub);
        assertEquals(EDIT_ABSQUANTITY_EXPECTED_LIST_AFTER, EDIT_ABSQUANTITY_LIST_BEFORE);

        // Edit Relative Quantity, 15 -> (15-5=)10 (of Paper)
        uiStub = new UiStub();
        EditCommand testRelQuantityEditCommand = new EditCommand(0,
                Optional.empty(),
                Optional.of(ItemStubs.PAPER_QUANTITY_15 - ItemStubs.PAPER_QUANTITY_10),
                Optional.empty(),
                Optional.of(false));
        testRelQuantityEditCommand.execute(EDIT_RELQUANTITY_LIST_BEFORE, uiStub);
        assertEquals(EDIT_RELQUANTITY_EXPECTED_LIST_AFTER, EDIT_RELQUANTITY_LIST_BEFORE);

        // Edit Description, "A4 stacks of 30" -> "A5 stacks of 30"
        uiStub = new UiStub();
        EditCommand testDescEditCommand = new EditCommand(0,
                Optional.empty(),
                Optional.empty(),
                Optional.of(ItemStubs.A5_PAPER_DESCRIPTION),
                Optional.empty());
        testDescEditCommand.execute(EDIT_DESC_LIST_BEFORE, uiStub);
        assertEquals(EDIT_DESC_EXPECTED_LIST_AFTER, EDIT_DESC_LIST_BEFORE);

        // Edit all, "Marker" -> "Whiteboard", 3 -> 1, "Drawing" -> "To draw on"
        uiStub = new UiStub();
        EditCommand testAllEditCommand = new EditCommand(0,
                Optional.of(ItemStubs.WHITEBOARD_NAME),
                Optional.of(ItemStubs.WHITEBOARD_QUANTITY),
                Optional.of(ItemStubs.WHITEBOARD_DESCRIPTION),
                Optional.empty());
        testAllEditCommand.execute(EDIT_ALL_LIST_BEFORE, uiStub);
        assertEquals(EDIT_ALL_EXPECTED_LIST_AFTER, EDIT_ALL_LIST_BEFORE);


        // Edit name, "DVI" -> "HDMI" while maintaining BorrowRecords
        uiStub = new UiStub();
        EditCommand testNameWithBorrowRecordsEditCommand = new EditCommand(0,
                Optional.of(ItemStubs.HDMI_CABLE_NAME),
                Optional.empty(),
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
