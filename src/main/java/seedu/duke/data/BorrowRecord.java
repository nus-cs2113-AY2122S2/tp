package seedu.duke.data;

import java.time.LocalDate;

public class BorrowRecord {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String borrowerName;

    public BorrowRecord(LocalDate sDate, LocalDate eDate, String borrowerName) {
        this.startDate = sDate;
        this.endDate = eDate;
        this.borrowerName = borrowerName;
    }
}
