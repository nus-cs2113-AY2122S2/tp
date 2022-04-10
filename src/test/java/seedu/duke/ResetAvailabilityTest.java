package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidNewWeekException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ResetAvailabilityTest {

    @Test
    public void extraWords_exceptionThrown() {
        assertThrows(InvalidNewWeekException.class, () -> new CommandParser().parse("is a new week yay "));
    }
}
