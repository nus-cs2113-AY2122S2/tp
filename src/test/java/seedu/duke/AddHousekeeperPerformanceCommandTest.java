package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import seedu.duke.command.Command;
import seedu.duke.command.housekeepercommands.AddHousekeeperPerformanceCommand;
import seedu.duke.exceptions.EmptyHousekeeperPerformanceNameException;
import seedu.duke.exceptions.InvalidHousekeeperPerformanceRatingException;
import seedu.duke.exceptions.InvalidHousekeeperPerformanceNameException;
import seedu.duke.exceptions.InvalidCommandException;
import seedu.duke.exceptions.DuplicateCommandException;
import seedu.duke.housekeeperperformancelists.HousekeeperPerformance;

/**
 * Class to test "add performance" command for a housekeeper.
 */

public class AddHousekeeperPerformanceCommandTest {
    @Test
    public void commandParser_addCommandWithNonEmptyNameAndRating_success()
            throws Exception {
        CommandParser parser = new CommandParser();

        Command command1 = parser.parse("add performance Bob Jones / 3");
        AddHousekeeperPerformanceCommand addHousekeeperPerformanceCommand = (AddHousekeeperPerformanceCommand) command1;
        HousekeeperPerformance housekeeperPerformance = addHousekeeperPerformanceCommand.getHousekeeperPerformance();
        assertEquals("bob jones", housekeeperPerformance.getName());
        assertEquals(3, housekeeperPerformance.getRating());
    }

    @Test
    public void commandParser_addCommandWithEmptyHousekeeperPerformanceName_exceptionThrown() {
        assertThrows(EmptyHousekeeperPerformanceNameException.class, ()
            -> new CommandParser().parse("add performance / 4"));
    }

    @Test
    public void commandParser_addCommandWithEmptyHousekeeperPerformanceRating_exceptionThrown() {
        assertThrows(InvalidHousekeeperPerformanceRatingException.class, ()
            -> new CommandParser().parse("add performance Bob / "));
    }

    @Test
    public void commandParser_addCommandWithNoSlash_exceptionThrown() {
        assertThrows(InvalidCommandException.class, ()
            -> new CommandParser().parse("add performance Bob 5"));
    }

    @Test
    public void commandParser_addCommandWithInvalidPerformanceRating_exceptionThrown() {
        assertThrows(InvalidHousekeeperPerformanceRatingException.class, ()
            -> new CommandParser().parse("add performance Joe / -1"));
    }

    @Test
    public void commandParser_addCommandWithHousekeeperPerformanceName_exceptionThrown() {
        assertThrows(InvalidHousekeeperPerformanceNameException.class, ()
            -> new CommandParser().parse("add performance Steve the 3rd / 2"));
    }

    @Test
    public void commandParser_addCommandWithDuplicatePerformanceCommands_exceptionThrown() {
        assertThrows(DuplicateCommandException.class, ()
            -> new CommandParser().parse("add performance add performance Steve / 2"));
    }
}
