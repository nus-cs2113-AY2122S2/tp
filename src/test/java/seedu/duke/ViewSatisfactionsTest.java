package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.customercommands.ViewSatisfactionsCommand;
import seedu.duke.command.customercommands.ViewAverageSatisfactionCommand;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.satisfactionlists.Satisfaction;
import seedu.duke.satisfactionlists.SatisfactionList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ViewSatisfactionsTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();
    private static final int INDEX_OF_MAN = 2;
    private static final int INDEX_OF_SALLY = 1;
    private static final int INDEX_OF_JAMES = 0;

    @BeforeEach
    public void pretestingSetUp() throws HotelLiteManagerException, IOException {
        listContainer = new ListContainer();
        ui = new Ui();
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        satisfactionList.clearSatisfactionList();
        Satisfaction satisfactionJames = new Satisfaction("James", 3);
        satisfactionList.addSatisfaction(satisfactionJames);
        Satisfaction satisfactionSally = new Satisfaction("Sally", 4);
        satisfactionList.addSatisfaction(satisfactionSally);
        Satisfaction satisfactionMan = new Satisfaction("Man", 5);
        satisfactionList.addSatisfaction(satisfactionMan);
    }

    @Test
    public void viewSatisfactionList_success() throws HotelLiteManagerException, IOException {
        ViewSatisfactionsCommand viewSatisfactionsCommand =
                new ViewSatisfactionsCommand();
        viewSatisfactionsCommand.execute(listContainer, ui);
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();

        assertEquals("James", satisfactionList.getSatisfaction(INDEX_OF_JAMES).getCustomerName());
        assertEquals("Sally", satisfactionList.getSatisfaction(INDEX_OF_SALLY).getCustomerName());
        assertEquals("Man", satisfactionList.getSatisfaction(INDEX_OF_MAN).getCustomerName());

        assertEquals(3, satisfactionList.getSatisfaction(INDEX_OF_JAMES).getSatisfactionValue());
        assertEquals(4, satisfactionList.getSatisfaction(INDEX_OF_SALLY).getSatisfactionValue());
        assertEquals(5, satisfactionList.getSatisfaction(INDEX_OF_MAN).getSatisfactionValue());

        ViewAverageSatisfactionCommand viewAverageSatisfactionCommand = new ViewAverageSatisfactionCommand();
        viewAverageSatisfactionCommand.execute(listContainer, ui);
        assertEquals(4.0, satisfactionList.calculateAverageSatisfaction());

        satisfactionList.clearSatisfactionList();
    }

    @Test
    public void viewSatisfactionListCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () ->
                new CommandParser().parse("view satisfactions blah blah "));
    }
}
