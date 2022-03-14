package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import seedu.splitlah.data.Manager;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.*;

class SessionDeleteCommandTest {

    Manager manager = new Manager();

    /**
     * Creates 2 sessions that is stored and managed by the Manager object.
     */
    @BeforeEach
    void setUp() {
        String sessionOneArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob";
        String sessionTwoArgs = "session /create /n Family gathering /d 20-02-2022  /pl Eves Mallory";
        Command createSessionOne = Parser.getCommand(sessionOneArgs);
        createSessionOne.run(manager);
        Command createSessionTwo = Parser.getCommand(sessionTwoArgs);
        createSessionTwo.run(manager);
    }

    /**
     * Checks if session is deleted with a missing delimiter.
     */
    @Test
    public void prepare_hasMissingDelimiter_InvalidCommand() {
        String argsMissingSidDelimiter = "session /delete";
        Command sessionWithMissingSidDelimiter = Parser.getCommand(argsMissingSidDelimiter);
        assertEquals(InvalidCommand.class, sessionWithMissingSidDelimiter.getClass());
    }

}