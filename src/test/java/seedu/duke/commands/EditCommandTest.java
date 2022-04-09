package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.stubs.ItemListStubs;
import seedu.duke.stubs.ItemStubs;
import seedu.duke.stubs.UiStub;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EditCommandTest {
    @Test
    public void execute_givenNegativeQuantity_failsToEdit() {
        UiStub uiStub = new UiStub();
        EditCommand testRelQuantityEditCommand = new EditCommand(0,
                Optional.empty(),
                Optional.of(Integer.MAX_VALUE),
                Optional.empty(),
                Optional.of(false));
        assertThrows(InvMgrException.class, () ->
                testRelQuantityEditCommand.execute(ItemListStubs.EDIT_NEGATIVE_QUANTITY_LIST_BEFORE, uiStub));
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
        testNameEditCommand.execute(ItemListStubs.EDIT_NAME_LIST_BEFORE, uiStub);
        assertEquals(ItemListStubs.EDIT_NAME_EXPECTED_LIST_AFTER, ItemListStubs.EDIT_NAME_LIST_BEFORE);

        // Edit Absolute Quantity, 10 -> 15 (of Paper)
        uiStub = new UiStub();
        EditCommand testAbsQuantityEditCommand = new EditCommand(0,
                Optional.empty(),
                Optional.of(ItemStubs.PAPER_QUANTITY_15),
                Optional.empty(),
                Optional.empty());
        testAbsQuantityEditCommand.execute(ItemListStubs.EDIT_ABSQUANTITY_LIST_BEFORE, uiStub);
        assertEquals(ItemListStubs.EDIT_ABSQUANTITY_EXPECTED_LIST_AFTER, ItemListStubs.EDIT_ABSQUANTITY_LIST_BEFORE);

        // Edit Relative Quantity, 15 -> (15-5=)10 (of Paper)
        uiStub = new UiStub();
        EditCommand testRelQuantityEditCommand = new EditCommand(0,
                Optional.empty(),
                Optional.of(ItemStubs.PAPER_QUANTITY_15 - ItemStubs.PAPER_QUANTITY_10),
                Optional.empty(),
                Optional.of(false));
        testRelQuantityEditCommand.execute(ItemListStubs.EDIT_RELQUANTITY_LIST_BEFORE, uiStub);
        assertEquals(ItemListStubs.EDIT_RELQUANTITY_EXPECTED_LIST_AFTER, ItemListStubs.EDIT_RELQUANTITY_LIST_BEFORE);

        // Edit Description, "A4 stacks of 30" -> "A5 stacks of 30"
        uiStub = new UiStub();
        EditCommand testDescEditCommand = new EditCommand(0,
                Optional.empty(),
                Optional.empty(),
                Optional.of(ItemStubs.A5_PAPER_DESCRIPTION),
                Optional.empty());
        testDescEditCommand.execute(ItemListStubs.EDIT_DESC_LIST_BEFORE, uiStub);
        assertEquals(ItemListStubs.EDIT_DESC_EXPECTED_LIST_AFTER, ItemListStubs.EDIT_DESC_LIST_BEFORE);

        // Edit all, "Marker" -> "Whiteboard", 3 -> 1, "Drawing" -> "To draw on"
        uiStub = new UiStub();
        EditCommand testAllEditCommand = new EditCommand(0,
                Optional.of(ItemStubs.WHITEBOARD_NAME),
                Optional.of(ItemStubs.WHITEBOARD_QUANTITY),
                Optional.of(ItemStubs.WHITEBOARD_DESCRIPTION),
                Optional.empty());
        testAllEditCommand.execute(ItemListStubs.EDIT_ALL_LIST_BEFORE, uiStub);
        assertEquals(ItemListStubs.EDIT_ALL_EXPECTED_LIST_AFTER, ItemListStubs.EDIT_ALL_LIST_BEFORE);


        // Edit name, "DVI" -> "HDMI" while maintaining BorrowRecords
        uiStub = new UiStub();
        EditCommand testNameWithBorrowRecordsEditCommand = new EditCommand(0,
                Optional.of(ItemStubs.HDMI_CABLE_NAME),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
        testNameWithBorrowRecordsEditCommand.execute(ItemListStubs.EDIT_NAME_LIST_WITH_BORROWRECORDS_BEFORE, uiStub);
        assertEquals(ItemListStubs.EDIT_NAME_LIST_WITH_BORROWRECORDS_AFTER,
                ItemListStubs.EDIT_NAME_LIST_WITH_BORROWRECORDS_BEFORE);


    }
}
