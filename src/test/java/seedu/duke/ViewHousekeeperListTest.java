package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidViewHousekeeperException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ViewHousekeeperListTest {

    @Test
    public void viewListCommand_exceptionThrown() {
        assertThrows(InvalidViewHousekeeperException.class, () ->
                new CommandParser().parse("view Recorded Housekeepers xx "));
    }
}
