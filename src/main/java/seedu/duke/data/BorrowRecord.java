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

    @Override
    public boolean equals(Object other) {
        if (other==this) {
            return true; // short circuit if same object
        }
        // instanceof handles nulls
        if (other instanceof BorrowRecord) {
            BorrowRecord otherItem = ((BorrowRecord) other);
            return this.startDate.equals(otherItem.startDate)
                    && this.endDate.equals(otherItem.endDate)
                    && this.borrowerName.equals(otherItem.borrowerName);
        }
        return false;
    }
}
