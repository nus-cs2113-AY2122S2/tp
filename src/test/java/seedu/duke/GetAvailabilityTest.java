package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.housekeepercommands.DeleteHousekeeperCommand;
import seedu.duke.command.housekeepercommands.GetAvailableHousekeeperCommand;
import seedu.duke.exceptions.DuplicateCommandException;
import seedu.duke.exceptions.EmptyDayException;
import seedu.duke.exceptions.InvalidDayException;
import seedu.duke.exceptions.InvalidNewWeekException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetAvailabilityTest {

    @Test
    public void commandParser_getAvailable_success() throws Exception {
        CommandParser parser = new CommandParser();
        Command command = parser.parse("get available on 1");
        GetAvailableHousekeeperCommand getAvailableHousekeeperCommand = (GetAvailableHousekeeperCommand) command;
        assertEquals(1, getAvailableHousekeeperCommand.getSearchDay() );
    }

    @Test
    public void duplicateAvailableDayCommand_exceptionThrown() {
        assertThrows(DuplicateCommandException.class, () ->
                new CommandParser().parse("get available on get available on  "));
    }

    @Test
    public void emptyDay_exceptionThrown() {
        assertThrows(EmptyDayException.class, () ->
                new CommandParser().parse("get available on "));
    }

    @Test
    public void dayGivenNotInteger_exceptionThrown() {
        assertThrows(InvalidDayException.class, () ->
                new CommandParser().parse("get available on x"));
    }

    @Test
    public void dayGivenNotInLimit_exceptionThrown() {
        assertThrows(InvalidDayException.class, () ->
                new CommandParser().parse("get available on 100"));
    }
}
