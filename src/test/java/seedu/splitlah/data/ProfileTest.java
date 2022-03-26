package seedu.splitlah.data;

import org.junit.jupiter.api.Test;
import seedu.splitlah.command.Command;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ProfileTest {

    Manager manager = new Manager();

    /**
     * Checks if method returns true when a Session object with the specified session name is found.
     */
    @Test
    public void hasSessionName_inputContainsExistingSessionName_true() {
        String sessionOneArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob";
        Command createSessionOne = Parser.getCommand(sessionOneArgs);
        createSessionOne.run(manager);

        String sessionNameToTest = "Class outing";
        boolean isExists = manager.getProfile().hasSessionName(sessionNameToTest);
        assertTrue(isExists);
    }

    /**
     * Checks if method returns false when a Session object with the specified session name is not found.
     */
    @Test
    public void hasSessionName_inputContainsNonExistingSessionName_false() {
        String sessionOneArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob";
        Command createSessionOne = Parser.getCommand(sessionOneArgs);
        createSessionOne.run(manager);

        String sessionNameToTest = "School gathering";
        boolean isExists = manager.getProfile().hasSessionName(sessionNameToTest);
        assertFalse(isExists);
    }

    /**
     * Checks if method returns false when the list of session is empty.
     */
    @Test
    public void hasSessionName_sessionListEmpty_false() {
        String sessionNameToTest = "Class gathering";
        boolean isExists = manager.getProfile().hasSessionName(sessionNameToTest);
        assertFalse(isExists);
    }

    /**
     * Checks if method returns true when a Session object with the specified session unique identifier is found.
     */
    @Test
    public void hasSessionId_inputContainsExistingSessionId_true() {
        String sessionArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob";
        Command createSession = Parser.getCommand(sessionArgs);
        createSession.run(manager);

        int sessionIdToTest = 1;
        boolean isExists = manager.getProfile().hasSessionId(sessionIdToTest);
        assertTrue(isExists);
    }

    /**
     * Checks if method returns false when a Session object with the specified session unique identifier is not found.
     */
    @Test
    public void hasSessionId_inputContainsNonExistingSessionId_false() {
        String sessionArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob";
        Command createSession = Parser.getCommand(sessionArgs);
        createSession.run(manager);

        int sessionIdToTest = 10;
        boolean isExists = manager.getProfile().hasSessionId(sessionIdToTest);
        assertFalse(isExists);
    }

    /**
     * Checks if method returns false when the list of session is empty.
     */
    @Test
    public void hasSessionId_sessionListEmpty_false() {
        int sessionIdToTest = 1;
        boolean isExists = manager.getProfile().hasSessionId(sessionIdToTest);
        assertFalse(isExists);
    }

    /**
     * Checks if the correct Session object is properly returned when a Session object with
     * the specified session unique identifier is found.
     */
    @Test
    public void getSession_validSessionId_sessionReturned() {
        String sessionArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob";
        Command createSession = Parser.getCommand(sessionArgs);
        createSession.run(manager);

        int sessionIdToTest = 1;
        try {
            Session retrievedSession = manager.getProfile().getSession(sessionIdToTest);
            assertEquals(sessionIdToTest, retrievedSession.getSessionId());
        } catch (InvalidDataException invalidDataException) {
            fail();
        }
    }

    /**
     * Checks if an exception is properly thrown when a Session object with
     * a specified session unique identifier is not found.
     */
    @Test
    public void getSession_invalidSessionId_InvalidDataExceptionThrown() {
        String sessionArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob";
        Command createSession = Parser.getCommand(sessionArgs);
        createSession.run(manager);

        int sessionIdToTest = 10;
        try {
            manager.getProfile().getSession(sessionIdToTest);
            fail();
        } catch (InvalidDataException invalidDataException) {
            assertEquals(invalidDataException.getMessage(), Message.ERROR_PROFILE_SESSION_NOT_IN_LIST);
        }
    }
}
