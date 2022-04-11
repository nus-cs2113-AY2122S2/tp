package seedu.duke.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.stubs.UiStub;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.duke.stubs.ItemStubs.ITEM_PENCIL;
import static seedu.duke.stubs.ItemStubs.ITEM_DVI_CABLE;
import static seedu.duke.stubs.ItemStubs.ITEM_HDMI_CABLE;
import static seedu.duke.stubs.ItemStubs.ITEM_MARKER;

public class DeleteCommandTest {

    private static ItemList LIST_BEFORE;
    private static ItemList LIST_AFTER;

    @BeforeAll
    public static void generateExpectedList() {
        LIST_BEFORE = generateItemList(ITEM_PENCIL, ITEM_DVI_CABLE, ITEM_HDMI_CABLE, ITEM_MARKER);
        LIST_AFTER = generateItemList(ITEM_PENCIL, ITEM_DVI_CABLE, ITEM_HDMI_CABLE);
    }

    @Test
    public void execute_invalidIndexOutOfRange_throwsIndexOutOfBoundsException() {
        UiStub uiStub = new UiStub();
        int invalidIndex = LIST_BEFORE.getSize() + 1;
        DeleteCommand testCmd = new DeleteCommand(invalidIndex);
        testCmd.execute(LIST_BEFORE, uiStub);
    }

    @Test
    public void execute_validIndex_deleteSuccessful() {
        UiStub uiStub = new UiStub();
        int validIndex = LIST_BEFORE.getSize() - 1;
        DeleteCommand testCmd = new DeleteCommand(validIndex);
        testCmd.execute(LIST_BEFORE, uiStub);

        assertEquals(LIST_AFTER, LIST_BEFORE);
    }

    private static ItemList generateItemList(Item... items) {
        ArrayList<Item> list = new ArrayList<>();
        for (Item item : items) {
            list.add(Item.copyItem(item));
        }
        return new ItemList(list);
    }


}
