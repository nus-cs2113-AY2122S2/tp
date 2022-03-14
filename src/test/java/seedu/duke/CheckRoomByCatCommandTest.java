package seedu.duke;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CheckRoomByCatCommandTest {
    @Test
    void execute_invalidCat_expectException()  {
        //RoomList inputRoomList = new RoomList();
        String inputCommand = "president";
        assertThrows(InvalidCategoryException.class,
            () -> new CheckRoomByCatCommand(inputCommand));
    }

}