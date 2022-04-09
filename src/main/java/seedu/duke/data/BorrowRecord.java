package seedu.duke.data;

import java.time.LocalDate;

public class BorrowRecord {
    private final LocalDate startDate;
    private LocalDate endDate;
    private final String borrowerName;
    private final BorrowStatus borrowStatus;
    private boolean isReturned = false;

    public BorrowRecord(LocalDate startDate, LocalDate endDate, String borrowerName, BorrowStatus borrowStatus) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.borrowerName = borrowerName;
        this.borrowStatus = borrowStatus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public String getBorrowDuration() {
        return (startDate.toString() + " to " + endDate.toString());
    }

    public BorrowStatus getBorrowStatus() {
        return borrowStatus;
    }

    public void setReturnStatus(boolean isReturned) {
        this.isReturned = isReturned;
    }

    public void setEndDate() {
        this.endDate = LocalDate.now();
    }

    public boolean getReturnStatus() {
        return isReturned;
    }
    
    /**
     * Returns true if otherRecord has overlapping dates with this borrow record.
     *
     * @param otherRecord Other borrow record you want to compare with.
     * @return True if there are dates overlapping between the 2 records.
     */
    public boolean isConflict(BorrowRecord otherRecord) {
        if (startDate.compareTo(otherRecord.endDate) > 0
                || endDate.compareTo(otherRecord.startDate) < 0) {
            return false;
        } else {
            return true;
        }
    }
}
