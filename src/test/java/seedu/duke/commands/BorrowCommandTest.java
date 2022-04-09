package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.BorrowRecord;
import seedu.duke.data.BorrowStatus;
import seedu.duke.data.Item;
import seedu.duke.data.ItemList;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorrowCommandTest {
    @Test
    public void execute_borrowCommand() throws InvMgrException {
        Ui ui = new Ui();
        ArrayList<Item> itemArrayList = new ArrayList<>();
        ItemList itemList = new ItemList(itemArrayList);
        Item item1 = new Item("Markers", 3, "Drawing");
        itemList.addItem(item1);

        LocalDate startDate = LocalDate.parse("2022-03-21");
        LocalDate endDate = LocalDate.parse("2022-04-02");
        BorrowCommand c = new BorrowCommand(0, startDate, endDate, "John Smith");
        c.execute(itemList, ui);
    }

    /**
     * Test if a BorrowCommand contains the same attributes as another BorrowCommand.
     */
    @Test
    public void equals_borrowCommand() {
        LocalDate startDate = LocalDate.parse("2022-03-21");
        LocalDate endDate = LocalDate.parse("2022-04-02");
        BorrowCommand c1 = new BorrowCommand(0, startDate, endDate, "John Smith");
        BorrowCommand c2 = new BorrowCommand(0, startDate, endDate, "John Smith");

        assertEquals(c1, c2);
    }
}
