package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import seedu.duke.command.Command;
import seedu.duke.command.customercommands.AddSatisfactionCommand;
import seedu.duke.command.itemcommands.AddItemCommand;
import seedu.duke.exceptions.*;
import seedu.duke.itemlists.Item;
import seedu.duke.itemlists.ItemList;
import seedu.duke.satisfactionlists.Satisfaction;
import seedu.duke.satisfactionlists.SatisfactionList;

import java.io.IOException;


public class AddSatisfactionCommandTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();
    private static final int INDEX_OF_FRED = 2;
    private static final int INDEX_OF_ANGELA = 2;
    private static final int INDEX_OF_STEVE = 2;

    @BeforeEach
    //Sets up the pretesting environment by creating a SatisfactionList with 2 Satisfaction objects.
    public void pretestingSetUp() throws HotelLiteManagerException, IOException {
        listContainer = new ListContainer();
        ui = new Ui();
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        Satisfaction testSatisfactionBob = new Satisfaction("Bob", 3);
        satisfactionList.addSatisfaction(testSatisfactionBob);
        Satisfaction testSatisfactionJoe = new Satisfaction("Joe", 4);
        satisfactionList.addSatisfaction(testSatisfactionJoe);
    }

    @Test
    public void execute_NonEmptyCustomerNameAndRating_success() throws HotelLiteManagerException, IOException {
        AddSatisfactionCommand addSatisfactionCommand= new AddSatisfactionCommand("Fred / 5");
        addSatisfactionCommand.execute(listContainer, ui);
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        Satisfaction satisfaction = satisfactionList.getSatisfaction(INDEX_OF_FRED);
        assertEquals("Fred", satisfaction.getCustomerName());
        assertEquals(5, satisfaction.getSatisfactionValue());
    }

    @Test
    public void execute_satisfactionValueWithTheUpperBoundaryValue_success() throws HotelLiteManagerException, IOException {
        AddSatisfactionCommand addSatisfactionCommand = new AddSatisfactionCommand("Angela / 5");
        addSatisfactionCommand.execute(listContainer, ui);
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        Satisfaction satisfaction = satisfactionList.getSatisfaction(INDEX_OF_ANGELA);
        assertEquals("angela", satisfaction.getCustomerName());
        assertEquals(5, satisfaction.getSatisfactionValue());
    }


//    @Test
//    public void commandParser_addCommandWithNonEmptySatisfactionCustomerAndSatisfactionValue_success()
//            throws Exception {
//        CommandParser parser = new CommandParser();
//
//        Command command1 = parser.parse("add satisfaction Bob Jones / 3");
//        AddSatisfactionCommand addSatisfactionCommand1 = (AddSatisfactionCommand) command1;
//        Satisfaction satisfaction1 = addSatisfactionCommand1.getSatisfaction();
//        assertEquals("bob jones", satisfaction1.getCustomerName());
//        assertEquals(3, satisfaction1.getSatisfactionValue());
//
//        Command command2 = parser.parse("add satisfaction Fred / 4");
//        AddSatisfactionCommand addSatisfactionCommand2 = (AddSatisfactionCommand) command2;
//        Satisfaction satisfaction2 = addSatisfactionCommand2.getSatisfaction();
//        assertEquals("fred", satisfaction2.getCustomerName());
//        assertEquals(4, satisfaction2.getSatisfactionValue());
//    }

    @Test
    public void commandParser_addCommandWithEmptySatisfactionCustomer_exceptionThrown() {
        assertThrows(EmptySatisfactionCustomerException.class, ()
            -> new CommandParser().parse("add satisfaction / 4"));
    }

    @Test
    public void commandParser_addCommandWithEmptySatisfactionValue_exceptionThrown() {
        assertThrows(EmptySatisfactionValueException.class, ()
            -> new CommandParser().parse("add satisfaction Bob / "));
    }

    @Test
    public void commandParser_addCommandWithNoSlash_exceptionThrown() {
        assertThrows(InvalidCommandException.class, ()
            -> new CommandParser().parse("add satisfaction Bob 5"));
    }

    @Test
    public void commandParser_addCommandWithNegativeSatisfactionValue_exceptionThrown() {
        assertThrows(InvalidSatisfactionValueException.class, ()
            -> new CommandParser().parse("add satisfaction Joe / -1"));
    }

    @Test
    public void commandParser_addCommandWithTooHighSatisfactionValue_exceptionThrown() {
        assertThrows(InvalidSatisfactionValueException.class, ()
                -> new CommandParser().parse("add satisfaction Joe / 6"));
    }



}
