package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.roomcommand.CheckRoomByCatCommand;
import seedu.duke.exceptions.InvalidCategoryException;

import static org.junit.jupiter.api.Assertions.assertThrows;


class CheckRoomByCatCommandTest {
    @Test
    void execute_invalidCat_expectException()  {
        //RoomList inputRoomList = new RoomList();
        String inputCommand = "president";
        assertThrows(InvalidCategoryException.class,
            () -> new CheckRoomByCatCommand(inputCommand));
    }

}