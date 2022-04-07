package seedu.duke.data;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.stubs.BorrowRecordStubs.FUTURERECORD_A;
import static seedu.duke.stubs.BorrowRecordStubs.PRESENTRECORD_A;
import static seedu.duke.stubs.BorrowRecordStubs.PASTRECORD_A;

public class BorrowRecordTest {
    @Test
    public void conflict_borrowRecord() {
        assertEquals(true, PRESENTRECORD_A.isConflict(PRESENTRECORD_A));
    }

    @Test
    public void conflict_sameDate_borrowRecord() {
        assertEquals(true, PRESENTRECORD_A.isConflict(FUTURERECORD_A));
    }

    @Test
    public void noConflict_borrowRecord() {
        assertEquals(false, PRESENTRECORD_A.isConflict(PASTRECORD_A));
    }
}
