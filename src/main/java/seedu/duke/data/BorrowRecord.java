package seedu.duke.data;

public class BorrowRecord {
    private final String sDate;
    private final String eDate;
    private final String borrowerName;

    public BorrowRecord(String sDate, String eDate, String borrowerName) {
        this.sDate = sDate;
        this.eDate = eDate;
        this.borrowerName = borrowerName;
    }
}
