package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.BorrowRecord;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.stubs.CommandStubs;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ListCurrentBorrowingsCommandTest {

    @Test
    public void execute_listCurrentBorrowingsCommand() {
        Ui ui = new Ui();

        LocalDate startDate = LocalDate.parse("2022-03-21");
        LocalDate endDate = LocalDate.parse("2022-04-02");
        BorrowCommand c = new BorrowCommand(0, startDate, endDate, "John");
        c.execute(CommandStubs.TEST_ITEM_LIST, ui);

        startDate = LocalDate.parse("2022-03-21");
        endDate = LocalDate.parse("2022-04-03");
        c = new BorrowCommand(0, startDate, endDate, "Sally");
        c.execute(CommandStubs.TEST_ITEM_LIST, ui);

        Optional<String> borrowerName = Optional.ofNullable(null);
        ListCurrentBorrowingsCommand c2 = new ListCurrentBorrowingsCommand(borrowerName);
        c2.execute(CommandStubs.TEST_ITEM_LIST, ui);
    }
}
