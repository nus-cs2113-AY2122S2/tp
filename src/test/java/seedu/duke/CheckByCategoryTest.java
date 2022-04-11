package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.roomcommand.CheckRoomByCatCommand;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidCategoryException;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckByCategoryTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();
    private static final int INDEX_OF_204 = 6;
    private static final int INDEX_OF_301 = 7;

    @BeforeEach
    public void pretestingSetUp() throws HotelLiteManagerException, IOException {
        listContainer = new ListContainer();
        ui = new Ui();
    }

    @Test
    public void execute_validCategory_success() throws HotelLiteManagerException, IOException {
        CommandParser commandParser = new CommandParser();
        Command cmd = commandParser.parse("check category queen");
        assertEquals("Queen",listContainer.getRoomList().getRoomList().get(INDEX_OF_204).getType().toString());
        assertEquals("Queen",listContainer.getRoomList().getRoomList().get(INDEX_OF_301).getType().toString());
    }

    @Test
    public void execute_invalidCategory_exceptionThrown() throws HotelLiteManagerException {
        assertThrows(InvalidCategoryException.class,() -> new CheckRoomByCatCommand("abc"));
    }

}
