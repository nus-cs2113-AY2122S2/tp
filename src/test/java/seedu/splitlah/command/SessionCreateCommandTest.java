package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Manager;
import seedu.splitlah.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SessionCreateCommandTest {

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
     * Checks if session is created with missing delimiters.
     */
    @Test
    public void prepare_hasMissingDelimiter_InvalidCommand() {
        // Case 1: Missing /n delimiter.
        String argsMissingNameDelimiter = "session /create /d 15-02-2022 /pl Alice Bob";
        Command sessionWithMissingNameDelimiter = Parser.getCommand(argsMissingNameDelimiter);
        assertEquals(InvalidCommand.class, sessionWithMissingNameDelimiter.getClass());

        // Case 2: Missing /d delimiter.
        String argsMissingDateDelimiter = "session /create /n Class outing /pl Alice Bob";
        Command sessionWithMissingDateDelimiter = Parser.getCommand(argsMissingDateDelimiter);
        assertEquals(InvalidCommand.class, sessionWithMissingDateDelimiter.getClass());

        // Case 3: Missing /pl delimiter.
        String argsMissingPersonListDelimiter = "session /create /n Class outing /d 15-02-2022";
        Command sessionWithMissingPersonListDelimiter = Parser.getCommand(argsMissingPersonListDelimiter);
        assertEquals(InvalidCommand.class, sessionWithMissingPersonListDelimiter.getClass());
    }

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
    }

    /**
     * Checks if session is created successfully and added into list of sessions.
     */
    @Test
    public void run_validCommand_sessionListSizeBecomesThree() {
        String userInput = "session /create /n Class gathering /d 15-02-2022 /pl Alice Bob";
        Command command = Parser.getCommand(userInput);

        // Check if a SessionCreateCommand instance was returned.
        assertEquals(SessionCreateCommand.class, command.getClass());
        command.run(manager);

        // Check if session was successfully added into the list of sessions.
        assertEquals(3, manager.getProfile().getSessionList().size());
    }

    /**
     * Checks if a session is created with duplicated person names.
     */
    @Test
    public void run_hasOneNameDuplicate_sessionListSizeRemainsTwo() {
        String userInput = "session /create /n University outing /d 23-02-2022 /pl Alice Alice Bob";
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
        String userInput = "session /create /n University outing /d 23-02-2022 /pl Alice Alice Bob";
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
        String userInput = "session /create /n Class outing /d 15-02-2022 /pl Mallory Eves";
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
        String userInput = "session /create /n Class outing /d 15-02-2022 /pl Mallory Eves";
        Command command = Parser.getCommand(userInput);
        command.run(manager);
        int testSessionId = manager.getProfile().getSessionIdTracker();
        assertEquals(currentSessionId, testSessionId);
    }
}
