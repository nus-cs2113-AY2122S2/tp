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

    /**
     * Returns output string representing a borrow record.
     *
     * @return String output containing borrower name and borrow duration.
     */
    @Override
    public String toString() {
        String output = String.format("Name of Borrower: %s", borrowerName) + System.lineSeparator();
        output += String.format("Borrow Duration: %s", this.getBorrowDuration());
        output += System.lineSeparator();
        return output;
    }
}
