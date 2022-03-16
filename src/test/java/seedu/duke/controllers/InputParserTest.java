package seedu.duke.controllers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.OperationTerminationException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {
    private static final InputStream sysInBackup = System.in;

    @AfterAll
    static void tearDown() {
        System.setIn(sysInBackup);
    }

    @Test
    void getString() {
        ByteArrayInputStream in = new ByteArrayInputStream(
            String.format("non-terminating string\n%s", InputParser.TERMINATOR).getBytes());
        System.setIn(in);
        assertDoesNotThrow(() -> InputParser.getString("Enter string: "));
        assertThrows(OperationTerminationException.class, () -> InputParser.getString("Enter string: "));
    }
}
