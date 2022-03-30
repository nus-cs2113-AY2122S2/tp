package seedu.duke.data;

import java.time.LocalDate;

public class BorrowRecord {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String borrowerName;

    public BorrowRecord(LocalDate startDate, LocalDate endDate, String borrowerName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.borrowerName = borrowerName;
    }
}
