package seedu.duke.data;

import java.time.LocalDate;

public class BorrowRecord {
    private final LocalDate sDate;
    private final LocalDate eDate;
    private final String borrowerName;

    public BorrowRecord(LocalDate sDate, LocalDate eDate, String borrowerName) {
        this.sDate = sDate;
        this.eDate = eDate;
        this.borrowerName = borrowerName;
    }
}
