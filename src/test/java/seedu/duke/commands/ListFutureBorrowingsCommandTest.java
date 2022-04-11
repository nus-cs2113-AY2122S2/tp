package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.stubs.BorrowRecordStubs;
import seedu.duke.stubs.ItemListStubs;
import seedu.duke.ui.Ui;

import java.util.Optional;

public class ListFutureBorrowingsCommandTest {

    /**
     * Test the ListFutureBorrowingsCommand when the user does not input any borrower's name.
     * Print all borrowed items.
     */
    @Test
    public void execute_listAllFutureBorrowings() {
        Ui ui = new Ui();
        Optional<String> borrowerName = Optional.ofNullable(null);
        ListFutureBorrowingsCommand c = new ListFutureBorrowingsCommand(borrowerName);
        c.execute(ItemListStubs.TEST_ITEM_LIST_WITH_RECORDS, ui);
    }

    /**
     * Test the ListFutureBorrowingsCommand when the user inputs a borrower's name.
     * Print only the specific items borrowed by the borrower specified.
     */
    @Test
    public void execute_listPersonFutureBorrowings() {
        Ui ui = new Ui();
        Optional<String> borrowerName = Optional.ofNullable(BorrowRecordStubs.FUTURERECORD_A_NAME);
        ListFutureBorrowingsCommand c = new ListFutureBorrowingsCommand(borrowerName);
        c.execute(ItemListStubs.TEST_ITEM_LIST_WITH_RECORDS, ui);
    }
}
