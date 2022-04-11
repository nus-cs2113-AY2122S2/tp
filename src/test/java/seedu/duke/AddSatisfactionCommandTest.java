package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import seedu.duke.command.customercommands.AddSatisfactionCommand;
import seedu.duke.exceptions.EmptySatisfactionValueException;
import seedu.duke.exceptions.EmptySatisfactionCustomerException;
import seedu.duke.exceptions.InvalidSatisfactionValueException;
import seedu.duke.exceptions.InvalidSatisfactionCustomerNameException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.DuplicateCommandException;
import seedu.duke.satisfactionlists.Satisfaction;
import seedu.duke.satisfactionlists.SatisfactionList;

import java.io.IOException;


public class AddSatisfactionCommandTest {
    private ListContainer listContainer;
    private Ui ui;
    private static final int INDEX_OF_FRED = 0;
    private static final int INDEX_OF_ANGELA = 0;
    private static final int INDEX_OF_STEVE = 0;

    @BeforeEach
    //Sets up the pretesting environment by creating an empty SatisfactionList
    public void pretestingSetUp() throws HotelLiteManagerException, IOException {
        listContainer = new ListContainer();
        ui = new Ui();
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        satisfactionList.clearSatisfactionList();

    }

    @Test
    public void execute_NonEmptyCustomerNameAndRating_success() throws HotelLiteManagerException, IOException {
        AddSatisfactionCommand addSatisfactionCommand = new AddSatisfactionCommand("Fred / 3");
        addSatisfactionCommand.execute(listContainer, ui);
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        Satisfaction satisfaction = satisfactionList.getSatisfaction(INDEX_OF_FRED);
        assertEquals("fred", satisfaction.getCustomerName());
        assertEquals(3, satisfaction.getSatisfactionValue());
        satisfactionList.clearSatisfactionList();
    }

    @Test
    public void execute_satisfactionValueWithUpperBoundaryValue_success() throws HotelLiteManagerException,
            IOException {
        AddSatisfactionCommand addSatisfactionCommand = new AddSatisfactionCommand("Angela / 5");
        addSatisfactionCommand.execute(listContainer, ui);
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        Satisfaction satisfaction = satisfactionList.getSatisfaction(INDEX_OF_ANGELA);
        assertEquals("angela", satisfaction.getCustomerName());
        assertEquals(5, satisfaction.getSatisfactionValue());
        satisfactionList.clearSatisfactionList();
    }

    @Test
    public void execute_satisfactionValueWithLowerBoundaryValue_success() throws HotelLiteManagerException,
            IOException {
        AddSatisfactionCommand addSatisfactionCommand = new AddSatisfactionCommand("Steve / 5");
        addSatisfactionCommand.execute(listContainer, ui);
        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        Satisfaction satisfaction = satisfactionList.getSatisfaction(INDEX_OF_STEVE);
        assertEquals("steve", satisfaction.getCustomerName());
        assertEquals(5, satisfaction.getSatisfactionValue());
        satisfactionList.clearSatisfactionList();
    }

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

    @Test
    public void commandParser_addCommandWithTooManySlashes_exceptionThrown() {
        assertThrows(InvalidCommandException.class, ()
            -> new CommandParser().parse("add satisfaction Joe / 3 / 3"));
    }

    @Test
    public void commandParser_addCommandWithDuplicateCommands_exceptionThrown() {
        assertThrows(DuplicateCommandException.class, ()
            -> new CommandParser().parse("add satisfaction add satisfaction Joe / 3"));
    }

    @Test
    public void commandParser_addCommandWithInvalidCustomerName_exceptionThrown() {
        assertThrows(InvalidSatisfactionCustomerNameException.class, ()
            -> new CommandParser().parse("add satisfaction Joe 3213 / 3"));
    }

}

