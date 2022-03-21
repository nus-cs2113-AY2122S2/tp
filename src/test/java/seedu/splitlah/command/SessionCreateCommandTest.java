package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Manager;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SessionCreateCommandTest {

    Manager manager = new Manager();

    /**
     * Creates 2 sessions and 1 group that is stored and managed by the Manager object.
     */
    @BeforeEach
    void setUp() {
        String sessionOneArgs = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob";
        String sessionTwoArgs = "session /create /n Family gathering /d 20-02-2022  /pl Eves Mallory";
        Command createSessionOne = Parser.getCommand(sessionOneArgs);
        createSessionOne.run(manager);
        Command createSessionTwo = Parser.getCommand(sessionTwoArgs);
        createSessionTwo.run(manager);
        String groupArgs = "group /create /n Class1 /pl Alice Bob";
        Command createGroup = Parser.getCommand(groupArgs);
        createGroup.run(manager);
    }

    /**
     * Checks if session is created with missing delimiters.
     */
    @Test
    public void prepare_hasMissingDelimiter_InvalidCommand() {
        // Case 1: Missing /n delimiter.
        String argsMissingNameDelimiter = "session /create /d 15-02-2022 /pl Alice Bob";
        Command sessionWithMissingNameDelimiter = Parser.getCommand(argsMissingNameDelimiter);
        assertEquals(InvalidCommand.class, sessionWithMissingNameDelimiter.getClass());

        // Case 2: Missing /d delimiter.
        String argsMissingDateDelimiter = "session /create /n Class gathering /pl Alice Bob";
        Command sessionWithMissingDateDelimiter = Parser.getCommand(argsMissingDateDelimiter);
        assertEquals(InvalidCommand.class, sessionWithMissingDateDelimiter.getClass());
    }

    /**
     * Checks if session is created when Person List and Group unique identifier delimiters are missing.
     */
    @Test
    public void prepare_hasMissingPersonListAndGidDelimiter_InvalidCommand() {
        String argsMissingPersonListAndGidDelimiters = "session /create /n Class gathering /d 15-02-2022";
        Command sessionWithMissingPersonListAndGidDelimiter = Parser.getCommand(argsMissingPersonListAndGidDelimiters);
        assertEquals(InvalidCommand.class,sessionWithMissingPersonListAndGidDelimiter.getClass());
    }

    /**
     * Checks if session is created with missing arguments.
     */
    @Test
    public void prepare_hasMissingArguments_InvalidCommand() {
        // Case 1: Missing Session name.
        String argsMissingNameArgument = "session /create /n /d 15-02-2022 /pl Alice Bob";
        Command sessionWithMissingNameArgument = Parser.getCommand(argsMissingNameArgument);
        assertEquals(InvalidCommand.class, sessionWithMissingNameArgument.getClass());

        // Case 2: Missing Session Date.
        String argsMissingDateArgument = "session /create /n Class gathering /d /pl Alice Bob";
        Command sessionWithMissingDateArgument = Parser.getCommand(argsMissingDateArgument);
        assertEquals(InvalidCommand.class, sessionWithMissingDateArgument.getClass());

        // Case 3: Missing List of persons.
        String argsMissingPersonListArgument = "session /create /n Class gathering /d 15-02-2022 /pl";
        Command sessionWithMissingPersonListArgument = Parser.getCommand(argsMissingPersonListArgument);
        assertEquals(InvalidCommand.class, sessionWithMissingPersonListArgument.getClass());

        // Case 4: Missing Group unique identifier.
        String argsMissingGidArgument = "session /create /n Class gathering /d 15-02-2022 /gid";
        Command sessionWithMissingGidArgument = Parser.getCommand(argsMissingGidArgument);
        assertEquals(InvalidCommand.class, sessionWithMissingGidArgument.getClass());
    }

    /**
     * Checks if session is created successfully with Person List delimiter
     * and added into list of sessions.
     */
    @Test
    public void run_validCommandWithPersonListDelimiter_sessionListSizeBecomesThree() {
        String userInput = "session /create /n Class gathering /d 15-02-2022 /pl Alice Bob";
        Command command = Parser.getCommand(userInput);

        // Check if a SessionCreateCommand instance was returned.
        assertEquals(SessionCreateCommand.class, command.getClass());
        command.run(manager);

        // Check if session was successfully added into the list of sessions.
        assertEquals(3, manager.getProfile().getSessionList().size());
    }

    /**
     * Checks if session is created successfully with Person List delimiter
     * and session unique identifier tracker in Profile object is incremented.
     */
    @Test
    public void run_validCommandWithPersonListDelimiter_sessionIdIncremented() {
        String userInput = "session /create /n Class gathering /d 15-02-2022 /pl Alice Bob";
        Command command = Parser.getCommand(userInput);
        int currentSessionId = manager.getProfile().getSessionIdTracker();
        command.run(manager);
        int testSessionId = manager.getProfile().getSessionIdTracker();
        assertEquals(currentSessionId + 1, testSessionId);
    }

    /**
     * Checks if session is created successfully with Group unique identifier delimiter
     * and added into list of sessions.
     */
    @Test
    public void run_validCommandWithGidDelimiter_sessionListSizeBecomesThree() {
        String userInput = "session /create /n Class gathering /d 15-02-2022 /gid 1";
        Command command = Parser.getCommand(userInput);

        // Check if a SessionCreateCommand instance was returned.
        assertEquals(SessionCreateCommand.class, command.getClass());
        command.run(manager);

        // Check if session was successfully added into the list of sessions.
        assertEquals(3, manager.getProfile().getSessionList().size());
    }

    /**
     * Checks if session is created successfully with Group unique identifier delimiter
     * and session unique identifier tracker in Profile object is incremented.
     */
    @Test
    public void run_validCommandWithGidDelimiter_sessionIdIncremented() {
        String userInput = "session /create /n Class gathering /d 15-02-2022 /gid 1";
        Command command = Parser.getCommand(userInput);
        int currentSessionId = manager.getProfile().getSessionIdTracker();
        command.run(manager);
        int testSessionId = manager.getProfile().getSessionIdTracker();
        assertEquals(currentSessionId + 1, testSessionId);
    }


    /**
     * Checks if a session is created with Person List and Group unique identifier delimiter
     * and list of persons stored in created session has size of 3.
     */
    @Test
    public void run_validCommandWithPersonListAndGidDelimiter_personListIsThree() throws InvalidDataException {
        String userInput = "session /create /n Class gathering /d 15-02-2022 /pl Charlie /gid 1";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        int sizeOfPersonList = manager.getProfile().getSession(3).getPersonList().size();
        assertEquals(3, sizeOfPersonList);
    }

    /**
     * Checks if a session is created with Person List and Group unique identifier delimiter
     * and list of persons stored in created session has size of 3 as name duplicates are removed.
     */
    @Test
    public void run_validCommandWithPersonListAndGidDelimiterHavingDuplicateNames_personListIsThree()
            throws InvalidDataException {
        String userInput = "session /create /n Class gathering /d 15-02-2022 /pl alice Charlie /gid 1";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        int sizeOfPersonList = manager.getProfile().getSession(3).getPersonList().size();
        assertEquals(3, sizeOfPersonList);
    }

    /**
     * Checks if a session is created with duplicated person names.
     */
    @Test
    public void run_hasOneNameDuplicate_sessionListSizeRemainsTwo() {
        String userInput = "session /create /n Class gathering /d 23-02-2022 /pl Alice Alice Bob";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        assertEquals(2, manager.getProfile().getSessionList().size());
    }

    /**
     * Checks if session unique identifier is incremented if a session fails
     * to be created due to duplicate names in person list.
     */
    @Test
    public void run_hasOneNameDuplicate_sessionIdNotIncremented() {
        int currentSessionId = manager.getProfile().getSessionIdTracker();
        String userInput = "session /create /n Class gathering /d 23-02-2022 /pl Alice Alice Bob";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        int testSessionId = manager.getProfile().getSessionIdTracker();
        assertEquals(currentSessionId, testSessionId);
    }

    /**
     * Checks if a session is created when a session with the same name exists.
     */
    @Test
    public void run_hasSessionDuplicate_sessionListSizeRemainsTwo() {
        String userInput = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        assertEquals(2, manager.getProfile().getSessionList().size());
    }

    /**
     * Checks if session unique identifier is incremented when a session fails to be created
     * because another session with the same name already exists.
     */
    @Test
    public void run_hasSessionDuplicate_sessionIdNotIncremented() {
        int currentSessionId = manager.getProfile().getSessionIdTracker();
        String userInput = "session /create /n Class outing /d 15-02-2022 /pl Alice Bob";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        int testSessionId = manager.getProfile().getSessionIdTracker();
        assertEquals(currentSessionId, testSessionId);
    }

    /**
     * Checks if a session is created when a group does not exist.
     */
    @Test
    public void run_groupDoesNotExists_sessionListSizeRemainsTwo() {
        String userInput = "session /create /n Class outing /d 15-02-2022 /gid 2";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        assertEquals(2, manager.getProfile().getSessionList().size());
    }
}
