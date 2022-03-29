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
     * Checks if the hasSessionName method returns true when a Session object with the specified session name is found.
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
     * Checks if the hasSessionName method returns false when a Session object
     * with the specified session name is not found.
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
     * Checks if the hasSessionName method returns false when the list of session is empty.
     */
    @Test
    public void hasSessionName_sessionListEmpty_false() {
        String sessionNameToTest = "Class gathering";
        boolean isExists = manager.getProfile().hasSessionName(sessionNameToTest);
        assertFalse(isExists);
    }

    /**
     * Checks if the hasSessionId method returns true when a Session object
     * with the specified session unique identifier is found.
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
     * Checks if the hasSessionId method returns false when a Session object
     * with the specified session unique identifier is not found.
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
     * Checks if the hasSessionId method returns false when the list of session is empty.
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
     * Checks if an InvalidDataException with the correct message is properly thrown when a Session object with
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

    /**
     * Checks if an InvalidDataException with the correct message is properly thrown list of session is empty.
     */
    @Test
    public void getSession_sessionListEmpty_InvalidDataExceptionThrown() {
        int sessionIdToTest = 1;
        try {
            Session retrievedSession = manager.getProfile().getSession(sessionIdToTest);
            fail();
        } catch (InvalidDataException invalidDataException) {
            assertEquals(invalidDataException.getMessage(), Message.ERROR_PROFILE_SESSION_LIST_EMPTY);
        }
    }

    /**
     * Checks if the hasGroupName method returns true when a Group object with the specified group name is found.
     */
    @Test
    public void hasGroupName_inputContainsExistingGroupName_true() {
        String groupArgs = "group /create /n Project members /pl Alice Bob Charlie";
        Command createGroup = Parser.getCommand(groupArgs);
        createGroup.run(manager);

        String groupNameToTest = "Project members";
        boolean isExists = manager.getProfile().hasGroupName(groupNameToTest);
        assertTrue(isExists);
    }

    /**
     * Checks if the hasGroupName method returns false when a Group object with the specified group name is not found.
     */
    @Test
    public void hasGroupName_inputContainsNonExistingGroupName_false() {
        String groupArgs = "group /create /n Project members /pl Alice Bob Charlie";
        Command createGroup = Parser.getCommand(groupArgs);
        createGroup.run(manager);

        String groupNameToTest = "High school Friends";
        boolean isExists = manager.getProfile().hasGroupName(groupNameToTest);
        assertFalse(isExists);
    }

    /**
     * Checks if the hasGroupName method returns false when the list of group is empty.
     */
    @Test
    public void hasGroupName_groupListEmpty_false() {
        String groupNameToTest = "Project members";
        boolean isExists = manager.getProfile().hasGroupName(groupNameToTest);
        assertFalse(isExists);
    }

    /**
     * Checks if the hasGroupId method returns true when a Group object
     * with the specified group unique identifier is found.
     */
    @Test
    public void hasGroupId_inputContainsExistingGroupId_true() {
        String groupArgs = "group /create /n Project members /pl Alice Bob Charlie";
        Command createGroup = Parser.getCommand(groupArgs);
        createGroup.run(manager);

        int groupIdToTest = 1;
        boolean isExists = manager.getProfile().hasGroupId(groupIdToTest);
        assertTrue(isExists);
    }

    /**
     * Checks if the hasGroupId method returns false when a Group object
     * with the specified group unique identifier is not found.
     */
    @Test
    public void hasGroupId_inputContainsNonExistingGroupId_false() {
        String groupArgs = "group /create /n Project members /pl Alice Bob Charlie";
        Command createGroup = Parser.getCommand(groupArgs);
        createGroup.run(manager);

        int groupIdToTest = 10;
        boolean isExists = manager.getProfile().hasGroupId(groupIdToTest);
        assertFalse(isExists);
    }

    /**
     * Checks if the hasGroupId method returns false when the list of group is empty.
     */
    @Test
    public void hasGroupId_groupListEmpty_false() {
        int groupIdToTest = 1;
        boolean isExists = manager.getProfile().hasGroupId(groupIdToTest);
        assertFalse(isExists);
    }

    /**
     * Checks if the correct Group object is properly returned when a Group object with
     * the specified group unique identifier is found.
     */
    @Test
    public void getGroup_validGroupId_groupReturned() {
        String groupArgs = "group /create /n Project members /pl Alice Bob Charlie";
        Command createGroup = Parser.getCommand(groupArgs);
        createGroup.run(manager);

        int groupIdToTest = 1;
        try {
            Group retrievedGroup = manager.getProfile().getGroup(groupIdToTest);
            assertEquals(groupIdToTest, retrievedGroup.getGroupId());
        } catch (InvalidDataException invalidDataException) {
            fail();
        }
    }

    /**
     * Checks if an InvalidDataException with the correct message is properly thrown when a Group object with
     * a specified group unique identifier is not found.
     */
    @Test
    public void getGroup_invalidGroupId_InvalidDataExceptionThrown() {
        String groupArgs = "group /create /n Project members /pl Alice Bob Charlie";
        Command createGroup = Parser.getCommand(groupArgs);
        createGroup.run(manager);

        int groupIdToTest = 10;
        try {
            manager.getProfile().getGroup(groupIdToTest);
            fail();
        } catch (InvalidDataException invalidDataException) {
            assertEquals(invalidDataException.getMessage(), Message.ERROR_PROFILE_GROUP_NOT_IN_LIST);
        }
    }

    /**
     * Checks if an InvalidDataException with the correct message is properly thrown when list of group is empty.
     */
    @Test
    public void getGroup_groupListEmpty_InvalidDataExceptionThrown() {
        int groupIdToTest = 1;
        try {
            Group retrievedGroup = manager.getProfile().getGroup(groupIdToTest);
            fail();
        } catch (InvalidDataException invalidDataException) {
            assertEquals(invalidDataException.getMessage(), Message.ERROR_PROFILE_GROUP_LIST_EMPTY);
        }
    }

    /**
     * Checks if sessionId is incremented when getNewSessionId method is called.
     */
    @Test
    public void getNewSessionId_functionCalled_sessionIdIncrementedByOne() {
        int sessionIdToTest = manager.getProfile().getSessionIdTracker();
        manager.getProfile().getNewSessionId();
        assertEquals(sessionIdToTest + 1, manager.getProfile().getSessionIdTracker());
    }

    /**
     * Checks if activityId is incremented when getNewActivityId method is called.
     */
    @Test
    public void getNewActivityId_functionCalled_activityIdIncrementedByOne() {
        int activityIdToTest = manager.getProfile().getActivityIdTracker();
        manager.getProfile().getNewActivityId();
        assertEquals(activityIdToTest + 1, manager.getProfile().getActivityIdTracker());
    }

    /**
     * Checks if groupId is incremented when getNewGroupId method is called.
     */
    @Test
    public void getNewGroupId_functionCalled_groupIdIncrementedByOne() {
        int groupIdToTest = manager.getProfile().getGroupIdTracker();
        manager.getProfile().getNewGroupId();
        assertEquals(groupIdToTest + 1, manager.getProfile().getGroupIdTracker());
    }
}
