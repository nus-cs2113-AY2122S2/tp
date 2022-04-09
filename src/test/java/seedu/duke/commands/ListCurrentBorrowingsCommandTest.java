package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.stubs.BorrowRecordStubs;
import seedu.duke.stubs.CommandStubs;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.Optional;

public class ListCurrentBorrowingsCommandTest {

    /**
     * Test the ListCurrentBorrowingsCommand when the user does not input any borrower's name.
     * Print all borrowed items.
     */
    @Test
    public void execute_listAllBorrowings() {
        Ui ui = new Ui();
        Optional<String> borrowerName = Optional.ofNullable(null);
        ListCurrentBorrowingsCommand c = new ListCurrentBorrowingsCommand(borrowerName);
        c.execute(CommandStubs.TEST_ITEM_LIST_WITH_RECORDS, ui);
    }

    /**
     * Test the ListCurrentBorrowingsCommand when the user inputs a borrower's name.
     * Print only the specific items borrowed by the borrower specified.
     */
    @Test
    public void execute_listPersonBorrowings() {
        Ui ui = new Ui();
        Optional<String> borrowerName = Optional.ofNullable(BorrowRecordStubs.PRESENTRECORD_A_NAME);
        ListCurrentBorrowingsCommand c = new ListCurrentBorrowingsCommand(borrowerName);
        c.execute(CommandStubs.TEST_ITEM_LIST_WITH_RECORDS, ui);
    }
}
