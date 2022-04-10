package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.InvalidNewYearException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AgeIncreaseTest {

    @Test
    public void extraWordIncreaseAgeCommand_exceptionThrown() {
        assertThrows(InvalidNewYearException.class, () ->
                new CommandParser().parse("is a new year hello  "));
    }
}
