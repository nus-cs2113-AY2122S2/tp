package seedu.duke.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.stubs.BorrowRecordStubs.FUTURERECORD_A;
import static seedu.duke.stubs.BorrowRecordStubs.PRESENTRECORD_A;
import static seedu.duke.stubs.BorrowRecordStubs.PASTRECORD_A;
import static seedu.duke.stubs.BorrowRecordStubs.PRESENTRECORD_A_ENDDATE;
import static seedu.duke.stubs.BorrowRecordStubs.PRESENTRECORD_A_NAME;
import static seedu.duke.stubs.BorrowRecordStubs.PRESENTRECORD_A_STARTDATE;

public class BorrowRecordTest {
    @Test
    public void borrowRecord_sameDate_isConflict() {
        assertEquals(true, PRESENTRECORD_A.isConflict(PRESENTRECORD_A));
    }

    @Test
    public void borrowRecord_overlapDate_isConflict() {
        assertEquals(true, PRESENTRECORD_A.isConflict(FUTURERECORD_A));
    }

    @Test
    public void borrowRecord_diffDate_noConflict() {
        assertEquals(false, PRESENTRECORD_A.isConflict(PASTRECORD_A));
    }

    // Test if string output for a BorrowRecord matches expected string output
    @Test
    public void borrowRecord_stringOutput() {
        String expectedOutput = String.format("Name of Borrower: %s",
                PRESENTRECORD_A_NAME) + System.lineSeparator();
        expectedOutput += String.format("Borrow Duration: %s to %s",
                PRESENTRECORD_A_STARTDATE, PRESENTRECORD_A_ENDDATE);
        expectedOutput += System.lineSeparator();

        assertEquals(expectedOutput, PRESENTRECORD_A.toString());
    }
}
