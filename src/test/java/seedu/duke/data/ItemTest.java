package seedu.duke.data;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvMgrException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.stubs.BorrowRecordStubs.PRESENTRECORD_A;
import static seedu.duke.stubs.ItemStubs.ITEM_PENCIL;
import static seedu.duke.stubs.ItemStubs.PENCIL_NAME;

public class ItemTest {

    // Test if filtered list by status is the same as expected
    @Test
    public void filterRecordsByStatus_Item() throws InvMgrException {
        String prefix = "Name of Item: " + PENCIL_NAME + System.lineSeparator();
        List<String> expectedList = new ArrayList<>();
        expectedList.add(prefix + PRESENTRECORD_A.toString());


        ITEM_PENCIL.addBorrowRecord(PRESENTRECORD_A);
        Optional<String> name = Optional.empty();
        List<String> outputList = ITEM_PENCIL.filterRecords(name, BorrowStatus.PRESENT);

        assertEquals(expectedList, outputList);
    }

}
