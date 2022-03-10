package seedu.splitlah.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.splitlah.data.Manager;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SessionCreateCommandTest {

    Manager manager = new Manager();

    /**
     * Creates 2 sessions that is stored and managed by the Manager object.
     */
    @BeforeEach
    void setUp() {
        String sessionOneArgs = "session /create /n Class outing /d 2022-02-15 /pl Alice Bob";
        String sessionTwoArgs = "session /create /n Family gathering /d 2022-02-20  /pl Eves Mallory";
        Command createSessionOne = SessionCreateCommand.prepare(sessionOneArgs);
        createSessionOne.run(manager);
        Command createSessionTwo = SessionCreateCommand.prepare(sessionTwoArgs);
        createSessionTwo.run(manager);
    }

    /**
     * Checks if a session is created with duplicated person names.
     */
    @Test
    public void run_hasOneNameDuplicate_sessionListSizeRemainsTwo() {
        String userInput = "session /create /n Class outing /d 2022-02-23 /pl Alice Alice Bob";
        Command command = SessionCreateCommand.prepare(userInput);
        command.run(manager);
        assertEquals(2, manager.getProfile().getSessionList().size());
    }
}
