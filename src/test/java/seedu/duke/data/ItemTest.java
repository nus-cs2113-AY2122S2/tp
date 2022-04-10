package seedu.duke.data;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvMgrException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.stubs.BorrowRecordStubs.PRESENTRECORD_A;
import static seedu.duke.stubs.ItemStubs.ITEM_PENCIL;

public class ItemTest {

    // Test if filtered list by status is the same as expected
    @Test
    public void filterRecordsByStatus_Item() throws InvMgrException {
        List<BorrowRecord> expectedList = new ArrayList<>();
        expectedList.add(PRESENTRECORD_A);


        ITEM_PENCIL.addBorrowRecord(PRESENTRECORD_A);
        Optional<String> name = Optional.empty();
        List<BorrowRecord> outputList = ITEM_PENCIL.filterRecords(name, BorrowStatus.PRESENT);

        assertEquals(expectedList, outputList);
    }

}
