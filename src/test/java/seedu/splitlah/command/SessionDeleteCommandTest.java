package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class SessionDeleteCommandTest {

    Manager manager = new Manager();

    /**
     * Creates two sessions that is stored and managed by the Manager object.
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
     * Checks if session is deleted successfully and removed from list of sessions.
     */
    @Test
    public void run_validCommand_sessionListSizeBecomesOne() {
        String userInput = "session /delete /sid 1";
        Command command = Parser.getCommand(userInput);

        // Check if a SessionDeleteCommand instance was returned.
        assertEquals(SessionDeleteCommand.class, command.getClass());
        command.run(manager);

        // Check if session was successfully removed from the list of sessions.
        assertEquals(1, manager.getProfile().getSessionList().size());

        // Check if session still exists.
        try {
            manager.getProfile().getSession(1);
            fail();
        } catch (InvalidDataException invalidDataException) {
            assertEquals(Message.ERROR_PROFILE_SESSION_NOT_IN_LIST, invalidDataException.getMessage());
        }
    }

    /**
     * Checks if session is deleted with an invalid session unique identifier.
     */
    @Test
    public void run_sessionDoesNotExists_sessionListSizeRemainsTwo() {
        String userInput = "session /delete /sid 3";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        assertEquals(2, manager.getProfile().getSessionList().size());
    }
}
