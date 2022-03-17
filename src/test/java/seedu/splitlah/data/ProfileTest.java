package seedu.splitlah.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


class ProfileTest {

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
     * Checks if method returns true when a session name exists within the list of session.
     */
    @Test
    public void hasSessionName_inputContainsExistingSessionName_true() {
        String sessionNameToTest = "Class outing";
        boolean isExists = manager.getProfile().hasSessionName(sessionNameToTest);
        assertEquals(true, isExists);
    }

    /**
     * Checks if method returns false when a session name does not exist within the list of session.
     */
    @Test
    public void hasSessionName_inputContainsNonExistingSessionName_false() {
        String sessionNameToTest = "School gathering";
        boolean isExists = manager.getProfile().hasSessionName(sessionNameToTest);
        assertEquals(false, isExists);
    }

    /**
     * Checks if method returns true when a session unique identifier exists within the list of session.
     */
    @Test
    public void hasSessionId_inputContainsExistingSessionId_true() {
        int sessionIdToTest = 1;
        boolean isExists = manager.getProfile().hasSessionId(sessionIdToTest);
        assertEquals(true, isExists);
    }

    /**
     * Checks if method returns false when a session unique identifier does not exist within the list of session.
     */
    @Test
    public void hasSessionId_inputContainsNonExistingSessionId_false() {
        int sessionIdToTest = 10;
        boolean isExists = manager.getProfile().hasSessionId(sessionIdToTest);
        assertEquals(false, isExists);
    }


    /**
     * Checks if the session is returned when a session unique identifier is in the list of sessions.
     */
    @Test
    public void getSession_validSessionId_sessionReturned() {
        int sessionIdToTest = 1;
        try {
            Session retrievedSession = manager.getProfile().getSession(sessionIdToTest);
            assertEquals(sessionIdToTest, retrievedSession.getSessionId());
        } catch (InvalidDataException invalidDataException) {
            fail();
        }
    }

    /**
     * Checks if an exception is properly thrown when a session unique identifier is not in the list of sessions.
     */
    @Test
    public void getSession_invalidSessionId_exceptionThrown() {
        int sessionIdToTest = 10;
        try {
            manager.getProfile().getSession(sessionIdToTest);
            fail();
        } catch (InvalidDataException invalidDataException) {
            assertEquals(invalidDataException.getMessage(), Message.ERROR_PROFILE_SESSION_NOT_IN_LIST);
        }
    }
}
