package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.roomcommand.CheckRoomByLevelCommand;
import seedu.duke.exceptions.HotelLiteManagerException;
import seedu.duke.exceptions.InvalidLevelException;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckLevelTest {
    private ListContainer listContainer;
    private Ui ui = new Ui();

    @BeforeEach
    public void pretestingSetUp() throws HotelLiteManagerException, IOException {
        listContainer = new ListContainer();
        ui = new Ui();
    }

    @Test
    public void execute_invalidLevel_exceptionThrown() throws HotelLiteManagerException {
        CheckRoomByLevelCommand checkRoomByLevelCommand1 = new CheckRoomByLevelCommand("0");
        CheckRoomByLevelCommand checkRoomByLevelCommand2 = new CheckRoomByLevelCommand("5");
        assertThrows(InvalidLevelException.class,() -> checkRoomByLevelCommand1.execute(listContainer,ui));
        assertThrows(InvalidLevelException.class,() -> checkRoomByLevelCommand2.execute(listContainer,ui));
    }

}
