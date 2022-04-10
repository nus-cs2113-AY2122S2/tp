package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.housekeepercommands.DeleteHousekeeperCommand;
import seedu.duke.exceptions.EmptyNameException;
import seedu.duke.exceptions.DuplicateCommandException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteHousekeeperTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();

    @Test
    public void commandParser_DeleteCommandName_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("delete housekeeper Susan");
        DeleteHousekeeperCommand deleteHousekeeperCommand = (DeleteHousekeeperCommand) command;
        assertEquals("susan", deleteHousekeeperCommand.getName());
    }

    @Test
    public void emptyName_exceptionThrown() {
        assertThrows(EmptyNameException.class, () -> new CommandParser().parse("Delete Housekeeper "));
    }

    @Test
    public void duplicateCommand_exceptionThrown() {
        assertThrows(DuplicateCommandException.class, () ->
                new CommandParser().parse("Delete Housekeeper delete housekeeper "));
    }

}
