package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import seedu.duke.command.Command;
import seedu.duke.command.customercommands.AddSatisfactionCommand;
import seedu.duke.command.customercommands.ViewAverageSatisfactionCommand;
import seedu.duke.exceptions.EmptySatisfactionCustomerException;
import seedu.duke.satisfactionlists.Satisfaction;
import seedu.duke.satisfactionlists.SatisfactionList;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.storage.SatisfactionListFileManager;

/**
 * Class to test "view average satisfaction" command.
 * NOTE: There is no direct way to test this command, since its functionality is handled by
 * the "calculateAverageSatisfaction" method in the SatisfactionList class.
 */

public class ViewAverageSatisfactionCommandTest {
    @Test
    public void commandParser_calculateAverageSatisfaction_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command1 = parser.parse("add satisfaction Ted / 3");
        Command command2 = parser.parse("add satisfaction Ned / 5");
        Command command3 = parser.parse("add satisfaction Chad / 4");

        AddSatisfactionCommand addSatisfactionCommand1 = (AddSatisfactionCommand) command1;
        AddSatisfactionCommand addSatisfactionCommand2 = (AddSatisfactionCommand) command2;
        AddSatisfactionCommand addSatisfactionCommand3 = (AddSatisfactionCommand) command3;

        ListContainer listContainer = new ListContainer();
        Ui ui = new Ui();
        addSatisfactionCommand1.execute(listContainer, ui);
        addSatisfactionCommand2.execute(listContainer, ui);
        addSatisfactionCommand3.execute(listContainer, ui);

        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        assertEquals(4.0, satisfactionList.calculateAverageSatisfaction());

        satisfactionList.clearSatisfactionList();
        SatisfactionListFileManager satisfactionListFileManager = new SatisfactionListFileManager();
        satisfactionListFileManager.save(satisfactionList);
    }

    @Test
    public void commandParser_viewAverageSatisfaction_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command1 = parser.parse("add satisfaction Ted / 3");
        Command command2 = parser.parse("add satisfaction Ned / 5");
        Command command3 = parser.parse("add satisfaction Chad / 4");

        AddSatisfactionCommand addSatisfactionCommand1 = (AddSatisfactionCommand) command1;
        AddSatisfactionCommand addSatisfactionCommand2 = (AddSatisfactionCommand) command2;
        AddSatisfactionCommand addSatisfactionCommand3 = (AddSatisfactionCommand) command3;

        ListContainer listContainer = new ListContainer();
        Ui ui = new Ui();
        addSatisfactionCommand1.execute(listContainer, ui);
        addSatisfactionCommand2.execute(listContainer, ui);
        addSatisfactionCommand3.execute(listContainer, ui);

        Command command4 = parser.parse("view average satisfaction");
        ViewAverageSatisfactionCommand viewAverageSatisfactionCommand = (ViewAverageSatisfactionCommand) command4;
        viewAverageSatisfactionCommand.execute(listContainer, ui);

        SatisfactionList satisfactionList = listContainer.getSatisfactionList();
        satisfactionList.clearSatisfactionList();
        SatisfactionListFileManager satisfactionListFileManager = new SatisfactionListFileManager();
        satisfactionListFileManager.save(satisfactionList);
    }

    @Test
    public void commandParser_invalidViewAverageSatisfactionCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, ()
            -> new CommandParser().parse("view average satisfaction blah blah"));
    }
}