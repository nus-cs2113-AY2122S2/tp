package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.parser.IncompleteCommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ModificationCommandTest {
    ModificationCommand modificationCommand;

    @Test
    void prepareModification_missingSlashDelimiter_assertionErrorThrown() throws AssertionError {
        ArrayList<String> testArrayList = new ArrayList<>(Collections.singleton("thiswillnotwork"));
        try {
            modificationCommand = new ModificationCommand(testArrayList);
            modificationCommand.prepareModification();
        } catch (AssertionError error) {
            assertEquals("Each args will need to include minimally a '/' to split arg and value upon", error.getMessage());
        }
    }

    @Test
    void prepareModification_missingSerialNumber_exceptionThrown() throws IncompleteCommandException {
        ArrayList<String> testArrayList = new ArrayList<>(Arrays.asList(
                "n/Speaker B", "t/SPEAKER", "c/1000", "pf/Loud Technologies", "pd/2022-02-23"));
        UpdateCommand updateCommand = new UpdateCommand(testArrayList);
        CommandResult expectedResult = new CommandResult("Serial Number is required to run this command");
        assertEquals(expectedResult, updateCommand.execute());
    }

    @Test
    void prepareModification_mostRecentArgValueUsed_success() throws IncompleteCommandException {
        ArrayList<String> testArrayList = new ArrayList<>(Arrays.asList(
                "s/S1404115ASF", "n/Speaker B", "n/Speaker A"));
        ModificationCommand expectedCommand = new ModificationCommand(new ArrayList<>(
                Arrays.asList("s/S1404115ASF", "n/Speaker A")
        ));
        ModificationCommand actualCommand = new ModificationCommand(testArrayList);
        actualCommand.prepareModification();
        assertEquals(expectedCommand, actualCommand);
    }
}