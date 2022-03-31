package seedu.duke.data;

import java.time.LocalDate;

public class BorrowRecord {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String borrowerName;
    private final BorrowStatus borrowStatus;

    public BorrowRecord(LocalDate startDate, LocalDate endDate, String borrowerName, BorrowStatus borrowStatus) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.borrowerName = borrowerName;
        this.borrowStatus = borrowStatus;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public String getBorrowDuration() {
        return (startDate.toString() + "to" + endDate.toString());
    }

    public BorrowStatus getBorrowStatus() {
        return borrowStatus;
    }
}
